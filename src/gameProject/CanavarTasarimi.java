package gameProject;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

// FURKAN DURAN 150124085
//GENERAL PURPOSE OF CLASS: Design and appearance for 3 different monsters.

public class CanavarTasarimi {
   //The method that designed the white ghost.
	public static Group defaultHayaletUreten() {
	      Group ghost = new Group();
		 
	      Circle defaultGhost = new Circle(0,0,25);
		  defaultGhost.setFill(Color.GHOSTWHITE);
		  
		  Rectangle defaultGhostGovde = new Rectangle(-25,0,50,30);
		  defaultGhostGovde.setFill(Color.GHOSTWHITE);
		  
		  Circle solGozDıs = new Circle(5);
		  solGozDıs.setFill(Color.WHITE);
		  solGozDıs.setCenterX(-10);
		  solGozDıs.setCenterY(-5);
		  
		  Circle solGoz = new Circle(3);
		  solGoz.setFill(Color.BLACK);
		  solGoz.setCenterX(-10);
		  solGoz.setCenterY(-3);
		  
		  Circle sagGozDıs = new Circle(5);
		  sagGozDıs.setFill(Color.WHITE);
		  sagGozDıs.setCenterX(10);
		  sagGozDıs.setCenterY(-5);
		 
		  Circle sagGoz = new Circle(3);
		  sagGoz.setFill(Color.BLACK);
		  sagGoz.setCenterX(10);
		  sagGoz.setCenterY(-3);
		  
		  ghost.getChildren().add(defaultGhost);
		  ghost.getChildren().add(defaultGhostGovde);
		  ghost.getChildren().add(solGozDıs);
		  ghost.getChildren().add(sagGozDıs);
		  ghost.getChildren().add(solGoz);
		  ghost.getChildren().add(sagGoz);
		  ghost.getProperties().put("tur","ghost");
		  return ghost;
	}
  //The method that designed for the Purple spiky monster.
  public static Group ripperUreten() {
		
	    Group ripper = new Group();
		
		Polygon morDiken = new Polygon(
				   42,0,20, 5,
				   36,17,16, 13,
				   20,30,6, 17,
				   0,35,-6, 17,
				  -20,30,-16, 13,
				  -36,17,-20, 5,
				  -42,0,-20,-5,
				  -36,-17,-16,-13,
				  -20,-30,-6,-17,
				   0,-35,6,-17,
				   20,-30,16,-13,
				   36,-17,20,-5);
				  
		 morDiken.setFill(Color.PURPLE);
				  
		 Ellipse dısElips = new Ellipse(26,20);
		 dısElips.setFill(Color.PURPLE);
				  
		 Circle ripperBody = new Circle(13);
		 ripperBody.setFill(Color.BLACK);
				  
	     Circle rippersolGoz = new Circle(-6,0,3);
		 rippersolGoz.setFill(Color.RED);
		 
		 Circle rippersagGoz = new Circle(6,0,3);
		 rippersagGoz.setFill(Color.RED);
				  
		 ripper.getChildren().add(dısElips);
		 ripper.getChildren().add(morDiken);
		 ripper.getChildren().add(ripperBody);
		 ripper.getChildren().add(rippersolGoz);
		 ripper.getChildren().add(rippersagGoz);
		 ripper.getProperties().put("tur","ripper");
		 return ripper;
	}
  //The method that designed for red , animated monster.
  public static  Group wispUreten() {
		
	    Group wisp = new Group();
		 
		Circle wispBody = new Circle(16);
	    wispBody.setFill(Color.RED);
		
		Circle wispsolGozDıs = new Circle(-5,-2,4);
		wispsolGozDıs.setFill(Color.LIGHTYELLOW);  
		
		Circle wispsagGozDıs = new Circle(5,-2,4);
		wispsagGozDıs.setFill(Color.LIGHTYELLOW);
		  
		Circle wispsolGoz = new Circle(-5,-2,2);
		wispsolGoz.setFill(Color.GOLD);
		
		Circle wispsagGoz = new Circle(5,-2,2);
		wispsagGoz.setFill(Color.GOLD);
		  
	    Line cizgi1 = new Line(25,0,38,0);
		cizgi1.setStroke(Color.RED); 
		cizgi1.setStrokeWidth(4);
		Line cizgi2 = new Line(15,25,20,33);
		cizgi2.setStroke(Color.RED); 
		cizgi2.setStrokeWidth(4);
		Line cizgi3 = new Line(-15,25,-20,33);
		cizgi3.setStroke(Color.RED); 
		cizgi3.setStrokeWidth(4);
		Line cizgi4 = new Line(-29,0,-38,0);
		cizgi4.setStroke(Color.RED);
		cizgi4.setStrokeWidth(4);
		Line cizgi5 = new Line(-15,-25,-20,-33);
		cizgi5.setStroke(Color.RED);
		cizgi5.setStrokeWidth(4);
		Line cizgi6 = new Line(15, -25, 20, -33);
		cizgi6.setStroke(Color.RED); 
		cizgi6.setStrokeWidth(4);
		  
		
		  wisp.getChildren().add(wispBody);
		  wisp.getChildren().add(wispsagGozDıs);
		  wisp.getChildren().add(wispsolGozDıs);
		  wisp.getChildren().add(wispsolGoz);
		  wisp.getChildren().add(wispsagGoz);
		  wisp.getChildren().add(cizgi1);
		  wisp.getChildren().add(cizgi2);
		  wisp.getChildren().add(cizgi3);
		  wisp.getChildren().add(cizgi4);
		  wisp.getChildren().add(cizgi5);
		  wisp.getChildren().add(cizgi6);
		 
		  //I coded around the monster it rotate around the monster..
		  //I called the return mechanism from animasyon class using .setId().
		  Rotate donus = new Rotate(0,0,0);
		  cizgi1.getTransforms().add(donus);
		  cizgi1.setId("wisp_rotate");
		  cizgi2.getTransforms().add(donus);
		  cizgi2.setId("wisp_rotate");
		  cizgi3.getTransforms().add(donus);
		  cizgi3.setId("wisp_rotate");
		  cizgi4.getTransforms().add(donus);
		  cizgi4.setId("wisp_rotate");
		  cizgi5.getTransforms().add(donus);
		  cizgi5.setId("wisp_rotate");
		  cizgi6.getTransforms().add(donus);
		  cizgi6.setId("wisp_rotate");
		   
		 
		  wisp.getProperties().put("tur","wisp");
		 
		  return wisp;
	}
}
