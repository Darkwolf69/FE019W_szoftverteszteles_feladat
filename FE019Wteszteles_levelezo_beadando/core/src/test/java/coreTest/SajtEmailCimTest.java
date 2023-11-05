package coreTest;

import exceptions.HibasEmailCim;
import model.Sajt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class SajtEmailCimTest {

    String gyartoEmailCim;
    Sajt sajt;

    @Parameterized.Parameters
    public static Collection data(){
        List data = new ArrayList<>();
        data.add("szabalyos.gmail.com");
        data.add("mail1-citromail.hu");
        data.add("mailcim_4hzog");
        data.add("address'address4.info");
        data.add("fiktiv@");
        data.add("helyes.freemail.hu");
        data.add("9njier/829hdgh");
        data.add("moegom,845.com");
        data.add("valodi&valodimail.com");
        data.add("emailcim.emailcim.hu");
        data.add("-");
        data.add("alhosfgwerg");
        return data;
    }

    public SajtEmailCimTest(String gyartoEmailCim) {
        this.gyartoEmailCim = gyartoEmailCim;
    }
    @Before
    public void initSajt(){
        sajt = new Sajt();
    }

    @Test(expected = HibasEmailCim.class)
    public void testRosszEmailCim() throws HibasEmailCim {
        System.out.println("A vizsgalt email cim: "+gyartoEmailCim);
        sajt.setGyartoEmailCim(gyartoEmailCim);
    }
}
