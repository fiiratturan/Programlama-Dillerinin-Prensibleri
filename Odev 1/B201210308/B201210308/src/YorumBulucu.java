/**
*
* @author Fırat TURAN firat.turan2@ogr.sakarya.edu.tr
* @since 23.04.2023
* <p>
* 	B201210308
* 	2. Öğretim A Grubu
* </p>
*/


import java.io.*;
import java.util.Scanner;
import java.util.regex.*;
public class YorumBulucu {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Bir dosya yolu giriniz (Örnek: C:\\Users\\firat\\Documents\\ornek.java): ");

		String dosyaYolu = scanner.nextLine();
		System.out.println();
		yorumSayici(dosyaYolu);
		
		scanner.nextLine();
		
		scanner.close();

	}
	
	static void yorumSayici(String dosyaYolu)
	{
		
		File dosya = new File(dosyaYolu);
        String satir;
        String fonksiyonAdi = "";
        String tekSatirYorum = "";
        String cokSatirYorum = "";
        String javadocYorum = "";
        
        boolean cokSatirYorumIci = false;
        boolean javadocIci = false;
        boolean fonksiyonIci = false;
        int tekSatirYorumSayisi = 0;
        int cokSatirYorumSayisi = 0;
        int javadocSayisi = 0;

        String desen ="\\b(public|private|protected)?\\s+\\b([a-zA-Z]+)\\s*\\([^\\)]*\\)\\s*\\{";
        Pattern pattern = Pattern.compile(desen);
        
        textTemizleme("teksatir.txt");
        textTemizleme("coksatir.txt");
        textTemizleme("javadoc.txt");
       
        

        try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosya))) {
            while ((satir = okuyucu.readLine()) != null) {
            	
            	Matcher matcher = pattern.matcher(satir);
                if (matcher.find()) {
                	fonksiyonAdi = matcher.group(2);
                	fonksiyonIci=true;
                }

            	for (int i = 0; i < satir.length(); i++) {
            		
            		
            		if (satir.charAt(i)=='}' && fonksiyonIci)
	        		{
	        			System.out.println("Fonksiyon adı: " + fonksiyonAdi);
	                    System.out.println("            Tek satır yorum sayısı   : " + tekSatirYorumSayisi);
	                    System.out.println("            Çok satırlı yorum sayısı : " + cokSatirYorumSayisi);
	                    System.out.println("            Javadoc sayısı           : " + javadocSayisi);
            			System.out.println("-----------------------------------------");
            			
            			textYazma("teksatir.txt",tekSatirYorum,fonksiyonAdi);
            			textYazma("coksatir.txt",cokSatirYorum,fonksiyonAdi);
            			textYazma("javadoc.txt",javadocYorum,fonksiyonAdi);
            			
	                    fonksiyonAdi = "";
	                    tekSatirYorum = "";
	        	        cokSatirYorum = "";
	        	        javadocYorum = "";
	                    fonksiyonIci = false;
	                    cokSatirYorumIci = false;
	                    javadocIci = false;
	                    tekSatirYorumSayisi = 0;
	                    cokSatirYorumSayisi = 0;
	                    javadocSayisi = 0;
	        		}
            		
            		if(cokSatirYorumIci==true || javadocIci==true )
            		{
            			
            			while(i<satir.length()-1 && satir.charAt(i)!='*' && satir.charAt(i+1)!='/')
	                    {
            				
            				if(cokSatirYorumIci==true)
            				{
            					cokSatirYorum=cokSatirYorum + satir.charAt(i);
            				}
            				else if(javadocIci==true)
            				{
            					javadocYorum=javadocYorum + satir.charAt(i+1);
            				}
            				i++;
            				
	                    }
            			
            			if(i<satir.length()-1 && satir.charAt(i)=='*' && satir.charAt(i+1)=='/')
            			{
            				
            				
            				cokSatirYorumIci=false;
            				javadocIci=false;
            				
            			}
            		}
            		
            		if(satir.charAt(i)=='"' && cokSatirYorumIci==false && javadocIci==false)
            		{
            			i++;
            			while(i<satir.length() && satir.charAt(i) !='"')
            			{
            				i++;
            			}
            			i++;
            			
            		}
            		
            		if(i<satir.length()-1 && satir.charAt(i)=='/' && cokSatirYorumIci==false && javadocIci==false)
                    {
                    	if(satir.charAt(i+1)=='/')
                    	{
                    		tekSatirYorumSayisi++;
                    		tekSatirYorum = tekSatirYorum + satir.substring(i);
                    		tekSatirYorum=tekSatirYorum + "\n";
                    		break;
                    	}else if (satir.charAt(i+1)=='*')
                    	{
                    		if(i<satir.length()-2 && satir.charAt(i+2)=='*')
                    		{
                    			javadocSayisi++;
                    			javadocIci=true;
                    			i=i+2;
                    		}else
                    		{
                    			cokSatirYorumSayisi++;
                    			cokSatirYorumIci = true;
                    			i=i+2;
                    		}
                    	}
                    }
 
                }
            	

            }
            

        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
	
	static void textYazma(String dosyaAdi,String yorum,String fonksiyonAdi)
	{
		yorum = "Fonksiyon adı :" + fonksiyonAdi +"\n" + yorum + "\n" + "--------------------------------------------------------------"+"\n";
		
		try 
		{
			FileWriter dosyaYazici = new FileWriter(dosyaAdi,true);
			BufferedWriter yazdirici = new BufferedWriter(dosyaYazici);
			
			yazdirici.write(yorum);
			
			yazdirici.close();
		}
		catch (IOException e) 
		{
            System.out.println("Dosya yazma hatası oluştu: " + e.getMessage());
        }
	}
	
	static void textTemizleme(String dosyaAdi)
	{
		try 
		{
			File file = new File(dosyaAdi);
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write("");
			fileWriter.flush();
			fileWriter.close();
		}catch (IOException e) {
            System.out.println("Dosya boşaltma işlemi başarısız: " + e.getMessage());
        }
	}

}
