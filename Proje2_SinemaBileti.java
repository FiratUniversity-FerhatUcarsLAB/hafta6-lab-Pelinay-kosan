import java.util.Scanner;

/**
 * Ad Soyad: Pelinay KOŞAN
 * Öğrenci No: 250542002
 * Proje: Sinema Bileti Fiyatlandırma Sistemi
 * Tarih: 27.11.2025
 */

public class Proje2_SinemaBiletSistemi {

    // Gün: 1=Pts, 2=Salı, 3=Çrş, 4=Prş, 5=Cuma, 6=Ctesi, 7=Pazar

    // 1) Hafta sonu mu?
    public static boolean isWeekend(int gun) {
        return (gun == 6) || (gun == 7);
    }

    // 2) Matine mi? (12:00 öncesi)
    public static boolean isMatinee(double saat) {
        return saat < 12.0;
    }

    // 3) Temel fiyatı hesapla
    public static double calculateBasePrice(int gun, double saat) {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (!weekend && matinee) {          // Hafta içi matine
            return 45.0;
        } else if (!weekend && !matinee) {  // Hafta içi normal
            return 65.0;
        } else if (weekend && matinee) {    // Hafta sonu matine
            return 55.0;
        } else {                            // Hafta sonu normal
            return 85.0;
        }
    }

    // 4) İndirim oranı hesapla (sadece en yüksek olanı uyguluyoruz)
    // Öğrenci: %20 (Pzt-Prş) / %15 (Cuma-Pazar)
    // 65+ yaş: %30
    // 12 yaş altı: %25
    // Öğretmen: %35 (sadece Çarşamba)
    public static double calculateDiscount(int yas, int meslek, int gun) {
        double oran = 0.0;

        // Meslek: 1=Öğrenci, 2=Öğretmen, 3=Diğer
        if (meslek == 1) { // Öğrenci
            if (gun >= 1 && gun <= 4) { // Pzt-Prş
                if (oran < 0.20) oran = 0.20;
            } else { // Cuma-Pazar
                if (oran < 0.15) oran = 0.15;
            }
        }

        if (meslek == 2 && gun == 3) { // Öğretmen Çarşamba
            if (oran < 0.35) oran = 0.35;
        }

        // Yaş indirimleri
        if (yas >= 65 && oran < 0.30) {
            oran = 0.30;
        }

        if (yas < 12 && oran < 0.25) {
            oran = 0.25;
        }

        return oran;
    }

    // 5) Film formatı ekstrası
    // 1=2D, 2=3D, 3=IMAX, 4=4DX
    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1:
                return 0.0;   // 2D
            case 2:
                return 25.0;  // 3D
            case 3:
                return 35.0;  // IMAX
            case 4:
                return 50.0;  // 4DX
            default:
                return 0.0;
        }
    }

    // 6) Nihai fiyat hesaplama
    public static double calculateFinalPrice(int gun, double saat, int yas, int meslek, int filmTuru) {
        double basePrice = calculateBasePrice(gun, saat);
        double extra = getFormatExtra(filmTuru);
        double discountRate = calculateDiscount(yas, meslek, gun);

        double araToplam = basePrice + extra;
        double finalPrice = araToplam * (1.0 - discountRate);

        return finalPrice;
    }

    // 7) Bilet bilgisini ekrana yazdır
    public static void generateTicketInfo(String ad, String soyad,
                                          int gun, double saat, int yas,
                                          int meslek, int filmTuru,
                                          double finalPrice) {

        String gunAdi;
        switch (gun) { // GÜN İÇİN ZORUNLU SWITCH-CASE
            case 1: gunAdi = "Pazartesi"; break;
            case 2: gunAdi = "Salı"; break;
            case 3: gunAdi = "Çarşamba"; break;
            case 4: gunAdi = "Perşembe"; break;
            case 5: gunAdi = "Cuma"; break;
            case 6: gunAdi = "Cumartesi"; break;
            case 7: gunAdi = "Pazar"; break;
            default: gunAdi = "Bilinmiyor"; break;
        }

        String meslekAdi;
        switch (meslek) { // MESLEK İÇİN ZORUNLU SWITCH-CASE
            case 1: meslekAdi = "Öğrenci"; break;
            case 2: meslekAdi = "Öğretmen"; break;
            case 3: meslekAdi = "Diğer"; break;
            default: meslekAdi = "Bilinmiyor"; break;
        }

        String formatAdi;
        switch (filmTuru) { // FİLM TÜRÜ İÇİN ZORUNLU SWITCH-CASE
            case 1: formatAdi = "2D"; break;
            case 2: formatAdi = "3D"; break;
            case 3: formatAdi = "IMAX"; break;
            case 4: formatAdi = "4DX"; break;
            default: formatAdi = "Bilinmiyor"; break;
        }

        System.out.println("\n===== SİNEMA BİLETİ =====");
        System.out.printf("Müşteri: %s %s%n", ad, soyad);
        System.out.printf("Yaş    : %d%n", yas);
        System.out.printf("Meslek : %s%n", meslekAdi);
        System.out.printf("Gün    : %s (Kod: %d)%n", gunAdi, gun);
        System.out.printf("Saat   : %.2f%n", saat);
        System.out.printf("Film Formatı: %s%n", formatAdi);
        System.out.printf("Ödenecek Tutar: %.2f TL%n", finalPrice);
        System.out.println("=========================");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Adınızı giriniz: ");
        String ad = input.nextLine();

        System.out.print("Soyadınızı giriniz: ");
        String soyad = input.nextLine();

        System.out.println("Gün seçiniz (1=Pzt, 2=Salı, 3=Çrş, 4=Prş, 5=Cuma, 6=Ctesi, 7=Pazar): ");
        int gun = input.nextInt();

        System.out.print("Film saatini giriniz (örn: 11.30, 14.00): ");
        double saat = input.nextDouble();

        System.out.print("Yaşınızı giriniz: ");
        int yas = input.nextInt();

        System.out.println("Meslek seçiniz: 1=Öğrenci, 2=Öğretmen, 3=Diğer");
        int meslek = input.nextInt();

        System.out.println("Film türü seçiniz: 1=2D, 2=3D, 3=IMAX, 4=4DX");
        int filmTuru = input.nextInt();

        double finalPrice = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);

        generateTicketInfo(ad, soyad, gun, saat, yas, meslek, filmTuru, finalPrice);

        input.close();
    }
}
