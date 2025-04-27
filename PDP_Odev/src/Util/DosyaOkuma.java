package Util;

import Model.Kisi;
import Model.UzayAraci;
import Model.Gezegen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DosyaOkuma {
    public static List<Kisi> kisileriOku(String dosyaYolu) throws Exception {
        List<Kisi> kisiler = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parcalar = line.split("#");
                if (parcalar.length == 4) {
                    kisiler.add(new Kisi(
                        parcalar[0],
                        Integer.parseInt(parcalar[1]),
                        Integer.parseInt(parcalar[2]),
                        parcalar[3]
                    ));
                }
            }
        }
        return kisiler;
    }

    public static List<UzayAraci> uzayAraclariOku(String dosyaYolu) throws Exception {
        List<UzayAraci> araclar = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parcalar = line.split("#");
                if (parcalar.length == 5) {
                    araclar.add(new UzayAraci(
                        parcalar[0],
                        parcalar[1],
                        parcalar[2],
                        parcalar[3],
                        Integer.parseInt(parcalar[4])
                    ));
                }
            }
        }
        return araclar;
    }

    public static List<Gezegen> gezegenleriOku(String dosyaYolu) throws Exception {
        List<Gezegen> gezegenler = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parcalar = line.split("#");
                if (parcalar.length == 3) {
                    gezegenler.add(new Gezegen(
                        parcalar[0],
                        Integer.parseInt(parcalar[1]),
                        parcalar[2]
                    ));
                }
            }
        }
        return gezegenler;
    }
}