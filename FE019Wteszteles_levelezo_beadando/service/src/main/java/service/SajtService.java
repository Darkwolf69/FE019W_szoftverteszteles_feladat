package service;

import dao.SajtDAO;
import exceptions.HibasEmailCim;
import model.Sajt;
import org.jetbrains.annotations.NotNull;
import service.exceptions.SajtNemTalalhato;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Collection;
import org.apache.commons.collections4.Predicate;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.filter;

public class SajtService {
    private SajtDAO dao;

    public SajtService(SajtDAO dao) {
        this.dao = dao;
    }

    public Collection<Sajt> getSajtok() {
        return dao.readSajtok();
    }


    public Collection<Sajt> getLejartSajtok() {
        Collection<Sajt> lejart;
        Collection<Sajt> osszesSajt = getSajtok();
        lejart = osszesSajt.stream().filter(a -> a.isEhetoE()).
                collect(Collectors.toList());
        return lejart;
    }

    public Collection<Sajt> getLejaratiDatumKozottiSajtok(@NotNull LocalDate tol, LocalDate ig) {
        if (tol.isAfter(ig)) {
            throw new InvalidParameterException();
        }
        Collection<Sajt> osszesSajt = getSajtok();
        Predicate<Sajt> pred = a -> a.getLejaratiIdo().isAfter(tol) &&
                a.getLejaratiIdo().isBefore(ig);

        filter(osszesSajt, pred);
        return osszesSajt;
    }


    public Sajt getSajtById (int id) throws SajtNemTalalhato {
        return dao.readSajtById(id);
    }

    public Sajt getSajtByEmailCim (String gyartoEmailCim) throws HibasEmailCim, SajtNemTalalhato {
        return dao.readSajtByEmailCim(gyartoEmailCim);
    }

    public void addSajt(Sajt sajt) {
        try {
            getSajtById(sajt.getId());
        } catch (SajtNemTalalhato e) {
            dao.createSajt(sajt);
        }
    }
}
