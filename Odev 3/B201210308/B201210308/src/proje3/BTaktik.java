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

public class BTaktik extends Taktik{
	
	public BTaktik(String ad)
	{
		super(ad);		
	}
	
	public int Savas()
	{
		Random random = new Random();
		int randomNumber = random.nextInt(10);
		return randomNumber;
	}

}
