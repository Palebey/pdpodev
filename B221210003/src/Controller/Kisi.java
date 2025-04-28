/**
*
* Akif Emre Yaman akif.yaman@ogr.sakarya.edu.tr
* 27.04.2025-12.04.2025
* <p>
* simülasyonda uzay araçlarında yolculuk eden veya gezegenlerde bulunan
* bireyleri temsil eder. Her bir kişi, yaş,
* kalan ömür ve bulunduğu uzay aracı gibi temel bilgilere sahiptir. 
* Çeşitli metotlar kullanır
* omurAzalt
* hayattaMi
* simülasyon boyunca uzay araçlarında veya gezegenlerde bulunabilirler.
* Her saat başı ömürleri azalır, ölenler gezegen veya araç nüfusundan
* çıkarılır. Kişiler, bulundukları
* uzay aracının durumuna göre gezegen nüfusuna dahil edilir veya edilmezler.
* </p>
*/
package Controller;
public class Kisi {
    private String isim;
    private int yas;
    private int kalanOmur;
    private UzayAraci uzayAraci;

    public Kisi(String isim, int yas, int kalanOmur, UzayAraci uzayAraci) {
        this.isim = isim;
        this.yas = yas;
        this.kalanOmur = kalanOmur;
        this.uzayAraci = uzayAraci;
    }

    public void omurAzalt() {
        if (kalanOmur > 0) kalanOmur--;
    }

    public boolean hayattaMi() {
        return kalanOmur > 0;
    }

  
    public String getIsim() { return isim; }
    public int getYas() { return yas; }
    public int getKalanOmur() { return kalanOmur; }
    public UzayAraci getUzayAraci() { return uzayAraci; }
    public void setUzayAraci(UzayAraci uzayAraci) { this.uzayAraci = uzayAraci; }
} 