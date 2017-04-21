package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * Created by ghost9087 on 21/04/2017.
 */
@Configuration
public class DaoFactory {
    @Bean
    public ProductDao productDao() {
        ProductDao productDao = new ProductDao();
        productDao.setDataSource(dataSource());

        return productDao;
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends java.sql.Driver>) Class.forName("com.mysql.jdbc.Driver"));
            dataSource.setUrl("jdbc:mysql://117.17.102.106/jeju");
            dataSource.setUsername("root");
            dataSource.setPassword("1234");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return  dataSource;
    }
}
