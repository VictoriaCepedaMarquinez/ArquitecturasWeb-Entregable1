package entrega1.repository.mysql;

import entrega1.dao.DAOFactura;
import entrega1.entities.Factura;

import java.sql.*;

public class FacturaDAO implements DAOFactura {
    private final Connection cn;
    private final ClienteDAO clienteDAO;

    public FacturaDAO(Connection cn,  ClienteDAO clienteDAO) {
        this.cn = cn;
        this.clienteDAO = clienteDAO;
    }

    @Override
    public Factura get(int id) {
        final String sql = "SELECT idFactura, idCliente FROM Factura WHERE idFactura = ?";
        try(PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Factura factura = new Factura();
                    factura.setIdFactura(rs.getInt("idFactura"));
                    factura.setCliente(this.clienteDAO.get(rs.getInt("idCliente")));
                    return factura;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la factura con id:" + id, e);
        }
    }

    @Override
    public void insert(Factura factura) {
        final String sql = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
        try(PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getCliente().getIdCliente());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar la factura con id: " + factura.getIdFactura(), e);
        }
    }

    @Override
    public void update(Factura factura) {
        final String sql = "UPDATE Factura SET idCliente = ? WHERE idFactura = ?";
        try(PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, factura.getCliente().getIdCliente());
            ps.setInt(2, factura.getIdFactura());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la factura con id: " + factura.getIdFactura(), e);
        }
    }

    @Override
    public void delete(int id) {
        final String sql = "DELETE FROM Factura WHERE idFactura = ?";
        try(PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la factura con id: " + id, e);
        }
    }
}
