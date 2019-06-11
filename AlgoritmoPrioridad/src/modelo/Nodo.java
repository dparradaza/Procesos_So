package modelo;

public class Nodo {

    private final int id;
    private final int T_LLEGADA;

    private int prioridad;
    private int t_rafaga;
    private int t_retorno;
    private int t_espera;
    private int t_final;

    //Enlace siguiente Nodo
    private Nodo siguiente;

    public Nodo(int id, int llegada, int rafaga) {
        this.id = id;
        this.T_LLEGADA = llegada;
        this.t_rafaga = rafaga;

        this.t_espera = 0;
        this.prioridad = 0;

        this.siguiente = null;
    }

    public int getId() {
        return id;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getT_llegada() {
        return T_LLEGADA;
    }

    public int getT_rafaga() {
        return t_rafaga;
    }

    public int getT_retorno() {
        return t_retorno;
    }

    public int getT_espera() {
        return t_espera;
    }

    public int getT_final() {
        return t_final;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    //Setters
    public void setT_final(int t_final) {
        this.t_final = t_final;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void aumentarPrioridad() {
        this.prioridad--;
    }

    public void setT_rafaga(int t_rafaga) {
        this.t_rafaga = t_rafaga;
    }

    public void setT_retorno(int t_retorno) {
        this.t_retorno = t_retorno;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    //metodos
    public void aumentarEspera() {
        this.t_espera++;
    }

    public void modRafaga(int a) {
        this.t_rafaga += a;
    }

}
