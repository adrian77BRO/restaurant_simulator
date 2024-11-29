package org.example.utils;
import java.util.Random;

public class PoissonDelay {
    private static final Random random = new Random();

    public static long generatePoissonDelay(double lambda) {
        double l = Math.exp(-lambda);
        double p = 1.0;
        int k = 0;

        do {
            k++;
            p *= random.nextDouble();
        } while (p > l);

        return (k - 1) * 1000; // a milisegundos
    }

    public static void main(String[] args) {
        double lambda = 1.5;

        for (int i = 0; i < 10; i++) {
            long delay = generatePoissonDelay(lambda);
            System.out.println("Delay generado: " + delay + " ms");

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("El hilo fue interrumpido.");
            }
        }
    }
}
