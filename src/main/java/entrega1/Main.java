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

        // 1. Crear la conexión (recurso compartido)
        Connection cn = ConnectionManagerMySQL.getInstance().getConnection();


        // 2. Crear los DAOs principales
        FacturaDAO facturaDAO = new FacturaDAO(cn);
        ProductoDAO productoDAO = new ProductoDAO(cn);

        // 3. Inyectar DAOs en el DAO compuesto
        FacturaProductoDAO facturaProductoDAO = new FacturaProductoDAO(cn, facturaDAO, productoDAO);


        System.out.println("Ejercicio 3:");
        AbstractFactory chosenFactory = AbstractFactory.getInstance(1);
        ProductoDAO p1 =chosenFactory.getProductoDAO();
        ProductoMasVendidoDTO p = p1.productoMasVendido();
        System.out.println(p);

        System.out.println();

        System.out.println("Ejercicio 4:");



    }
}
