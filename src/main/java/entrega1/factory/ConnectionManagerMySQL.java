package entrega1.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManagerMySQL {

    /**
     * volatile garantiza que todos los hilos vean el mismo valor de instance en memoria,
     * incluso si un hilo la inicializa mientras otro la está leyendo.
     * Sin volatile, un hilo podría recibir una referencia a un objeto parcialmente construido por otro hilo.
     */
    private static volatile ConnectionManagerMySQL instance;
    private Connection connection; // java.sql.Connection (para SQL)

    // --- Configuración de conexión ---
    private static final String URL = "jdbc:mysql://localhost:3306/Entregable1";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // contraseña vacía


    // --- Constructor privado ---
    private ConnectionManagerMySQL() {
        try {
            // Registrar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida correctamente con MySQL.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos.");
            e.printStackTrace();
        }
    }

    // --- Singleton Thread-Safe ---
    public static ConnectionManagerMySQL getInstance() {
        if (instance == null) { // 1er chequeo: Evita bloquear si ya existe la instancia.
            synchronized (ConnectionManagerMySQL.class) { // Bloque sincronizado: Asegura que solo un hilo cree la instancia en caso de concurrencia.
                if (instance == null) { // 2do chequeo Confirma que instance sigue siendo null antes de crearla.
                    instance = new ConnectionManagerMySQL();
                }
            }
        }
        return instance;
    }


    // --- Retornar la conexión ---
    public Connection getConnection() {
        return connection;
    }


}
