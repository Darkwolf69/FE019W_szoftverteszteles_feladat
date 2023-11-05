package coreTest;

import exceptions.HibasEgysegar;
import model.Sajt;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
    public class SajtEgysegarTest {
        String kategoria;
        int ar;

    @Parameterized.Parameters
    public static Collection data(){
        List data = new ArrayList<>();
        data.add(new Object[]{"belepo", 500});
        data.add(new Object[]{"olcso", 800});
        data.add(new Object[]{"atlagos", 1000});
        data.add(new Object[]{"hazi", 2000});
        data.add(new Object[]{"minosegi", 5000});
        data.add(new Object[]{"premium", 8000});

        return data;
    }

    public SajtEgysegarTest(String kategoria, int ar) {
        this.kategoria = kategoria;
        this.ar = ar;
    }

    @Test
    public void testJoEgysegar() throws HibasEgysegar {
        System.out.println("A vizsgált kategoria: "+ kategoria);
        Sajt sajt = new Sajt();
        sajt.setEgysegar(kategoria);
        Assert.assertEquals(ar, sajt.getEgysegar());
    }
}
