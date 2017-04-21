package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    private JdbcContext jdbcContext;

    public Product get(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new GetProductStatementStrategy(id);
        return jdbcContext.jdbcContextWithStatementForGet(statementStrategy);
    }

    public void add(Product product) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new AddProductStatementStrategy(product);

        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }

    public void update(Product product) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new UpdateProductStatementStrategy(product);

        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new DeleteProductStatementStrategy(id);

        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }


    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}
