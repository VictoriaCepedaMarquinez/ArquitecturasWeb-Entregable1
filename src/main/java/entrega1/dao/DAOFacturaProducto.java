package entrega1.dao;

import entrega1.entities.FacturaProducto;

public interface DAOFacturaProducto {

    public FacturaProducto get(int id1, int id2);
    public void insert(FacturaProducto facturaProducto);
    public void update(FacturaProducto facturaProducto);
    public void delete(int id);
}
