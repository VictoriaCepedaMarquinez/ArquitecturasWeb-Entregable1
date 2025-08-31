package entrega1.dao;

import entrega1.entities.Factura;

public interface DAOFactura {
    public Factura get(int id);
    public void insert(Factura factura);
    public void update(Factura factura);
    public void delete(int id);
}
