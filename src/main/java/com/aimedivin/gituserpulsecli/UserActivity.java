package com.aimedivin.gituserpulsecli;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class UserActivity {
    private JsonArray events;
    private final String username;

    public UserActivity(String username) {
        this.username = username;
    }

    public void fetchActivity() {
        try {
            URL GITHUB_API_URL = new URI("https://api.github.com/users/" + username + "/events").toURL();

            HttpURLConnection conn = (HttpURLConnection) GITHUB_API_URL.openConnection();

            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() != 200) {
                System.out.println("Error: Unable to fetch data from GitHub API");
                return;
            } else {

                StringBuilder data = new StringBuilder();

                // Read and store the data from the API
                Scanner sc = new Scanner(GITHUB_API_URL.openStream());

                while (sc.hasNext()) {
                    data.append(sc.nextLine());
                }

                // Close the scanner
                sc.close();

                // Parse the data to JSON format to display it
                events = JsonParser.parseString(data.toString()).getAsJsonArray();



                conn.disconnect();
            }
        } catch (Exception e) {
            System.out.println("Error fetching activity, \n Please try again. ");
        }
    }

    public void displayEvents() {
        try {
            if(events.isEmpty()) {
                System.out.println("No recent activity found for user: " + username);
            }

            // Process events
            for (JsonElement ev : events) {

                JsonObject event = ev.getAsJsonObject();

                String type = event.get("type").getAsString();
                String repoName = event.get("repo").getAsJsonObject().get("name").getAsString();

                switch(type) {
                    case "PushEvent":
                        int commitsCount = event.get("payload").getAsJsonObject().get("commits").getAsJsonArray().size();
                        System.out.printf("- Pushed %d commit%s to %s\n", commitsCount, commitsCount > 1 ? "s" : "", repoName);
                        break;
                    case "IssuesEvent":
                        String action =  event.get("payload").getAsJsonObject().get("action").getAsString();
                        System.out.println("- " +  action.substring(0,1).toUpperCase() + action.substring(1) + (action.equals("opened")?" new": " an") + " issue in " + repoName );
                        break;
                    case "WatchEvent":
                        System.out.println("- Starred " + repoName);
                        break;
                    default:
                        System.out.print("");
                }
            }


        } catch (Exception e) {
            System.out.println("Error displaying activity, \n Please try again. ");
        }
    }
}

