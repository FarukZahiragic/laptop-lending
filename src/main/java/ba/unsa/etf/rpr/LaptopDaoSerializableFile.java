package ba.unsa.etf.rpr;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LaptopDaoSerializableFile implements LaptopDao {
    private File file;
    private ArrayList<Laptop> laptopi;


    LaptopDaoSerializableFile() {
        file = new File("");
        laptopi = new ArrayList<Laptop>();
    }
    @Override
    public void dodajLaptopUListu(Laptop laptop) {
        if(laptopi.isEmpty()) laptopi = new ArrayList<Laptop>();
        laptopi.add(laptop);
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) {
        try {
            ObjectOutputStream izlaz = new ObjectOutputStream(Files.newOutputStream(file.toPath()));
            izlaz.writeObject(laptop);
            izlaz.close();
        }
        catch (Exception e) {
            System.out.println("Greska u serijalizaciji: " + e);
        }
    }

    @Override
    public Laptop getLaptop(String procesor) throws NeodgovarajuciProcesorException {
        for (Laptop laptop : laptopi) if (laptop.getProcesor().equals(procesor)) return laptop;
        throw new NeodgovarajuciProcesorException(procesor);
    }

    @Override
    public void napuniListu(ArrayList<Laptop> laptopi) {
        this.laptopi = laptopi;
    }

    @Override
    public void vratiPodatkeIzDatoteke() {
        try {
            ObjectInputStream ulaz = new ObjectInputStream(Files.newInputStream(file.toPath()));
            while (ulaz.available() != 0) laptopi.add((Laptop) ulaz.readObject());
            ulaz.close();
        }
        catch (Exception e) {
            System.out.println("Greska: " + e);
        }
    }

    public void setFile(String imeFilea) {
        this.file = new File(imeFilea);
    }


}
