/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feed.forward.framework;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author beckf
 */
public class DataReader {
    
    public static ArrayList<Datapoint> readData(String filename) {
        ArrayList<Datapoint> dataList = new ArrayList<Datapoint>();
        
        File dataFile = new File(filename);
        
        try(Scanner fileScanner = new Scanner(dataFile)) {
            ArrayList<String> lines = new ArrayList<String>();
            
            while(fileScanner.hasNextLine()) {
                lines.add(fileScanner.nextLine());
            }
            
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                
                String[] data = line.split(",");
                
                //  data[0] = 1st feature, ...
                
                Datapoint datapoint = new Datapoint(data);
                
                dataList.add(datapoint);
            }
        }
        
        catch(FileNotFoundException error) {
            System.out.println("Fehler");
        }
        
        return dataList;
    }
}
