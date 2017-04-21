package kr.ac.jejunu;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao {
    private JdbcTemplate jdbcTemplate;

    public Product get(Long id) throws SQLException, ClassNotFoundException {
        String sql = "select * from product where id = ?";
        Object[] params = new Object[]{id};
        Product product = null;
        try {
            product = jdbcTemplate.queryForObject(sql, params, new RowMapper<Product>() {
                @Override
                public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                    Product productFromQuery = new Product();
                    productFromQuery.setId(resultSet.getLong("id"));
                    productFromQuery.setTitle(resultSet.getString("title"));
                    productFromQuery.setPrice(resultSet.getInt("price"));

                    return productFromQuery;
                }
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void update(Product product) throws SQLException, ClassNotFoundException {
        String sql = "update product set title=?, price=? where id =?";
        Object[] params = new Object[]{product.getTitle(), product.getPrice(), product.getId()};

        jdbcTemplate.update(sql, params);
    }

    public void add(Product product) throws SQLException, ClassNotFoundException {
        String sql = "insert into product(id, title, price) VALUES (?, ?, ?)";
        Object[] params = new Object[]{product.getId(), product.getTitle(), product.getPrice()};

        jdbcTemplate.update(sql, params);
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        String sql = "delete from product where id=?";
        Object[] params = new Object[]{id};

        jdbcTemplate.update(sql, params);
    }


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
