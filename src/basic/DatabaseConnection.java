package basic;

import java.sql.*;

public class DatabaseConnection {

    /*

    This class is used to connect to the database and execute queries. For each query, a new instance of this class is
    created. For current implementation, the database is MySQL the driver is MySQL Connector/J 8.0.31 (2022-10-11).

    For the current scope of this application, the database url, hostname, and password are hardcoded. In the future,
    these values will be stored in a properties file and read from there.

 */

    private Connection newConnection = null;
    private PreparedStatement newStatement = null;
    private ResultSet newResultSet = null;

    String databaseURL, hostname, password;

    public DatabaseConnection() {

        try{
            databaseURL = "jdbc:mysql://127.0.0.1:3306/?user=root";
            hostname = "root";
            password = "foobar88";
        } catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public Connection getConnection() throws SQLException {

        System.out.println("Connecting to database . . .");
        newConnection = DriverManager.getConnection(databaseURL, hostname, password);

        System.out.println("Connection successful\n");
        return newConnection;

    }

    // Following method will be used to check if elements exist in the database
    // by returning a ResultSet that will either be empty or nonempty

    public ResultSet getConnectionToCheckElementExists(String sql) throws SQLException {

        newConnection = DriverManager.getConnection(databaseURL, hostname, password);
        newStatement = newConnection.prepareStatement(sql);
        newResultSet = newStatement.executeQuery(sql);

        return newResultSet;

    }

    // Following method will execute the passed INSERT, UPDATE, or DELETE query and return void

    public void executeSQL(String sql) throws SQLException {

        newConnection = DriverManager.getConnection(databaseURL, hostname, password);
        System.out.println("Executing query . . .");

        newStatement = newConnection.prepareStatement(sql);
        newStatement.executeUpdate(sql);

        System.out.println("Query executed successfully\n");

    }

    // Following method will execute the passed SELECT query and return a ResultSet

    public ResultSet selectSQL(String sql) throws SQLException {

        newConnection = DriverManager.getConnection(databaseURL, hostname, password);
        System.out.println("Executing query . . .");

        newStatement = newConnection.prepareStatement(sql);
        newResultSet = newStatement.executeQuery(sql);

        System.out.println("Query executed successfully\n");

        return newResultSet;

    }

    // Following method pass a non SELECT and a SELECT query and return a ResultSet
    // The first parameter will update the database and the second parameter will confirm the update

    public ResultSet updateSQL(String sql1, String sql2) throws SQLException {

        newConnection = DriverManager.getConnection(databaseURL, hostname, password);
        System.out.println("Executing query . . .");

        newStatement = newConnection.prepareStatement(sql1);
        int rowsAffected = newStatement.executeUpdate(sql1);

        newResultSet = newStatement.executeQuery(sql2);
        return newResultSet;

    }

    // Following method will close the connection, statement, and result set

    public void closeConnection() throws SQLException {

        if (newConnection != null) {
            newConnection.close();
        }

        if (newStatement != null) {
            newStatement.close();
        }

        if (newResultSet != null) {
            newResultSet.close();
        }

    }

}
