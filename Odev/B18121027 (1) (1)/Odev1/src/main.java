/**
*
@author ibrahim ŞAHİN ibrahim.sahin5@ogr.sakarya.edu.tr
@since 07.04.2024
<p>
B18121027
1. Öğretim C Grubu
</p>
*/
import java.io.File;
import java.util.Scanner;


public class main {

	public static void main(String[] args) {
		

		Scanner scanner = new Scanner(System.in);
		System.out.print("Bir url yolu giriniz (Örnek: https://github.com/mfadak/Odev1Ornek.git)");
		
		String url = scanner.nextLine();
		System.out.println();
		
		DosyaKlonlama klonla = new DosyaKlonlama();
		klonla.DosyaKlonla(url);
		DosyaIslemleri dosyaIslemleri = new DosyaIslemleri();
		dosyaIslemleri.javaDosyalarınıBul("./clone");
		scanner.nextLine();
		scanner.close();

	}
	

}

