import java.util.Scanner;

/**
 * Ad Soyad: Pelinay KOŞAN
 * Öğrenci No: 250542002
 * Proje: Akıllı Restoran Sipariş Sistemi
 * Tarih: 27.11.2025
 */

public class Proje3_RestoranSiparisSistemi {

    // 1) Ana yemek fiyatı (switch-case ZORUNLU)
    // 1=Izgara Tavuk (85₺), 2=Adana Kebap (120₺),
    // 3=Levrek (110₺), 4=Mantı (65₺), 0=Yok
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85.0;
            case 2: return 120.0;
            case 3: return 110.0;
            case 4: return 65.0;
            case 0: return 0.0;
            default: return 0.0;
        }
    }

    // 2) Başlangıç fiyatı (switch-case ZORUNLU)
    // 1=Çorba (25₺), 2=Humus (45₺), 3=Sigara Böreği (55₺), 0=Yok
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25.0;
            case 2: return 45.0;
            case 3: return 55.0;
            case 0: return 0.0;
            default: return 0.0;
        }
    }

    // 3) İçecek fiyatı (switch-case ZORUNLU)
    // 1=Kola (15₺), 2=Ayran (12₺), 3=Meyve Suyu (35₺), 4=Limonata (25₺), 0=Yok
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15.0;
            case 2: return 12.0;
            case 3: return 35.0;
            case 4: return 25.0;
            case 0: return 0.0;
            default: return 0.0;
        }
    }

    // 4) Tatlı fiyatı (switch-case ZORUNLU)
    // 1=Künefe (65₺), 2=Baklava (55₺), 3=Sütlaç (35₺), 0=Yok
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65.0;
            case 2: return 55.0;
            case 3: return 35.0;
            case 0: return 0.0;
            default: return 0.0;
        }
    }

    // 5) Kombin menü kontrolü (Ana + İçecek + Tatlı var mı?)
    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    // 6) Happy Hour (14:00-17:00) kontrolü
    public static boolean isHappyHour(double saat) {
        return (saat >= 14.0) && (saat <= 17.0);
    }

    // 7) İndirim hesaplama
    // - combo = true ise %15
    // - tutar >= 200 ise %10
    // - ogrenciHaftaIci = true ise %10
    // Not: Saat parametresini imza gereği alıyoruz, içerde kullanmak zorunlu değil.
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenciHaftaIci, double saat) {
        double indirimOrani = 0.0;

        if (combo) {
            indirimOrani += 0.15;
        }

        if (tutar >= 200.0) {
            indirimOrani += 0.10;
        }

        if (ogrenciHaftaIci) {
            indirimOrani += 0.10;
        }

        double indirimTutar = tutar * indirimOrani;
        return indirimTutar;
    }

    // 8) Bahşiş hesaplama: %10
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Sipariş saati (örn: 13.30, 15.00): ");
        double saat = input.nextDouble();

        System.out.println("\nAna Yemekler: 1=Izgara Tavuk, 2=Adana Kebap, 3=Levrek, 4=Mantı, 0=Yok");
        System.out.print("Ana yemek seçiminiz: ");
        int anaSecim = input.nextInt();

        System.out.println("\nBaşlangıçlar: 1=Çorba, 2=Humus, 3=Sigara Böreği, 0=Yok");
        System.out.print("Başlangıç seçiminiz: ");
        int baslangicSecim = input.nextInt();

        System.out.println("\nİçecekler: 1=Kola, 2=Ayran, 3=Meyve Suyu, 4=Limonata, 0=Yok");
        System.out.print("İçecek seçiminiz: ");
        int icecekSecim = input.nextInt();

        System.out.println("\nTatlılar: 1=Künefe, 2=Baklava, 3=Sütlaç, 0=Yok");
        System.out.print("Tatlı seçiminiz: ");
        int tatliSecim = input.nextInt();

        System.out.print("\nÖğrenci misiniz ve sipariş günü hafta içi mi? (E/H): ");
        char ogrenciHaftaIciCevap = 'H';
        // önceki nextInt'ten kalan satırı bozmasın diye:
        input.nextLine();
        String satir = input.nextLine();
        if (satir.length() > 0) {
            ogrenciHaftaIciCevap = satir.charAt(0);
        }

        boolean ogrenciHaftaIci = (ogrenciHaftaIciCevap == 'E' || ogrenciHaftaIciCevap == 'e');

        double anaFiyat = getMainDishPrice(anaSecim);
        double baslangicFiyat = getAppetizerPrice(baslangicSecim);
        double icecekFiyat = getDrinkPrice(icecekSecim);
        double tatliFiyat = getDessertPrice(tatliSecim);

        boolean anaVar = anaFiyat > 0.0;
        boolean icecekVar = icecekFiyat > 0.0;
        boolean tatliVar = tatliFiyat > 0.0;

        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);

        double toplam = anaFiyat + baslangicFiyat + icecekFiyat + tatliFiyat;

        // Happy Hour indirimi sadece içeceklerde %20
        double happyHourIndirimi = 0.0;
        if (isHappyHour(saat) && icecekFiyat > 0.0) {
            happyHourIndirimi = icecekFiyat * 0.20;
        }

        double araTutar = toplam - happyHourIndirimi;

        double digerIndirim = calculateDiscount(araTutar, combo, ogrenciHaftaIci, saat);

        double indirimSonrasiTutar = araTutar - digerIndirim;

        double bahsis = calculateServiceTip(indirimSonrasiTutar);

        double odenecekTutar = indirimSonrasiTutar + bahsis;

        System.out.println("\n===== SİPARİŞ ÖZETİ =====");
        System.out.printf("Ana Yemek Tutarı     : %.2f TL%n", anaFiyat);
        System.out.printf("Başlangıç Tutarı     : %.2f TL%n", baslangicFiyat);
        System.out.printf("İçecek Tutarı        : %.2f TL%n", icecekFiyat);
        System.out.printf("Tatlı Tutarı         : %.2f TL%n", tatliFiyat);
        System.out.printf("Toplam (ilk)         : %.2f TL%n", toplam);
        System.out.printf("Happy Hour İndirimi  : -%.2f TL%n", happyHourIndirimi);
        System.out.printf("Diğer İndirimler     : -%.2f TL%n", digerIndirim);
        System.out.printf("İndirimli Tutar      : %.2f TL%n", indirimSonrasiTutar);
        System.out.printf("Bahşiş (%%10)         : %.2f TL%n", bahsis);
        System.out.printf("Ödenecek Toplam      : %.2f TL%n", odenecekTutar);
        System.out.println("=========================");

        input.close();
    }
}
