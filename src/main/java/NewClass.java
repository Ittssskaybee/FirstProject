
package FirstProject1.java;


/**
 *
 * @author RC_Student_lab
 */

/**
 *
 * @author rc student
 */

import javax.swing.JOptionPane;
import java.util.ArrayList;

 //User variables

public class FirstProject1{
    private static String username; // Store username
    private static String password; // Store password
    private static String firstName; //Store First name 
    private static String lastName; // Store LastName
    private static boolean loggedIn = false; // Tracks if user log in or logged out 

    private static final ArrayList<String> developers = new ArrayList<>(); // Assigned to tasks
    private static final ArrayList<String> taskNames = new ArrayList<>(); //  Assigned task name
    private static final ArrayList<String> taskIDs = new ArrayList<>(); // Assigned taskId
    private static final ArrayList<Integer> taskDurations = new ArrayList<>(); // Assigned Duration
    private static final ArrayList<String> taskStatuses = new ArrayList<>(); //  Assigned Task Status

    
    public static void main(String[] args) {
        // Register user
        registerUser();
        loginUser();
        // If user is login in sucessfully then menu will show
        if (loggedIn) {
            // Display the menu
            displayWelcomeMessage();
            while (true) {
                displayMenu();
                String choice = JOptionPane.showInputDialog("Enter your choice:");
                if (choice == null) {
                    JOptionPane.showMessageDialog(null, "Thank you for using the EasyKanban. Goodbye!");
                    return;
                }
                switch (choice) {
                    case "1" -> addTask(); // Option 1 Add task
                    case "2" -> showReport(); // Optiom 2 Add Report
                    case "3" -> searchForTask(); // Option 3 Search Task
                    case "4" -> searchTasksByDeveloper(); // Search Task by the developer
                    case "5" -> deleteTask(); // Delete Task
                    case "6" -> displayAllTasks(); // Display Tasks 
                    case "7" -> { //Return 
                        JOptionPane.showMessageDialog(null, "Thank you for using EasyKanban. Goodbye!");
                        return;
                    }
                    default -> JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                }
            }
        }
    }
    
    // Method for user registration
    private static void registerUser() {
        JOptionPane.showMessageDialog(null, " Please sign up for EasyKanban "); // Prompt the 
        username = JOptionPane.showInputDialog("Enter username:"); // Prompt user for a username
        password = JOptionPane.showInputDialog("Enter password:"); // Prompt user for a  password
        firstName = JOptionPane.showInputDialog("Enter first name:"); // Prompt First name
        lastName = JOptionPane.showInputDialog("Enter last name:"); // Prompt Last Name

        // Validate the username and password
        if (checkUserName(username) && checkPasswordComplexity(password)) { 
            JOptionPane.showMessageDialog(null, " Registration is successful!"); // Registration is successful
        } else {
            JOptionPane.showMessageDialog(null, " Registration invalid. Please check the " // Else resigteration is invaild      
                    + "username and password requirements.");
            // Retry registration
            registerUser(); 
        }
    }

    // Method to check if the username is at least 5 characters long
    private static boolean checkUserName(String username) {
        return username.length() >= 5;
    }

    // Method to check if the password is at least 8 characters long
    private static boolean checkPasswordComplexity(String password) {
        return password.length() >= 8;
    }
    
    // Method for user login
    private static void loginUser() {
        JOptionPane.showMessageDialog(null, "EasyKanban sign in");
        String inputUsername = JOptionPane.showInputDialog("Enter username:"); // Prompt user for username
        String inputPassword = JOptionPane.showInputDialog("Enter password:"); // Prompt user for Password
        
        // Check if the input username and password match the stored ones
        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            loggedIn = true; // Set loggedIn flag to true
            JOptionPane.showMessageDialog(null, "Welcome, " + firstName + " " + lastName + "!");
        } else {
            // If credentials are incorrect, show an error message and retry login
            JOptionPane.showMessageDialog(null, "Username or password incorrect. Please try again.");
            // Retry Login
            loginUser();
        }
    }
    
    // Method to display a welcome message after successful login
    private static void displayWelcomeMessage() {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban!");
    }
    // Method to display the menu with options for the user to choose from
    private static void displayMenu() {
        JOptionPane.showMessageDialog(null, "EasyKanban Menu \n" +
                "1) Add tasks\n" +
                "2) Show report\n" +
                "3) Search for a task by name\n" +
                "4) Search tasks by developer\n" +
                "5) Delete a task by name\n" +
                "6) Display all tasks\n" +
                "7) Quit");
    }
// Method to add a new task
    private static void addTask() {
        // Prompt user to enter task details
        String taskName = JOptionPane.showInputDialog("Enter task name:");
        String taskDescription = JOptionPane.showInputDialog("Enter task description:");
        String developerDetails = JOptionPane.showInputDialog("Enter developer details (First Name Last Name):");
        String durationInput = JOptionPane.showInputDialog("Enter task duration (in hours):");
        int taskDuration = Integer.parseInt(durationInput);
        String[] statusOptions = {"To Do", "Done", "Doing"};
         // Options for task status (To Do, Doing, Done)
        String taskStatus = (String) JOptionPane.showInputDialog(
                null,
                "Select task status:",
                "Task Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                statusOptions,
                statusOptions[0]
        );
// Validate task description length (should be 50 characters or less)
        
        if (taskDescription.length() <= 50) {
            // Generate a unique task ID based on task name and developer detail
            String taskId = generateTaskId(taskName, developerDetails);
            developers.add(developerDetails);
            taskNames.add(taskName);
            taskIDs.add(taskId);
            taskDurations.add(taskDuration);
            taskStatuses.add(taskStatus);
            // Add task details to the e lists
            JOptionPane.showMessageDialog(null, "Task successfully captured:\n" +
                    "Task Name: " + taskName + "\n" +
                    "Task Description: " + taskDescription + "\n" +
                    "Developer Details: " + developerDetails + "\n" +
                    "Task Duration: " + taskDuration + " hours\n" +
                    "Task Status: " + taskStatus + "\n" +
                    "Task ID: " + taskId);
        } else {
            // If description is too long, prompt the user to shorten it
            JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
        }
    }
// Method to show a report of all tasks in the system
    private static void showReport() {
        StringBuilder report = new StringBuilder("Task Report:\n");
        // Loop through the list of tasks and their details to the report
        for (int i = 0; i < taskNames.size(); i++) {
            report.append("Task Name: ").append(taskNames.get(i)).append("\n")
                    .append("Developer: ").append(developers.get(i)).append("\n")
                    .append("Task Duration: ").append(taskDurations.get(i)).append(" hours\n")
                    .append("Task Status: ").append(taskStatuses.get(i)).append("\n\n");
        }
        // Display the task report to the user
        JOptionPane.showMessageDialog(null, report.toString());
    }
// Method to search for a task by its name
    private static void searchForTask() {
        String taskName = JOptionPane.showInputDialog("Enter the task name to search:");
        int index = taskNames.indexOf(taskName);
        if (index != -1) {
            // If task found, display its details
            JOptionPane.showMessageDialog(null, "Task found:\n" +
                    "Developer: " + developers.get(index) + "\n" +
                    "Task Duration: " + taskDurations.get(index) + " hours\n" +
                    "Task Status: " + taskStatuses.get(index));
        } else {
            // If task not found, inform the user
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }
// Method to search tasks by developer's name
    private static void searchTasksByDeveloper() {
        String developerName = JOptionPane.showInputDialog("Enter the developer's name to search tasks:");
        StringBuilder tasks = new StringBuilder("Tasks for developer " + developerName + ":\n");
        boolean found = false;
        for (int i = 0; i < developers.size(); i++) {
            if (developers.get(i).contains(developerName)) {
                tasks.append("Task Name: ").append(taskNames.get(i)).append("\n")
                        .append("Task Duration: ").append(taskDurations.get(i)).append(" hours\n")
                        .append("Task Status: ").append(taskStatuses.get(i)).append("\n\n");
                found = true;
            }
        }
        if (found) {
            JOptionPane.showMessageDialog(null, tasks.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found for developer " + developerName);
        }
    }
// Method to delete all tasks
    private static void deleteTask() {
        String taskName = JOptionPane.showInputDialog("Enter the task name to delete:");
        int index = taskNames.indexOf(taskName);
        if (index != -1) {
            developers.remove(index);
            taskNames.remove(index);
            taskIDs.remove(index);
            taskDurations.remove(index);
            taskStatuses.remove(index);
            JOptionPane.showMessageDialog(null, "Task \"" + taskName + "\" successfully deleted.");
        } else {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }
// Method to display all tasks
    private static void displayAllTasks() {
        StringBuilder report = new StringBuilder("All Tasks:\n");
        for (int i = 0; i < taskNames.size(); i++) {
            report.append("Task Name: ").append(taskNames.get(i)).append("\n")
                    .append("Developer: ").append(developers.get(i)).append("\n")
                    .append("Task Duration: ").append(taskDurations.get(i)).append(" hours\n")
                    .append("Task Status: ").append(taskStatuses.get(i)).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }
// Method  to display 
    private static String generateTaskId(String taskName, String developerDetails) {
        String firstTwoLetters = taskName.substring(0, Math.min(taskName.length(), 2)).toUpperCase();
        String[] names = developerDetails.split(" ");
        String lastName = names[names.length - 1];
        String lastThreeLetters = lastName.substring(Math.max(0, lastName.length() - 3)).toUpperCase();
        return firstTwoLetters + ":0:" + lastThreeLetters;
    }
}
