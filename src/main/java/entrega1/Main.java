package entrega1;

import entrega1.entities.Cliente;
import entrega1.entities.Producto;
import entrega1.factory.AbstractFactory;
import entrega1.repository.mysql.ClienteDAO;
import entrega1.repository.mysql.ProductoDAO;
import entrega1.utils.HelperMySQL;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        HelperMySQL helper = new HelperMySQL();
        helper.dropTables();
        helper.createTables();
        helper.rellenarTablas();

        AbstractFactory chosenFactory = AbstractFactory.getInstance(1);
        ClienteDAO cliente = chosenFactory.getClienteDAO();
        System.out.println(cliente.get(1));


    }
}
