package gameProject;

import javafx.application.Application;
import javafx.stage.Stage;

//FURKAN DURAN 150124085 _ MEHMET KEREM İMAMOGLU 150125002
/*
 Bu class uygulamamızın ana girişidir.
  Application classından miras alarak (extends) uygulamamızı başlatır.
   Oyunun ilk açılışını, ana menü ve seviye seçim ekranları arasındaki scene 
   geçişlerini ve asıl oyun motorunun tetiklenmesini bu class üzerinden
    yönettik. 
*/


public class GhostHunter extends Application {
	/*JavaFX uygulamasının çalışmaya
 başladığı ilk metottur. Uygulama açılır açılmaz
 ConfigReader classını çağırarak ayarları (config.txt)
 hafızaya alıyorum. Ardından pencere başlığını 
 belirleyip oyuncuyu Ana Menü ile karşılıyorum. */ 

	@Override
	public void start(Stage primaryStage) {
		ConfigReader.ayarlariOku();
		primaryStage.setTitle("Ghost Hunter Inc.");
		AnaEkran.ekraniGoster(primaryStage, this);
	}
/* Kullanıcımız oyuna başlamak istediğinde (Ana menüden veya level
  seçiminden) bu metot çağrılır. Seçilen level bilgisini alarak
 asıl oyun döngüsünü barındıran AnaOyunMotoru classımızı
  başlatan  metottur. */	
	public void oyunStart(Stage primaryStage, int level) {
		AnaOyunMotoru.oyunStart(primaryStage, this, level);
	}
/* Kullanıcımızı ana menüde "Select Level" butonuna
tıkladığında level seçim ekranına yönlendiren metottur. */	
	public void seviyeSecimScreen(Stage primaryStage) {
		LevelSecimi.SeviyeyiGoster(primaryStage, this);
	}
// Klasik main metodumuzda launch() komutu ile JavaFX'in arka plandaki işlerini başlatırız
	public static void main(String[] args) {
		launch(args);
	}
}
