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
        int N = 500; // num test examples
        
        double W = 10;
        double b = 25;
        
        // Create network
        Netzwerk n = new Netzwerk();
        Layer l = new Linear(1, 2);
        Layer l2 = new Linear(2, 1);
        n.neuerLayer(l);
        n.neuerLayer(l2);
        //n.print();
        
        Optimizer optim = new Optimizer(n.getParams(), 0.01);
        optim.update();
        
        // Create test data
        Tensor x = new Tensor(1, N);

        Tensor c = new Tensor(1, N, b); // b = 2
        
        Tensor targets = x.weightedAdd(x, 1, W).add(c); // y = W * x + b
        
        
        Tensor preds;
        for(int i = 1; i <= 500; i++) {
            // Forward phase
            preds = n.forward(x);
            
            // Backward phase
            n.backward(preds, targets);
            n.compGrads();
            optim.update();
            
            // Debug
            if(i % 20 == 0) {
                System.out.println("===== Results in iteration: "+i);
                //System.out.println("Preds: ");
                //preds.print();
            
                //System.out.println("Targets: ");
                //targets.print();
                
                calcMSE(preds, targets);
            }
        }
        
    }
    
    static void calcMSE(Tensor preds, Tensor targets) {
        double constant = 2.0 * targets.getShape()[1];
        Tensor loss_per_sample = preds.weightedAdd(targets, 1, -1);
        loss_per_sample = loss_per_sample.multiply(loss_per_sample);
        
        Tensor summed_loss = loss_per_sample.sum_rows();
        summed_loss = summed_loss.multiply(1/constant);
        summed_loss.print();
    }
    
}
