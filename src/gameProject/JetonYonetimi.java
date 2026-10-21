package gameProject;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;
/*  Muhammed Kaban  (Student ID: 150124026) 
Amaç: Bu class oyundaki jetonların (can, hız, menzil, görünürlük) rastgele
 zamanlarda  haritanın oynanabilir sınırları
 içinde üretilmesini ve  avcının bu jetonlarla temas edip etmediğini kontrol eder.
 Ardından da  jetonların etkilerini  ve sonuçlarını yönetir. 
*/ 

public class JetonYonetimi {

	private static StackPane anlikCanJeton=null;
	private static StackPane anlikRangeJeton=null;
	private static StackPane anlikGozJeton=null;
	private static StackPane anlikHizJeton=null;
	
	private static long sonJetonTime=0;
	private static long hizEtkiTime=0;
	private static long rangeEtkiTime=0;
	private static long gozEtkiTime=0;
	
	public static boolean gozAktifligi=false;
	public static boolean hizAktifligi=false;
	
	public static void JetonTuretimi(Pane alan,double sol,double sag,double ust,double alt,Group avci,Polygon fener,long anlikZaman,int maxSaglik) {
/*  Bu kısımda oyun döngüsü boyunca her 5 saniyede bir haritadaki aktif
 jeton sayısını kontrol ederek rastgele koordinatlarda yeni bir 
 jeton üretip avcının bu jetonların üzerine gelip gelmediğini kontrol ediyoruz
 ve jeton toplandığında ilgili özellikleri
(sağlık barı animasyonu, hız/menzil artışları) güncelliyoruz 
*/
		
		Random rd = new Random();
	
		int anlikJetonSayisi=0;
		
		if(anlikCanJeton !=null) {anlikJetonSayisi++;}
		if(anlikRangeJeton !=null) {anlikJetonSayisi++;}
		if(anlikGozJeton != null) {anlikJetonSayisi++;}
		if(anlikHizJeton != null) {anlikJetonSayisi++;}
		
		if(anlikZaman-sonJetonTime >=5_000_000_000L) {
			if(anlikJetonSayisi<2) {
				ArrayList<Integer> mevcutJetonlar = new ArrayList<Integer>();
				if(anlikCanJeton==null) {mevcutJetonlar.add(0);}
				if(anlikRangeJeton==null) {mevcutJetonlar.add(1);}
				if(anlikGozJeton==null) {mevcutJetonlar.add(2);}
				if(anlikHizJeton==null) {mevcutJetonlar.add(3);}
				
			 if(!mevcutJetonlar.isEmpty()) {
				int secilen = rd.nextInt(mevcutJetonlar.size());
				int uretilecek = mevcutJetonlar.get(secilen);
					
				double rastgeleX=sol +20+ rd.nextDouble()*(sag-sol-40);
				double rastgeleY=ust +20+ rd.nextDouble()*(alt-ust-40);
					
				if(uretilecek==0) {
					anlikCanJeton=JetonCagırma.canJetonuCagirma();
					anlikCanJeton.setLayoutX(rastgeleX);
					anlikCanJeton.setLayoutY(rastgeleY);
					alan.getChildren().add(anlikCanJeton);
				}else if(uretilecek==1) {
					anlikRangeJeton = JetonCagırma.rangeJetonuCagirma();
					anlikRangeJeton.setLayoutX(rastgeleX);
					anlikRangeJeton.setLayoutY(rastgeleY);
					alan.getChildren().add(anlikRangeJeton);
				} else if(uretilecek==2) {
					anlikGozJeton =JetonCagırma.gozJetonuCagirma();
					anlikGozJeton.setLayoutX(rastgeleX);
					anlikGozJeton.setLayoutY(rastgeleY);
					alan.getChildren().add(anlikGozJeton);
				} else if(uretilecek==3) {
					anlikHizJeton =JetonCagırma.hizJetonuCagirma();
					anlikHizJeton.setLayoutX(rastgeleX);
					anlikHizJeton.setLayoutY(rastgeleY);
					alan.getChildren().add(anlikHizJeton);
					}
				}
			}
			sonJetonTime=anlikZaman;
		}
		
		Bounds avciSiniri = avci.localToScene(avci.getBoundsInLocal());
		
		if(anlikCanJeton != null && avciSiniri.intersects(anlikCanJeton.localToScene(anlikCanJeton.getBoundsInLocal()))) {
			int canArtis = ConfigReader.ayarlar.get("health_token_increase");
			AnaOyunMotoru.anlikSaglik +=canArtis;
			
			if(AnaOyunMotoru.anlikSaglik>maxSaglik) {AnaOyunMotoru.anlikSaglik=maxSaglik;}
			
			double saglikOrani = AnaOyunMotoru.anlikSaglik/maxSaglik;
			double yeniBoyu = 200*saglikOrani;
			double yeniYukseklik =40+(200-yeniBoyu); 
		    
			Timeline canBariAnimasyonu = new Timeline();
			KeyValue boy = new KeyValue(ArayuzCizen.dikdortgenSaglik.heightProperty(), yeniBoyu);
			KeyValue konum = new KeyValue(ArayuzCizen.dikdortgenSaglik.yProperty(), yeniYukseklik);
		    KeyFrame kF = new KeyFrame(Duration.millis(500), boy,konum);
		    canBariAnimasyonu.getKeyFrames().add(kF);
		    canBariAnimasyonu.play();
			
			alan.getChildren().remove(anlikCanJeton);
			anlikCanJeton=null;
		}
		if(anlikRangeJeton != null && avciSiniri.intersects(anlikRangeJeton.localToScene(anlikRangeJeton.getBoundsInLocal()))) {
			rangeEtkiTime = anlikZaman + 5_000_000_000L;	
			alan.getChildren().remove(anlikRangeJeton);
			anlikRangeJeton=null;}
		
		if(anlikGozJeton != null && avciSiniri.intersects(anlikGozJeton.localToScene(anlikGozJeton.getBoundsInLocal()))) {
			long gozSuresi = ConfigReader.ayarlar.get("eye_token_duration") * 1_000_000_000L; 
			gozEtkiTime=anlikZaman+ gozSuresi;
			alan.getChildren().remove(anlikGozJeton);
			anlikGozJeton=null;}
		
		if(anlikHizJeton !=null && avciSiniri.intersects(anlikHizJeton.localToScene(anlikHizJeton.getBoundsInLocal()))) {
			long hizSuresi = ConfigReader.ayarlar.get("speed_token_duration")*1_000_000_000L;
			hizEtkiTime=anlikZaman+hizSuresi;
			alan.getChildren().remove(anlikHizJeton);
			anlikHizJeton=null;}
		
		if(anlikZaman<rangeEtkiTime) {
			int fenerArtisi = ConfigReader.ayarlar.get("vacuum_token_increase");
			fener.getPoints().setAll(0.0, 0.0, 
					  100.0+ fenerArtisi,-50.0 -fenerArtisi, 
					  100.0+ fenerArtisi,50.0 +fenerArtisi);
			} else{	
				fener.getPoints().setAll(0.0, 0.0, 
					                   100.0,-50.0, 
				                       100.0,50.0);
			}
		
		if(anlikZaman<gozEtkiTime) {gozAktifligi=true;}
		else {gozAktifligi=false;}
		
		if(anlikZaman<hizEtkiTime) {hizAktifligi=true;}
		else {hizAktifligi=false;}
	}
}
