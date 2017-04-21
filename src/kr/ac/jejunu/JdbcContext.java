package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ghost9087 on 2017-04-21.
 */
public class JdbcContext {
    private DataSource dataSource;

    public Product queryForObject(String sql, Object[] params) throws SQLException, ClassNotFoundException {
        return jdbcContextWithStatementForGet(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int index = 1; index <= params.length; index++){
                preparedStatement.setObject(index, params[index-1]);
            }
            return preparedStatement;
        });
    }

    public void update(String sql, Object[] params) throws SQLException, ClassNotFoundException {
        jdbcContextWithStatementStrategyForUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int index = 1; index <= params.length; index++){
                preparedStatement.setObject(index, params[index-1]);
            }
            return preparedStatement;
        });
    }

    public Product jdbcContextWithStatementForGet(StatementStrategy statementStrategy) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        Product product = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();


            preparedStatement = statementStrategy.makeStatement(connection);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setTitle(resultSet.getString("title"));
                product.setPrice(resultSet.getInt("price"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return product;
    }

    public void jdbcContextWithStatementStrategyForUpdate(StatementStrategy statementStrategy) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {

            connection = dataSource.getConnection();

            preparedStatement = statementStrategy.makeStatement(connection);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
