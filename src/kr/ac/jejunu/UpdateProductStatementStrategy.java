package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ghost9087 on 2017-04-21.
 */
public class UpdateProductStatementStrategy implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Object object, Connection connection) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement("update product set title=?, price=? where id =?");
            Product product = (Product) object;
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());
            return preparedStatement;
    }
}
