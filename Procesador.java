package programa;

/**
 * @author radge
 * @version 1.0.0
 */
class Procesador {
	/** A instruction holds a operacion and a operando **/
    private int instruccion; // 4 dígitos, primeros dos operacion, últimos dos, operando
	
	/** The code of what will be done to the operando data **/
    private int operacion; //operación a llevar a cabo en el operando
	
	/** The data that will be used to make the operacion **/
    private int operando; //posicion en memoria a operar
	
	/** Holds all the instructions that the Procesador will read **/
    private int[] memoria;
	
	/** A temporal memory used in the Dictado Mode **/
    private int[] memoriaDeDictado = new int[100];
	
	/** The slot of memory used to store data to access right now. **/
    private int acumulador = 0;
	
	/** The current position of the memory slot that will be processed next. **/
    private int indexOperacion;

    /**
	 * Creates a new Procesador using the given int array for the Memory
	 * @param memoriaParaProcesar array of memory instructions.
	 **/
    Procesador(int[] memoriaParaProcesar) {
        memoria = memoriaParaProcesar;
    }
	/**
	 * Process the array of memory instructions given as the argument.
	 * @param memoria Memoria para procesar.\
	 **/
    private void procesarMemoria(int[] memoria) {
        for(int instruccion : memoria) {
            if(Simpletron.isPrendida()) {
                procesar(instruccion);
            } else {
                break;
            }
        }
    }

	/**
	 * Reads every instruction stored in the memory of the Procesador if the Processador
	 * is on, if not it does nothing.
	 **/
    void procesarMemoria() {
        for(indexOperacion = 0; indexOperacion < memoria.length; indexOperacion++) {
            if(Simpletron.isPrendida()) {
                procesar(memoria[indexOperacion]);
            } else {
                break;
            }
        }
    }

	/**
	 * Process the given instruction, assigns intruction, operation and operando using instruccionAOperar,<br>
	 * extraerOperacion y extraerOperando, ends calling procesarOperacion once all the parameters are set.
	 * 
	 * @param instruccionAOperar The instruction to read and execute
	 **/
    private void procesar(int instruccionAOperar) {
        if(Simpletron.isInstruccionValida(instruccionAOperar)) {
            instruccion = instruccionAOperar;
            operacion = extraerOperacion();
            operando = extraerOperando();
        }
        procesarOperacion();
    }

	/**
	 * Calss actions based on the current operacion value
	 **/
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


	/**
	 * Prints a prompt and  stores the input of the user on memory.<br>
	 * it doesn't read the input itself, instead, it calls the Simpletron.getEntrada() method.
	 **/
    private void leer() {
        System.out.print(">>> ");
        memoria[operando] = Simpletron.getEntrada();
    }

	/**
	 * Prints the value of the current memory slot
	 **/
    private void escribir() {
        System.out.println(memoria[operando]);
    }

	/**
	 * Loads the slot to the acumulador
	 **/
    private void cargar() {
        acumulador = memoria[operando];
    }

	/**
	 * Stores the current acumulador on memory.
	 **/
    private void almacenar() {
        memoria[operando] = acumulador;
    }
	/**
	 * Adds the current memory slot to the acumulador
	 **/
    private void sumar() {
        acumulador += memoria[operando];
    }

	/**
	 * Diminishes the current memory slot to the acumulador.\
	 **/
    private void restar() {
        acumulador -= memoria[operando];
    }

	/**
	 * Divides the acumulador with the current slot.
	 **/
    private void dividir() {
        acumulador /= memoria[operando];
    }

	/**
	 * Multiplies the current memory slot to the acumulador.
	 **/
    private void multiplicar() {
        acumulador *= memoria[operando];
    }

	/**
	 * Does a goto instruction
	 **/
    private void bifurcar() {
        indexOperacion = operando - 1;
    }

	/**
	 * If the acumulador is less than zero, calls a goto instruction.
	 **/
    private void bifurcarNeg() {
        if(acumulador < 0) {
            indexOperacion = operando - 1;
        }
    }

	/**
	 * If the acumulador is equals to zero, calls the goto instruction.
	 **/
    private void bifurcarCero() {
        if(acumulador == 0) {
            indexOperacion = operando - 1;
        }
    }

	/**
	 * Stops the execution of the memory slots and turns of the Simpletron.
	 **/
    private void alto() {
        Simpletron.apagar();
    }

	/**
	 * Gets the operaction from the current memory slot.
	 **/
    private int extraerOperacion() {
        int digitoUno = instruccion / 1000;
        int digitoDos = instruccion % 1000 / 100;
        String numero = Integer.toString(digitoUno) + Integer.toString(digitoDos);
        return Integer.parseInt(numero);
    }

	/**
	 * Gets the operando from the current memory slot.
	 **/
    private int extraerOperando() {
        int resta = extraerOperacion() * 100;
        return instruccion - resta;
    }

	/**
	 * Gets the memoria array.
	 * @return The memory of the Procesador.
	 **/
    int[] getMemoriaProcesada() {
        return memoria;
    }

	/**
	 * Gives a new order that is not stored into memory
	 *
	 * @param orden The instruction to be processed.
	 **/
    void darOrden(int orden) {
        if(Simpletron.isInstruccionValida(orden)) {
            procesar(orden);
        }
    }

	/**
	 * Prints the help information for the given operando.
	 **/
    private void getAyuda() {
        Operaciones.imprimirInformaciónDeOperacion(operando);
    }

	/**
	 * Starts a interpreter mode, when every instruction is read as it is typed to the console.
	 **/
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
