package entrega1;

import entrega1.dao.DAOProducto;
import entrega1.dto.ProductoMasVendidoDTO;
import entrega1.entities.Cliente;
import entrega1.entities.Factura;
import entrega1.entities.Producto;
import entrega1.factory.AbstractFactory;
import entrega1.factory.ConnectionManagerMySQL;
import entrega1.repository.mysql.*;
import entrega1.utils.HelperMySQL;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        HelperMySQL helper = new HelperMySQL();
        helper.dropTables();
        helper.createTables();
        helper.rellenarTablas();


        FacturaDAO facturaDAO = MySQLFactory.getInstance().getFacturaDAO();
        ProductoDAO productoDAO = MySQLFactory.getInstance().getProductoDAO();


        FacturaProductoDAO facturaProductoDAO = MySQLFactory.getInstance().getFacturaProductoDAO();


        System.out.println("Ejercicio 3:");
        AbstractFactory chosenFactory = AbstractFactory.getInstance(1);
        ProductoDAO p1 =chosenFactory.getProductoDAO();
        ProductoMasVendidoDTO p = p1.productoMasVendido();
        System.out.println(p);

        System.out.println();

        System.out.println("Ejercicio 4:");
        ClienteDAO c1 =chosenFactory.getClienteDAO();
        c1.listarClientesPorFacturacion();

    }
}
