package exceptions;

public class HibasEmailCim extends Throwable {
    public HibasEmailCim(String gyartoEmailCim) {
        super("A kovetkezo email cim nem megfelelo: "+ gyartoEmailCim);
    }
}
