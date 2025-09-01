package entrega1.utils;

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
                "edad INT NOT NULL, " +
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






}