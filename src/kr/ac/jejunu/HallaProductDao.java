package kr.ac.jejunu;

import java.sql.*;

/**
 * Created by ghost9087 on 21/04/2017.
 */
public class HallaProductDao extends ProductDao {
    @Override
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://117.17.102.106/jeju", "root", "1234");
    }
}
