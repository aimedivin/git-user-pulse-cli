# Git User Pulse - GitHub User Activity

GitUserPulse CLI is a simple command-line tool that fetches and displays a GitHub user's recent public activity using the GitHub API.

🔗 Project URL: https://roadmap.sh/projects/github-user-activity

## 🚀 Features

- Fetch recent public activity of any GitHub user.
- Displays common actions like:
- Push commits
- Star repositories
- Open or close issues
- Create pull requests
- Handles invalid usernames and API errors gracefully.
- Runs directly from the terminal.

## ⚙️ Requirement

- Java 11+ or newer installed on your system
- Maven 3+

## 🛠️ How to run

Follow these steps to run the project locally:

```bash
# Clone the repository
git clone https://github.com/aimedivin/git-user-pulse-cli.git

# Navigate to the project directory
cd git-user-pulse-cli

# Build the project
nvm clean package

# Run the Program
java -jar target/gituserpulsecli-1.0-SNAPSHOT-jar-with-dependencies.jar <username>

```
