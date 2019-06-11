package modelo;

public class Cola {

    protected Nodo cabeza;

    public void push(Nodo A) {
        if (cabeza == null) {
            cabeza = A;
        } else {
            Nodo temp = cabeza;
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(A);
        }
    }

    //Función para obtener el nodo
    public Nodo pop() {
        if (cabeza != null) {
            Nodo temp = cabeza;
            cabeza = cabeza.getSiguiente();
            temp.setSiguiente(null);
            return temp;
        }
        return null;
    }

    public boolean isEmpty() {
        if (cabeza == null) {
            return true;
        }
        return false;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void aumentarEsperas() {
        if (cabeza != null) {
            Nodo M = cabeza.getSiguiente();
            while (M != null) {
                M.aumentarEspera();
                M = M.getSiguiente();
            }
        }
    }

    public void ejecutarCabeza() {
        if (cabeza != null) {
            cabeza.modRafaga(-1);
        }
    }
}
