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
    private final FacturaDAO facturaDAO;
    private final ProductoDAO productoDAO;

    public FacturaProductoDAO(Connection cn, FacturaDAO facturaDAO, ProductoDAO productoDAO) {
        this.cn = cn;
        this.facturaDAO = facturaDAO;
        this.productoDAO = productoDAO;
    }

    public FacturaProducto get(int id1, int id2) {
        final String sql = "SELECT cantidad FROM FacturaProducto WHERE idFactura = ? AND idProducto = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id1);
            ps.setInt(2, id2);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    Factura factura = facturaDAO.get(id1);
                    Producto producto = productoDAO.get(id2);
                    rs.close();
                    cn.close();

                    return new FacturaProducto(factura, producto, rs.getInt(1));
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
        final String sql = "INSERT INTO FacturaProducto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";

        try(PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, facturaProducto.getFactura().getIdFactura());
            ps.setInt(2, facturaProducto.getProducto().getIdProducto());
            ps.setInt(3, facturaProducto.getCantidad());
            ps.executeUpdate();
            cn.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar la factura con id: " + facturaProducto.getFactura().getIdFactura(), e);
        }

    }

    public void update(FacturaProducto facturaProducto) {
        final String sql = "UPDATE FacturaProducto SET cantidad = ? WHERE idFactura = ? AND idProducto = ?";

        try {
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1, facturaProducto.getCantidad());
            ps.executeUpdate();
            ps.close();
            cn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int idFactura, int idProducto) {
        final String sql = "DELETE FROM FacturaProducto WHERE idFactura = ? AND idProducto = ?";

        try {
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);
            ps.executeUpdate();
            cn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}
