package gameProject;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.Color;
/* Muhammed Kaban (Student ID: 150124026)
Amaç : Bu class oyundaki çarpışma testlerini (Sınır tespiti,
vakum ışığı-canavar kesişimi, avcı-canavar teması)
kontrol ederek oyunun mekaniklerinin fiziksel sonuçlarını yönettiğimiz kısımdır.
*/

public class FizikselTemas {

	public static void canavarlarVeVakum(double solKenar, double sagKenar, double ustKenar, double altKenar,boolean vakumlar) {
/*Bu metot Canavarların harita sınırlarından sekmesini kontrol eder,
  avcının tarama feneri  veya göz jetonuna göre görünürlüklerini ayarlar
  ve son olarak ta  vakumlanma durumlarında canavarı küçülterek
  canını düşürür ve canı bitenleri haritadan silip skoru günceller. 
*/

		for (int i = AnaOyunMotoru.canavarlar.size() - 1; i >= 0; i--) {
			Group canavar = AnaOyunMotoru.canavarlar.get(i);
			int hizX = (int) canavar.getProperties().get("hizX");
			int hizY = (int) canavar.getProperties().get("hizY");

			double konumX = canavar.getLayoutX() + hizX;
			double konumY = canavar.getLayoutY() + hizY;

			if (konumX < solKenar) {
				konumX = solKenar;
				hizX = Math.abs(hizX);
				canavar.getProperties().put("hizX", hizX);
			} else if (konumX > sagKenar) {
				konumX = sagKenar;
				hizX = -Math.abs(hizX);
				canavar.getProperties().put("hizX", hizX);
			}

			if (konumY < ustKenar) {
				konumY = ustKenar;
				hizY = Math.abs(hizY);
				canavar.getProperties().put("hizY", hizY);
			} else if (konumY > altKenar) {
				konumY = altKenar;
				hizY = -Math.abs(hizY);
				canavar.getProperties().put("hizY", hizY);
			}

			canavar.setLayoutX(konumX);
			canavar.setLayoutY(konumY);

			Bounds fenerSiniri = AvciVePaneller.tarayanUcgen
					.localToScene(AvciVePaneller.tarayanUcgen.getBoundsInLocal());
			Bounds canavarSiniri = canavar.localToScene(canavar.getBoundsInLocal());

			if (KlavyeAtalama.hackAktif || JetonYonetimi.gozAktifligi
					|| (AvciVePaneller.tarayanUcgen.isVisible() && fenerSiniri.intersects(canavarSiniri))) {
				canavar.setVisible(true);
			} else {
				canavar.setVisible(false);
			}

			if (vakumlar && fenerSiniri.intersects(canavarSiniri) && ArayuzCizen.dikdortgenVacum.getHeight() > 0) {
				ColorAdjust solgunluk = new ColorAdjust();
				solgunluk.setSaturation(-1.0);
				solgunluk.setBrightness(0.6);
				canavar.setEffect(solgunluk);

				double anlikBoyut = canavar.getScaleX();
				if (anlikBoyut > 0.40) {
					canavar.setScaleX(anlikBoyut - 0.005);
					canavar.setScaleY(anlikBoyut - 0.005);
				}

				double anlikCan = (double) canavar.getProperties().get("can");
				anlikCan -= 1.0;
				canavar.getProperties().put("can", anlikCan);

				if (anlikCan <= 0) {
					int alinanPuan = (int) canavar.getProperties().get("puan");
					AnaOyunMotoru.anlikSkor += alinanPuan;
					ArayuzCizen.metinSkor.setText("SCORE: " + AnaOyunMotoru.anlikSkor);

					AnaOyunMotoru.anaAlan.getChildren().remove(canavar);
					AnaOyunMotoru.canavarlar.remove(i);

					if (AnaOyunMotoru.canavarlar.isEmpty()) {
						AnaOyunMotoru.zamanlayici.stop();
						AvciVePaneller.winPanel.setVisible(true);
					}
				}
			} else {
				canavar.setEffect(null);
			}
		}
	}

	public static void avciHasariKontrolu() {
/* Bu kısımda Avcı ile canavarların birbirine temas edip etmediğini ölçüyoruz
  eğer temas varsa avcıyı kırmızıya boyayarak canını düşürüp sağlık 
  barını güncelliyoruz. Sağlık sıfırlanırsa da oyunu durduruyoruz
*/ 
	
		boolean hasarAlir = false;
		double alinanHasar = 0.0;

		Bounds avciSinir = AvciVePaneller.avciBody.localToScene(AvciVePaneller.avciBody.getBoundsInLocal());

		for (Group canavar : AnaOyunMotoru.canavarlar) {
			Bounds canavarSinir = canavar.localToScene(canavar.getBoundsInLocal());

			if (avciSinir.intersects(canavarSinir)) {
				hasarAlir = true;
				alinanHasar = (double) canavar.getProperties().get("hasar");
				break;
			}
		}
		if (hasarAlir) {
			AvciVePaneller.avciBody.setFill(Color.RED);
			AnaOyunMotoru.anlikSaglik -= alinanHasar;

			double saglikOran = AnaOyunMotoru.anlikSaglik / AnaOyunMotoru.maxSaglik;
			if (saglikOran < 0) {
				saglikOran = 0;
			}

			double yeniBoy = 200 * saglikOran;
			ArayuzCizen.dikdortgenSaglik.setHeight(yeniBoy);
			ArayuzCizen.dikdortgenSaglik.setY(40 + (200 - yeniBoy));

			if (AnaOyunMotoru.anlikSaglik <= 0) {
				AnaOyunMotoru.zamanlayici.stop();
				AvciVePaneller.losePanel.setVisible(true);
			}
		} else {
			AvciVePaneller.avciBody.setFill(Color.DARKTURQUOISE);
		}
	}
}
