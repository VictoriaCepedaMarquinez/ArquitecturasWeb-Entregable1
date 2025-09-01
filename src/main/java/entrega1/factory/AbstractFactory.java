package entrega1.factory;

import entrega1.repository.mysql.*;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;

    public static AbstractFactory getInstance(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : {
              return MySQLFactory.getInstance();
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
