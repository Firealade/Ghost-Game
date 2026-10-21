package gameProject;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
/* Mehmet Kerem İmamoğlu (Student ID: 150125002) 
Bu classımız; oyunun süresi, düşman sayısı, harita sınırı, tokenların etkileri gibi değişken olan şeyleri kodumuzun içine sıkıştırmak yerine dışarıdan bir metinden (config.txt) değişkenleri okuyup bir HashMap yapısına kaydetmemizi sağlıyor, bu sayede oyunu tekrar kodlamamıza gerek kalmadan tüm zorluk ayarlarını dışarıdan yönetebiliyoruz.
*/

public class ConfigReader {
	public static HashMap<String, Integer> ayarlar = new HashMap<>();
   // Boş satırlar “trim” ile kontrol edilip atlanıyor, dolu satırları ise “:” karakterine göre bölüyoruz.
    public static void ayarlariOku() {
        try {
        File dosya = new File("config.txt");
        Scanner sc = new Scanner(dosya);
            
        while (sc.hasNextLine()) {
          String satir = sc.nextLine();
                
           if(satir.trim().isEmpty()) {continue;}
                
           String[] parcalar = satir.split(":");   
           String anahtar = parcalar[0].trim();
           int deger = Integer.parseInt(parcalar[1].trim());
                
                ayarlar.put(anahtar, deger);
            }
            sc.close();
            System.out.println("Dosya başarıyla okundu!");
            
        } catch (Exception e) {
            System.out.println("HATA: dosya okunamadı! ");
            System.out.println("Hata detayı:"+ e.getMessage());
        }
    }
}
