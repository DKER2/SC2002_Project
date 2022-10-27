package src.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileWriter;
public class IO {
    public static List read(String FILENAME) throws IOException{
        List data = new ArrayList() ;
        Scanner scanner = new Scanner(new FileInputStream(FILENAME));
        try {
            while (scanner.hasNextLine()){
                data.add(scanner.nextLine());
            }
        }
        finally{
            scanner.close();
        }
        
        return data;
    }

    public static void write(String fileName, List data) throws IOException  {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
    
        try {
            for (int i =0; i < data.size() ; i++) {
                  out.println((String)data.get(i));
            }
        }
        finally {
          out.close();
        }
    }
}
