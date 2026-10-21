package gameProject;

/* Mehmet Kerem İmamoğlu (Student ID: 150125002) 
Bu class oyundaki geri sayım sayacını ve zaman 
yönetimini kontrol eder. Seviyeye özel belirlenen süreyi 
takip ederek ekrandaki sayacı günceller ve süre bittiğinde 
oyunu durdurarak kaybetme (Lose) durumunu tetikler. 
*/

public class OyunZamani {
	public static double kalanSure;
	public static long sonGuncelleme;
 /* Bu metot, oyun veya yeni bir seviye
    başladığında çağrılır. Parametre olarak
    aldığı seviye bilgisine göre, ConfigReader sınıfından o bölümün
    toplam süresini çeker. Ardından zaman takibini başlatmak için sistemin o 
    anki nano saniyesini  'sonGuncelleme' değişkenine kaydeder. 
 */ 
	public static void start(int level) {
		kalanSure = ConfigReader.ayarlar.get("level_"+level+"_time");
		sonGuncelleme=System.nanoTime();
	}
/* Bu metot, AnaOyunMotoru'ndaki AnimationTimer 
 tarafından saniyede defalarca çağrılır. 'now' parametresi
 ile anlık zamanı alıp geçen süreyi hesaplıyoruz. Eğer tam 1
 saniye geçmişse kalanSure'yi azaltıp ekrandaki metni 
 (dakika:saniye formatında) güncelliyorum. Eğer süre 0'a veya altına 
 düşerse, oyun motorunu durdurarak 'Game Over' ekranını görünür yapıyorum. 
*/ 

	public static boolean zamanVeArayuz(long now) {
		double gecenSure=(now-sonGuncelleme)/1_000_000_000.0;
		
		if(gecenSure>=1.0) {
			kalanSure--;
			sonGuncelleme=now;
			
			int dakika = (int) kalanSure /60;
			int saniye = (int) kalanSure %60;
			ArayuzCizen.metinZaman.setText(String.format("%d:%02d",dakika,saniye));
	    
		   if(kalanSure <=0) {
			AnaOyunMotoru.zamanlayici.stop();
			AvciVePaneller.losePanel.setVisible(true);
			return true;
		   }
		}
		return false;
	}
}
