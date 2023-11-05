package service;

import dao.SajtDAO;
import exceptions.ErvenytelenZsirtartalom;
import exceptions.HibasEmailCim;
import exceptions.HibasGyartasiIdo;
import exceptions.UresNev;
import model.Sajt;
import model.enums.Tejfajta;
import model.enums.Tipus;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;
import service.exceptions.EmailCimMarFoglalt;
import service.exceptions.SajtNemTalalhato;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.reset;

public class SajtServiceTest {

    private SajtService service;
    private SajtDAO dao;
    protected Collection<Sajt> sajtok;


    @Before
    public void setUp() throws UresNev, HibasGyartasiIdo, HibasEmailCim, ErvenytelenZsirtartalom, SajtNemTalalhato {
        dao = Mockito.mock(SajtDAO.class);
        service = new SajtService(dao);
        Sajt sajt1 = new Sajt("teszkosSajtTomb", "xy4@valami.com", 800, 300, 30,
                LocalDate.of(2023, 10, 28),
                LocalDate.of(2023, 11, 16), Tipus.TRAPPISTA, Tejfajta.TEHEN);
        Sajt sajt2 = new Sajt("alpesiSajt", "schwyzer@xymail.com", 5000, 200, 40,
                LocalDate.of(2023, 9, 26),
                LocalDate.of(2023, 10, 21), Tipus.APPENZELLER, Tejfajta.TEHEN);
        Sajt sajt3 = new Sajt("görögSajt", "xy4@valami.com", 2000, 100, 50,
                LocalDate.of(2023, 10, 19),
                LocalDate.of(2023, 12, 13), Tipus.FETA, Tejfajta.JUH);
        sajtok = new ArrayList<>();
        sajtok.add(sajt1);
        sajtok.add(sajt2);
        sajtok.add(sajt3);

        Mockito.when(dao.readSajtok()).thenReturn(sajtok);
        Mockito.when(dao.readSajtById(org.mockito.Matchers.anyInt())).
                thenThrow(new SajtNemTalalhato());
        Mockito.doReturn(sajt1).when(dao).readSajtById(1);
        Mockito.doReturn(sajt2).when(dao).readSajtById(2);
        Mockito.doReturn(sajt3).when(dao).readSajtById(3);
        Mockito.doThrow(HibasEmailCim.class).when(dao).readSajtByEmailCim(
                AdditionalMatchers.not(Mockito.matches("\\w+@\\w.\\S{2-4}")));
        Mockito.doThrow(EmailCimMarFoglalt.class).when(dao).createSajt(sajt1);
        Mockito.doThrow(EmailCimMarFoglalt.class).when(dao).createSajt(sajt2);
        Mockito.doThrow(EmailCimMarFoglalt.class).when(dao).createSajt(sajt3);
    }

    @Test
    public void testOsszeSajt() {
        Collection<Sajt> lekerdezett = service.getSajtok();
        assertEquals(3, lekerdezett.size());
        for (Sajt a : sajtok) {
            MatcherAssert.assertThat(lekerdezett, Matchers.hasItem(a));
        }
    }

    @Test
    public void testUgyanaz() throws UresNev, HibasGyartasiIdo, HibasEmailCim, ErvenytelenZsirtartalom {
        Sajt sajt = new Sajt("teszkosSajtTomb", "xy4@valami.com", 800, 300, 30,
                LocalDate.of(2021, 12, 28),
                LocalDate.of(2022, 1, 16), Tipus.TRAPPISTA, Tejfajta.TEHEN);
        for (Sajt a : sajtok) {
            MatcherAssert.assertThat(service.getSajtok(), Matchers.hasItem(a));
        }
    }

    @Test
    public void testVanLejartSajt() {
        assertNotEquals(0, service.getLejartSajtok().size());
    }

    @Test(expected = InvalidParameterException.class)
    public void testRosszIntervallum() {
        service.getLejaratiDatumKozottiSajtok(LocalDate.now(),
                LocalDate.now().minusMonths(2));
    }

    @Test
    public void testEgyNaposIntervallum() {
        assertEquals(0, service.getLejaratiDatumKozottiSajtok(LocalDate.parse("2023-11-04"),
                LocalDate.parse("2023-11-04")).size());
    }

    @Test
    public void testJoIntervallum() {
        assertEquals(2, service.getLejaratiDatumKozottiSajtok(LocalDate.parse("2023-09-01"),
                LocalDate.parse("2023-11-20")).size());
    }

    @Test
    public void testSajtByIdJoErtekek() throws SajtNemTalalhato {
        for (Sajt a : sajtok
        ) {
            assertEquals(a, service.getSajtById(a.getId()));
        }
    }

    @Test
    public void testSajtEmailCimMarFoglalt() throws HibasEmailCim, UresNev, HibasGyartasiIdo, SajtNemTalalhato, ErvenytelenZsirtartalom {
        Sajt sajt = new Sajt("teszkosSajtTomb", "xy4@valami.com", 800, 300, 30,
                LocalDate.of(2021, 12, 28),
                LocalDate.of(2022, 1, 16), Tipus.TRAPPISTA, Tejfajta.TEHEN);
        service.addSajt(sajt);
        Collection<Sajt> ujSajtok = sajtok;
        ujSajtok.add(sajt);
        Mockito.when(dao.readSajtok()).thenReturn(ujSajtok);
        assertEquals(4, service.getSajtok().size());
        Mockito.doThrow(EmailCimMarFoglalt.class).when(dao).
                readSajtByEmailCim(sajt.getGyartoEmailCim());
        service.addSajt(sajt);
    }
}
