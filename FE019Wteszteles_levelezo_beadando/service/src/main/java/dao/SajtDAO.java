package dao;

import model.Sajt;
import exceptions.HibasEmailCim;
import service.exceptions.SajtNemTalalhato;

import java.util.Collection;

public interface SajtDAO {
    public Collection<Sajt> readSajtok();
    public Sajt readSajtById(int id) throws SajtNemTalalhato;
    public Sajt readSajtByEmailCim(String emailCim) throws SajtNemTalalhato, HibasEmailCim;
    public boolean createSajt(Sajt sajt);
    public void updateSajt(Sajt sajt);
    public void deleteSajt(Sajt sajt);
}
