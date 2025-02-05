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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DecimalFormat;
public class DosyaIslemleri {
	
	 public void javaDosyalarınıBul(String dosyaYolu) {
		 	
		 File klasor = new File(dosyaYolu);
		 
			if (klasor.exists() && klasor.isDirectory()) {
	            File[] dosyalar = klasor.listFiles();

	            if (dosyalar != null) {

	                for (File dosya : dosyalar) {
	                	if (dosya.isFile() && dosya.getName().endsWith(".java")) 
	                	{
	                		if(sınıfKontrol(dosya.getAbsolutePath()))
	                		{
	                			yorumBulucu(dosya.getAbsolutePath());
	                		}else
	                		{
	                		}
	                    }
	                    if (dosya.isDirectory()) {
	                    	javaDosyalarınıBul(dosya.getAbsolutePath());
	                    }
	                }
	            } else {
	                System.out.println("Klasör boş.");
	            }
	        } else {
	            System.out.println("Belirtilen klasör bulunamadı veya bir klasör değil.");
	        }
	 }
	 
	 public boolean sınıfKontrol(String dosyaYolu)
		{
			
			String desen = "\\b(?:public|protected|private|static|abstract|final|strictfp|@interface|enum)?\\s*class\\s+[a-zA-Z_]\\w*\\s*(?:extends\\s+[a-zA-Z_]\\w*\\s*)?(?:implements\\s+[a-zA-Z_]\\w*(?:\\s*,\\s*[a-zA-Z_]\\w*)*\\s*)?\\{";
			String satir;
			
			try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
	            StringBuilder dosyaIcerigi = new StringBuilder();
	            
	            while ((satir = br.readLine()) != null) {
	                dosyaIcerigi.append(satir).append("\n");
	            }

	            Pattern pattern = Pattern.compile(desen);
	            Matcher matcher = pattern.matcher(dosyaIcerigi);

	            if (matcher.find()) {
	               	return true;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			return false;
		}
	 
	 public void yorumBulucu(String dosyaYolu)
	 {
		 
		 File dosya = new File(dosyaYolu);
	        String satir;
	        String tekSatir="";
	        
	        boolean cokSatirYorumIci = false;
	        boolean javadocIci = false;
	        int tekSatirYorumSayisi = 0;
	        int cokSatirYorumSayisi = 0;
	        int javadocSayisi = 0;
	        int locSayisi =0;
	        int kodOlmayan=0;
	        int fonksiyonSayisi=0;
	        int kodSatirSayisi=0;
	        double YG = 0;
	        double YH =0;
	        double yorumSapmaYuzdesi=0;
	      
	        String desen ="\\b(?:public|private|protected|static|final|abstract|\\s)*\\s+\\b\\w+\\s*\\([^)]*\\)\\s*\\{";
	        Pattern pattern = Pattern.compile(desen);
	        
		 try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosya))) 
		 {
			 	
	            while ((satir = okuyucu.readLine()) != null) 
	            {
	            	tekSatir+=satir;
	            	locSayisi++;
	            	for (int i = 0; i < satir.length(); i++) 
	            	{

	            		if(i<satir.length()-1 && satir.charAt(i)=='*' && satir.charAt(i+1)=='/' && cokSatirYorumIci==true)
            			{
            				cokSatirYorumIci=false;
            				cokSatirYorumSayisi=cokSatirYorumSayisi-2;

            			}
	            		if(i<satir.length()-1 && satir.charAt(i)=='*' && satir.charAt(i+1)=='/'&& javadocIci==true)
            			{
            				javadocIci=false;
            				javadocSayisi= javadocSayisi - 2;
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
	                    		if(satir.substring(0,i).isBlank())
	                    		{
	                    			kodOlmayan++;
	                    		}
	                    		tekSatirYorumSayisi++;
	                    	}else if (satir.charAt(i+1)=='*')
	                    	{
	                    		if(i<satir.length()-2 && satir.charAt(i+2)=='*')
	                    		{
	                    			//kodOlmayan++;
	                    			javadocSayisi++;
	                    			javadocIci=true;
	                    			kodOlmayan++;
	                    			i=i+2;
	                    		}else
	                    		{
	                    			//
	                    			cokSatirYorumSayisi++;
	                    			cokSatirYorumIci = true;
	                    			kodOlmayan++;
	                    			i=i+2;
	                    		}
	                    	}
	                    }
	
	                }
	            	
	            	if(satir.isBlank() || javadocIci==true || cokSatirYorumIci==true )
	            	{
	            		kodOlmayan++;
	            	}
	            	if(javadocIci==true)
            		{
            			javadocSayisi++;
            		}
	            	if(cokSatirYorumIci==true)
            		{
	            		cokSatirYorumSayisi++;
            		}

	            }
	            fonksiyonSayisi = fonksiyonBulucu(tekSatir);
	            kodSatirSayisi = locSayisi-kodOlmayan;

	            YG=( Double.valueOf((javadocSayisi+cokSatirYorumSayisi+tekSatirYorumSayisi))*0.8 ) / Double.valueOf(fonksiyonSayisi);
	           
	            YH=(Double.valueOf((kodSatirSayisi))/Double.valueOf(fonksiyonSayisi)) *0.3;
	            
	            yorumSapmaYuzdesi =((100*YG)/YH) - 100;
	            
	            double roundedValue = Math.floor(yorumSapmaYuzdesi * 100 + 0.5) / 100;
	            
	            System.out.println("Sınıf : " + dosya.getName());
	            System.out.println("Javadoc sayısı : " + javadocSayisi);
                System.out.println("Yorum satır sayısı : " + (cokSatirYorumSayisi+tekSatirYorumSayisi));
                System.out.println("Kod Satır Sayısı : " + kodSatirSayisi);
                System.out.println("LOC : " + locSayisi);
                System.out.println("Fonksiyon Sayisi : " + fonksiyonSayisi);
                System.out.println("Yorum Sapma Yüzdesi : " + roundedValue);
    			System.out.println("-----------------------------------------");

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 
	 public int fonksiyonBulucu(String tekSatir)
	 {
		
		int fonksiyonSayisi=0;
		
	    String desen ="\\b(?:public|private|protected|static|final|abstract|\\s)*\\s+\\b\\w+\\s*\\([^)]*\\)\\s*\\{";
	    Pattern pattern = Pattern.compile(desen);

        Matcher matcher = pattern.matcher(tekSatir);

        while (matcher.find()) 
        {
        	fonksiyonSayisi++;
        }
    
		 return fonksiyonSayisi;
	 }

}
