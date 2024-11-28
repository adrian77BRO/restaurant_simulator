// Clase que implementa el Observador y reacciona cuando un comensal llega
package org.example.models;

public class ObservadorEntrada implements Observador {
    private boolean comensalHaLlegado = false;
    private int idComensalEsperado;

    @Override
    public void notificarComensalLlegado(int idComensal) {
        System.out.println("Comensal con ID " + idComensal + " ha llegado a la entrada.");
        this.comensalHaLlegado = true;
        this.idComensalEsperado = idComensal;
        synchronized (this) {
            notify(); // Notificamos que un comensal ha llegado y podemos proceder
        }
    }

    public synchronized void esperarComensal() throws InterruptedException {
        while (!comensalHaLlegado) {
            wait(); // Esperamos hasta que un comensal llegue
        }
    }

    public int getIdComensalEsperado() {
        return idComensalEsperado;
    }
}
