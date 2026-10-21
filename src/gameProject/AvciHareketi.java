package gameProject;

/* Muhammed Kaban (Student ID: 150124026)
Amaç :Bu class  klavyeden alınan girdilere göre avcı karakterinin
koordinatlarını günceller ve harita sınırlarının dışına çıkmasını
engeller. Ardından da farenin konumuna göre avcıyı döndürür 
*/ 

public class AvciHareketi {
	
	public static void avciHareketi(double solKenar, double sagKenar, double ustKenar, double altKenar) {
		/* Bu metotta Avcının mevcut konumunu alarak klavye yön 
		tuşlarına göre yeni koordinatları hesaplayıp  harita dışı taşma 
		kontrollerini yapıyoruz ve karakterin açısını fare imlecine doğru eşitliyoruz.
		*/

		int hiz = 2;
        if(JetonYonetimi.hizAktifligi) {
        Integer jetonHizi = ConfigReader.ayarlar.get("speed_token_increase");
        	if(jetonHizi != null) {
        		hiz+= jetonHizi;
        	}else {
        		hiz +=3;
        	}
        }
		double yeniX = AvciVePaneller.avci.getLayoutX();
		double yeniY = AvciVePaneller.avci.getLayoutY();

		if (KlavyeAtalama.yukari) {yeniY -= hiz;}
		if (KlavyeAtalama.asagi) {yeniY += hiz;}
		if (KlavyeAtalama.sola) {yeniX -= hiz;}
		if (KlavyeAtalama.saga) {yeniX += hiz;}

		if (yeniX < solKenar) {yeniX = solKenar;}
		if (yeniX > sagKenar) {yeniX = sagKenar;}
		if (yeniY < ustKenar) {yeniY = ustKenar;}
		if (yeniY > altKenar) {yeniY = altKenar;}
		
		AvciVePaneller.avci.setLayoutX(yeniX);
		AvciVePaneller.avci.setLayoutY(yeniY);
		
		double fareX = KlavyeAtalama.mouseX - AvciVePaneller.avci.getLayoutX();
		double fareY = KlavyeAtalama.mouseY -AvciVePaneller.avci.getLayoutY();
		double yön = Math.toDegrees(Math.atan2(fareY, fareX));
		AvciVePaneller.avciDonusu.setAngle(yön);
	}
}
