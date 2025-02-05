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

import java.util.Scanner;
import java.util.Random;


public class main {

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Koloni Sayılarını Giriniz: ");
	    String girilenDeger = scanner.nextLine();
		
		Oyun o = new Oyun(kodAyirma(girilenDeger));
		
		o.koloniSavas();	
	}
	
	private static int[] kodAyirma(String kod)
	{
		String[] kodlar = kod.split("\\s+");
		
		int[] koloniler = new int[kodlar.length];
		
		for (int i = 0; i < kodlar.length; i++) 
		{
			koloniler[i] = Integer.parseInt(kodlar[i]);
        }
		
		return koloniler;
	}
	
	
}
