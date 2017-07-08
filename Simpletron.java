package programa;

import java.util.Scanner;

/**
 * @author radge
 * @version 1.0.0
 */
class Simpletron {

    private int[] memoria = new int[100]; // memoria del simpletron.
    private Procesador procesador;
    private static  Scanner scanner = new Scanner(System.in);
    private static boolean prendida;
    private static boolean modoInteractivo = false;
    private static boolean modoDictado = false;
    private static int ordenPararInteractiva = 9999;

    Simpletron() {
        prender();
        setModoDeUso();
    }

    void correr() {
        if(!modoInteractivo && !modoDictado) {
            procesador.procesarMemoria();
            memoria = procesador.getMemoriaProcesada();
        } else if(modoDictado) {
            dictar();
        } else {
            interpreteDeOrdenes();
        }
    }

    void cargarPrograma(int[] programa) {
        if(!modoInteractivo) {
            System.arraycopy(programa, 0, memoria, 0, programa.length);
        }
        procesador = new Procesador(memoria);
    }

    private void interpreteDeOrdenes() {
        System.out.print(">>> ");
        int orden = getEntrada();
        while(true) {
            if(orden == ordenPararInteractiva) {
                break;
            }
            procesador.darOrden(orden);
            System.out.print(">>> ");
            orden = getEntrada();
        }
        Simpletron.apagar();
    }

    private void dictar() {
        procesador.dictar();

    }

    static int getEntrada() {
        return scanner.nextInt();
    }

    static int getInstruccion() {
        int instruc = getEntrada();
        if(!isInstruccionValida(instruc)) {
            System.out.println("Instrucción inválida");
            getInstruccion();
        }
        return instruc;
    }


    static boolean isInstruccionValida(int instruccion) {
        return Integer.toString(instruccion).length() == 4 || instruccion == 0;
    }

    private static void prender() {
        prendida = true;
        System.out.println("SIMPLETON ON");
    }
    static void apagar() {
        System.out.println("SIMPLETON OFF");
        prendida = false;
    }

    static boolean isPrendida() { // MUy mal, porque simpletron debería ser un objeto independiente
        return prendida;
    }

    private static void usarModoInteractivo() {
        modoInteractivo = true;
    }

    private static void setModoDeUso() {
        System.out.print("Modo de uso: ");
        String modoDeUso = scanner.next();
        switch(modoDeUso) {
            case "interactivo":
                usarModoInteractivo();
                System.out.println("MODO INTERACTIVO");
                System.out.printf("Introduzca \"%d\" para salir", ordenPararInteractiva);
                break;
            case "lector":
                System.out.println("MODO LECTOR");
                break;
            case "dictado":
                System.out.println("MODO DICTADO");
                modoDictado = true;
                break;
            default:
                System.out.println("Modo de uso inválido");
                setModoDeUso();
        }
    }
}