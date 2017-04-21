package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ghost9087 on 2017-04-21.
 */
public class GetProductStatementStrategy implements StatementStrategy {
    private Long id;

    public GetProductStatementStrategy(Long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
    }
}
