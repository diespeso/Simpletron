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

    /**
     * Creates a new Simpletron and turns it on calling prender() and sets the useMode using setModoDeUso().
     **/
    Simpletron() {
        prender();
        setModoDeUso();
    }

    /**
     * Used when the modoDictado and the modoInteractivo are disabled.
     **/
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

    /**
     * Loads the memory and makes a new Procesador to read and execute it.
     *
     * @param programa The set of instructions to be processed.
     **/
    void cargarPrograma(int[] programa) {
        if(!modoInteractivo) {
            System.arraycopy(programa, 0, memoria, 0, programa.length);
        }
        procesador = new Procesador(memoria);
    }

    /**
     * WHY IS THIS CALLED LIKE THIS? ISN'T THIS OBJECT ORIENTED PROGRAMMING???????
     *
     * Prints a prompt to the user, gets its input calling getEntrada() and  loops while the Simpletron is turned on.
     * Processes every instruction on the memory and when it ends, turns off the Simpletron.
     **/
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

    /**
     * Starts the Dictado Mode for the Simpletron.
     **/
    private void dictar() { procesador.dictar(); }

    /**
     * Gets the input from the user.
     *
     * @return The input from the user.
     **/
    static int getEntrada() { return scanner.nextInt(); }

    /**
     * Gets the instruction that the user entered, but checks if it is valid first, this is done by
     * calling isInstruccionValida() on the user's input.
     *
     * @return The revised instruction that the user entered.
     **/
    static int getInstruccion() {
        int instruc = getEntrada();
        if(!isInstruccionValida(instruc)) {
            System.out.println("Instrucción inválida");
            getInstruccion();
        }
        return instruc;
    }

    /**
     * Checks whether the instruction is valid or not.
     *
     * @return Whether the instruction is valid or not.
     **/
    static boolean isInstruccionValida(int instruccion) {
        return Integer.toString(instruccion).length() == 4 || instruccion == 0;
    }

    /**
    * Turns on the simpletron and prints a message notifying the user.
    **/
    private static void prender() {
        prendida = true;
        System.out.println("SIMPLETON ON");
    }
    
    /**
     * 
    static void apagar() {
        System.out.println("SIMPLETON OFF");
        prendida = false;
    }

    static boolean isPrendida() { return prendida;}

    private static void usarModoInteractivo() { modoInteractivo = true; }

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
