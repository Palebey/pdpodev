package Model;

public class Kisi {
    private String isim;
    private int yas;
    private int kalanOmur;
    private String bulunduguUzayAraciAdi;

    public Kisi(String isim, int yas, int kalanOmur, String bulunduguUzayAraciAdi) {
        if (isim == null || isim.trim().isEmpty()) {
            throw new IllegalArgumentException("İsim boş olamaz");
        }
        if (yas <= 0) {
            throw new IllegalArgumentException("Yaş pozitif olmalı");
        }
        if (kalanOmur < 0) {
            throw new IllegalArgumentException("Kalan ömür negatif olamaz");
        }

        this.isim = isim;
        this.yas = yas;
        this.kalanOmur = kalanOmur;
        this.bulunduguUzayAraciAdi = bulunduguUzayAraciAdi;
    }

    public boolean omurAzalt() {
        if (kalanOmur > 0) {
            kalanOmur--;
            return kalanOmur == 0;
        }
        return false;
    }

    // Durum kontrol metodları
    public boolean olduMu() {
        return kalanOmur <= 0;
    }

    // Getter'lar
    public String getIsim() {
        return isim;
    }

    public int getYas() {
        return yas;
    }

    public int getKalanOmur() {
        return kalanOmur;
    }

    public String getBulunduguUzayAraci() {
        return bulunduguUzayAraciAdi;
    }

    // Setter (sadece değişebilen alanlar için)
    public void setBulunduguUzayAraci(String yeniArac) {
        this.bulunduguUzayAraciAdi = yeniArac;
    }

    @Override
    public String toString() {
        return String.format("%s (Yaş: %d, Kalan Ömür: %d, Araç: %s)",
                isim, yas, kalanOmur, bulunduguUzayAraciAdi);
    }
}