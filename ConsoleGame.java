package Threading;

import java.util.Scanner;
import java.util.Random;

class CountdownTimer extends Thread {
    private boolean timeUp = false;

    @Override
    public void run() {
        for (int i = 10; i > 0; i--) {
            System.out.println("Time left: " + i + " seconds");
            try {
                Thread.sleep(1000); // Pause for 1 second
            } catch (InterruptedException e) {
                System.out.println("Timer interrupted");
            }
        }
        timeUp = true;
        System.out.println("Time is up! Game over.");
    }

    public boolean isTimeUp() {
        return timeUp;
    }
}

public class ConsoleGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        CountdownTimer timer = new CountdownTimer();

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You have 10 seconds to guess the correct number between 1 and 10.");

        int targetNumber = random.nextInt(10) + 1;
        boolean guessedCorrectly = false;

        timer.start(); // Start the countdown timer

        while (!timer.isTimeUp() && !guessedCorrectly) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();

            if (guess == targetNumber) {
                System.out.println("Congratulations! You guessed the correct number: " + targetNumber);
                guessedCorrectly = true;
            } else if (guess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }

        if (!guessedCorrectly) {
            System.out.println("The correct number was: " + targetNumber);
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
