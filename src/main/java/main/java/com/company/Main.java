package main.java.com.company;




public class Main {


    /**
     * para hacer lectura de un archivo properties.. se debe de establecer la ruta desde la carpeta "src" y pues
     * se le agrega el nombre del archivo.
     *
     * @param args
     */
    public static void main(String[] args) {
        // write your code here
        SQLJose datos = new SQLJose("pruebaSQLRemota");


        datos.recibirSQL();
        datos.insertarSQL("Antonieta ", 1);
        datos.recibirSQL();
        datos.cerrar();

    }
}


