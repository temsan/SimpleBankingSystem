package banking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Accounts {

    public static Connection connection;
    static Statement statement;

    static void initialize() {
        if (connection != null) {
            try (Statement stat = connection.createStatement()) {
                statement = stat;
//                stat.executeUpdate("create database Accounts");
                stat.executeUpdate("create table card(id INTEGER PRIMARY KEY,\n" +
                        "number TEXT,\n" +
                        "pin TEXT,\n" +
                        "balance INTEGER DEFAULT 0)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Card newCard() {
        Card card = new Card();
        try {
            statement.executeUpdate(String.format("insert into card (number, pin) values (%s, %s)",
                    card.number, card.getPin()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }

    public static Card getCard(String number, String pin) {
        try {
            ResultSet result = statement.executeQuery(String.format("select * from card where number = %s and pin = %s",
                    number, pin));
            if (result.next()) {
                return new Card(number, pin, result.getInt("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

