package pe.idat.rest.productos.model;

public class Producto {
    private long id;
    private String nombre;
    private String decripcion;
    private int precio;
    private int cantidad;


    
    public Producto(long id, String nombre, String decripcion, int precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.decripcion = decripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDecripcion() {
        return decripcion;
    }
    public void setDecripcion(String decripcion) {
        this.decripcion = decripcion;
    }
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
}
