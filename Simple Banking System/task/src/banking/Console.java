package banking;

import java.util.Scanner;

public class Console {

    private static Card sessionId;

    public static void start() {
        System.out.print("1. Create an account\n" +
                "2. Log into account\n" +
                "0. Exit\n");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {

            case "1":
                Card card = Accounts.newCard();
                System.out.printf("\nYour card has been created\n" +
                        "Your card number:\n" +
                        "%s\n" +
                        "Your card PIN:\n" +
                        "%s\n\n", card.number, card.getPin());
                start();
                break;

            case "2":
                System.out.print("\nEnter your card number:\n" +
                        ">");
                String cardNum = scanner.nextLine();
                System.out.print("\nEnter your PIN:\n" +
                        ">");
                String pin = scanner.nextLine();
                Card found = Accounts.getCard(cardNum, pin);
                if (found == null) {
                    System.out.println("\nWrong card number or PIN!\n");
                    start();
                } else {
                    sessionId = found;
                    System.out.println("\nYou have successfully logged in!\n");
                    logIn();
                }
                break;

            case "0":
                System.out.println("\nBye!");
                break;
            default:
                System.out.print("Wrong enter, try again:\n" +
                        ">");
        }
    }

    public static void logIn() {
        System.out.print("1. Balance\n" +
                "2. Log out\n" +
                "0. Exit\n" +
                ">");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {

            case "1":
                if (sessionId == null) {
                    System.out.println("You haven't logged in!");
                    start();
                } else {
                    System.out.printf("\nBalance: %01f\n", sessionId.getBalance());
                    logIn();
                }
                break;

            case "2":
                sessionId = null;
                start();
                break;

            case "0":
                System.out.println("\nBye!");
                break;
            default:
                System.out.print("\nWrong enter, try again:\n" +
                        ">");
                start();
        }
    }
}
