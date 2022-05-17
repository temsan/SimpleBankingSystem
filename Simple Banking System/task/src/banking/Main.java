package banking;

import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        String dbname;
        if (args.length >= 2 && args[0].contains("-fileName")) {
            dbname = args[1];
        } else {
            dbname = "db.s3db";
        }

        File file = new File(dbname);
        if (!file.exists()) {
            try {
                Files.createFile(Path.of(dbname));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String url = String.format("jdbc:sqlite:%s", dbname);
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection connection = dataSource.getConnection()) {
            Accounts.connection = connection;
            Accounts.initialize();

            Console.start();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}