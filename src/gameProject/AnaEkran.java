package gameProject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// FURKAN DURAN 150124085
//GENERAL PURPOSE OF THE CLASS :The design of the screen that comes when the game open. 
//Features a uniuqe background and 3 butons which one below other.

public class AnaEkran {
	
  public static void ekraniGoster(Stage primaryStage , GhostHunter oyun) {
	    Pane anaMenuArkaPlan = new Pane();
	    Scene sahne1 = new Scene(anaMenuArkaPlan,800,600);
	  
	    Image arkaPlanResmi = new Image("file:src/gameProject/ArkaPlan2.png");//Arka Görsel için Image ve ImageView nesnesi kullandım.
		ImageView gostereci = new ImageView(arkaPlanResmi);
		gostereci.fitWidthProperty().bind(anaMenuArkaPlan.widthProperty());
		gostereci.fitHeightProperty().bind(anaMenuArkaPlan.heightProperty());
		gostereci.setPreserveRatio(false);
		
		VBox dikey = new VBox(20);
		dikey.setAlignment(Pos.CENTER);
        dikey.layoutXProperty().bind(anaMenuArkaPlan.widthProperty().multiply(0.44));
        dikey.layoutYProperty().bind(anaMenuArkaPlan.heightProperty().multiply(0.70)); 
        //I set the size for the vertical box which the butons will be placed to fixed value using .multiply(). 
        
        //I desiged 3 butons and their colors ,font sizes using .setStlye() in single line.
		Button basla = new Button("START GAME");
		Button selectLvl = new Button("SELECT LEVEL");
		Button exit = new Button("EXIT");
		
		basla.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-weight: bold; -fx-pref-width: 200px; -fx-pref-height: 50px; -fx-font-size: 18px;-fx-font-family: 'Comic Sans MS';");
		selectLvl.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-weight: bold; -fx-pref-width: 200px; -fx-pref-height: 50px; -fx-font-size: 18px;-fx-font-family: 'Comic Sans MS';");
		exit.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-weight: bold; -fx-pref-width: 200px; -fx-pref-height: 50px; -fx-font-size: 18px;-fx-font-family: 'Comic Sans MS';");
        
		//I used setOn..() events for changes when press the start buton .
		basla.setOnMousePressed(e-> {basla.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-font-weight: bold; -fx-pref-width: 200px; -fx-pref-height: 50px; -fx-font-size: 18px;-fx-font-family: 'Comic Sans MS';");});
		basla.setOnMouseReleased(e-> {basla.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-weight: bold; -fx-pref-width: 200px; -fx-pref-height: 50px; -fx-font-size: 18px;-fx-font-family: 'Comic Sans MS';");});
		basla.setOnAction(e-> oyun.oyunStart(primaryStage, 1)); //When press the START buton starts the game from first level.
        
		//I used same events for SELECT LEVEL buton.
		selectLvl.setOnMousePressed(e-> {selectLvl.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-font-weight: bold; -fx-pref-width: 200px; -fx-pref-height: 50px; -fx-font-size: 18px;-fx-font-family: 'Comic Sans MS';");});
		selectLvl.setOnMouseReleased(e-> {selectLvl.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-weight: bold; -fx-pref-width: 200px; -fx-pref-height: 50px; -fx-font-size: 18px;-fx-font-family: 'Comic Sans MS';");});
		selectLvl.setOnAction(e-> oyun.seviyeSecimScreen(primaryStage)); //When press the SELECT LEVEL buton, level selection screen opens.
        
		//I used same events for EXIT button.
		exit.setOnMousePressed(e-> {exit.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-font-weight: bold; -fx-pref-width: 200px; -fx-pref-height: 50px; -fx-font-size: 18px;-fx-font-family: 'Comic Sans MS';");});
		exit.setOnMouseReleased(e -> {exit.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-weight: bold; -fx-pref-width: 200px; -fx-pref-height: 50px; -fx-font-size: 18px;-fx-font-family: 'Comic Sans MS';");});
		exit.setOnAction(e -> System.exit(0)); //When press the EXIT buton , the game closes.
		
		dikey.getChildren().add(basla);
		dikey.getChildren().add(selectLvl);
		dikey.getChildren().add(exit);
        
		anaMenuArkaPlan.getChildren().add(gostereci);
        anaMenuArkaPlan.getChildren().add(dikey);
      
        //Make the game open as full screen , I used these events.
	    primaryStage.setScene(sahne1);
		primaryStage.setFullScreenExitHint("");
		primaryStage.setFullScreen(true);
		primaryStage.show();
  }
}
