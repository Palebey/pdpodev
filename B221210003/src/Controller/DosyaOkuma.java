/**
*
* Akif Emre Yaman akif.yaman@ogr.sakarya.edu.tr
* 27.04.2025-12.04.2025
* <p>
* Simülasyonda kullanılacak olan kişi,uzayaracı ve gezegen
* verilerini txt dosyalarından okuyup uygun nesneye
* dönüştürür. Eğer hata varsa uyarı hata mesajı verir
* </p>
*/
package Controller;
import java.util.*;
import java.io.*;

public class DosyaOkuma {
    public static ArrayList<Gezegen> gezegenleriOku(String dosyaAdi) throws Exception {
        ArrayList<Gezegen> gezegenler = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(dosyaAdi));
        String satir;
        while ((satir = br.readLine()) != null) {
            String[] parca = satir.split("#");
            String ad = parca[0];
            int saatSayisi = Integer.parseInt(parca[1]);
            String[] tarih = parca[2].split("\\.");
            int gun = Integer.parseInt(tarih[0]);
            int ay = Integer.parseInt(tarih[1]);
            int yil = Integer.parseInt(tarih[2]);
            Zaman zaman = new Zaman(gun, ay, yil, saatSayisi);
            gezegenler.add(new Gezegen(ad, saatSayisi, zaman));
        }
        br.close();
        return gezegenler;
    }

    public static ArrayList<UzayAraci> araclariOku(String dosyaAdi, ArrayList<Gezegen> gezegenler) throws Exception {
        ArrayList<UzayAraci> araclar = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(dosyaAdi));
        String satir;
        while ((satir = br.readLine()) != null) {
            String[] parca = satir.split("#");
            String ad = parca[0];
            Gezegen cikis = null, varis = null;
            for (Gezegen g : gezegenler) {
                if (g.getAd().equals(parca[1])) cikis = g;
                if (g.getAd().equals(parca[2])) varis = g;
            }
            String[] tarih = parca[3].split("\\.");
            int gun = Integer.parseInt(tarih[0]);
            int ay = Integer.parseInt(tarih[1]);
            int yil = Integer.parseInt(tarih[2]);
            Zaman cikisTarihi = new Zaman(gun, ay, yil, cikis.getGununSaatSayisi());
            int mesafe = Integer.parseInt(parca[4]);
            araclar.add(new UzayAraci(ad, cikis, varis, cikisTarihi, mesafe));
        }
        br.close();
        return araclar;
    }

    public static ArrayList<Kisi> kisileriOku(String dosyaAdi, ArrayList<UzayAraci> araclar) throws Exception {
        ArrayList<Kisi> kisiler = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(dosyaAdi));
        String satir;
        while ((satir = br.readLine()) != null) {
            String[] parca = satir.split("#");
            String isim = parca[0];
            int yas = Integer.parseInt(parca[1]);
            int kalanOmur = Integer.parseInt(parca[2]);
            UzayAraci arac = null;
            for (UzayAraci a : araclar) {
                if (a.getAd().equals(parca[3])) arac = a;
            }
            Kisi kisi = new Kisi(isim, yas, kalanOmur, arac);
            kisiler.add(kisi);
            if (arac != null) arac.yolcuEkle(kisi);
        }
        br.close();
        return kisiler;
    }
} 