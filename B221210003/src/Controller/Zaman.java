/**
*
* Akif Emre Yaman akif.yaman@ogr.sakarya.edu.tr
* 27.04.2025-12.04.2025
* <p>
* Her gezegenin kendine özgü tarih ve saatini yönetmek için tasarlanmıştır.
* Simülasyonda her gezegenin zamanı farklı ilerleyebildiği için,
* bu sınıf gezegenlerin gün, ay, yıl ve saat bilgisini tutar ve günceller.
* Metotlar
* ileriSaat
* esitMi
* tarihString
* saatliTarihString
* Her gezegenin kendi zamanını yönetmek, uzay araçlarının çıkış
* ve varış tarihlerini kontrol etmek ve
* simülasyonun zaman akışını doğru şekilde ilerletmek için kullanılır.
* </p>
*/
package Controller;
public class Zaman {
    private int gun;
    private int ay;
    private int yil;
    private int saat;
    private int gununSaatSayisi;

    public Zaman(int gun, int ay, int yil, int gununSaatSayisi) {
        this.gun = gun;
        this.ay = ay;
        this.yil = yil;
        this.saat = 0;
        this.gununSaatSayisi = gununSaatSayisi;
    }

    public void ileriSaat() {
        saat++;
        if (saat >= gununSaatSayisi) {
            saat = 0;
            gun++;
            if (gun > 30) { 
                gun = 1;
                ay++;
                if (ay > 12) {
                    ay = 1;
                    yil++;
                }
            }
        }
    }

    public boolean esitMi(Zaman diger) {
        return this.gun == diger.gun && this.ay == diger.ay && this.yil == diger.yil;
    }

    public String tarihString() {
        return String.format("%02d.%02d.%04d", gun, ay, yil);
    }

    public String saatliTarihString() {
        return String.format("%02d.%02d.%04d %02d:00", gun, ay, yil, saat);
    }

    // Getter ve setterlar
    public int getGun() { return gun; }
    public int getAy() { return ay; }
    public int getYil() { return yil; }
    public int getSaat() { return saat; }
    public int getGununSaatSayisi() { return gununSaatSayisi; }
    public void setSaat(int saat) { this.saat = saat; }
    public void setGun(int gun) { this.gun = gun; }
    public void setAy(int ay) { this.ay = ay; }
    public void setYil(int yil) { this.yil = yil; }
    public void setGununSaatSayisi(int gununSaatSayisi) { this.gununSaatSayisi = gununSaatSayisi; }
} 