package model;

public interface Feldolgoz {
    default void becsomagol() { System.out.println("Sajt becsomagolva"); };
    default void kiszallit() { System.out.println("Sajt kiszallitva"); };
}
