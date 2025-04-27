package Model;

public class Zaman {
    private String tarih;
    private int gunSaatSayisi;

    public Zaman(String tarih, int gunSaatSayisi) {
        this.tarih = tarih;
        this.gunSaatSayisi = gunSaatSayisi;
    }

    public void zamanIlerle(int saat) {
        // Tarih formatı: DD.MM.YYYY
        String[] tarihParcalari = tarih.split("\\.");
        int gun = Integer.parseInt(tarihParcalari[0]);
        int ay = Integer.parseInt(tarihParcalari[1]);
        int yil = Integer.parseInt(tarihParcalari[2]);

        // Saatleri güne ekle
        gun += saat / gunSaatSayisi;
        saat = saat % gunSaatSayisi;

        // Ay ve yıl hesaplamaları (basitleştirilmiş)
        while (gun > 30) {
            gun -= 30;
            ay++;
            if (ay > 12) {
                ay = 1;
                yil++;
            }
        }

        this.tarih = String.format("%02d.%02d.%04d", gun, ay, yil);
    }

    public String getTarih() {
        return tarih;
    }

    public int getGunSaatSayisi() {
        return gunSaatSayisi;
    }
}