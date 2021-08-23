public class Fone {

    private final Identificador identificador;

    private final String numero;
    
    public Fone(Identificador identificador, String numero){
        this.identificador = identificador;
        this.numero = numero;
    }

    public static boolean validarNumero(String numero){
        return numero.matches("[0-9()-]+");
    }

    public Identificador getIdentificador() {
        return identificador;
    }

    public String getNumero() {
        return numero;
    }

}
