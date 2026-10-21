package gameProject;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

//FURKAN DURAN 150124085
//GENERAL PURPOSE OF CLASS:Shortly , the appearance of hunter's flashlight, the rotation mechanism .
//Formation, type of the win loss panels.
//Setting the functions of the buttons of win lose panels.

public class AvciVePaneller {
	public static Group avci;
	public static Circle avciBody;
	public static Polygon tarayanUcgen;
	public static Rotate avciDonusu;
	public static StackPane winPanel;
	public static StackPane losePanel;
   
	public static void olustur(Pane anaAlan, Stage primaryStage, GhostHunter oyun, int level) {
		
	    //Design of the hunter's return and appearance.
		avci = new Group();
		avciDonusu = new Rotate();
		avci.getTransforms().add(avciDonusu);
		avciBody = new Circle(20, Color.DARKTURQUOISE);
		avciBody.setStroke(Color.BLACK);
		
		//Design of the flasthlight using polygon.
		tarayanUcgen = new Polygon(0.0, 0.0, 
			                       100.0, -50.0, 
			                       100.0, 50.0);
		tarayanUcgen.setFill(Color.rgb(255,0,0,0.3)); 
		tarayanUcgen.setVisible(false); 
		
		//I used a common group to fix the body and flashlight of hunter.
		avci.getChildren().add(tarayanUcgen);
		avci.getChildren().add(avciBody);
	
		Integer baslangicX =ConfigReader.ayarlar.get("level_" +level + "_playable_area_x");
		Integer baslangicY =ConfigReader.ayarlar.get("level_" +level + "_playable_area_y");
		
		//Setting the initial position of hunter.
		if(baslangicX !=null && baslangicY !=null) {
			avci.setLayoutX(baslangicX +100);
			avci.setLayoutY(baslangicY +100);
		} else {
			avci.setLayoutX(300);
			avci.setLayoutY(300);}
		
		anaAlan.getChildren().add(avci);
        
		//Design of win panel and its texts.
		winPanel = new StackPane(); 
		winPanel.prefWidthProperty().bind(anaAlan.widthProperty());
		winPanel.prefHeightProperty().bind(anaAlan.heightProperty());
		winPanel.setVisible(false);
		
		VBox winKutu = new VBox(20);
		winKutu.setAlignment(Pos.CENTER);
		winKutu.setMaxSize(450, 250);
		winKutu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.85); -fx-padding: 30px; -fx-background-radius: 15px;");
		
		Text winText = new Text(" YOU WIN ");
		winText.setFill(Color.WHITE);
		winText.setStyle("-fx-font-weight: bold;-fx-font-size: 40px;-fx-font-family: 'Comic Sans MS';");
		
		Text altText = new Text("YOU HAVE VACUUMED ALL GHOSTS ");
		altText.setFill(Color.WHITE);
		altText.setStyle("-fx-font-weight: bold;-fx-font-size: 20px;-fx-font-family: 'Comic Sans MS';");
		
		//I used an if-else block in order to control the button text based on the level stat.
		String butonYazisi = "";
		if(level<3) {
			butonYazisi="NEXT LEVEL";
		}else {
			butonYazisi="MAIN MENU";
		}
		
		Button nextLevelButon = new Button(butonYazisi);
		nextLevelButon.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS'; -fx-pref-width: 200px; -fx-pref-height: 40px; -fx-font-size: 18px;");
		nextLevelButon.setOnMousePressed(e -> nextLevelButon.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS'; -fx-pref-width: 200px; -fx-pref-height: 40px; -fx-font-size: 18px;"));
		nextLevelButon.setOnMouseReleased(e -> nextLevelButon.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS'; -fx-pref-width: 200px; -fx-pref-height: 40px; -fx-font-size: 18px;"));
		
		nextLevelButon.setOnAction(e -> 
		    {if(level< 3) {oyun.oyunStart(primaryStage, level + 1);
			} else {oyun.start(primaryStage);}});
		
		winKutu.getChildren().add(winText);
		winKutu.getChildren().add(altText);
		winKutu.getChildren().add(nextLevelButon);
		winPanel.getChildren().add(winKutu);
		anaAlan.getChildren().add(winPanel);

	    //Design of the loss panel, and its texts.
		losePanel = new StackPane();
		losePanel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
		losePanel.prefWidthProperty().bind(anaAlan.widthProperty());
		losePanel.prefHeightProperty().bind(anaAlan.heightProperty());
		losePanel.setVisible(false);
		
		StackPane loseArkaKutu = new StackPane();
		loseArkaKutu.setMaxSize(450, 300);
		loseArkaKutu.setStyle("-fx-background-color: black; -fx-background-radius: 15px; -fx-padding: 30px;");
		
		VBox loseKutu = new VBox(20);
		loseKutu.setAlignment(Pos.CENTER);
		
		Text loseText = new Text("GAME OVER");
		loseText.setFill(Color.DARKRED);
		loseText.setStyle("-fx-font-weight: bold; -fx-font-size: 40px; -fx-font-family: 'Comic Sans MS';");
		
		Text sonSkor = new Text("FINAL SCORE: " + ArayuzCizen.metinSkor.getText().replace("SCORE: ", ""));
		sonSkor.setFill(Color.DARKRED);
		sonSkor.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-font-family: 'Comic Sans MS';");
		
		Button retry = new Button("RETRY");
		retry.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS'; -fx-pref-width: 200px; -fx-pref-height: 40px; -fx-font-size: 18px;");
		retry.setOnMousePressed(e -> retry.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS'; -fx-pref-width: 200px; -fx-pref-height: 40px; -fx-font-size: 18px;"));
		retry.setOnMouseReleased(e -> retry.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS'; -fx-pref-width: 200px; -fx-pref-height: 40px; -fx-font-size: 18px;"));
		retry.setOnAction(e -> oyun.oyunStart(primaryStage, level));
		
		Button menu = new Button("MAIN MENU");
		menu.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS'; -fx-pref-width: 200px; -fx-pref-height: 40px; -fx-font-size: 18px;");
		menu.setOnMousePressed(e -> menu.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS'; -fx-pref-width: 200px; -fx-pref-height: 40px; -fx-font-size: 18px;"));
		menu.setOnMouseReleased(e -> menu.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS'; -fx-pref-width: 200px; -fx-pref-height: 40px; -fx-font-size: 18px;"));
		menu.setOnAction(e -> oyun.start(primaryStage));
		
		loseKutu.getChildren().addAll(loseText);
		loseKutu.getChildren().add(sonSkor);
		loseKutu.getChildren().add(retry);
		loseKutu.getChildren().add(menu);
		loseArkaKutu.getChildren().add(loseKutu);
		losePanel.getChildren().add(loseArkaKutu);
		anaAlan.getChildren().add(losePanel);
	}
	  
}
