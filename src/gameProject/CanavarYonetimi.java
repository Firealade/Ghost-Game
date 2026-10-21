package gameProject;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Random;

/*  Muhammed Kaban  (Student ID: 150124026)  
Amaç: Bu class bölüm başında dosyadan okunan canavar sayılarına göre nesneleri 
üretip oyun alanının sınırları içerisinde rastgele konumlara dağıtıyor 
ve hız/can/hasar gibi  başlangıç değerlerini canavar türlerine atıyor. 
*/ 
public class CanavarYonetimi {
   
	public static void canavarlariDuzenle(Pane anaAlan,int level, ArrayList<Group> canavarListesi) {
		/* Bu metotta belirtilen levelin sınır bilgilerini config dosyasından
		 çekip canavarları haritaya yerleştiyoruz ve
		 gidicekleri yön ile can/hasar özelliklerini yazıyoruz.
		 */

		int sinirX = ConfigReader.ayarlar.get("level_" + level + "_playable_area_x");
		int sinirY = ConfigReader.ayarlar.get("level_" + level + "_playable_area_y");
		int sinirGenisligi = ConfigReader.ayarlar.get("level_" + level + "_playable_area_width");
		int sinirYuksekligi = ConfigReader.ayarlar.get("level_" + level + "_playable_area_height");

		int hayaletSayisi = ConfigReader.ayarlar.get("level_"+level+"_ghosts");
		int ripperSayisi = ConfigReader.ayarlar.get("level_"+level+"_rippers");
		int wispSayisi = ConfigReader.ayarlar.get("level_"+level+"_wisps");
		
		Random rastgele = new Random();
		canavarListesi.clear();
		int totalCanavar = hayaletSayisi+ripperSayisi+wispSayisi;
		
		for(int i=0;i<totalCanavar;i++) {
			Group newCanavar;
			int Hiz;
			
			double can =0;
			int puan=0;
			double configHasar=ConfigReader.ayarlar.get("entity_damage");
			double hasar= 0.0;
			
			if(i<hayaletSayisi) {
				newCanavar=CanavarTasarimi.defaultHayaletUreten();
				Hiz = 1;
				can = 240.0;
				puan =10;
				hasar = configHasar/60.0;
			}else if(i<hayaletSayisi+ripperSayisi) {
				newCanavar = CanavarTasarimi.ripperUreten();
				Hiz = 2;
				can =480.0;
				puan=20;
				hasar=configHasar/60.0;
			}else {
				newCanavar = CanavarTasarimi.wispUreten();
				Hiz = 3;
				can=720.0;
				puan=30;
				hasar= configHasar/60.0;}
			
			// Canavarların oyun alanı sınırları içinde doğmasını sağlıyoruz
			double rastgeleX = sinirX + 20 + rastgele.nextInt(sinirGenisligi-40);
			double rastgeleY = sinirY+ 20 + rastgele.nextInt(sinirYuksekligi-40);
			newCanavar.setLayoutX(rastgeleX);
			newCanavar.setLayoutY(rastgeleY);
			
			int hizX;
			if(rastgele.nextBoolean()) {
				hizX = Hiz;
			}else {hizX = -Hiz;}
			int hizY;
			if(rastgele.nextBoolean()) {
				hizY= Hiz;
			}else {hizY= -Hiz;}
			
			newCanavar.getProperties().put("hizX",hizX);
			newCanavar.getProperties().put("hizY",hizY);
			newCanavar.getProperties().put("can",can);
			newCanavar.getProperties().put("puan",puan);
			newCanavar.getProperties().put("hasar",hasar);
			
			canavarListesi.add(newCanavar);
			anaAlan.getChildren().add(newCanavar);
	  }
    } 
}
