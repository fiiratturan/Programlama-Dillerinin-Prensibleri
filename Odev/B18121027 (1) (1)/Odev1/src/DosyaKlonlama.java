/**
*
@author ibrahim ŞAHİN ibrahim.sahin5@ogr.sakarya.edu.tr
@since 07.04.2024
<p>
B18121027
1. Öğretim C Grubu
</p>
*/
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import java.io.File;
import java.io.IOException;
public class DosyaKlonlama {
	
	void DosyaKlonla(String url )
	{
		String currentWorkingDirectory = System.getProperty("user.dir");
		
		File localPath = new File(currentWorkingDirectory, "clone");

		 try {
	            if (localPath.exists()) {
	                deleteDirectory(localPath);
	            }

	            Git.cloneRepository().setURI(url).setDirectory(localPath).call();

	            System.out.println("Depo başarıyla klonlandı.");
	        } catch (GitAPIException | IOException e) {
	            System.out.println("Klonlama sırasında bir hata oluştu: " + e.getMessage());
	        }
	}
	 private static void deleteDirectory(File directory) throws IOException {
	        if (directory.exists()) {
	            File[] files = directory.listFiles();
	            if (files != null) {
	                for (File file : files) {
	                    if (file.isDirectory()) {
	                        deleteDirectory(file);
	                    } else {
	                        if (!file.delete()) {
	                            throw new IOException("Dosya silinemedi: " + file);
	                        }
	                    }
	                }
	            }
	            if (!directory.delete()) {
	                throw new IOException("Klasör silinemedi: " + directory);
	            }
	        }
	    }
}
