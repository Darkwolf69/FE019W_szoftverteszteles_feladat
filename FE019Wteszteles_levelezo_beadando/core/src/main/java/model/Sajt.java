package model;

import exceptions.*;
import model.enums.Tejfajta;
import model.enums.Tipus;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Sajt implements Feldolgoz {

    public static Map<String, Integer> egysegarErtekek;

    static {
        egysegarErtekek = new HashMap<>();
        egysegarErtekek.put("belepo", 500);
        egysegarErtekek.put("olcso", 800);
        egysegarErtekek.put("atlagos", 1000);
        egysegarErtekek.put("hazi", 2000);
        egysegarErtekek.put("minosegi", 5000);
        egysegarErtekek.put("premium", 8000);
    }

    protected static int globalID = 0;

    protected int id;
    protected String nev;
    public String gyartoEmailCim;
    protected int egysegar;
    protected double tomeg;
    protected double zsirtartalom;
    protected boolean ehetoE;
    protected LocalDate gyartasiIdo;
    protected LocalDate lejaratiIdo;
    protected Tipus tipus;
    protected Tejfajta tejfajta;

    public int getId() {
        return id;
    }

    public void setId() {
        globalID++;
        this.id = globalID;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) throws UresNev {
        if (nev.trim().length() == 0) {
            throw new UresNev();
        }
        this.nev = nev;
    }

    public String getGyartoEmailCim () {
        return gyartoEmailCim;
    }

    public void setGyartoEmailCim(String gyartoEmailCim) throws HibasEmailCim {
        String minta = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (!gyartoEmailCim.matches(minta)) {
            throw new HibasEmailCim(gyartoEmailCim);
        } else {
            this.gyartoEmailCim = gyartoEmailCim;
        }
    }

    public int getEgysegar() {
        return egysegar;
    }

    public void setEgysegar(String egysegar) throws HibasEgysegar {
        if (!egysegarErtekek.containsKey(egysegar)){
            throw new HibasEgysegar(egysegar);
        }
        Integer ertek = egysegarErtekek.get(egysegar);
        this.egysegar = ertek.intValue();
    }

    public double getTomeg() {
        return tomeg;
    }

    public void setTomeg(double tomeg) {
        this.tomeg = tomeg;
    }

    public double getZsirtartalom() {
        return zsirtartalom;
    }

    public void setZsirtartalom(double zsirtartalom) throws ErvenytelenZsirtartalom {
        if ((zsirtartalom < 20.0) || (zsirtartalom > 90.0)) {
            throw new ErvenytelenZsirtartalom();
        }
        this.zsirtartalom = zsirtartalom;
    }

    public boolean isEhetoE() {
        return ehetoE;
    }

    public void setEhetoE() {
        if (this.getLejaratiIdo().isBefore(LocalDate.now())) {
            this.ehetoE = false;
        } else {
            this.ehetoE = true;
        }
    }

    public LocalDate getGyartasiIdo() {
        return gyartasiIdo;
    }

    public void setGyartasiIdo(LocalDate gyartasiIdo) throws HibasGyartasiIdo {
        if (gyartasiIdo.isAfter(LocalDate.now()) || gyartasiIdo.isBefore(LocalDate.parse("1888-01-01"))) {
            throw new HibasGyartasiIdo(gyartasiIdo);
        }
        this.gyartasiIdo = gyartasiIdo;
    }

    public LocalDate getLejaratiIdo() {
        return lejaratiIdo;
    }

    public void setLejaratiIdo(LocalDate lejaratiIdo) {
        this.lejaratiIdo = lejaratiIdo;
    }

    public Tipus getTipus() {
        return tipus;
    }

    public void setTipus(Tipus tipus) {
        this.tipus = tipus;
    }

    public Tejfajta getTejfajta() {
        return tejfajta;
    }

    public void setTejfajta(Tejfajta tejfajta) {
        this.tejfajta = tejfajta;
    }

    public Sajt(String nev, String gyartoEmailCim, int egysegar, double tomeg, double zsirtartalom,
                LocalDate gyartasiIdo, LocalDate lejaratiIdo, Tipus tipus, Tejfajta tejfajta) throws UresNev, HibasEmailCim, ErvenytelenZsirtartalom, HibasGyartasiIdo {
        setId();
        setNev(nev);
        setGyartoEmailCim(gyartoEmailCim);
        setEgysegar(egysegar);
        setTomeg(tomeg);
        setZsirtartalom(zsirtartalom);
        setGyartasiIdo(gyartasiIdo);
        setLejaratiIdo(lejaratiIdo);
        setEhetoE();
        setTipus(tipus);
        setTejfajta(tejfajta);
    }

    public void setEgysegar(int egysegar) { this.egysegar = egysegar; }

    public Sajt() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sajt sajt = (Sajt) o;
        return id == sajt.id && egysegar == sajt.egysegar && Double.compare(sajt.tomeg, tomeg) == 0 && Double.compare(sajt.zsirtartalom, zsirtartalom) == 0 && ehetoE == sajt.ehetoE && nev.equals(sajt.nev) && gyartoEmailCim.equals(sajt.gyartoEmailCim) && gyartasiIdo.equals(sajt.gyartasiIdo) && lejaratiIdo.equals(sajt.lejaratiIdo) && tipus == sajt.tipus && tejfajta == sajt.tejfajta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nev, gyartoEmailCim, egysegar, tomeg, zsirtartalom, ehetoE, gyartasiIdo, lejaratiIdo, tipus, tejfajta);
    }

    @Override
    public String toString() {
        return "Sajt{" +
                "id=" + id +
                ", nev='" + nev + '\'' +
                ", gyartoEmailCim='" + gyartoEmailCim + '\'' +
                ", egysegar=" + egysegar +
                ", tomeg=" + tomeg +
                ", zsirtartalom=" + zsirtartalom +
                ", ehetoE=" + ehetoE +
                ", gyartasiIdo=" + gyartasiIdo +
                ", lejaratiIdo=" + lejaratiIdo +
                ", tipus=" + tipus +
                ", tejfajta=" + tejfajta +
                '}';
    }
}
