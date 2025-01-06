/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package feed.forward.framework;

import java.util.ArrayList;

/**
 *
 * @author beckf
 */
public class FeedForwardFramework {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        testData();
        testTensor();
    }
    
    static void testData() {
        ArrayList<Datapoint> testData = DataReader.readData("mnist/train.csv");
        
        for (int i = 0; i < 5; i++) {
            Datapoint cur = testData.get(i);
            System.out.println(cur.getData());
        }
    }
    
    static void testTensor() {
        Tensor t1 = new Tensor(5, 3);
        Tensor t2 = new Tensor(5, 1);
        Tensor r = t1.add(t2);
        
        t1.print();
        t2.print();
        r.print();
    }
    
}
