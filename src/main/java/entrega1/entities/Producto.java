package entrega1.entities;

import entrega1.repository.mysql.ProductoDAO;

public class Producto {
    private int idProducto;
    private String nombre;
    private float valor;


    public Producto(){

    }
    public Producto(String nombre, float valor){
        this.nombre = nombre;
        this.valor = valor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public float getValor() {
        return valor;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
