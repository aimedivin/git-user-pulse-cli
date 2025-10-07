package com.aimedivin.gituserpulsecli;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java GitPulse [argument]");
            return;
        }
        UserActivity userActivity = new UserActivity(args[0]);
        userActivity.fetchActivity();
        userActivity.displayEvents();
    }
}