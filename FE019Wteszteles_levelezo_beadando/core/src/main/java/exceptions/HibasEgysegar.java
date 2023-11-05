package exceptions;

import java.time.LocalDate;

public class HibasEgysegar extends Throwable {
    public HibasEgysegar(String egysegar) {
        super("A kovetkezo egysegar nem megfelelo: "+ egysegar);
    }
}
