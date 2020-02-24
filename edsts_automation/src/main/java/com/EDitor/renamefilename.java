/*package com.EDitor;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

public class renamefilename {

	
	@Test
	public void filerename() {
		
		String path = "C:\\Users\\rahverma3\\Box Sync\\Sapient-STS\\Quality_Assurance\\PhaseII_Test_File\\NABCP\\NPE\\Historical";
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			
			String currentfilename = file.getName();
			// System.out.println(currentfilename.lastIndexOf("_"));
			
				
		
			String file1 = currentfilename.replace("Boundary", "_1_NPE_Historical_2018-02-28");
					
			
			String expectedfilename = file1;
			File srcdir = null;
			File destdir = null;
			
				File expectedfile = new File(path + "\\" + expectedfilename);
				file.renameTo(expectedfile);

			}
	
		//	FileUtils.copyFile(srcdir, destdir);
		
	}
}
*/