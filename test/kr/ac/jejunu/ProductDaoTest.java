package kr.ac.jejunu;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductDaoTest {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
        ProductDao productDao = context.getBean("productDao", ProductDao.class);

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

        ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
        ProductDao productDao = context.getBean("productDao", ProductDao.class);

        productDao.add(product);

        Product insertedProduct = productDao.get(id);
        assertThat(insertedProduct.getTitle(), is(title));
        assertThat(insertedProduct.getPrice(), is(price));
        assertThat(insertedProduct.getId(), is(id));
    }
    @Test
    public void update() throws SQLException {
        Random random = new Random();
        Long id = random.nextLong() % 2000L;

        Product product = new Product();
        product.setId(id);
        product.setTitle("파스타 소스");
        product.setPrice(3000);

        ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
        ProductDao productDao = context.getBean("productDao", ProductDao.class);

        productDao.add(product);

        String title = "피자";
        Integer price = 50000;

        product.setTitle(title);
        product.setPrice(price);

        productDao.update(product);

        Product updatedProduct = productDao.get(id);
        assertThat(updatedProduct.getTitle(), is(title));
        assertThat(updatedProduct.getPrice(), is(price));
    }

    @Test
    public void delete() throws SQLException {
        Random random = new Random();
        Long id = random.nextLong() % 2000L;

        Product product = new Product();
        product.setId(id);
        product.setTitle("파스타 소스");
        product.setPrice(3000);

        ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
        ProductDao productDao = context.getBean("productDao", ProductDao.class);

        productDao.add(product);

        String title = "피자";
        Integer price = 50000;

        productDao.delete(product.getId());

        Product deletedProduct = productDao.get(id);
        assertThat(deletedProduct, is(nullValue()));
    }
}
