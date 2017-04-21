package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by ghost9087 on 21/04/2017.
 */
public interface ConnectionMaker {

    Connection getConnection() throws ClassNotFoundException, SQLException;
}
