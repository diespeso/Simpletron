package programa;

/**
 * Crea un programa y lo carga a la memoria del Simpletron.
 */
class Main {

    public static void main(String[] args) {
        Simpletron simpletron =new Simpletron();
        int[] programa = {
                1009,
                1109,
                4300
        };
        simpletron.cargarPrograma(programa);
        simpletron.correr();
    }

}