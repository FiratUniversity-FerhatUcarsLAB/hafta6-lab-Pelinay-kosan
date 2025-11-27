import java.util.Scanner;

/**
 * Ad soyad : Pelinay KOŞAN
 * Öğrenci No:250542002
 * Proje: Öğrenci Not Değerlendirme Sistemi
 * Tarih: 27.11.2025
 */

public class Proje1_OgrenciNotSistemi {

    // 1) Ortalama: Vize %30 + Final %40 + Ödev %30
    public static double calculateAverage(double vize, double fin, double odev) {
        double ortalama = vize * 0.30 + fin * 0.40 + odev * 0.30;
        return ortalama;
    }

    // 2) Geçme durumu (ortalama >= 50)
    public static boolean isPassingGrade(double ortalama) {
        return ortalama >= 50.0;
    }

    // 3) Harf notu (A-F arası tek karakter)
    public static char getLetterGrade(double ortalama) {
        if (ortalama >= 90) {
            return 'A';
        } else if (ortalama >= 80) {
            return 'B';
        } else if (ortalama >= 70) {
            return 'C';
        } else if (ortalama >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    // 4) Onur listesi: ort >= 85 VE tüm notlar >= 70
    public static boolean isHonorList(double ortalama, double vize, double fin, double odev) {
        boolean tumNotlarYeterli = (vize >= 70.0) && (fin >= 70.0) && (odev >= 70.0);
        return (ortalama >= 85.0) && tumNotlarYeterli;
    }

    // 5) Bütünleme hakkı: 40 <= ort < 50
    public static boolean hasRetakeRight(double ortalama) {
        return (ortalama >= 40.0) && (ortalama < 50.0);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Öğrencinin adını giriniz: ");
        String ad = input.nextLine();

        System.out.print("Öğrencinin soyadını giriniz: ");
        String soyad = input.nextLine();

        System.out.print("Vize notunu giriniz (0-100): ");
        double vize = input.nextDouble();

        System.out.print("Final notunu giriniz (0-100): ");
        double fin = input.nextDouble();

        System.out.print("Ödev notunu giriniz (0-100): ");
        double odev = input.nextDouble();

        double ortalama = calculateAverage(vize, fin, odev);
        boolean gectiMi = isPassingGrade(ortalama);
        char harfNotu = getLetterGrade(ortalama);
        boolean onur = isHonorList(ortalama, vize, fin, odev);
        boolean butunleme = hasRetakeRight(ortalama);

        System.out.println("\n===== ÖĞRENCİ NOT RAPORU =====");
        System.out.printf("Öğrenci: %s %s%n", ad, soyad);
        System.out.printf("Vize Notu : %.2f%n", vize);
        System.out.printf("Final Notu: %.2f%n", fin);
        System.out.printf("Ödev Notu : %.2f%n", odev);
        System.out.printf("Ortalama  : %.2f%n", ortalama);
        System.out.printf("Harf Notu : %c%n", harfNotu);

        if (gectiMi) {
            System.out.println("Durum     : GEÇTİ");
        } else {
            System.out.println("Durum     : KALDI");
        }

        if (onur) {
            System.out.println("Onur Listesi: EVET");
        } else {
            System.out.println("Onur Listesi: HAYIR");
        }

        if (butunleme) {
            System.out.println("Bütünleme Hakkı: VAR");
        } else {
            System.out.println("Bütünleme Hakkı: YOK");
        }

        System.out.println("===== RAPOR BİTTİ =====");

        input.close();
    }
}
