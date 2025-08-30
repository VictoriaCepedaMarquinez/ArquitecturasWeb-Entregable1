package entrega1.factory;

import entrega1.repository.mysql.ClienteDAO;
import entrega1.repository.mysql.FacturaDAO;
import entrega1.repository.mysql.FacturaProductoDAO;
import entrega1.repository.mysql.ProductoDAO;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;

    public static AbstractFactory getInstance(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : {
//                return MySQLDAOFactory.getInstance();
            }
            case DERBY_JDBC: return null;
            default: return null;
        }
    }


    public abstract ProductoDAO getProductoDAO();
    public abstract ClienteDAO getClienteDAO();
    public abstract FacturaDAO getFacturaDAO();
    public abstract FacturaProductoDAO getFacturaProductoDAO();

}
