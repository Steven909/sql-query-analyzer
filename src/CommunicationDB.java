import java.sql.*;


public class CommunicationDB {

    public static Connection getDBConnection() {
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        try {
            String dbURL = "jdbc:sqlite:dvd:sqlite";

            connection = DriverManager.getConnection(dbURL);
            return connection;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;

    }

    public static void retrieve() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        String query = "SELECT * FROM DVDs;";
        try {
            connection = getDBConnection();
            statement = connection.createStatement();
            System.out.println(query);
            resultset = statement.executeQuery(query);
            while (resultset.next()) {
                System.out.println(resultset.getString("ID"));
                System.out.println(resultset.getString("Title"));
                System.out.println(resultset.getString("IMDB Rating"));
                System.out.println(resultset.getString("Year"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
