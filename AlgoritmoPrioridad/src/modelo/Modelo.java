package modelo;

public class Modelo {

    private final controlador.Controlador control;

    private Cola colaA; //Activo
    private Cola colaB; //Bloqueado

    //inicializa controlador y ejecución 
    public Modelo() {
        control = new controlador.Controlador(this);
    }

    //cambia tipo de Cola por una iniciarPrioridad
    public void iniciarPrioridad() {
        colaA = new ColaPr();
    }

    //inicializa cola bloqueado
    public void iniciarBloquedos() {
        colaB = new Cola();
    }

    public Cola getColaA() {
        return colaA;
    }

    public Cola getColaB() {
        return colaB;
    }
}
