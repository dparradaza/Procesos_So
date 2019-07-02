package Modelo;

public class Cola {

    public class Nodo {
        Proceso proceso;
        Nodo sig;
    }

    private Nodo raiz, fondo;

    public Cola() {
        raiz = null;
        fondo = null;
    }

    public boolean vacia() {
        return raiz == null;
    }

    public void insertar(Proceso proceso) {
        Nodo nuevo;
        nuevo = new Nodo();
        nuevo.proceso = proceso;
        if (vacia()) {
            raiz = nuevo;
            fondo = nuevo;
        } else {
            fondo.sig = nuevo;
            fondo = nuevo;
        }
    }

    public Proceso extraer() {
        if (!vacia()) {
            Proceso informacion;
            informacion = raiz.proceso;
            if (raiz == fondo) {
                raiz = null;
                fondo = null;
            } else {
                raiz = raiz.sig;
            }
            return informacion;
        } else {
            return null;
        }
    }
}
