package entrega1.dao;

import entrega1.entities.Cliente;

public interface DAOCliente {
    public Cliente get (int id);
    public void insert (Cliente c);
    public void update (Cliente c);
    public void delete (int id);

}
