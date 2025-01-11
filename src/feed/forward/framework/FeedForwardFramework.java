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
        //testNetwork();
        testCycle();
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
    
    static void testCycle() {
        int N = 10; // num test examples
        
        // Create network
        Netzwerk n = new Netzwerk();
        Layer l = new Linear(1, 1);
        n.neuerLayer(l);
        n.print();
        
        Optimizer optim = new Optimizer(n.getParams(), 0.001);
        optim.update();
        
        // Create test data
        Tensor x = new Tensor(1, N);

        Tensor c = new Tensor(1, N, 2); // b = 2
        
        Tensor targets = x.add(x).add(c); // y = a * x + b; here: y = 2 * x + 2
        
        // Forward phase
        Tensor preds = n.forward(x);
        
        Tensor temp = preds.weightedAdd(targets, 1, -1);
        //temp.print();
        Tensor loss = temp.multiply(temp);
        
        // Backward phase
        n.backward(preds, targets);
        
        n.compGrads();
        optim.update();
        
        System.out.println("Forward 2");
        
        // Forward 2
        Tensor preds2 = n.forward(x);
        
        preds.print();
        preds2.print();
        
        targets.print();
        
    }
    
}
