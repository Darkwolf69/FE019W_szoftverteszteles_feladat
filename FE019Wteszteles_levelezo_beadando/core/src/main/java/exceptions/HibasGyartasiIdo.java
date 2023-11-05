package exceptions;

import java.time.LocalDate;

public class HibasGyartasiIdo extends Throwable {
    public HibasGyartasiIdo(LocalDate gyartasiIdo) {
        super("A kovetkezo gyartasi ido nem megfelelo: "+ gyartasiIdo);
    }
}
