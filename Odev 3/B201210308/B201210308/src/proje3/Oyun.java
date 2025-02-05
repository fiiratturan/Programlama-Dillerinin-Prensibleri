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

public class Oyun {
	Koloni[] koloniler;
	int turSayisi = 0;

	public Oyun(int[] koloniler)
	{
		this.koloniler = koloniOlusturma(koloniler);
	}
	
	private Koloni[]  koloniOlusturma(int[] kodlar)
	{
		this.koloniler = new Koloni[kodlar.length];
		for (int i = 0; i < kodlar.length; i++) 
		{
			koloniler[i]  = new Koloni(kodlar[i]);
		}
		
		return koloniler;
	}
	
	public void koloniSavas()
	{
		int sayi = koloniler.length;
		double artisOrani = 0.2;
		
		ekranaYazdir();
		while(tekKolononiKontrol())
		{
			
			for(int i=0;i<sayi;i++)
			{
				if(koloniler[i].isAktif())
				{
					for(int j=i+1;j<sayi;j++)
					{
						if(koloniler[j].isAktif())
						{
							ikiKoloniKarsilasma(koloniler[i],koloniler[j]);
							if(!koloniler[i].isAktif())
							{
								break;
							}
						}
					}
				}
			}
			
			for(int k=0;k<sayi;k++)
			{
				if(koloniler[k].isAktif())
				{
					int yemekStok;
					int populasyon;
					int uretim;
					yemekStok = koloniler[k].getYemekStok();
					uretim = koloniler[k].getUretim().Uret();
					populasyon = koloniler[k].getPopulasyon();
					
					populasyon = (int)(populasyon * (1 + artisOrani));
					koloniler[k].setPopulasyon(populasyon);
					
					yemekStok = yemekStok + uretim;
					
					yemekStok = yemekStok-(populasyon*2);
					
					koloniler[k].setYemekStok(yemekStok);
					
					if(koloniler[k].getYemekStok()<0)
					{
						koloniler[k].setAktif(false);
					}
				}
			}	
			
			turSayisi++;
			ekranaYazdir();	
		}
	}
	
	private void ikiKoloniKarsilasma(Koloni koloni1,Koloni koloni2)
	{
		int koloni1Sayi=koloni1.getTaktik().Savas();
		int koloni2Sayi=koloni2.getTaktik().Savas();

		if(koloni1Sayi>koloni2Sayi)
		{
			koloniGuncelleme(koloni1,koloni2,koloni1Sayi,koloni2Sayi);

		}else if(koloni2Sayi>koloni1Sayi)
		{
			koloniGuncelleme(koloni2,koloni1,koloni2Sayi,koloni1Sayi);
		}else if(koloni2Sayi==koloni1Sayi)
		{
			Random random = new Random();
			int randomNumber = random.nextInt(1);
			if(randomNumber==0)
			{
				koloniGuncelleme(koloni1,koloni2,koloni1Sayi,koloni2Sayi);
			}else
			{
				koloniGuncelleme(koloni2,koloni1,koloni2Sayi,koloni1Sayi);
			}
		}
		
	}
	
	private boolean tekKolononiKontrol()
	{
		int sayi = koloniler.length;
		int aktifKoloniSayisi=0;
		
		for(int i=0;i<sayi;i++)
		{
			if(koloniler[i].isAktif())
			{
				aktifKoloniSayisi++;
			}
		}	
		if(aktifKoloniSayisi>1)
		{
			return true;
		}else
		{
			return false;
		}
	
	}
	
	public void koloniGuncelleme(Koloni kazanan,Koloni kaybeden,int kazananDeger,int kaybedenDeger)
	{
		
		int fark=0;
		double koloni2Populasyon=kaybeden.getPopulasyon();
		double koloni1Yemek=kazanan.getYemekStok();
		double koloni2Yemek=kaybeden.getYemekStok();

		int yuzde=0;
		kazanan.Zafer();
		kaybeden.Kayıp();
		fark = kazananDeger - kaybedenDeger;
		yuzde = (int)Math.ceil(fark/10);
		
		koloni2Populasyon=koloni2Populasyon-(int)Math.ceil(koloni2Populasyon*yuzde/100);
		kaybeden.setPopulasyon((int)koloni2Populasyon);

		koloni2Yemek=koloni2Yemek-(int)Math.ceil(koloni2Yemek*yuzde/100);	
		kaybeden.setYemekStok((int)koloni2Yemek);

		koloni1Yemek=koloni1Yemek + (int)Math.ceil(koloni2Yemek*yuzde/100);
		kazanan.setYemekStok((int)koloni1Yemek);
		
		
		if(kaybeden.getPopulasyon()<=0||kaybeden.getYemekStok()<=0)
		{
			kaybeden.setAktif(false);
		}
	}
	
	public void ekranaYazdir()
	{
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Tur Sayısı :"+ turSayisi);
	    System.out.format("%-10s", "Koloni");
		System.out.format("%-15s", "Populasyon");
		System.out.format("%-15s", "Yemek Stogu");
		System.out.format("%-10s", "Kazanma");
		System.out.format("%-10s", "Kaybetme");
		System.out.println();

		for (int i = 0; i < koloniler.length; i++) 
		{	
			if(koloniler[i].isAktif())
			{
				System.out.format("%-10s", koloniler[i].getSembol());
				System.out.format("%-15s", koloniler[i].getPopulasyon());
				System.out.format("%-15s", koloniler[i].getYemekStok());
				System.out.format("%-10s", koloniler[i].getKazanma());
				System.out.format("%-10s", koloniler[i].getKaybetme());
				System.out.println();
			}
			else
			{
				System.out.format("%-10s",koloniler[i].getSembol());
				System.out.format("%-15s","--");
				System.out.format("%-15s","--");
				System.out.format("%-10s","--");
				System.out.format("%-10s","--");
				System.out.println();
			}
		}
		System.out.println("-------------------------------------------------------------------");
		System.out.println();
	}
	
	
}
