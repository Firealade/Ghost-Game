package gameProject;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

// FURKAN DURAN 150124085
// GENERAL PURPOSE OF CLASS: Design of the appearance of tokens.
//Additionally , I added a token that increases the hunter's speed.

public class JetonCagırma {
    //The design method of the health potion token.
	public static StackPane canJetonuCagirma() {
		Circle healthJeton = new Circle(15);
		  healthJeton.setFill(Color.GREEN);
		  healthJeton.setStroke(Color.BLACK);
		 
		  Rectangle healthPlus1 = new Rectangle(20,5);
		  healthPlus1.setFill(Color.WHITE);
		 
		  Rectangle healthPlus2 = new Rectangle(5,20);
		  healthPlus2.setFill(Color.WHITE);
		 
		  StackPane healthJetonSP = new StackPane();
		  healthJetonSP.getChildren().add(healthJeton);
		  healthJetonSP.getChildren().add(healthPlus1);
		  healthJetonSP.getChildren().add(healthPlus2);
          healthJetonSP.getProperties().put("tur","can");
          return healthJetonSP;
	}
	//The design method of range token and scanning area of the hunter's flashlight.
	public static StackPane rangeJetonuCagirma() {
		Circle menzilJetonDıs = new Circle(15);
		  menzilJetonDıs.setFill(Color.YELLOW);
		  menzilJetonDıs.setStroke(Color.BLACK);
		 
		  Polygon turuncuUcgen = new Polygon(0,9,
				                          -18,0,
				                           0,-9);
		  turuncuUcgen.setFill(Color.ORANGE);
		 
		  StackPane menzilJetonSP = new StackPane();
		  menzilJetonSP.getChildren().add(menzilJetonDıs);
		  menzilJetonSP.getChildren().add(turuncuUcgen);
		  menzilJetonSP.getProperties().put("tur", "range");
		  return menzilJetonSP;
	}
	//The design method of eye token allows the hunter to see monsters on screen.
	public static StackPane gozJetonuCagirma() {
		  Circle gozJeton = new Circle(15);
		  gozJeton.setFill(Color.BLUE);
		  gozJeton.setStroke(Color.BLACK);
		 
		  Ellipse gozElıps = new Ellipse(12,7);
		  gozElıps.setFill(Color.WHITESMOKE);
		  gozElıps.setStroke(Color.BLACK);
		  Circle gozRenk = new Circle(5);
		  gozRenk.setFill(Color.DEEPSKYBLUE);
		  Circle gozBaby = new Circle(3);
		  gozBaby.setFill(Color.BLACK);
		  Circle miniBeyaz = new Circle(0.75);
		  miniBeyaz.setFill(Color.WHITE);
		 
		  StackPane gozJetonSP = new StackPane();
		  gozJetonSP.getChildren().add(gozJeton);
		  gozJetonSP.getChildren().add(gozElıps);
		  gozJetonSP.getChildren().add(gozRenk);
		  gozJetonSP.getChildren().add(gozBaby);
		  gozJetonSP.getChildren().add(miniBeyaz);
		  
		  gozJetonSP.getProperties().put("tur","goz");
		  return gozJetonSP;
	}
	//EXTRA: The design method of the speed token . allows the hunter to run.
	public static StackPane hizJetonuCagirma() {
		  Circle hizJeton = new Circle(15);
		  hizJeton.setFill(Color.YELLOW);
		  hizJeton.setStroke(Color.BLACK);
		  
		  Polygon ucgen1= new Polygon(0.0,-4.0,
				                     -6.0,2.0,
				                     6.0,2.0);
		  ucgen1.setFill(Color.GREEN);
		  ucgen1.setTranslateY(-3);
		
		  Polygon ucgen2=new Polygon(0.0,0.0,
				                    -6.0,6.0,
				                     6.0,6.0); 
		  ucgen2.setFill(Color.GREEN);
		  ucgen2.setTranslateY(3);
		 
		  StackPane hizJetonSP= new StackPane();
		  hizJetonSP.getChildren().add(hizJeton);
		  hizJetonSP.getChildren().add(ucgen1);
		  hizJetonSP.getChildren().add(ucgen2);
  
		  return hizJetonSP;
	}
}
