import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    
    public static void main(String[] args) {
        // Create a scanner object to take input from the user
        Scanner scanner = new Scanner(System.in);
        
        // Generate a random number between 1 and 100
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        
        // Set the maximum number of attempts
        int maxAttempts = 7;
        int attempts = 0;
        boolean hasWon = false;
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You need to guess a number between 1 and 100.");
        System.out.println("You have a maximum of " + maxAttempts + " attempts.");

        // Loop until the player has either won or used all attempts
        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            // Check if the guess is correct, too high, or too low
            if (guess == randomNumber) {
                hasWon = true;
                break;  // Exit the loop if the guess is correct
            } else if (guess < randomNumber) {
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Your guess is too high.");
            }

            System.out.println("Attempts remaining: " + (maxAttempts - attempts));
        }

        // Output the result of the game
        if (hasWon) {
            System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
        } else {
            System.out.println("Sorry, you've used all your attempts. The correct number was " + randomNumber + ".");
        }

        // Close the scanner
        scanner.close();
    }
}
