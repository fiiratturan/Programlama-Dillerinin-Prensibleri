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
//import java.nio.charset.StandardCharsets;

public class Koloni {
	private int populasyon;
	private int yemekStok;
	private boolean aktif;
	private String sembol;
	private Taktik taktik;
	private Uretim uretim;
	private int kazanma;
	private int kaybetme;
	
	
	protected Koloni(int populasyon)
	{
		this.populasyon=populasyon;
		this.yemekStok = populasyon * populasyon;
		this.aktif=true;
		rastgeleTaktik();
		rastgeleUretim();
		this.sembol=sembolOluştur();
	}
	
	public void setPopulasyon(int populasyon)
	{
		this.populasyon=populasyon;
	}
	
	public int getPopulasyon()
	{
		return this.populasyon;
	}
	
	public void setYemekStok(int yemekStok)
	{
		this.yemekStok=yemekStok;
	}
	
	public int getYemekStok()
	{
		return this.yemekStok;
	}
	
	public void setSembol(String sembol)
	{
		this.sembol=sembol;
	}
	
	public String getSembol()
	{
		return this.sembol;
	}
	
	public boolean isAktif()
	{
		return aktif;
	}
	
	public void setAktif(boolean aktif)
	{
		this.aktif = aktif;
	}

	public Taktik getTaktik() {
		return taktik;
	}

	public void setTaktik(Taktik taktik) {
		this.taktik = taktik;
	}
	
	public Uretim getUretim() {
		return uretim;
	}

	public void setUretim(Uretim uretim) {
		this.uretim = uretim;
	}
	
	public int getKazanma() {
		return kazanma;
	}
	
	public int getKaybetme() {
		return kaybetme;
	}
	
	
	private void rastgeleTaktik()
	{
		Random random = new Random();
		int randomNumber = random.nextInt(1);
		if(randomNumber==0)
		{
			this.setTaktik(new ATaktik("a"));
		}else
		{
			this.setTaktik(new BTaktik("b"));
		}
	}
	
	private void rastgeleUretim()
	{
		Random random = new Random();
		int randomNumber = random.nextInt(1);
		if(randomNumber==0)
		{
			this.setUretim(new AUretim("a"));
		}else
		{
			this.setUretim(new BUretim("b"));
		}
	}
	
	private String sembolOluştur()
	{
		/*Random random = new Random(); 
		byte[] randomBytes = new byte[1];

		random.nextBytes(randomBytes); 
		String randomChar = new String(randomBytes, StandardCharsets.UTF_8);
		
		return randomChar;*/	
		Random random = new Random();
        String karakterler = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()/?-<>|0123456789";

        char rastgeleKarakter = karakterler.charAt(random.nextInt(karakterler.length()));
          
        return String.valueOf(rastgeleKarakter);
	}
	
	public void Zafer() 
	{
		this.kazanma++;
	}
	
	public void Kayıp() 
	{
		this.kaybetme++;
	}	
}
