package modelo;

public class ColaPr extends Cola {

    @Override
    public void push(Nodo A) {
        if (A.getPrioridad() == 0) {
            A.setPrioridad((int) (Math.random() * 4) + 1);
        }
        if (cabeza == null) {
            cabeza = A;
        } else if (A.getPrioridad() < cabeza.getPrioridad()) {
            Nodo P = cabeza;
            A.setSiguiente(P);
            cabeza = A;
        } else {
            Nodo auxAnt = cabeza;
            Nodo temp = auxAnt.getSiguiente();
            while (temp != null) {
                if (A.getPrioridad() < temp.getPrioridad()) {
                    A.setSiguiente(temp);
                    break;
                }
                auxAnt = temp;
                temp = temp.getSiguiente();
            }
            auxAnt.setSiguiente(A);
        }
    }

    @Override
    public void aumentarEsperas() {
        if (cabeza != null) {
            Nodo N = cabeza;
            Nodo M = cabeza.getSiguiente();
            while (M != null) {
                M.aumentarEspera();
                if (M.getPrioridad() > 1) {
                    //criterio de envejecimiento
                    if (M.getT_espera() % 100 == 0) {
                        M.aumentarPrioridad();
                        //al cambiar la prioridad, también debe cambiar (en teoría) su posicion en la cola
                        //por lo que se saca el nodo y se ata el siguiente al anterior
                        N.setSiguiente(M.getSiguiente());
                        //luego vuelve a ingresar ( el algoritmo de push lo ordena)
                        push(M);
                        //y mantenemos nuestra variable de control como el siguiente de ndo N 
                        //para que no hayan lios
                        if (N.getSiguiente() != null) {
                            M = N.getSiguiente();
                        } else {
                            break;
                        }
                    }
                }
                N = M;
                M = M.getSiguiente();
            }
        }
    }
}
