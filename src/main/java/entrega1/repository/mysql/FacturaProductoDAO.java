package entrega1.repository.mysql;

import entrega1.dao.DAOFacturaProducto;
import entrega1.entities.Factura;
import entrega1.entities.FacturaProducto;
import entrega1.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacturaProductoDAO implements DAOFacturaProducto {

    private final Connection cn;

    public FacturaProductoDAO(Connection cn) {
        this.cn = cn;
    }

    public FacturaProducto get(int id1, int id2) {
        final String sql = "SELECT cantidad FROM FacturaProducto WHERE idFactura = ? AND idProducto = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id1);
            ps.setInt(2, id2);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    FacturaDAO facturaDAO = new FacturaDAO(cn); //esto podria hacerse con inyeccion para no crear 2
                    ProductoDAO productoDAO = new ProductoDAO(cn);// preguntar

                    Factura factura = facturaDAO.get(id1);
                    Producto producto = productoDAO.get(id2);

                    return new FacturaProducto(factura, producto, rs.getInt(3));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar FacturaProducto con idFactura="
                    + id1 + " e idProducto=" + id2, e);
        }
    }


    public void insert(FacturaProducto facturaProducto) {

    }

    public void update(FacturaProducto facturaProducto) {
    }

    public void delete(int i) {

    }



}
