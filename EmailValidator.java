package Lab2;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    // Initalise email database
    static String[] emailIDs = {
            "employee1@test.com",
            "employee2@test.com",
            "employee3@test.com",
            "employee4@test.com",
            "employee5@test.com",
    };

    public static void main(String[] args) {
        System.out.println("===================");
        System.out.println("| Email Validator |");
        System.out.println("===================");

        // Get email to search with
        Scanner sc = new Scanner(System.in);
        String userEmail = "";

        boolean isRunning = true;
        do {
            // Validate user input (Email address)
            boolean isValidating = true;
            do {
                System.out.print("\nEnter email to be validated: ");
                userEmail = sc.nextLine().trim();

                boolean isEmailValid = validateEmail(userEmail);

                // Display validity results
                System.out.println(
                        "\n'" + userEmail + "' is " + ((isEmailValid) ? "" : "not ") + "a valid email address.");

                if (isEmailValid) {
                    isValidating = false;
                }

            } while (isValidating != false);

            // Search for email address in database
            boolean isEmailInDatabase = false;
            for (String emailID : emailIDs) {
                if (emailID.equals(userEmail)) {
                    isEmailInDatabase = true;
                }
            }

            // Display results
            System.out.println((isEmailInDatabase) ? "'" + userEmail + "' has been found."
                    : "Could not find '" + userEmail + " in database.");

            // Ask if user wants to validate another email address
            boolean isContinuing = false;
            do {
                System.out.print("\nSearch for another email? (y/n): ");
                char continueSearching = sc.next().toLowerCase().charAt(0);
                sc.nextLine();

                if (continueSearching == 'y' || continueSearching == 'n') {
                    isContinuing = true;
                }

                if (continueSearching == 'n') {
                    isRunning = false;
                }

            } while (isContinuing != true);

        } while (isRunning != false);

        // Close scanner
        sc.close();
    }

    /**
     * Helper function that matches text to an email regex pattern
     * 
     * @param userEmail String to validate
     * @return boolean representing if email is valid
     */
    public static boolean validateEmail(String userEmail) {
        String regex = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userEmail);

        return matcher.matches();
    }
}
