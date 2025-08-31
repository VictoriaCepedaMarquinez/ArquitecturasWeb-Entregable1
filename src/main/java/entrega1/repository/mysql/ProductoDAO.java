package entrega1.repository.mysql;

import entrega1.dao.DAOProducto;
import entrega1.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO implements DAOProducto {

    private Connection conn;

    public ProductoDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public Producto get(int id) {
        Producto producto = null;
        try{
            String statement = "SELECT * FROM Producto WHERE idProducto = ?";
            PreparedStatement ps = this.conn.prepareStatement(statement);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setValor(rs.getFloat("valor"));
            }
            rs.close();
            ps.close();
            this.conn.commit();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return producto;
    }

    @Override
    public void insert(Producto nuevoProducto) {
        try{
            String statement = "INSERT INTO Producto (nombre,valor) VALUES (?,?)";
            PreparedStatement ps = this.conn.prepareStatement(statement);
            ps.setString(1,nuevoProducto.getNombre());
            ps.setFloat(2,nuevoProducto.getValor());
            ps.executeUpdate();
            ps.close();
            this.conn.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Producto nuevoProducto) {
        try{
            String statement = "UPDATE Producto SET nombre = ?, valor = ? WHERE idProducto = ?";
            PreparedStatement ps = this.conn.prepareStatement(statement);
            ps.setString(1, nuevoProducto.getNombre());
            ps.setFloat(2,nuevoProducto.getValor());
            ps.setInt(3,nuevoProducto.getIdProducto());
            ps.executeUpdate();
            ps.close();
            this.conn.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String statement = "DELETE FROM Producto WHERE idProducto = ?";
            PreparedStatement ps = this.conn.prepareStatement(statement);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            this.conn.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
