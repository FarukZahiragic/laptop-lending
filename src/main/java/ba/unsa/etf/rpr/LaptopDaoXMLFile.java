package ba.unsa.etf.rpr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class LaptopDaoXMLFile implements LaptopDao{
    private File file;
    private ArrayList<Laptop> laptopi;
    @Override
    public void dodajLaptopUListu(Laptop laptop) {
        laptopi.add(laptop);
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) {
        try {
            XmlMapper mapper = new XmlMapper();
            String xml = mapper.writeValueAsString(laptop);
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(xml.getBytes());
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
            XmlMapper xmlMapper = new XmlMapper();
            laptopi = (xmlMapper.readValue(file, new TypeReference<ArrayList<Laptop>>() {}));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFile(String imeFilea) {
        this.file = new File(imeFilea);
    }
}
