package Model;

import java.util.ArrayList;
import java.util.List;

public class UzayAraci {
    public enum Durum {
        BEKLIYOR,
        YOLDA,
        VARDI,
        IMHA
    }

    private String adi;
    private String cikisGezegeni;
    private String varisGezegeni;
    private String cikisTarihi;
    private int mesafe;
    private Durum durum;
    private List<Kisi> yolcular;
    private int hedefeKalanSaat;

    public UzayAraci(String adi, String cikisGezegeni, String varisGezegeni, 
                    String cikisTarihi, int mesafe) {
        this.adi = adi;
        this.cikisGezegeni = cikisGezegeni;
        this.varisGezegeni = varisGezegeni;
        this.cikisTarihi = cikisTarihi;
        this.mesafe = mesafe;
        this.durum = Durum.BEKLIYOR;
        this.yolcular = new ArrayList<>();
        this.hedefeKalanSaat = mesafe;
    }

    public void hareketEt() {
        if (durum == Durum.BEKLIYOR) {
            durum = Durum.YOLDA;
        }
    }

    public void ilerle() {
        if (durum == Durum.YOLDA && hedefeKalanSaat > 0) {
            hedefeKalanSaat--;
            if (hedefeKalanSaat == 0) {
                durum = Durum.VARDI;
            }
        }
    }

    public void yolcuEkle(Kisi kisi) {
        yolcular.add(kisi);
    }

    public String getAdi() {
        return adi;
    }

    public String getCikisGezegeni() {
        return cikisGezegeni;
    }

    public String getVarisGezegeni() {
        return varisGezegeni;
    }

    public String getCikisTarihi() {
        return cikisTarihi;
    }

    public int getMesafe() {
        return mesafe;
    }

    public Durum getDurum() {
        return durum;
    }

    public void setDurum(Durum durum) {
        this.durum = durum;
    }

    public List<Kisi> getYolcular() {
        return yolcular;
    }

    public int getHedefeKalanSaat() {
        return hedefeKalanSaat;
    }
}