package kr.ac.jejunu;

/**
 * Created by ghost9087 on 21/04/2017.
 */
public class DaoFactory {
    public ProductDao getProductDao() {
        return new ProductDao(getConnectionMaker());
    }

    public ConnectionMaker getConnectionMaker() {
        return new JejuConnectionMaker();
    }
}
