package banking;

import java.util.Random;

import static banking.Const.BIN;

public class Card {
    public String number;
    private String pin;
    private double balance;

    public Card() {
        Random random = new Random();

        this.number = BIN + String.format("%09d", random.nextInt(1_000_000_000));
        this.number += this.Luhn(this.number);
        this.pin = String.format("%04d", random.nextInt(10_000));
        this.balance = 0;
    }

    public Card(String number, String pin, int balance) {
        this.number = number;
        this.pin = pin;
        this.balance = balance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    private String Luhn(String number) {
        int sum = 0;
        for (int i = number.length(); i > 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i - 1));
            if (i % 2 != 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        return String.valueOf(sum * 9 % 10);
    }
}