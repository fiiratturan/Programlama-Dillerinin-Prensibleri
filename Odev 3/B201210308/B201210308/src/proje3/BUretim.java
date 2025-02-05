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

public class BUretim extends Uretim {
	
	public BUretim(String ad)
	{
		super(ad);		
	}
	
	public int Uret()
	{
		//long anlikSaniye = System.currentTimeMillis() / 1000;
		//anlikSaniye = anlikSaniye * anlikSaniye;
		Random random = new Random();
		int randomNumber = random.nextInt(10);
		return randomNumber;
	}


}
