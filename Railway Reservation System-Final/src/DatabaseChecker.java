import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseChecker {

    // Replace these variables with your database credentials
    private static final String DB_URL = "jdbc:mysql://localhost/railway_reservation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Abhi";

    public static boolean isDataMatching(String enteredData) {
        boolean isMatch = false;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Replace the following query with your actual query to fetch data based on your requirements
            String query = "SELECT * FROM user WHERE uid = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, enteredData);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Data exists in the database, set isMatch to true
                        isMatch = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }

        return isMatch;
    }

    public static void main(String[] args) {
        // Example usage
        String enteredData = "U-1";
        if (isDataMatching(enteredData)) {
            System.out.println("Entered data matches data in the database.");
        } else {
            System.out.println("Entered data does not match data in the database.");
        }
    }
}