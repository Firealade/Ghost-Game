package gameProject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// FURKAN DURAN 150124085
//GENERAL PURPOSE: Design of the level selection screen when pressed the SELECT LEVEL buton.
//I added 3 diffrent unique background images for the 3 levels, with functional outer frames.
public class LevelSecimi {
 public static void SeviyeyiGoster(Stage primaryStage,GhostHunter oyun) {
	   
	 StackPane secimEkrani = new StackPane();
	 //The unique background of Select Level Panel.	
	 Image seviyeSecimArkaPlan = new Image("file:src/gameProject/LevelSelectArkaPlan.png");
	 ImageView gosterici = new ImageView(seviyeSecimArkaPlan);
		
	 gosterici.fitWidthProperty().bind(secimEkrani.widthProperty());
	 gosterici.fitHeightProperty().bind(secimEkrani.heightProperty());
	 gosterici.setPreserveRatio(false);
		
	    
		VBox anaBox = new VBox();
		anaBox.setAlignment(Pos.CENTER);
		anaBox.setSpacing(30);
		HBox mapBox = new HBox();
		mapBox.setAlignment(Pos.CENTER);
		mapBox.setSpacing(30);
		
	    Label baslık = new Label(" SELECT LEVEL ");
		baslık.setStyle("-fx-text-fill: white;-fx-font-weight: bold;-fx-font-size: 36px;-fx-font-family:'Comic Sans MS';");
		anaBox.getChildren().add(baslık);
		anaBox.getChildren().add(mapBox);
		//The background images for 3 levels placed in sparete frames.
		Image level1 = new Image("file:src/gameProject/Levelbir.png");
		ImageView map1 = new ImageView(level1);
		map1.setFitWidth(250);
		map1.setFitHeight(150);

		Image level2 = new Image("file:src/gameProject/Leveliki.png");
		ImageView map2 = new ImageView(level2);
		map2.setFitWidth(250);
		map2.setFitHeight(150);

		Image level3 = new Image("file:src/gameProject/Levelüc.png");
		ImageView map3 = new ImageView(level3);
		map3.setFitWidth(250);
		map3.setFitHeight(150);
        
		//Thickness and color of the frames.
		StackPane cerceve1 = new StackPane(map1);
		cerceve1.setStyle("-fx-background-color: darkgreen;-fx-padding: 10px;");
		StackPane cerceve2 = new StackPane(map2);
		cerceve2.setStyle("-fx-background-color: darkgreen;-fx-padding: 10px;");	
		StackPane cerceve3 = new StackPane(map3);
		cerceve3.setStyle("-fx-background-color: darkgreen;-fx-padding: 10px;");
		
		//I specified the images for each frame . I made mouse events using setStyle().
		cerceve1.setOnMouseEntered(e-> cerceve1.setStyle("-fx-background-color: green;-fx-padding: 10px;"));
		cerceve1.setOnMouseExited(e-> cerceve1.setStyle("-fx-background-color: darkgreen;-fx-padding: 10px;"));
		cerceve1.setOnMousePressed(e-> cerceve1.setStyle("-fx-background-color: red;-fx-padding: 10px;"));
		cerceve1.setOnMouseReleased(e-> {cerceve1.setStyle("-fx-background-color: green;-fx-padding: 10px;");
			oyun.oyunStart(primaryStage,1);}); //opens first level.

		cerceve2.setOnMouseEntered(e-> cerceve2.setStyle("-fx-background-color: green;-fx-padding: 10px;"));
		cerceve2.setOnMouseExited(e-> cerceve2.setStyle("-fx-background-color: darkgreen;-fx-padding: 10px;"));
		cerceve2.setOnMousePressed(e-> cerceve2.setStyle("-fx-background-color: red;-fx-padding: 10px;"));
		cerceve2.setOnMouseReleased(e-> {cerceve2.setStyle("-fx-background-color: green;-fx-padding: 10px;");
			oyun.oyunStart(primaryStage,2);}); // opens second level.

		cerceve3.setOnMouseEntered(e-> cerceve3.setStyle("-fx-background-color: green;-fx-padding: 10px;"));
		cerceve3.setOnMouseExited(e-> cerceve3.setStyle("-fx-background-color: darkgreen;-fx-padding: 10px;"));
		cerceve3.setOnMousePressed(e-> cerceve3.setStyle("-fx-background-color: red;-fx-padding: 10px;"));
		cerceve3.setOnMouseReleased(e-> {cerceve3.setStyle("-fx-background-color: green; -fx-padding: 10px;");
			oyun.oyunStart(primaryStage, 3);}); // opens third level.

		mapBox.getChildren().add(cerceve1);
		mapBox.getChildren().add(cerceve2);
		mapBox.getChildren().add(cerceve3);

		Button getBack = new Button("BACK TO MENU");
		getBack.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-weight: bold; -fx-pref-width: 250px; -fx-pref-height: 50px; -fx-font-size: 18px; -fx-font-family: 'Comic Sans MS';");
		getBack.setOnMouseReleased(e-> getBack.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-pref-width: 250px; -fx-pref-height: 50px; -fx-font-size: 18px; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS';"));
		getBack.setOnMousePressed(e-> getBack.setStyle("-fx-background-color: white; -fx-text-fill: darkgreen; -fx-pref-width: 250px; -fx-pref-height: 50px; -fx-font-size: 18px; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS';"));
		getBack.setOnAction(e-> oyun.start(primaryStage));
		anaBox.getChildren().add(getBack);
  
		secimEkrani.getChildren().add(gosterici);
		secimEkrani.getChildren().add(anaBox);
		Scene secim = new Scene(secimEkrani,900,400);
		primaryStage.setScene(secim);
		primaryStage.setFullScreen(true);
 }
}
