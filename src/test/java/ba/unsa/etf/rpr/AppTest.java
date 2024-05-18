package ba.unsa.etf.rpr;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    @Test
    public void testSerijalizacija() throws NeodgovarajuciProcesorException {
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
        assertEquals(hp, laptopiFile.getLaptop("AMD Radeon"));
    }

    @Test
    public void testXML() throws NeodgovarajuciProcesorException {
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

        LaptopDaoXMLFile laptopiFile = new LaptopDaoXMLFile();
        laptopiFile.dodajLaptopUFile(hp);
        laptopiFile.setFile("laptopi.json");
        laptopiFile.dodajLaptopUListu(hp);
        try {
            Laptop hp2 = laptopiFile.getLaptop("AMD Radeon");
        } catch (NeodgovarajuciProcesorException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Laptop> laptops3 = new ArrayList<Laptop>();
        laptopiFile.napuniListu(laptops3);
        assertEquals(hp, laptopiFile.getLaptop("AMD Radeon"));
    }

    @Test
    public void testJSON() throws NeodgovarajuciProcesorException {
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

        LaptopDaoJSONFile laptopiFile = new LaptopDaoJSONFile();
        laptopiFile.dodajLaptopUFile(hp);
        laptopiFile.setFile("laptopi.json");
        laptopiFile.dodajLaptopUListu(hp);
        try {
            Laptop hp2 = laptopiFile.getLaptop("AMD Radeon");
        } catch (NeodgovarajuciProcesorException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Laptop> laptops3 = new ArrayList<Laptop>();
        laptopiFile.napuniListu(laptops3);
        assertEquals(hp, laptopiFile.getLaptop("AMD Radeon"));
    }

    @Test
    public void testMockLaptop()
    {
        // Mocking se ovdje koristi cisto da vidimo kako bi radila klasa u odstustvu unesenih podataka
        Laptop hp = (Laptop) Mockito.mock(Laptop.class);
        hp.setBrend("hp");
        hp.setCijena(1000);
        hp.setModel("crnoBijeli");
        hp.setRam(8);
        hp.setGrafickaKartica("gforce 750");
        hp.setProcesor("AMD Radeon");
        hp.setSsd(200);
        hp.setVelicinaEkrana(17);
        Mockito.when(hp.getHdd()).thenReturn(0);
        assertEquals(hp.getHdd(), 0);
    }
}
