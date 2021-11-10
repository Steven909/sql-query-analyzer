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
            String dbURL = "jdbc:sqlite:C:/Users/Steve/OneDrive/Documents/SQLite-DB-Files/dvd.db";

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
                System.out.println(resultset.getString("Rating"));
                System.out.println(resultset.getString("Year"));
                System.out.println(resultset.getString("Director"));
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

    public static void insert() throws SQLException {
        Connection connection = null;
        ResultSet resultset = null;
        String sql = "INSERT INTO DVDs(Title, Year, Director, Rating, ID) VALUES (?, ?, ?, ?, ?)";

        connection = getDBConnection();
        int rowsInserted;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "Inception");
            preparedStatement.setInt(2, 2010);
            preparedStatement.setString(3, "Christopher Nolan");
            preparedStatement.setDouble(4, 8.8);
            preparedStatement.setInt(5, 11);


            rowsInserted = preparedStatement.executeUpdate();
        }
        if (rowsInserted > 0) {
            System.out.println("A new DVD was inserted successfully!");
        }
    }

    public static void update() throws SQLException {
        Connection connection = null;
        ResultSet resultset = null;
      //  String sql = "UPDATE DVDs SET (Title, Year, Director, Rating, ID) VALUES (?, ?, ?, ?, ?)";
        String sql = "UPDATE DVDs SET Title=?, Year=?, Director=?, Rating=?, ID=? WHERE ID=1";


        connection = getDBConnection();
        int rowsInserted;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "Jackie Brown");
            /*preparedStatement.setInt(2, 2010);
            preparedStatement.setString(3, "Christopher Nolan");
            preparedStatement.setDouble(4, 8.8);
            preparedStatement.setInt(5, 11);*/

            rowsInserted = preparedStatement.executeUpdate();
        }
        if (rowsInserted > 0) {
            System.out.println("A  DVD was updated successfully!");
        }

    }
}



