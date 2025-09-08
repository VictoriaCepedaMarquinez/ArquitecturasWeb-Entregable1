package entrega1.dao;

import entrega1.entities.Cliente;
import entrega1.entities.Producto;
import entrega1.repository.mysql.ProductoDAO;

public interface DAOProducto {
    public Producto get (int id);
    public void insert (Producto nuevoProducto);
    public void update (Producto nuevoProducto);
    public void delete (int id);
    public Producto productoMasVendido();
}
