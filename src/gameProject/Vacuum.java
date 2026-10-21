package gameProject;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;


/* Mehmet Kerem İmamoğlu (Student ID: 150125002) 
Bu class, oyuncunun etrafındaki hayaletleri 
bulmasını sağlayan tarayıcı/vakum mekanizmasının 
enerji yönetimini yapar. Boşluk (SPACE) tuşuna basıldığında 
enerjinin azalması, bırakıldığında ise tekrar dolması mantığını
 ve ekrandaki vakum barının anlık fiziksel değişimi burda kurgulandı. 
*/

public class Vacuum {
      private static double maxVakum;
      private static double anlikVakum;
 /* Oyun veya yeni bir seviye başladığında 
   çağrılan hazırlık metodudur. ConfigReader 
   üzerinden okunan maksimum vakum kapasitesini (max) parametre 
   olarak alır ve oyun başlarken tarayıcının enerjisini tam (full)
   olarak başlatır. 
 */
    
      public static void baslangic(double max) {
    	  maxVakum = max;
    	  anlikVakum = max;}
 
/* Oyun döngüsü tarafından hep çağrılan ana kontrol metodudur.
 'aktif' parametresi klavyeden SPACE tuşuna basılıp basılmadığını 
  belirtir. Eğer tuşa basılıysa ve enerjimiz (anlikVakum) 0'dan büyükse;
  enerjiyi config dosyasından aldığım değere göre azaltıp tarayıcı üçgenini 
  (fener) görünür olur. Tuş bırakıldığında ise enerjiyi zamanla dolar.
  Ayrıca, hesaplanan anlık enerji oranını matematiksel olarak ekrandaki
  mor Vakum Barı'nın (Rectangle) yüksekliğine (height) ve Y eksenindeki konumuna
  yansıtılır. 
 */ 
      public static boolean vakumHareketi(boolean aktif,Rectangle vakumBar,Polygon fener) {
    	  boolean vakumla = false;
    	  double azalmaMiktari = ConfigReader.ayarlar.get("vacuum_decrease")/5.0;
    	  double artmaMiktari = ConfigReader.ayarlar.get("vacuum_increase")/5.0;
    	  if(aktif) {
    		 if(anlikVakum>0) {
    		  anlikVakum -= azalmaMiktari;
    		  fener.setVisible(true);
    		  vakumla = true;
    	  
    	  }
    		 else {
    		  fener.setVisible(false);
    		  vakumla=false;
    		  
    	  }
    	  }else {
    		  fener.setVisible(false);
    		  vakumla=false;
    		  if(anlikVakum<maxVakum) {
    			  anlikVakum +=artmaMiktari;
    		  }
    	  }
    	  double oran = anlikVakum/maxVakum;
    	  if(oran <0) {oran =0;}
    	  if(oran>1) {oran =1;}
    	  
    	  double anlikBoy = 200 *oran;
    	  vakumBar.setHeight(anlikBoy);
    	  vakumBar.setY(40+ (200 -anlikBoy));
    	 
    	  return vakumla;
      }
}
