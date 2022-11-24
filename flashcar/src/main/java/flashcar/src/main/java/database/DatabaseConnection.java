package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:h2:C:\\Users\\tailon.prado\\Documents\\NetBeansProjects\\flashcar\\H2database\\flashcar", "", "");
    }
}