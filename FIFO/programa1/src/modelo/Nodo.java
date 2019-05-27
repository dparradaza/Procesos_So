package modelo;

public class Nodo {

    private String id;
    private String nombre;
    private int numeroServicios;
    private Nodo sig;
    
    public Nodo(int value, String value2, String nombre) {
        numeroServicios = value;
        id = value2;
        this.nombre = nombre;
        sig = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroServicios() {
        return numeroServicios;
    }

    public void setNumeroServicios(int numeroServicios) {
        this.numeroServicios = numeroServicios;
    }

    
    
    public Nodo getSig() {
        return this.sig;
    }

    

    public void setSig(Nodo value) {
        this.sig = value;
    }

}
