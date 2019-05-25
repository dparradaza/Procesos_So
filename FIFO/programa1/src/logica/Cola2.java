package logica;

/**
 *
 * @author ADBA
 */
public class Cola2 {

    private Nodo cabeza;
    private int tamano;
    Nodo n;

    public Cola2() {
        cabeza = new Nodo("banco", "caj", 1);
        n = cabeza;
        n.setSig(n);
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public void insertar(String nombre, int valor) {
        tamano++;
        Nodo m = new Nodo(Integer.toString(tamano), nombre, valor);
        n.setSig(m);
        m.setSig(cabeza);
        n = m;
    }
    
    public void eliminar() {
        Nodo r;
        r = cabeza.getSig();
        r = r.getSig();
        cabeza.setSig(r);
        tamano = tamano - 1;
    }

    public Nodo obtenerDato(int pos) {
        Nodo aux;
        aux = cabeza;
        int p = 1;
        while (p < pos && aux != null) {
            aux = aux.getSig();
            p++;
        }
        if (aux != null) {
            return aux;
        }
        return null;
    }

    public boolean lista_vacia() {
        return tamano == 0;
    }
}
