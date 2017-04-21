package kr.ac.jejunu;

import org.junit.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductDaoTest {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        ProductDao productDao = new JejuProductDao();
        Product product = productDao.get(id);
        assertThat(id, is(product.getId()));
        assertThat(title, is(product.getTitle()));
        assertThat(price, is(product.getPrice()));
    }
    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        Random random = new Random();
        Long id = random.nextLong() % 2000L;
        String title = "파스타 소스";
        Integer price = 2000;

        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);

        ProductDao productDao = new JejuProductDao();
        productDao.add(product);

        Product insertedProduct = productDao.get(id);
        assertThat(insertedProduct.getTitle(), is(title));
        assertThat(insertedProduct.getPrice(), is(price));
        assertThat(insertedProduct.getId(), is(id));
    }
    @Test
    public void getHalla() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        ProductDao productDao = new HallaProductDao();
        Product product = productDao.get(id);
        assertThat(id, is(product.getId()));
        assertThat(title, is(product.getTitle()));
        assertThat(price, is(product.getPrice()));
    }
    @Test
    public void addHalla() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        Random random = new Random();
        Long id = random.nextLong() % 2000L;
        String title = "파스타 소스";
        Integer price = 2000;

        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);

        ProductDao productDao = new HallaProductDao();
        productDao.add(product);

        Product insertedProduct = productDao.get(id);
        assertThat(insertedProduct.getTitle(), is(title));
        assertThat(insertedProduct.getPrice(), is(price));
        assertThat(insertedProduct.getId(), is(id));
    }
}
