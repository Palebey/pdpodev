/**
*
* Akif Emre Yaman akif.yaman@ogr.sakarya.edu.tr
* 27.04.2025-12.04.2025
* <p>
* simülasyonda gezegenler arasında yolculuk yapan uzay araçlarını temsil eder.
* Her uzay aracı, çıkış ve varış gezegenleri, yolculuk mesafesi,
* yolcuları ve mevcut durumu gibi bilgilere sahiptir. Çeşitli Metotlara sahiptir.
* yolcuEkle
* yolcuCıkar
* hareketEttir
* varisYap
* imhaEt
* tumYolcularOlmusMu
* Hareket, varış ve imha gibi durum değişiklikleriyle birlikte yolcuların
* yaşam durumunu ve gezegenlere dahil edilip edilmeyeceğini belirler.
* </p>
*/
package Controller;
import java.util.ArrayList;

public class UzayAraci {
    private String ad;
    private Gezegen cikis;
    private Gezegen varis;
    private Zaman cikisTarihi;
    private int mesafe;
    private int hedefeKalanSaat;
    private String durum; // "Bekliyor", "Yolda", "Vardı", "IMHA"
    private ArrayList<Kisi> yolcular;
    private String hedefeVarisTarihi;

    public UzayAraci(String ad, Gezegen cikis, Gezegen varis, Zaman cikisTarihi, int mesafe) {
        this.ad = ad;
        this.cikis = cikis;
        this.varis = varis;
        this.cikisTarihi = cikisTarihi;
        this.mesafe = mesafe;
        this.hedefeKalanSaat = mesafe;
        this.durum = "Bekliyor";
        this.yolcular = new ArrayList<>();
        this.hedefeVarisTarihi = "-";
    }

    public void yolcuEkle(Kisi kisi) {
        yolcular.add(kisi);
    }

    public void yolcuCikar(Kisi kisi) {
        yolcular.remove(kisi);
    }

    public void hareketEttir() {
        durum = "Yolda";
    }

    public void varisYap() {
        durum = "Vardı";
        hedefeKalanSaat = 0;
    }

    public void imhaEt() {
        durum = "IMHA";
        hedefeKalanSaat = -1;
        hedefeVarisTarihi = "--";
    }

    public boolean tumYolcularOlmusMu() {
        for (Kisi k : yolcular) {
            if (k.hayattaMi()) return false;
        }
        return true;
    }

   
    public String getAd() { return ad; }
    public Gezegen getCikis() { return cikis; }
    public Gezegen getVaris() { return varis; }
    public Zaman getCikisTarihi() { return cikisTarihi; }
    public int getMesafe() { return mesafe; }
    public int getHedefeKalanSaat() { return hedefeKalanSaat; }
    public void setHedefeKalanSaat(int saat) { this.hedefeKalanSaat = saat; }
    public String getDurum() { return durum; }
    public ArrayList<Kisi> getYolcular() { return yolcular; }
    public String getHedefeVarisTarihi() { return hedefeVarisTarihi; }
    public void setHedefeVarisTarihi(String tarih) { this.hedefeVarisTarihi = tarih; }
} 