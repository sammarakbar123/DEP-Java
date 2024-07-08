
package JavaInternship;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
class BooksStore {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3305/sys";
    static final String USER = "root";
    static final String PASSWORD = "BSF21#F@ll23";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Register JDBC drive
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // Create a table
            System.out.println("Creating table...");
            stmt = conn.createStatement();
            String createTableSQL = "CREATE TABLE bookstore " +
                    "(bookId INT NOT NULL, " +
                    " bookName VARCHAR(255), " +
                    " authorName VARCHAR(255), " +
                    " price DOUBLE)";
            stmt.executeUpdate(createTableSQL);

            // Insert values into the table
            System.out.println("Inserting values...");
            String insertValuesSQL = "INSERT INTO bookstore VALUES (1, 'Book1', 'Author1', 19.99)";
            stmt.executeUpdate(insertValuesSQL);

            // Display the table
            displayTable(stmt);

            // Delete the column 'authorName'
            System.out.println("Deleting column 'authorName'...");
            String deleteColumnSQL = "ALTER TABLE bookstore DROP COLUMN authorName";
            stmt.executeUpdate(deleteColumnSQL);

            // Insert a column 'writer' with values
            System.out.println("Inserting column 'writer'...");
            String insertColumnSQL = "ALTER TABLE bookstore ADD COLUMN writer VARCHAR(255)";
            stmt.executeUpdate(insertColumnSQL);

            // Update values in the 'writer' column
            String updateValuesSQL = "UPDATE bookstore SET writer = 'Writer1' WHERE bookId = 1";
            stmt.executeUpdate(updateValuesSQL);

            // Display the updated table
            displayTable(stmt);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void displayTable(Statement stmt) throws SQLException {
        // Display the table
        System.out.println("Displaying table...");
        String selectSQL = "SELECT * FROM bookstore";
        ResultSet resultSet = stmt.executeQuery(selectSQL);

        while (resultSet.next()) {
            int bookId = resultSet.getInt("bookId");
            String bookName = resultSet.getString("bookName");
            String writer = resultSet.getString("writer");
            double price = resultSet.getDouble("price");

            System.out.println("BookID: " + bookId + ", BookName: " + bookName + ", Writer: " + writer + ", Price: " + price);
        }

        resultSet.close();
    }
}
