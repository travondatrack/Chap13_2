package murach.data;

import java.sql.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/murach");
            System.out.println("DataSource lookup successful");
        } catch (NamingException e) {
            System.out.println("JNDI lookup failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() {
        try {
            if (dataSource == null) {
                System.out.println("DataSource is null - JNDI lookup may have failed");
                return null;
            }
            Connection conn = dataSource.getConnection();
            System.out.println("Database connection obtained successfully");
            return conn;
        } catch (SQLException e) {
            System.out.println("SQLException getting connection: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}