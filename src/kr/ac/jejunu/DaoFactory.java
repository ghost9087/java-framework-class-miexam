package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ghost9087 on 21/04/2017.
 */
@Configuration
public class DaoFactory {
    @Bean
    public ProductDao productDao() {
        ProductDao productDao = new ProductDao();
        productDao.setConnectionMaker(connectionMaker());

        return productDao;
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        JejuConnectionMaker jejuConnectionMaker = new JejuConnectionMaker();
        jejuConnectionMaker.setDriverClass("com.mysql.jdbc.Driver");
        jejuConnectionMaker.setUrl("jdbc:mysql://117.17.102.106/jeju");
        jejuConnectionMaker.setId("root");
        jejuConnectionMaker.setPassword("1234");
        return  jejuConnectionMaker;
    }
}
