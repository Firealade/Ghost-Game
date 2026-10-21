package gameProject;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import java.util.ArrayList;

/*  Muhammed Kaban (Student ID: 150124026) 
Amaç: Bu class üçüncü seviyede ortaya çıkan özel Wisp canavarlarının
 etrafındaki halkaların ve geometrik çizgilerin kendi etrafında
 dönmesini sağlayan görsel efekt animasyonunu yönetir. 
*/ 

public class Animasyon {
    private static double wispAcisi=0;
      
   public static void wispAnimasyonu(ArrayList<Group> canavarListesi) {  
	   /* Bu metotta aktif canavar listesini tarayarak türü wisp olanları bulup
	   bu canavar grubunun altındaki dönüş çizgilerini
	    yakalayarak her karede Rotate açısını arttırıyoruz
	   */ 
	   wispAcisi +=2.0;
    	  if(wispAcisi >=360) {wispAcisi =0;}
    	  
    	  for(Group canavar: canavarListesi) {
    	   String tur = (String) canavar.getProperties().get("tur");
    		  
    	   if("wisp".equals(tur)) {
    		for(Node node : canavar.getChildren()) {
    		if(node instanceof Line && "wisp_rotate".equals(node.getId())) {
    		   for(Transform trns : node.getTransforms()) {
    		   if(trns instanceof Rotate) {
    			((Rotate) trns).setAngle(wispAcisi);}}}}}}
   	  
   }
}
