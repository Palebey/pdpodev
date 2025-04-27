package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Gezegen {
    private String adi;
    private Zaman zaman;
    private List<Kisi> nufus;
    private List<UzayAraci> uzayAraclari;

    public Gezegen(String adi, int gunSaatSayisi, String tarih) {
        this.adi = adi;
        this.zaman = new Zaman(tarih, gunSaatSayisi);
        this.nufus = new ArrayList<>();
        this.uzayAraclari = new ArrayList<>();
    }

    // Nüfus işlemleri
    public List<Kisi> getNufus() {
        return nufus;
    }

    public void kisiEkle(Kisi kisi) {
        nufus.add(kisi);
    }

    public void kisiCikar(Kisi kisi) {
        nufus.remove(kisi);
    }

    public int getNufusSayisi() {
        return nufus.size();
    }

    // Uzay araçları işlemleri
    public List<UzayAraci> getUzayAraclari() {
        return uzayAraclari;
    }

    public void uzayAraciEkle(UzayAraci arac) {
        uzayAraclari.add(arac);
    }

    public List<UzayAraci> bekleyenAraclariGetir() {
        return uzayAraclari.stream()
                .filter(a -> a.getDurum() == UzayAraci.Durum.BEKLIYOR)
                .collect(Collectors.toList());
    }

    // Zaman işlemleri
    public void zamanIlerle(double saat) {
        zaman.zamanIlerle(saat);
    }

    // Getter'lar
    public String getAdi() {
        return adi;
    }

    public Zaman getZaman() {
        return zaman;
    }
}