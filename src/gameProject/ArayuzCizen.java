package gameProject;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

// FURKAN DURAN 150124085
//GENERAL PURPOSE OF CLASS:Shorty, it creates visual skeleton of levels.
//Includes the visual representation of positon of the health and vacum bars ,their frames . 
//Contains the score, counter position and their texts.

public class ArayuzCizen {
	
	public static Rectangle oyunCercevesi;
	public static Rectangle dikdortgenSaglik;
	public static Rectangle dikdortgenVacum;
	public static Rectangle saglikCerceve;
	public static Rectangle vakumCerceve;
	public static Text metinSkor;
	public static Text metinZaman;
	public static Text metinSaglik;
	public static Text metinVacuum;
	
	public static void olustur(Pane anaAlan,int level) {
		//I used an if-else block for a unique background on the specific level.
		Image oyunArkaPlan = null;
		if(level == 1) {oyunArkaPlan = new Image("file:src/gameProject/Level1bir.png");}
		else if(level == 2) {oyunArkaPlan = new Image("file:src/gameProject/Level2iki.png");}
		else if(level == 3) {oyunArkaPlan = new Image("file:src/gameProject/Level3üc.png");}
		
		//Placing the background in panel.
		ImageView gosterici = new ImageView(oyunArkaPlan);
		gosterici.fitWidthProperty().bind(anaAlan.widthProperty());
		gosterici.fitHeightProperty().bind(anaAlan.heightProperty());
		gosterici.setPreserveRatio(false);	
		anaAlan.getChildren().add(gosterici);
		
		//Health bar design and position.
		dikdortgenSaglik = new Rectangle(30, 200);
		dikdortgenSaglik.setFill(Color.RED);
		dikdortgenSaglik.xProperty().bind(anaAlan.widthProperty().subtract(50));
		//The subtract() event makes that the bar maintains ist poisiton (right up).
		dikdortgenSaglik.setY(40);
		
		//The outer black frame of health bar.
		saglikCerceve = new Rectangle(30,200);
		saglikCerceve.setFill(Color.TRANSPARENT);
		saglikCerceve.setStroke(Color.BLACK);
		saglikCerceve.setStrokeWidth(5);
		saglikCerceve.xProperty().bind(anaAlan.widthProperty().subtract(50)); 
		saglikCerceve.setY(40);
        //Vacuum color bar design.
		dikdortgenVacum = new Rectangle(30, 200);
		dikdortgenVacum.setFill(Color.PURPLE);
		dikdortgenVacum.setX(20);
		dikdortgenVacum.setY(40);
		dikdortgenVacum.setStroke(Color.BLACK);
		dikdortgenVacum.setStrokeWidth(3);
		//The outer frame of the health bar.
		vakumCerceve = new Rectangle(30,200);
		vakumCerceve.setFill(Color.TRANSPARENT);
		vakumCerceve.setStroke(Color.BLACK);
		vakumCerceve.setStrokeWidth(5);
		vakumCerceve.setX(20);
		vakumCerceve.setY(40);
		
		//Shortly, I use .format() event for counter value of each level .
		int sureSecond = ConfigReader.ayarlar.get("level_" + level + "_time");
		int dakika = sureSecond/60;
		int saniye = sureSecond%60;
		String formatliZaman = String.format("%d:%02d", dakika, saniye);
	    
		//I created text elements for score,time,health bar, and vacuum bar.
		
		metinSkor = new Text("SCORE: 0");
		metinSkor.xProperty().bind(anaAlan.widthProperty().divide(2).subtract(30));
		metinSkor.setY(30);
		metinSkor.setFill(Color.WHITE);
		metinSkor.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;-fx-font-family: 'Comic Sans MS'; ");
        
		metinZaman = new Text(formatliZaman);
		metinZaman.xProperty().bind(anaAlan.widthProperty().divide(2).subtract(15));
		metinZaman.setY(50);
		metinZaman.setFill(Color.WHITE);
		metinZaman.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;-fx-font-family: 'Comic Sans MS';");

		metinSaglik = new Text("HEALTH:");
		metinSaglik.setFill(Color.WHITE);
		metinSaglik.xProperty().bind(anaAlan.widthProperty().subtract(70));
		metinSaglik.setY(20);
		metinSaglik.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;-fx-font-family: 'Comic Sans MS';");
		
		metinVacuum = new Text("VACUUM:");
		metinVacuum.setFill(Color.WHITE);
		metinVacuum.setX(15);
		metinVacuum.setY(20);
		metinVacuum.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;-fx-font-family: 'Comic Sans MS';");
		
		//The special game area for each level for hunter and ghosts.
		int genislik = ConfigReader.ayarlar.get("level_"+level+"_playable_area_width");
		int yukseklik = ConfigReader.ayarlar.get("level_"+level+"_playable_area_height");
		
		int xKordinat =ConfigReader.ayarlar.get("level_"+level+"_playable_area_x");
		int yKordinat = ConfigReader.ayarlar.get("level_"+level+"_playable_area_y");
		
		//The visual of the game rectangular frame.
		oyunCercevesi=new Rectangle(genislik,yukseklik);
		oyunCercevesi.setFill(Color.TRANSPARENT);
		oyunCercevesi.setStroke(Color.LIME);
		oyunCercevesi.setStrokeWidth(2);
		oyunCercevesi.setX(xKordinat);
		oyunCercevesi.setY(yKordinat);
		oyunCercevesi.setVisible(false);
		
		anaAlan.getChildren().add(oyunCercevesi);
		anaAlan.getChildren().add(dikdortgenSaglik);
		anaAlan.getChildren().add(saglikCerceve);
		anaAlan.getChildren().add(dikdortgenVacum);
		anaAlan.getChildren().add(vakumCerceve);
		anaAlan.getChildren().add(metinSkor);
		anaAlan.getChildren().add(metinZaman);
		anaAlan.getChildren().add(metinSaglik);
		anaAlan.getChildren().add(metinVacuum);
		
	}
}
