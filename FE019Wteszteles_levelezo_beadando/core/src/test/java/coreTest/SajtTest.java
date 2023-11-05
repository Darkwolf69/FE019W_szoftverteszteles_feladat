package coreTest;

import exceptions.HibasEgysegar;
import exceptions.HibasEmailCim;
import exceptions.HibasGyartasiIdo;
import exceptions.UresNev;
import model.Sajt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class SajtTest {

    Sajt sajt;

    @Before
    public void initSajt() {
        sajt = new Sajt();
    }

    @Test
    public void testJoEmailCim() throws HibasEmailCim {
        String emailCim = "emailcim@freemail.hu";
        sajt.setGyartoEmailCim(emailCim);
        Assert.assertEquals(emailCim, sajt.gyartoEmailCim);
    }

    @Test(expected = HibasEmailCim.class)
    public void testEmailCimKukacNelkul() throws HibasEmailCim {
        String emailCim = "emailCim.freemail.hu";
        sajt.setGyartoEmailCim(emailCim);
    }

    @Test
    public void testGetterEmailCim() throws HibasEmailCim {
        sajt.gyartoEmailCim = "emailCim@freemail.hu";
        assertEquals(sajt.gyartoEmailCim, sajt.getGyartoEmailCim());
    }

    @Test
    public void testJoGyartasiIdo() throws HibasGyartasiIdo {
        LocalDate datum = LocalDate.now();
        sajt.setGyartasiIdo(datum);
    }

    @Test(expected = HibasGyartasiIdo.class)
    public void testKesobbiGyartasiIdo() throws HibasGyartasiIdo{
        LocalDate datum = LocalDate.now().plusDays(1);
        sajt.setGyartasiIdo(datum);
    }


    @Test(expected = HibasGyartasiIdo.class)
    public void testKorabbiGyartasiIdo() throws HibasGyartasiIdo{
        LocalDate datum = LocalDate.of(1885,12,31);
        sajt.setGyartasiIdo(datum);
    }

    @Test(expected = HibasEgysegar.class)
    public void testNemSzereploEgysegar() throws HibasEgysegar {
        String egysegar = "600";
        sajt.setEgysegar(egysegar);
    }

    @Test(expected = UresNev.class)
    public void testUresNev() throws UresNev {
        Sajt sajt = new Sajt();
        sajt.setNev("");
        sajt.becsomagol();
        sajt.kiszallit();
    }
}
