package entrega1;

import entrega1.utils.HelperMySQL;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        HelperMySQL helper = new HelperMySQL();
        helper.dropTables();
        helper.createTables();

        helper.rellenarTablas();

    }
}
