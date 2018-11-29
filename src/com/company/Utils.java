package com.company;
import java.io.*;
public class Utils {
    // read file .txt
    public static String readContentFromFile(String path) {
        Reader reader;
        String content="";
        BufferedReader bufferedReader = null;
        try {
            //Opening the file
            reader = new FileReader(path);
            bufferedReader = new BufferedReader(reader);

            //Reading the file
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                //System.out.println(currentLine);
                currentLine +="\r\n";
                content+=currentLine;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            //Closing the file
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        return content;
    }
    // write into file .txt
    public static void writeContentToFile(String path){
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {

            String content = "This is the content to write into file\n";
            fw = new FileWriter(path,true);
            bw = new BufferedWriter(fw);
            bw.write(content);
            System.out.println("Done");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (Exception ex) {
                System.out.println(ex);

            }
        }
    }
//    public static File findFileByName(String folderPath, String fileName){
//        File file = new File(folderPath,fileName);
//        if (file.exists()) {
//            System.out.println("Your search is exists");
//            System.out.println();
//            if (file.isFile()) {
//                if (file.getName().endsWith(fileName)) {
//                    System.out.println("This is a file");
//                    System.out.println(file.getAbsolutePath());
//                }
//            } else if(file.canExecute()){
//                System.out.println("This is a floder");
//                System.out.println(file.getAbsolutePath());
//                System.out.println();
//                File[] listFile = file.listFiles() ;
//                int count = 0;
//                for (File f : listFile) {
//                    count++;
//                    if (count==1) {
//                        System.out.println("This floder have:");
//                    }
//                    System.out.println("File "+count+":");
//                    System.out.println(f.getAbsoluteFile().getAbsolutePath());
//                    System.out.println();
//                }
//                if (count==0) {
//                    System.out.println("But it is empety");
//                }
//            }
//        } else{
//            System.out.println("Dont exists");
//            return null;
//        }
//        return file;
//    }

    public static File findFileByName(String folderPath, String fileName){
        if (fileName.isEmpty()) {
            return null;
        } else{
            File file = new File(folderPath);
            if (file.exists()) {
                if(file.isDirectory()){
                    File[] listFile = file.listFiles();
                    File result = null;
                    if (listFile!=null) {
                        for (File filesearch: listFile){
                            File temp;
                            temp = findFileByName(filesearch.getAbsolutePath(),fileName);
                            if(temp!=null){
                                if(temp.isFile()){
                                    if (temp.getName().endsWith(fileName)) {
                                        result=temp;
                                    }
                                }
                            }
                        }
                    }

                    if (result!=null) {
                        file=result;
                    } else{
                        file=null;
                    }
                } else if(file.isFile()){
                    if (file.getName().endsWith(fileName)) {
                        return file;
                    } else{
                        return null;
                    }
                }
            } else{
                return null;
            }
            return file;
        }
    }

    public static void main(String[] args) {
        // write your code here
        //String read;
        //read = readContentFromFile("D:\\Lab9\\src\\com\\company\\reads.txt");
        //System.out.println(read);
        //writeContentToFile("D:\\Lab9\\src\\com\\company\\read.txt");
        File file = findFileByName("D:\\Lab9","read.txt");
        if (file!=null){
            System.out.println("Exists");
            System.out.println(file.getAbsolutePath());
        } else{
            System.out.println("Not Exists");
        }
    }

}
