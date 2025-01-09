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
        //testData();
        //testTensor();
        testNetwork();
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
    
    static void testNetwork() {
        Tensor testx = new Tensor(10, 10);
        Layer l1 = new Linear(10, 5);
        Layer l2 = new Linear(5, 3);
        
        Netzwerk nn = new Netzwerk();
        nn.neuerLayer(l1);
        nn.neuerLayer(l2);
        nn.print();
        
        Tensor res = nn.forward(testx);
        res.print();
        
        Optimizer optim = new Optimizer(nn.getParams(), 0.001);
        optim.update();
        
        Tensor res2 = nn.forward(testx);
        res2.print();
    }
    
}
