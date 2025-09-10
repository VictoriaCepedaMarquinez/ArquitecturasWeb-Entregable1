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
    public ProductoDAO getProductoDAO() {return new ProductoDAO(ConnectionManagerMySQL.getInstance().getConnection());}

    @Override
    public ClienteDAO getClienteDAO() {
        return new ClienteDAO(ConnectionManagerMySQL.getInstance().getConnection());
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        return new FacturaDAO(ConnectionManagerMySQL.getInstance().getConnection(), MySQLFactory.getInstance().getClienteDAO());
    }

    @Override
    public FacturaProductoDAO getFacturaProductoDAO() { //TODO: Preguntar si asi esta bien la iyeccion de dependencias
        return new FacturaProductoDAO(ConnectionManagerMySQL.getInstance().getConnection(),
                MySQLFactory.getInstance().getFacturaDAO(),
                MySQLFactory.getInstance().getProductoDAO());
    }
}
