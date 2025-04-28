/**
*
* Akif Emre Yaman akif.yaman@ogr.sakarya.edu.tr
* 27.04.2025-12.04.2025
* <p>
* zayda gezegenler ve uzay araçları arasında geçen yolculukların ve 
* olayların ana akışını yöneten, projenin merkezi kontrol birimidir.
* Tüm veri okuma, zaman ilerletme,
* nüfus güncelleme ve çıktı işlemleri bu sınıf üzerinden yürütülür.
* Sahip olduğu bazı metotlar
* baslat
* saatIlerle
* guncelNufuslariGuncelle
* ekranYazdir
* dosyayaYaz
* tumAraclarVardi
*  Zamanı ilerletir, olayları tetikler, nüfus ve ömür güncellemelerini yapar,
*  sonuçları hem ekrana hem de dosyaya yazar.
*  Projenin ana motoru ve kontrol merkezidir.
* </p>
*/


package Controller;

import java.util.*;
import java.io.*;

public class Simulasyon {
    private ArrayList<Gezegen> gezegenler;
    private ArrayList<UzayAraci> araclar;
    private ArrayList<Kisi> kisiler;
    private StringBuilder sonucCiktisi = new StringBuilder();
    private int saatSayaci = 0;

    public void baslat() {
        try {
            gezegenler = DosyaOkuma.gezegenleriOku("data/Gezegenler.txt");
            araclar = DosyaOkuma.araclariOku("data/Araclar.txt", gezegenler);
            kisiler = DosyaOkuma.kisileriOku("data/Kisiler.txt", araclar);
        } catch (Exception e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
            return;
        }

        boolean devam = true;
        while (devam) {
            saatSayaci++;
            guncelNufuslariGuncelle();
            ekranYazdir();
            saatIlerle();
            devam = !tumAraclarVardi();
            try { Thread.sleep(20); } catch (InterruptedException e) {}
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        guncelNufuslariGuncelle();
        ekranYazdir();
        System.out.println("Simülasyon tamamlandı.");
        sonucCiktisi.append("Simülasyon tamamlandı.\n");
        dosyayaYaz();
    }

    private void guncelNufuslariGuncelle() {
       
        for (Gezegen g : gezegenler) {
            g.getKisiler().clear();
        }
      
        for (Kisi k : kisiler) {
            if (!k.hayattaMi()) continue;
            UzayAraci arac = k.getUzayAraci();
            if (arac == null) continue;
          
            if (arac.getDurum().equals("Bekliyor")) {
                arac.getCikis().kisiEkle(k);
            } else if (arac.getDurum().equals("Vardı")) {
                arac.getVaris().kisiEkle(k);
            }
            
        }
    }

    private void saatIlerle() {
        for (Gezegen g : gezegenler) {
            g.getZaman().ileriSaat();
        }
        for (Kisi k : kisiler) {
            k.omurAzalt();
        }
        for (UzayAraci a : araclar) {
            if (a.getDurum().equals("IMHA") || a.getDurum().equals("Vardı")) continue;
            Gezegen cikis = a.getCikis();
            if (a.getDurum().equals("Bekliyor") && cikis.getZaman().esitMi(a.getCikisTarihi())) {
                a.hareketEttir();
                a.setHedefeKalanSaat(a.getMesafe());
            }
            if (a.getDurum().equals("Yolda")) {
                a.setHedefeKalanSaat(a.getHedefeKalanSaat() - 1);
                if (a.getHedefeKalanSaat() <= 0) {
                    a.varisYap();
                    a.setHedefeVarisTarihi(a.getVaris().getZaman().tarihString());
                }
            }
            if (a.tumYolcularOlmusMu()) {
                a.imhaEt();
            }
        }
    }

    private boolean tumAraclarVardi() {
        for (UzayAraci a : araclar) {
            if (!a.getDurum().equals("Vardı") && !a.getDurum().equals("IMHA")) return false;
        }
        return true;
    }

    private void ekranYazdir() {
       
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
           
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Saat: ").append(saatSayaci).append("\n\n");
        sb.append("Gezegenler:\n\n");
        for (Gezegen g : gezegenler) {
            sb.append(String.format("--- %s ---\nTarih   %s\nNüfus   %d\n\n", g.getAd(), g.getZaman().tarihString(), g.getNufus()));
        }
        sb.append("Uzay Araclari:\n");
        sb.append("Araç Adı  Durum     Çıkış  Varış  Hedefe Kalan Saat  Hedefe Varacağı Tarih\n");
        for (UzayAraci a : araclar) {
            String kalanSaat = a.getDurum().equals("IMHA") ? "--" : (a.getDurum().equals("Vardı") ? "0" : String.valueOf(a.getHedefeKalanSaat()));
            String varisTarih = a.getDurum().equals("IMHA") ? "--" : (a.getDurum().equals("Vardı") ? a.getHedefeVarisTarihi() : "-");
            sb.append(String.format("%-8s %-9s %-6s %-6s %-18s %-22s\n",
                a.getAd(), a.getDurum(), a.getCikis().getAd(), a.getVaris().getAd(), kalanSaat, varisTarih));
        }
        sb.append("\n");
        System.out.print(sb.toString());
        sonucCiktisi.append(sb);
    }

    private void dosyayaYaz() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("sonuc.txt"))) {
            writer.print(sonucCiktisi.toString());
        } catch (IOException e) {
            System.out.println("sonuc.txt dosyasına yazılamadı: " + e.getMessage());
        }
    }
} 