package ba.unsa.etf.rpr;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Laptop hp = new Laptop();
        hp.setBrend("hp");
        hp.setCijena(1000);
        hp.setHdd(500);
        hp.setModel("crnoBijeli");
        hp.setRam(8);
        hp.setGrafickaKartica("gforce 750");
        hp.setProcesor("AMD Radeon");
        hp.setSsd(200);
        hp.setVelicinaEkrana(17);

        LaptopDaoSerializableFile laptopiFile = new LaptopDaoSerializableFile();
        laptopiFile.setFile("laptopi.txt");
        laptopiFile.dodajLaptopUFile(hp);
        laptopiFile.dodajLaptopUListu(hp);
        try {
            Laptop hp2 = laptopiFile.getLaptop("AMD Radeon");
        } catch (NeodgovarajuciProcesorException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Laptop> laptops = new ArrayList<Laptop>();
        laptopiFile.napuniListu(laptops);

        LaptopDaoXMLFile laptopiFile2 = new LaptopDaoXMLFile();
        laptopiFile.setFile("laptopi.xml");
        laptopiFile2.dodajLaptopUFile(hp);
        laptopiFile2.dodajLaptopUListu(hp);
        try {
            Laptop hp2 = laptopiFile2.getLaptop("AMD Radeon");
        } catch (NeodgovarajuciProcesorException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Laptop> laptops2 = new ArrayList<Laptop>();
        laptopiFile2.napuniListu(laptops);

        LaptopDaoJSONFile laptopiFile3 = new LaptopDaoJSONFile();
        laptopiFile3.setFile("laptopi.json");
        laptopiFile3.dodajLaptopUFile(hp);
        laptopiFile3.dodajLaptopUListu(hp);
        try {
            Laptop hp2 = laptopiFile3.getLaptop("AMD Radeon");
        } catch (NeodgovarajuciProcesorException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Laptop> laptops3 = new ArrayList<Laptop>();
        laptopiFile3.napuniListu(laptops);
    }
}
