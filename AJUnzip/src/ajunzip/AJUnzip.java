package ajunzip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AJUnzip {

    public static void main(String[] args) throws Exception {
        String zipFilePath = "/Users/none/tmp.zip";
        String destDir = "/Users/none/outputFORZIP";
        
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "ZIP only", "zip");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            for (File f : files) {
                zipFilePath = f.getAbsolutePath();
                destDir = f.getAbsolutePath();
                
                if(destDir.contains(".")) 
                    destDir = destDir.substring(0, destDir.lastIndexOf('.'));
                else
                    destDir = destDir + "-DIR";
                
                //System.out.println(destDir);
                unzip(zipFilePath, destDir);
            }
        }   
    }

    private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);

        if(dir.exists()) {
            System.out.printf("Already exists: %s\n", destDir);
            System.exit(-1);
        } else
            dir.mkdirs();
        
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to "+newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                if (ze.isDirectory()) {
                    if (!newFile.mkdir()) {
                        System.out.println("ERROR: could not make directory: " + newFile.getAbsolutePath());
                        System.exit(-1);
                    }
                } else {
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                zis.closeEntry();
                ze = zis.getNextEntry();
                
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
