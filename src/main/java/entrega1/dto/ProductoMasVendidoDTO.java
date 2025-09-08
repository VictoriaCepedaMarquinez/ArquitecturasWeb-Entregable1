package entrega1.dto;

public class ProductoMasVendidoDTO {
    private String nombre;
    private float valor;
    private double recaudacion;

    public ProductoMasVendidoDTO(String nombre, float valor, double recaudacion) {
        this.nombre = nombre;
        this.valor = valor;
        this.recaudacion = recaudacion;
    }

    public String getNombre() {
        return nombre;
    }

    public float getValor() {
        return valor;
    }

    public double getRecaudacion() {
        return recaudacion;
    }

    @Override
    public String toString() {
        return "ProductoMasVendidoDTO{" +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", recaudacion=" + recaudacion +
                '}';
    }
}
