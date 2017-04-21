package kr.ac.jejunu;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDao {
    private JdbcContext jdbcContext;

    public Product get(Long id) throws SQLException, ClassNotFoundException {
        String sql = "select * from product where id = ?";
        Object[] params = new Object[]{id};
        return jdbcContext.queryForObject(sql, params);
    }

    public void update(Product product) throws SQLException, ClassNotFoundException {
        String sql = "update product set title=?, price=? where id =?";
        Object[] params = new Object[]{product.getTitle(), product.getPrice(), product.getId()};

        jdbcContext.update(sql, params);
    }

    public void add(Product product) throws SQLException, ClassNotFoundException {
        String sql = "insert into product(id, title, price) VALUES (?, ?, ?)";
        Object[] params = new Object[]{product.getId(), product.getTitle(), product.getPrice()};

        jdbcContext.update(sql, params);
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        String sql = "delete from product where id=?";
        Object[] params = new Object[]{id};

        jdbcContext.update(sql, params);
    }


    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}
