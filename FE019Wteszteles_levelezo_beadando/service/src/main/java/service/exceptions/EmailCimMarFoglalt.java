package service.exceptions;

public class EmailCimMarFoglalt extends Exception {
    public EmailCimMarFoglalt(String gyartoEmailCim) {
        super("A kovetkezo email cim mar foglalt: "+ gyartoEmailCim);
    }
}
