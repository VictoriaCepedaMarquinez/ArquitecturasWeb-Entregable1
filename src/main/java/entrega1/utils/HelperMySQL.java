package entrega1.utils;

import entrega1.entities.Cliente;
import entrega1.entities.Factura;
import entrega1.entities.Producto;
import entrega1.factory.AbstractFactory;
import entrega1.repository.mysql.ClienteDAO;
import entrega1.repository.mysql.FacturaDAO;
import entrega1.repository.mysql.ProductoDAO;
import org.apache.commons.csv.CSVRecord;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelperMySQL {
    private Connection conn = null;

    public HelperMySQL() {//Constructor
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/Entregable1";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createTables() throws SQLException {
        String tableCliente = "CREATE TABLE IF NOT EXISTS Cliente(" +
                "idCliente INT NOT NULL, " +
                "nombre VARCHAR(150), " +
                "email VARCHAR(255), " +
                "CONSTRAINT idCliente_pk PRIMARY KEY (idCliente));" ;
        this.conn.prepareStatement(tableCliente).execute();

        String tableProducto = "CREATE TABLE IF NOT EXISTS Producto (" +
                "idProducto INT NOT NULL, " +
                "nombre VARCHAR(150), " +
                "valor FLOAT, " +
                "CONSTRAINT Producto_pk PRIMARY KEY (idProducto))" ;
        this.conn.prepareStatement(tableProducto).execute();

        String tableFactura = "CREATE TABLE IF NOT EXISTS Factura(" +
                "idFactura INT NOT NULL, " +
                "idCliente INT NOT NULL, " +
                "CONSTRAINT Factura_pk PRIMARY KEY (idFactura), "+
                "CONSTRAINT FK_idCliente FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente))";
        this.conn.prepareStatement(tableFactura).execute();

        String tableFacturaProducto = "CREATE TABLE IF NOT EXISTS FacturaProducto (" +
                "idFactura INT NOT NULL, " +
                "idProducto INT NOT NULL, " +
                "cantidad INT, " +
                "CONSTRAINT FacturaProducto_pk PRIMARY KEY (idFactura, idProducto), " +
                "CONSTRAINT FK_Factura FOREIGN KEY (idFactura) REFERENCES Factura(idFactura), " +
                "CONSTRAINT FK_Producto FOREIGN KEY (idProducto) REFERENCES Producto(idProducto))";
        this.conn.prepareStatement(tableFacturaProducto).execute();


    }

    public void dropTables() throws SQLException {

        String dropFacturaProducto = "DROP TABLE IF EXISTS FacturaProducto";
        this.conn.prepareStatement(dropFacturaProducto).execute();

        String dropFactura = "DROP TABLE IF EXISTS Factura";
        this.conn.prepareStatement(dropFactura).execute();

        String dropCliente = "DROP TABLE IF EXISTS Cliente";
        this.conn.prepareStatement(dropCliente).execute();

        String dropProducto = "DROP TABLE IF EXISTS Producto";
        this.conn.prepareStatement(dropProducto).execute();

    }



    public void rellenarTablas(){
        AbstractFactory chosenFactory = AbstractFactory.getInstance(1);
        ClienteDAO cliente = chosenFactory.getClienteDAO();

        Iterable<CSVRecord> registros = LectorCSV.leerCSV("src/main/resources/data/clientes.csv");
        for (CSVRecord row : registros) {
            Cliente nuevoCliente = new Cliente(
                    Integer.parseInt(row.get("idCliente")),
                    row.get("nombre"),
                    row.get("email")
            );
            cliente.insert(nuevoCliente);
        }

        ProductoDAO producto = chosenFactory.getProductoDAO();
        registros = LectorCSV.leerCSV("src/main/resources/data/productos.csv");
        for (CSVRecord row : registros) {
            Producto nuevoProducto = new Producto(
                    Integer.parseInt(row.get("idProducto")),
                    row.get("nombre"),
                    Float.parseFloat(row.get("valor"))

            );
            producto.insert(nuevoProducto);
        }

        FacturaDAO factura = chosenFactory.getFacturaDAO();
        registros = LectorCSV.leerCSV("src/main/resources/data/facturas.csv");
        for (CSVRecord row : registros) {
            Factura nuevaFactura = new Factura(
                    Integer.parseInt(row.get("idFactura")),
                    Integer.parseInt(row.get("idCliente"))
            );
            factura.insert(nuevaFactura);
        }


    }






}