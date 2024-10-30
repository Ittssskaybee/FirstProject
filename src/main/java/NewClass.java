
package FirstProject1.java;

import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */
// User Variable
public class FirstProject{
    private static String username;
    private static String password;
    private static String firstName;
    private static String lastName;
    private static boolean loggedIn = false;

    public static void main(String[] args) { // Main Method
        registerUser(); // Prompt user to register
        loginUser();  // Prompt user to login
        // Check if user is logged in
        if (loggedIn) { 
            displayWelcomeMessage(); // Show Welcome message
            while (true) { // Infinite Loop for  menu display
                displayMenu(); // Show menu option
                String choice = JOptionPane.showInputDialog("Enter your choice:");
                
                // Exit if option is null (User is canceled)
                if (choice == null) {
                    JOptionPane.showMessageDialog(null, "Thank you for using the EasyKanban. Goodbye!");
                    return;
                }
                switch (choice) {  // Handle user choice
                    case "1" -> addTask();
                    case "2" -> showReport(); // Add a new task
                    case "3" -> { // Exit a program
                        JOptionPane.showMessageDialog(null, "Thank you for using EasyKanban . Goodbye !");
                        return;
                    }
                    default -> JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                }
            }
        }
    }

    private static void registerUser() { //Method for user registration
        JOptionPane.showMessageDialog(null, " Please sign up for EasyKanban "); // Show registration menu
        username = JOptionPane.showInputDialog("Enter username:"); // Prompt for username
        password = JOptionPane.showInputDialog("Enter password:"); // Prompt for password
        firstName = JOptionPane.showInputDialog("Enter first name:"); // Prompt for first name
        lastName = JOptionPane.showInputDialog("Enter last name:");  // Prompt for last name
        
        // Check if username and password meet requirements 
        if (checkUserName(username) && checkPasswordComplexity(password)) {
            JOptionPane.showMessageDialog(null, " Registration is successful!"); // Sucess
        } else {
            // Registration failed
            JOptionPane.showMessageDialog(null, " Registration invalid. Please check the "
                    + "username and password requirements.");
            registerUser();  // Retry registration
        }
    }

    private static boolean checkUserName(String username) { // Method to vaildateusername
        // Username must be at least 5 chracters
        return username.length() >= 5; 
    }

    private static boolean checkPasswordComplexity(String password) { // Method to vaildate password complexity
        // Password must be at least 8 characters
        return password.length() >= 8; 
    }

    private static void loginUser() { // Method for user login
        JOptionPane.showMessageDialog(null, "EasyKanban sign in"); //Prompt for message
        String inputUsername = JOptionPane.showInputDialog("Enter username:"); // Prompt for username
        String inputPassword = JOptionPane.showInputDialog("Enter password:"); // Prompt for password
        
        // Vaildate login
        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            loggedIn = true;
            JOptionPane.showMessageDialog(null, "Welcome, " + firstName + " " + lastName + "!");
        } else {
            JOptionPane.showMessageDialog(null, "Username or password incorrect. Please try again.");
            loginUser(); 
        }
    }

    private static void displayWelcomeMessage() { // Method to display welcome
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban!"); // welcome message
    }

    private static void displayMenu() { // Method to add a task
        JOptionPane.showMessageDialog(null, """
                                            EasyKanban Menu 
                                            1) Add tasks
                                            2) Show report (Coming Soon)
                                            3) Quit""");
    }

    private static void addTask() { // Method to add a task
    
    String taskName = JOptionPane.showInputDialog("Enter task name:"); // Prompt for task name

   
    String taskDescription = JOptionPane.showInputDialog("Enter task description:"); // Prompt for task description

    
    String developerDetails = JOptionPane.showInputDialog("Enter developer details (First Name Last Name):"); //  Prompt developer details


    String durationInput = JOptionPane.showInputDialog("Enter task duration (in hours):"); // Prompt task duration
    int taskDuration = Integer.parseInt(durationInput);  // Deconstruct task duration to integer

   // Option for task
    String[] statusOptions = {"To Do", "Done", "Doing"};
    String taskStatus = (String) JOptionPane.showInputDialog(
            null,
            "Select task status:",
            "Task Status",
            JOptionPane.QUESTION_MESSAGE,
            null,
            statusOptions,
            statusOptions[0]
    );

    if (taskDescription.length() <= 50) {
        // Proceed with capturing task 
        String taskId = generateTaskId(taskName, developerDetails);
        JOptionPane.showMessageDialog(null, """
                                            Task successfully captured:
                                            Task Name: """ + taskName + "\n" +
                "Task Description: " + taskDescription + "\n" +
                "Developer Details: " + developerDetails + "\n" +
                "Task Duration: " + taskDuration + " hours\n" +
                "Task Status: " + taskStatus + "\n" +
                "Task ID: " + taskId);
    } else { // Task should not exceed 50 Characters
        // Show an error message to the user
        JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
    }
    }
    // Method to show a report 
    private static void showReport () { 
        // Display a message indicating that feature is not yet implemented
        JOptionPane.showMessageDialog(null, "Feature coming soon");
    }
    // Method to generate a unique task ID based on task name and developer details
    private static String generateTaskId(String taskName, String developerDetails) {
        // Extract the first two letters of the task name, converting to uppercase
    String firstTwoLetters = taskName.substring(0, Math.min(taskName.length(), 2)).toUpperCase();
    String[] names = developerDetails.split(" ");
    var LastName = names[names.length - 1];
    String lastThreeLetters = LastName.substring(Math.max(0, LastName.length() - 3)).toUpperCase();
    // Return the combined task ID format with two letters and last three letters
    return firstTwoLetters + ":0:" + lastThreeLetters;
}
}
