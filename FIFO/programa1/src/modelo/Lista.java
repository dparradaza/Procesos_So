package modelo;

import modelo.Nodo;

public class Lista {

    private Nodo cab;
    private int tam;
    Nodo p;

    public Lista() {
        cab = new Nodo(0, "bank", "Procesador");
        p = cab;
        p.setSig(p);
    }

    public Nodo getCab() {
        return this.cab;
    }

    public Integer getTam() {
        return this.tam;
    }

    public void setCab(Nodo value) {
        this.cab = value;
    }

    public void setTam(Integer value) {
        this.tam = value;
    }

    public void insertar(int value, String nombre) {
        tam++;
        Nodo q = new Nodo(value, Integer.toString(tam), nombre);
        p.setSig(q);
        q.setSig(cab);
        p = q;

    }

    public void eliminar() {
        Nodo r;
        r = cab.getSig();
        r = r.getSig();
        cab.setSig(r);
        tam = tam - 1;
    }

    public Nodo obtenerDato(int pos) {
        Nodo aux;
        aux = cab;
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
        return tam == 0;
    }
}