/**
*
* Akif Emre Yaman akif.yaman@ogr.sakarya.edu.tr
* 27.04.2025-12.04.2025
* <p>
* simülasyonda yer alan gezegenleri temsil eder.
* Her gezegenin kendine özgü zamanı, gün uzunluğu ve üzerinde
* bulunan kişiler listesi vardır.Çeşitli metotlarla birlikte:
* kisiEkle
* kisiCikar
* getNufus
* simülasyon boyunca üzerlerinde bulunan kişileri ve zamanı yönetir.
* Uzay araçları gezegende beklerken veya varış yaptığında,
* yolcuları gezegenin nüfusuna dahil edilir. 
* Gezegenlerin zamanı, simülasyonun her saatinde güncellenir.
* </p>
*/
package Controller;
import java.util.ArrayList;

public class Gezegen {
    private String ad;
    private int gununSaatSayisi;
    private Zaman zaman;
    private ArrayList<Kisi> kisiler;

    public Gezegen(String ad, int gununSaatSayisi, Zaman zaman) {
        this.ad = ad;
        this.gununSaatSayisi = gununSaatSayisi;
        this.zaman = zaman;
        this.kisiler = new ArrayList<>();
    }

    public void kisiEkle(Kisi kisi) {
        kisiler.add(kisi);
    }

    public void kisiCikar(Kisi kisi) {
        kisiler.remove(kisi);
    }

    public int getNufus() {
        int nufus = 0;
        for (Kisi k : kisiler) {
            if (k.hayattaMi()) nufus++;
        }
        return nufus;
    }

    
    public String getAd() { return ad; }
    public int getGununSaatSayisi() { return gununSaatSayisi; }
    public Zaman getZaman() { return zaman; }
    public ArrayList<Kisi> getKisiler() { return kisiler; }
} 