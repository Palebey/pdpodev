package Controller;

import Model.*;
import Util.DosyaOkuma;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class Simulasyon {
    private List<Gezegen> gezegenler;
    private List<UzayAraci> uzayAraclari;
    private List<Kisi> kisiler;
    private int simülasyonSaati;

    public void baslat() throws Exception {
        // Dosyaları oku
        kisiler = DosyaOkuma.kisileriOku("data/Kisiler.txt");
        uzayAraclari = DosyaOkuma.uzayAraclariOku("data/Araclar.txt");
        gezegenler = DosyaOkuma.gezegenleriOku("data/Gezegenler.txt");

        // Yolcuları uzay araçlarına yerleştir
        yolculariAta();

        // Ana döngü
        while (!tumAraclarHedefeUlasti()) {
            simülasyonSaati++;
            zamanIlerlet();
            omurKontrol();
            araclariGuncelle();
            konsoluGoster();
            sonucuDosyayaYaz("data/sonuc.txt");
            Thread.sleep(1); // 1 saniye bekle (opsiyonel)
        }
        System.out.println("SIMÜLASYON TAMAMLANDI!");
    }

    private void yolculariAta() {
        for (UzayAraci arac : uzayAraclari) {
            List<Kisi> aracYolculari = kisiler.stream()
                .filter(k -> k.getBulunduguUzayAraci().equals(arac.getAdi()))
                .collect(Collectors.toList());
            arac.getYolcular().addAll(aracYolculari);
        }
    }

    private boolean tumAraclarHedefeUlasti() {
        return uzayAraclari.stream().allMatch(a -> 
            a.getDurum() == UzayAraci.Durum.VARDI || 
            a.getDurum() == UzayAraci.Durum.IMHA
        );
    }

    private void zamanIlerlet() {
        gezegenler.forEach(g -> g.getZaman().zamanIlerle(1));
    }

    private void omurKontrol() {
        kisiler.forEach(k -> {
            if (k.omurAzalt()) {
                System.out.println("[ÖLÜM] " + k.getIsim() + " öldü!");
            }
        });

        // İmha kontrolü
        uzayAraclari.forEach(arac -> {
            if (arac.getYolcular().stream().allMatch(k -> k.getKalanOmur() <= 0)) {
                arac.setDurum(UzayAraci.Durum.IMHA);
                System.out.println("[İMHA] " + arac.getAdi() + " imha edildi!");
            }
        });
    }

    private void araclariGuncelle() {
        uzayAraclari.forEach(arac -> {
            // Kalkış kontrolü
            if (arac.getDurum() == UzayAraci.Durum.BEKLIYOR) {
                Gezegen bulunduguGezegen = gezegenler.stream()
                    .filter(g -> g.getAdi().equals(arac.getCikisGezegeni()))
                    .findFirst()
                    .orElse(null);
                if (bulunduguGezegen != null && 
                    bulunduguGezegen.getZaman().getTarih().equals(arac.getCikisTarihi())) {
                    arac.hareketEt();
                    System.out.println("[KALKIS] " + arac.getAdi() + " yola çıktı!");
                }
            }
            // Yolda ilerleme
            arac.ilerle();
        });
    }

    private void konsoluGoster() {
        System.out.print("\033[H\033[2J"); // Konsolu temizle (Linux/Windows uyumlu)
        System.out.println("=== GEZEGENLER ===");
        gezegenler.forEach(g -> {
            System.out.printf("%-10s Tarih: %-12s Nüfus: %d\n",
                g.getAdi(), g.getZaman().getTarih(), g.getNufus().size());
        });

        System.out.println("\n=== UZAY ARAÇLARI ===");
        System.out.printf("%-10s %-10s %-10s %-10s %-20s %s\n",
            "Araç Adı", "Durum", "Çıkış", "Varış", "Kalan Saat", "Varış Tarihi");
        uzayAraclari.forEach(arac -> {
            String kalanSaat = (arac.getDurum() == UzayAraci.Durum.IMHA) ? "--" : 
                String.valueOf(arac.getHedefeKalanSaat());
            String varisTarihi = (arac.getDurum() == UzayAraci.Durum.IMHA) ? "--" : 
                "Hesaplanıyor..."; // Gerçek projede tarih hesaplanacak
            System.out.printf("%-10s %-10s %-10s %-10s %-20s %s\n",
                arac.getAdi(), arac.getDurum(), arac.getCikisGezegeni(),
                arac.getVarisGezegeni(), kalanSaat, varisTarihi);
        });
        System.out.println("\nGeçen Süre: " + simülasyonSaati + " saat");
    }

    private void sonucuDosyayaYaz(String dosyaYolu) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("=== SIMÜLASYON ADIMI ===\n");
        sb.append("Gezegenler:\n");
        gezegenler.forEach(g -> sb.append(g.getAdi()).append(": ").append(g.getZaman().getTarih()).append("\n"));
        sb.append("\nUzay Araçları:\n");
        uzayAraclari.forEach(a -> sb.append(a.getAdi()).append(": ").append(a.getDurum()).append("\n"));
        sb.append("\n----------------\n");

        Files.write(Paths.get(dosyaYolu), sb.toString().getBytes(), 
            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static void main(String[] args) {
        try {
            new Simulasyon().baslat();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}