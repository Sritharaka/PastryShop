/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ConnectionManager {

    private static final String USERNAME = "root";
    private static final String PASSWORD = " newrootpassword";
    private static final String CON_STRING = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
    Connection conn = null;

    public void connect() throws InstantiationException, IllegalAccessException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn =  DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/mydb?"
                            + "user=root&password=newrootpassword&useSSL=false");
            
            System.out.println("Connected!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void execute(String query) throws SQLException {
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);
    }
    
    public ResultSet executeResults(String query) throws SQLException {        
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        statement.closeOnCompletion();
        return rs;
    }


    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

}
