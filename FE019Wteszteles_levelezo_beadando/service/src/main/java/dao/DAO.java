package dao;

import model.Sajt;
import exceptions.HibasEmailCim;
import service.exceptions.SajtNemTalalhato;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

public class DAO implements SajtDAO {

    private static final String Sajt_File = "sajtok.txt";
    private File dataFile;

    public DAO() {
        Path dPath = FileSystems.getDefault().getPath("src/main/resources", Sajt_File);
        dataFile = new File(dPath.toString());
    }

    @Override
    public ArrayList<Sajt> readSajtok() {
        Scanner sc;
        String record = null;
        String[] fields;
        ArrayList<Sajt> sajtok = new ArrayList<>();

        try {
            sc = new Scanner(dataFile);
            while (sc.hasNextLine()) {
                record = sc.nextLine();
                fields = record.split(";");
                String id = fields[0];
                String nev = fields[1];
                String gyartoEmailCim = fields[2];
                int egysegar = Integer.parseInt(fields[3]);
                double tomeg = Double.parseDouble(fields[4]);
                double zsirtartalom = Double.parseDouble(fields[5]);
                boolean ehetoE = Boolean.parseBoolean(fields[6]);
                String gyartasiIdo = fields[7];
                String lejaratiIdo = fields[8];
                String tipus = fields[9];
                String tejfajta = fields[10];
                Sajt s = new Sajt();
                sajtok.add(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found!");
            //e.printStackTrace();
        }
        return sajtok;
    }

    @Override
    public Sajt readSajtById(int id) {
        ArrayList<Sajt> sajtok = readSajtok();
        Sajt sajt = null;
        for (Sajt s: sajtok) {
            if (s.getId() == id) {
                sajt = s;
                break;
            }
        }
        return sajt;
    }

    @Override
    public Sajt readSajtByEmailCim(String emailCim) {
        ArrayList<Sajt> sajtok = readSajtok();
        Sajt sajt = null;
        for (Sajt s: sajtok) {
            if (!s.getGyartoEmailCim().equals(emailCim)) {
                sajt = s;
                break;
            }
        }
        return sajt;
    }

    @Override
    public boolean createSajt(Sajt sajt) {
        boolean existing = false;
        ArrayList<Sajt> sajtok = readSajtok();
        for (Sajt s: sajtok) {
            if (s.getId() == sajt.getId()) {
                existing = true;
                break;
            }
        }
        if (!existing) {
            sajtok.add(sajt);
            try {
                FileWriter out = new FileWriter(dataFile);
                for (Sajt s: sajtok) {
                    out.append(s.toString() + "\n");
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return !existing;
    }

    @Override
    public void updateSajt(Sajt sajt) {
        ArrayList<Sajt> sajtok = readSajtok();
        for (int i = 0; i < sajtok.size(); i++) {
            Sajt s = sajtok.get(i);
            if (s.getId() == sajt.getId()) {
                sajtok.set(i, sajt);
            }
        }
        try {
            FileWriter out = new FileWriter(dataFile);
            for (Sajt s: sajtok) {
                out.append(s.toString() + "\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSajt(Sajt sajt) {
        ArrayList<Sajt> sajtok = readSajtok();
        Sajt delSajt = null;
        for (Sajt s: sajtok) {
            if (s.getId() == sajt.getId()) {
                delSajt = s;
                break;
            }
        }
        if (delSajt != null) {
            sajtok.remove(delSajt);
            try {
                FileWriter out = new FileWriter(dataFile);
                for (Sajt s: sajtok) {
                    out.append(s.toString() + "\n");
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
