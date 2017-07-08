package programa;

/**
 * @author radge
 * @version 1.0.0
 */
class Procesador {
    private int instruccion; // 4 dígitos, primeros dos operacion, últimos dos, operando
    private int operacion; //operación a llevar a cabo en el operando
    private int operando; //posicion en memoria a operar
    private int[] memoria;
    private int[] memoriaDeDictado = new int[100];
    private int acumulador = 0;
    private int indexOperacion;

    Procesador(int[] memoriaParaProcesar) {
        memoria = memoriaParaProcesar;
    }

    private void procesarMemoria(int[] memoria) {
        for(int instruccion : memoria) {
            if(Simpletron.isPrendida()) {
                procesar(instruccion);
            } else {
                break;
            }
        }
    }

    void procesarMemoria() {
        for(indexOperacion = 0; indexOperacion < memoria.length; indexOperacion++) {
            if(Simpletron.isPrendida()) {
                procesar(memoria[indexOperacion]);
            } else {
                break;
            }
        }
    }

    private void procesar(int instruccionAOperar) {
        if(Simpletron.isInstruccionValida(instruccionAOperar)) {
            instruccion = instruccionAOperar;
            operacion = extraerOperacion();
            operando = extraerOperando();
        }
        procesarOperacion();
    }

    private void procesarOperacion() {
        switch(operacion) {
            case Operaciones.LEE:
                leer();
                break;
            case Operaciones.ESCRIBE:
                escribir();
                break;
            case Operaciones.CARGA:
                cargar();
                break;
            case Operaciones.ALMACENA:
                almacenar();
                break;
            case Operaciones.SUMA:
                sumar();
                break;
            case Operaciones.RESTA:
                restar();
                break;
            case Operaciones.DIVIDE:
                dividir();
                break;
            case Operaciones.MULTIPLICA:
                multiplicar();
                break;
            case Operaciones.BIFURCA:
                bifurcar();
                break;
            case Operaciones.BIFURCANEG:
                bifurcarNeg();
                break;
            case Operaciones.BIFURCACERO:
                bifurcarCero();
                break;
            case Operaciones.ALTO:
                alto();
                break;
            case 0:
                //no hacer nada
                break;
            case Operaciones.AYUDA:
                getAyuda();
            default:
                System.out.println("Operación inválida");
        }
    }


    private void leer() {
        System.out.print(">>> ");
        memoria[operando] = Simpletron.getEntrada();
    }

    private void escribir() {
        System.out.println(memoria[operando]);
    }

    private void cargar() {
        acumulador = memoria[operando];
    }

    private void almacenar() {
        memoria[operando] = acumulador;
    }

    private void sumar() {
        acumulador += memoria[operando];
    }

    private void restar() {
        acumulador -= memoria[operando];
    }

    private void dividir() {
        acumulador /= memoria[operando];
    }

    private void multiplicar() {
        acumulador *= memoria[operando];
    }

    private void bifurcar() {
        indexOperacion = operando - 1;
    }

    private void bifurcarNeg() {
        if(acumulador < 0) {
            indexOperacion = operando - 1;
        }
    }

    private void bifurcarCero() {
        if(acumulador == 0) {
            indexOperacion = operando - 1;
        }
    }

    private void alto() {
        Simpletron.apagar();
    }

    private int extraerOperacion() {
        int digitoUno = instruccion / 1000;
        int digitoDos = instruccion % 1000 / 100;
        String numero = Integer.toString(digitoUno) + Integer.toString(digitoDos);
        return Integer.parseInt(numero);
    }

    private int extraerOperando() {
        int resta = extraerOperacion() * 100;
        return instruccion - resta;
    }

    int[] getMemoriaProcesada() {
        return memoria;
    }

    void darOrden(int orden) {
        if(Simpletron.isInstruccionValida(orden)) {
            procesar(orden);
        }
    }

    private void getAyuda() {
        Operaciones.imprimirInformaciónDeOperacion(operando);
    }

    void dictar() {
        System.out.println("Introduce \"9999\" para salir");
        int instruc;
        do {
            System.out.printf("%02d ? ", indexOperacion);
            instruc = Simpletron.getInstruccion();
            memoriaDeDictado[indexOperacion] =instruc;
            indexOperacion++;
            if(instruc == 9999) {
                procesarMemoria(memoriaDeDictado);
                Simpletron.apagar();
                break;
            }
        }while(true);
    }
}