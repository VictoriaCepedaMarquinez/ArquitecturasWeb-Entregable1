package entrega1.entities;

public class FacturaProducto {

    private Factura factura;
    private Producto producto;
    private int cantidad;

    public FacturaProducto(Factura factura, Producto producto, int cantidad) {
        this.factura = factura;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "FacturaProducto {\n" +
                "  factura=" + factura + ",\n" +
                "  producto=" + producto + ",\n" +
                "  cantidad=" + cantidad + "\n" +
                '}';
    }

}
