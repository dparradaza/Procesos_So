package roundRobin;

import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Cola {

    public int i, num = 0, t_proceso = 0, primerProceso = 1;
    public Nodo nuevo, cab, p, aux;

    public void nuevo() {//crea la cola
        p = new Nodo();
        p.sig = p;
        cab = p;
    }

    public void procesos(int start) {//asigna elementos aleatorios a esa cola
        Random rnd2 = new Random();
        num = 1;

        t_proceso = 5;//genera un numero aleatorio que es el tiempo de caca proceso
        nuevo = new Nodo();//pedir memoria, generar un nodo
        nuevo.proceso = start;//asignar numero de proceso
        nuevo.tiempo_estimado = t_proceso;//tiempo de cpu 
        nuevo.setTiempo_cpu(0);
        nuevo.setTiempo_final(nuevo.tiempo_estimado * 2 + rnd2.nextInt(nuevo.tiempo_estimado));

        nuevo.quantum_suspendido = 1;

        cab.sig = nuevo;//Actualiza el enlace para apuntar al siguiente nodo 
        nuevo.sig = p; //actualiza el ultimo nodo con la direccion del primero
        cab = nuevo;// actualizar el contenido de la variable para recibir el siguiente nodo

    }

    public void agregarProceso(int nuevo_num) {//Agrega un nuevo proceso aleatorio a esa cola en un instante de tiempo cualquiera 
        Random rnd = new Random();
        Random rnd2 = new Random();
        Random rnd3 = new Random();
        t_proceso = 5;//genera un numero aleatorio que es el tiempo de caca proceso
        nuevo = new Nodo();//pedir memoria, generar un nodo
        nuevo.proceso = nuevo_num;//asignar numero de proceso
        nuevo.tiempo_estimado = t_proceso;//tiempo de cpu 
        nuevo.setTiempo_cpu(0);
        nuevo.setTiempo_final(nuevo.tiempo_estimado * 2 + rnd2.nextInt(nuevo.tiempo_estimado));
        nuevo.quantum_suspendido = 1;

        cab.sig = nuevo;//Actualiza el enlace para apuntar al siguiente nodo 
        nuevo.sig = p; //actualiza el ultimo nodo con la direccion del primero
        cab = nuevo;// actualizar el contenido de la variable para recibir el siguiente nodo
        num++;
    }

    //imprimir los nodos de la cola
    public void imprimir(JTextArea tx, JLabel label) {//imprime la cola en la interfaz
        String txt = "  ------------------ --------------- ---------------------- ---------------\n"
                + "    | PROCESO | RÁFAGA |  EJECUTADO  | ESPERA | \n"
                + "     ------------------ --------------- ---------------------- ---------------\n";
        Nodo impr;
        impr = p.sig;//asignar direccion del primer nodo que sigue a la cpu
        if (p == p.sig) {
            tx.setText("");
        }
        while (impr != p) {//se repite hasta encontrar el ultimo nodo
            if (impr.tiempo_cpu < 10) { //se valida esto para no dañar la tabla q se muestra
                txt = txt + "    |           " + impr.proceso
                        + "        |         " + impr.tiempo_final
                        + "        |         " + "0" + impr.tiempo_cpu
                        + "        |         " + impr.tiempo_en_espera
                        + "        \n" + "     ---------------------- --------------- ---------------------- ---------------\n";
            } else {
                txt = txt + "    |              "
                        + impr.proceso + "             |        "
                        + impr.tiempo_final + "       |             "
                        + impr.tiempo_cpu + "            |          "
                        + impr.tiempo_en_espera + "        \n" + "     ---------------------- --------------- ---------------------- ---------------\n";
            }
            tx.setText(txt);
            impr = impr.sig;//ubicar el siguiente nodo a mostrar
        }
        label.setText(num + "");
    }

    public void imprimirBloqueados(JTextArea tx, JLabel label, Cola round, Cola srtf, Cola fifo) {//imprime la cola en la interfaz
        String txt = "";
        Nodo impr, impr2, impr3;
        impr = fifo.p.sig;//asignar direccion del primer nodo que sigue a la cpu

        while (impr != fifo.p) {//se repite hasta encontrar el ultimo nodo
            txt = "     ---------------------- --------------- ---------------------- ---------------\n"
                    + "    | PROCESO | RÁFAGA |  EJECUTADO  | ESPERA \n"
                    + "     ---------------------- --------------- ---------------------- ---------------\n";
            if (impr.tiempo_cpu < 10) { //se valida esto para no dañar la tabla q se muestra
                txt = txt + "    |              "
                        + impr.proceso + "             |        "
                        + impr.tiempo_final + "       |             "
                        + "0" + impr.tiempo_cpu + "            |          "
                        + impr.tiempo_en_espera + "        \n" + "     ---------------------- --------------- ---------------------- ---------------\n";
            } else {
                txt = txt + "    |              "
                        + impr.proceso + "             |        "
                        + impr.tiempo_final + "       |             "
                        + impr.tiempo_cpu + "            |          "
                        + impr.tiempo_en_espera + "        \n" + "     ---------------------- --------------- ---------------------- ---------------\n";
            }
            impr = impr.sig;//ubicar el siguiente nodo a mostrar
        }
        impr2 = srtf.p.sig;
        while (impr2 != srtf.p) {//se repite hasta encontrar el ultimo nodo
            txt = "     ---------------------- --------------- ---------------------- ---------------\n"
                    + "    | PROCESO | RÁFAGA |  EJECUTADO  | ESPERA \n"
                    + "     ---------------------- --------------- ---------------------- ---------------\n";
            if (impr2.tiempo_cpu < 10) { //se valida esto para no dañar la tabla q se muestra
                txt = txt + "    |              "
                        + impr2.proceso + "             |        "
                        + impr2.tiempo_final + "       |             "
                        + "0" + impr2.tiempo_cpu + "            |          "
                        + impr2.tiempo_en_espera + "        \n" + "     ---------------------- --------------- ---------------------- ---------------\n";
            } else {
                txt = txt + "    |              "
                        + impr2.proceso + "             |        "
                        + impr2.tiempo_final + "       |             "
                        + impr2.tiempo_cpu + "            |          "
                        + impr2.tiempo_en_espera + "        \n" + "     ---------------------- --------------- ---------------------- ---------------\n";
            }
            impr2 = impr2.sig;//ubicar el siguiente nodo a mostrar
        }
        impr3 = round.p.sig;
        while (impr3 != round.p) {//se repite hasta encontrar el ultimo nodo
            txt = "     ---------------------- --------------- ---------------------- ---------------\n"
                    + "    | PROCESO | RÁFAGA |  EJECUTADO  | ESPERA \n"
                    + "     ---------------------- --------------- ---------------------- ---------------\n";
            if (impr3.tiempo_cpu < 10) { //se valida esto para no dañar la tabla q se muestra
                txt = txt + "    |              "
                        + impr3.proceso + "             |        "
                        + impr3.tiempo_final + "       |             "
                        + "0" + impr3.tiempo_cpu + "            |          "
                        + impr3.tiempo_en_espera + "        \n" + "     ---------------------- --------------- ---------------------- ---------------\n";
            } else {
                txt = txt + "    |              "
                        + impr3.proceso + "             |        "
                        + impr3.tiempo_final + "       |             "
                        + impr3.tiempo_cpu + "            |          "
                        + impr3.tiempo_en_espera + "        \n" + "     ---------------------- --------------- ---------------------- ---------------\n";
            }
            impr3 = impr3.sig;//ubicar el siguiente nodo a mostrar
        }
        tx.setText(txt);
        label.setText(round.num + fifo.num + srtf.num + "");

    }

    public void imprimir() {//imprime la cola en consola
        Nodo impr;
        impr = p.sig;//asignar direccion del primer nodo que sigue a la cpu
        while (impr != p) {//se repiteNo proceso:  "+ hasta encontrar el ultimo nodo
            impr = impr.sig;//ubicar el siguiente nodo a mostrar
        }
    }

    public void eliminarPrimero2() {
        Nodo auxx = p.sig;
        if (auxx.sig != null) {
            p.sig = auxx.sig;
            cab = p.sig;
        } else {
            p.sig = p;
            cab = p;
        }
        // auxx.sig=null;

    }

//segundo metodo para agregar un proceso, a este se le agrega el tiempo en el cuál salió
    public void agregar(Nodo agregado) {//agrega un nodo a otra cola
        nuevo = new Nodo();
        nuevo.proceso = agregado.proceso;
        nuevo.tiempo_estimado = agregado.tiempo_estimado;
        nuevo.tiempo_cpu = agregado.tiempo_cpu;
        nuevo.tiempo_final = agregado.tiempo_final;
        nuevo.quantum_suspendido = agregado.quantum_suspendido;
        nuevo.tiempo_en_espera = agregado.tiempo_en_espera;
        cab.sig = nuevo;
        nuevo.sig = p;
        cab = nuevo;
        num++;
    }

    public void eliminarNodo(Nodo eliminar) {//elimina un nodo en cualquier posición
        int c = -1;
        int num_eliminado;
        Nodo auxel = eliminar;
        Nodo asignar = this.p;
        while (auxel != this.p) {
            c++;
            auxel = auxel.sig;
        }

        if (num != 1) {
            num_eliminado = this.num - c;
            for (int n = num_eliminado; n > 1; n--) {
                asignar = asignar.sig;
            }
            asignar.sig = eliminar.sig;
            if (eliminar.sig == this.p) {
                cab = asignar;
            }
        } else {
            this.eliminarPrimero2();
        }
        num--;
    }
}
