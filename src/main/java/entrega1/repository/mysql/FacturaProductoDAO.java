package entrega1.repository.mysql;

import entrega1.dao.DAOFacturaProducto;
import entrega1.entities.Factura;
import entrega1.entities.FacturaProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacturaProductoDAO implements DAOFacturaProducto {

    private final Connection cn;

    public FacturaProductoDAO(Connection cn) {
        this.cn = cn;
    }


    public FacturaProducto get(int id){

        final String sql = "SELECT idFactura, idProducto, cantidad FROM FacturaProducto WHERE idFactura = ? AND idProducto = ?";
        try(PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    FacturaProducto facturaProducto = new FacturaProducto();
                    facturaProducto.setIdFactura(rs.getInt(1));
                    facturaProducto.setIdProducto(rs.getInt("idCliente"));
                    return FacturaProducto;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la Factura del Prodcuto  con id:" + id, e);
        }

        return null;
    }


    public void insert(FacturaProducto facturaProducto) {

    }

    public void update(FacturaProducto facturaProducto) {
    }

    public void delete(int i) {

    }



}
