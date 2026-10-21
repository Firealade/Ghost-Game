package gameProject;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

/*  Muhammed Kaban (Student ID: 150124026)
Amaç: Bu class JavaFX sahnesi (Scene) üzerindeki klavye basma/bırakma 
ve fare- hareket olaylarını yöneterek oyun kontrollerinin kesintisiz çalışmasını sağlar. 
*/ 

public class KlavyeAtalama {
	public static boolean yukari = false;
	public static boolean asagi = false;
	public static boolean sola = false;
	public static boolean saga = false;
	public static boolean vakumAktif = false;
	public static boolean hackAktif = false;
	public static double mouseX = 0;
	public static double mouseY=0;
	
	public static void klavyeVeFare(Scene sahne) {
/* Bu metot basılan tuşları dinleyen ve yöneten event handler'ları bağlar
, tuş bırakıldığındaysa ilgili yön hareketini durdurur ve
farenin o anki anlık ekran koordinatlarını günceller
*/
		
		sahne.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.W) {yukari = true;}
			if (e.getCode() == KeyCode.S) {asagi = true;}
			if (e.getCode() == KeyCode.A) {sola = true;}
			if (e.getCode() == KeyCode.D) {saga = true;}
			if (e.getCode() == KeyCode.X) {hackAktif =true;}
			if (e.getCode() == KeyCode.SPACE) {vakumAktif=true;}
		});
		sahne.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.W) {yukari = false;}
			if (e.getCode() == KeyCode.S) {asagi = false;}
			if (e.getCode() == KeyCode.A) {sola = false;}
			if (e.getCode() == KeyCode.D) {saga = false;}
			if (e.getCode() == KeyCode.X) {hackAktif = false;}
			if (e.getCode() == KeyCode.SPACE) {vakumAktif=false;}
		});
        
		sahne.setOnMouseMoved(e->{
			mouseX=e.getX();
			mouseY=e.getY();
		});
		sahne.setOnMouseDragged(e->{
			mouseX=e.getX();
			mouseY=e.getY();
		});
	}
}
