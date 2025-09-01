package entrega1.repository.mysql;

import java.sql.Connection;

import entrega1.dao.DAOCliente;
import entrega1.entities.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClienteDAO implements DAOCliente {
    private Connection conexion;

    public ClienteDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insert (Cliente c) {
        try{
            String sql = "INSERT INTO Cliente (idCliente,nombre,email) VALUES (?,?,?)";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1,c.getIdCliente());
            stmt.setString(2, c.getNombre());
            stmt.setString(3, c.getEmail());
            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(Cliente c) {
        try {
            String sql = "UPDATE CLIENTE SET nombre = ?, email = ? WHERE idCliente = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, c.getNombre());
            stmt.setString(2, c.getEmail());
            stmt.setInt(3, c.getIdCliente());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete (int id) {
        try {
            String sql = "DELETE FROM CLIENTE WHERE idCliente = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Cliente get(int id) {
        Cliente cliente = null;
        try{
            String sql = " SELECT * FROM CLIENTE WHERE idCliente = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));
            }
            rs.close();
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cliente;
    }

}
