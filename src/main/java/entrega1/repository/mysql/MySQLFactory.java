package entrega1.repository.mysql;

import entrega1.factory.AbstractFactory;
import entrega1.factory.ConnectionManagerMySQL;

public class MySQLFactory extends AbstractFactory {
    private static MySQLFactory instance;


    public static synchronized MySQLFactory getInstance() {
        if (instance == null) {
            instance = new MySQLFactory();
        }
        return instance;
    }


    //TODO Implementar
    @Override
    public ProductoDAO getProductoDAO() {
        return null;
    }

    @Override
    public ClienteDAO getClienteDAO() {
        return new ClienteDAO(ConnectionManagerMySQL.getInstance().getConnection());
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        return null;
    }

    @Override
    public FacturaProductoDAO getFacturaProductoDAO() {
        return null;
    }
}
