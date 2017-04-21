package kr.ac.jejunu;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDao {
    private JdbcContext jdbcContext;

    public Product get(Long id) throws SQLException, ClassNotFoundException {
        return jdbcContext.jdbcContextWithStatementForGet(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        });
    }

    public void add(Product product) throws SQLException, ClassNotFoundException {
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into product(id, title, price) VALUES (?, ?, ?)");
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getTitle());
            preparedStatement.setInt(3, product.getPrice());

            return preparedStatement;
        });
    }

    public void update(Product product) throws SQLException, ClassNotFoundException {
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("update product set title=?, price=? where id =?");
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());
            return preparedStatement;
        });
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from product where id=?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        });
    }


    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}
