/**
*
* @author Fırat TURAN firat.turan2@ogr.sakarya.edu.tr
* @since 31.05.2023
* <p>
* 	B201210308
* 	2. Öğretim A Grubu
* </p>
*/

package proje3;

import java.util.Random;

public class ATaktik extends Taktik{
	
	public ATaktik(String ad)
	{
		super(ad);		
	}
	
	public int Savas()
	{
		int enBuyukSayi=0;
		Random random = new Random(); 
		int rastgeleSayi = random.nextInt(1000);
		int rastgeleSayi2 = random.nextInt(1000);
		
		if(rastgeleSayi>rastgeleSayi2)
		{
			enBuyukSayi=rastgeleSayi;
		}else
		{
			enBuyukSayi=rastgeleSayi2;
		}
		return enBuyukSayi;
	}

}
