import java.util.Scanner;
import java.util.ArrayList;

abstract class Entity {
    protected String ad;
    public Entity(String ad) {
        this.ad = ad;
    }

    public String getAd() {
        return ad;
    }
    public void setAd(String ad) {
        this.ad = ad;
    }
    @Override
    public abstract String toString();
}

class Film extends Entity {
    private String tur;
    private int sure;
    public Film(String ad, String tur, int sure) {
        super(ad);
        this.tur = tur;
        this.sure = sure;
    }
    public String getTur() {
        return tur;
    }
    public void setTur(String tur) {
        this.tur = tur;
    }
    public int getSure() {
        return sure;
    }
    public void setSure(int sure) {
        this.sure = sure;
    }
    @Override
    public String toString() {
        return ad + " (" + sure + " dk," + tur + " )";
    }
}

class Musteri extends Entity {
    private String email;
    public Musteri(String ad, String email) {
        super(ad);
        this.email = email;
    }
    public String getEmail() {
        return email;

    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return ad + " (" + email + ")";
    }
}
class Sinema_Yonetimi {
    private ArrayList<Film> filmler;
    private ArrayList<Musteri> musteriler;
    private int[][] biletler;
    private static final int MaxFilmler = 20;
    private static final int maxMusteriler = 20;

    public Sinema_Yonetimi() {
        filmler = new ArrayList<>();
        musteriler = new ArrayList<>();
        biletler = new int[MaxFilmler][maxMusteriler];

    }

    public void addFilmer(Film film) {
        if (filmler.size() >= MaxFilmler) {
            System.out.println("Filmer maximum sayısına ulaştı " + MaxFilmler );
            return;
        }
        filmler.add(film);
        System.out.println(film.getAd() + "Filmer eklendi.");
    }

    public void addMusteriler(Musteri musteril) {
        if (musteriler.size() >= maxMusteriler) {
            System.out.println("Maksimum müşteri sayısına ulaşıldı" + maxMusteriler );
            return;
        }
        musteriler.add(musteril);
        System.out.println(musteril.getAd() + "Musteriler eklendi.");
    }

    public void bilet_Kaydet(int musteriIdx, int filmIdx){
        if (filmler.isEmpty() || musteriler.isEmpty()){
            System.out.println("Önce film ve müşteri eklenmeli.");
            return;
        }
        if (musteriIdx >= musteriler.size() || filmIdx >= filmler.size() || musteriIdx < 0 || filmIdx < 0 ){
            System.out.println("Geçersiz işlem");
            return;
        }
        biletler[musteriIdx][filmIdx] = 1;
        System.out.println(musteriler.get(musteriIdx).getAd() + "için" + filmler.get(filmIdx).getAd() + "filmine bilet kaydedildi.");

    }

    public void biletleri_listele(){
        System.out.println("Bilet Kayıtları:");
        boolean biletVar = false;
        for (int i = 0; i < musteriler.size(); i++) {
            for (int j = 0; j < filmler.size(); j++) {
                if (biletler[i][j] == 1) {
                    System.out.println(musteriler.get(i).getAd() + "->" + filmler.get(j).getAd());
                    biletVar = true;
                }
            }
        }
        if (!biletVar){
            System.out.println("Bilet kaydı yok!");
        }
    }
    public ArrayList<Film> getFilmler() {
        return filmler;
    }
    public ArrayList<Musteri> getMusteriler() {
        return musteriler;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sinema_Yonetimi system = new Sinema_Yonetimi();

        while (true){
            System.out.println("\n1 Film Ekle");
            System.out.println("2 Müşteri Ekle");
            System.out.println("3 Bilet Kaydet");
            System.out.println("4 Biletleri Listele");
            System.out.println("5 Çıkış");
            System.out.print("Seçiminizi yapın (1-5): ");
            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    System.out.print("Film adını girin: ");
                    String filmAd = scanner.nextLine();
                    System.out.print("Film süresini (dk) girin: ");
                    int sure = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Film türünü girin: ");
                    String tur = scanner.nextLine();
                    system.addFilmer(new Film(filmAd, tur, sure));
                    break;

                case 2:
                    System.out.print("Müşteri adını girin: ");
                    String ad = scanner.nextLine();
                    System.out.print("Muşteri emailini girin: ");
                    String email = scanner.nextLine();
                    system.addMusteriler(new Musteri(ad, email));
                    break;

                case 3:
                    System.out.println("Müşteriler: ");
                    for (int i = 0; i < system.getMusteriler().size(); i++){
                        System.out.println(i + ":" + system.getMusteriler().get(i));
                    }
                    System.out.println("Filmler");
                    for (int i = 0; i< system.getFilmler().size(); i++) {
                        System.out.println(i + ":" + system.getFilmler().get(i));
                    }
                    System.out.print("Müşteri seçin: ");
                    int musteriIdx = scanner.nextInt();
                    System.out.print("Film seçin: ");
                    int filmIdx = scanner.nextInt();
                    system.bilet_Kaydet(musteriIdx, filmIdx);
                    break;

                case 4:
                    system.biletleri_listele();
                    break;

                case 5:
                    System.out.println("Programdan çıkış yağılıyor...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Geçersiz İşlem....");
            }

        }
    }
}
