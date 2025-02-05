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

import java.util.Date;

public abstract class Uretim {

	private Date olusturulmaTarihi;
	private String ad;
	
	protected Uretim(String ad)
	{
		olusturulmaTarihi = new Date();
		this.ad = ad;
	}
	public void setAd(String ad)
	{
		this.ad=ad;
	}
	public Date OlusturulmaTarihi()
	{
		return olusturulmaTarihi;
	}
	public abstract int Uret();
}
