package gameProject;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

//FURKAN DURAN 150124085 // MEHMET KEREM İMAMOGLU 150125002
//GENERAL PURPOSE OF CLASS: It ensures the game runs smoothly by bringing together other classes.

public class AnaOyunMotoru {
	
	public static int anlikSkor=0;
	public static double kalanSure;
	public static long sonGuncelleme;
	
	public static ArrayList<Group> canavarlar = new ArrayList<>();
	public static Pane anaAlan;
	public static double anlikSaglik;
	public static AnimationTimer zamanlayici;
	public static int maxSaglik;
	
	public static void oyunStart(Stage primaryStage,GhostHunter oyun, int level) {
	//When a level is selected. The game screen from scratch . It runs the interface,hunter,and monster mechanic together.
    // Lastly calls the underly class.
		anaAlan = new Pane();
		canavarlar.clear();
		
		maxSaglik = ConfigReader.ayarlar.get("maximum_health");
		anlikSaglik=maxSaglik;
		
		int maxVakum = ConfigReader.ayarlar.get("maximum_vacuum");
		Vacuum.baslangic(maxVakum);
		
		ArayuzCizen.olustur(anaAlan, level);
		anlikSkor=0;
		ArayuzCizen.metinSkor.setText("SCORE: 0");
		AvciVePaneller.olustur(anaAlan,primaryStage ,oyun, level);
		CanavarYonetimi.canavarlariDuzenle(anaAlan, level, canavarlar);
		
		Scene sahne2 = new Scene(anaAlan, 800, 600);
		primaryStage.setScene(sahne2);
        primaryStage.setFullScreen(true);
        
        OyunZamani.start(level);
		kontrollerVeDongu(sahne2, level);
	}
	
 // This method activates the keyboard and mouse listeners , calls configReader, calculates playable area.
 // AmimationTimer (zamanlayici.start()) starts the handle method running at 60 times per second from moment on.
	
	private static void kontrollerVeDongu(Scene sahne2,int level) {
		KlavyeAtalama.klavyeVeFare(sahne2);
		//Playable area values from config txt.
		int sinirGenisligi = ConfigReader.ayarlar.get("level_" + level + "_playable_area_width");
		int sinirYuksekligi = ConfigReader.ayarlar.get("level_" + level + "_playable_area_height");
		double solKenar = ConfigReader.ayarlar.get("level_"+level+"_playable_area_x");
		double sagKenar = solKenar + sinirGenisligi;
		double ustKenar = ConfigReader.ayarlar.get("level_"+level+"_playable_area_y");
		double altKenar = ustKenar + sinirYuksekligi;
		
		zamanlayici = new AnimationTimer() {
       // The handle method is heart of the game.
			@Override
		    public void handle(long now) {
				
				if(OyunZamani.zamanVeArayuz(now)) {return;}
				
				AvciHareketi.avciHareketi(solKenar, sagKenar, ustKenar, altKenar);
				
				boolean vakumlar= Vacuum.vakumHareketi(KlavyeAtalama.vakumAktif,ArayuzCizen.dikdortgenVacum,AvciVePaneller.tarayanUcgen);
				JetonYonetimi.JetonTuretimi(anaAlan, solKenar, sagKenar, ustKenar, altKenar,AvciVePaneller.avci,AvciVePaneller.tarayanUcgen, now, maxSaglik);
				Animasyon.wispAnimasyonu(canavarlar);
				
				ArayuzCizen.oyunCercevesi.setVisible(KlavyeAtalama.hackAktif);
				FizikselTemas.canavarlarVeVakum(solKenar, sagKenar, ustKenar, altKenar, vakumlar);
				FizikselTemas.avciHasariKontrolu();
			}
		};
		zamanlayici.start();
	}
}