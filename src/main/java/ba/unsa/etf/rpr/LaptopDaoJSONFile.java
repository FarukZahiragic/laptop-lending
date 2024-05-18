package ba.unsa.etf.rpr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class LaptopDaoJSONFile implements LaptopDao{
    private File file;
    private ArrayList<Laptop> laptopi;

    @Override
    public void dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) {
        try {
            ObjectWriter ow = new ObjectMapper().writer();
            String json = null;
            json = ow.writeValueAsString(laptop);
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(json.getBytes());
            fo.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
            ObjectMapper or = new ObjectMapper();
            laptopi = or.readValue(file, new TypeReference<ArrayList<Laptop>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setFile(String imeFilea) {
        this.file = new File(imeFilea);
    }
}
