package programa;

/**
 * @author radge
 * @version 1.0.0
 */
class Operaciones {
    //Operaciones de entrada y salida.
    final static int LEE = 10;
    final static int ESCRIBE = 11;

    //Operaciones de carga y almacenamiento.
    final static int CARGA = 20;
    final static int ALMACENA = 21;

    //Operaciones aritméticas.
    final static int SUMA = 30;
    final static int RESTA = 31;
    final static int DIVIDE = 32;
    final static int MULTIPLICA = 33;

    //Operaciones de transferencia de Control.
    final static int BIFURCA = 40;
    final static int BIFURCANEG = 41;
    final static int BIFURCACERO = 42;
    final static int ALTO = 43;

    //Orden para mostrar ayuda (esto)
    final static int AYUDA = 50;

    static void imprimirInformaciónDeOperacion(int operacion) {
        String salida;
        switch(operacion) {
            case LEE:
                salida = "Leer%n" +
                            "Lee una palabra desde el teclado y la introduce en%n" +
                            "una ubicación específica de memoria.";
                break;
            case ESCRIBE:
                salida = "Escribir%n" +
                            "Escribe una palabra de una ubicación específica%n" +
                            "de memoria y la imprime en la pantalla.";
                break;
            case CARGA:
                salida = "Cargar%n" +
                            "Carga una palabra de una ubicación específica de%n" +
                            "memoria y la coloca en el acumulador.";
                break;
            case ALMACENA:
                salida = "Almacenar%n" +
                            "Almacena una palabra del acumulador dentro de una%n" +
                            "ubicación específica de memoria.";
                break;
            case SUMA:
                salida = "Sumar%n" +
                            "Suma una palabra de una ubicación específica de memoria%n" +
                            "a la palabra en el acumulador (deja el resultado en el acumulador).";
                break;
            case RESTA:
                salida = "Restar%n" +
                            "Resta una palabra de una ubicación específica de memoria%n" +
                            "a la palabra en el acumulador (deja el resultado en el acumulador).";
                break;
            case DIVIDE:
                salida = "Dividir%n" +
                            "Divide una palabra de una ubicación específica de memoria%n" +
                            "entre la palabra en el acumulador (deja el resultado en el acumulador).";
                break;
            case MULTIPLICA:
                salida = "Multiplicar%n" +
                            "Multiplica una palabra de una ubicación específica%n" +
                            "de memoria por la palabra en el acumulador (deja el resultado en el acumulador).";
                break;
            case BIFURCA:
                salida = "Bifurcar%n" +
                            "Bifurca hacia una ubicación específica de memoria.";
                break;
            case BIFURCANEG:
                salida = "Bifucar Negativo%n" +
                            "Bifurca hacia una ubicación específica de memoria%n" +
                            "si el acumulador es negativo";
                break;
            case BIFURCACERO:
                salida = "Bifurcar Cero%n" +
                            "Bifurca hacia una ubicación específica de memoria%n" +
                            " si el acumulador es cero.";
                break;
            case ALTO:
                salida = "Alto%n" +
                            "Alto. El programa completó su tarea.";
                break;
            case AYUDA:
                salida = "Ayuda%n" +
                            "Muestra la ayuda de la operación especificada en el operando";
                break;
            default:
                salida = "Operación no identificada";
        }
        System.out.printf(salida);
        System.out.println();
    }
}
