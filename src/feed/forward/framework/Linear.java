/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feed.forward.framework;

/**
 *
 * @author beckf
 */
public class Linear extends Layer {
    Tensor W, b;
    
    Linear(int num_in, int num_out) {
        W = new Tensor(num_out, num_in);
        b = new Tensor(num_out, 1);
    }
    
    Tensor forward(Tensor x) {
        Tensor xprime = b.add(W.matmul(x));
        
        return naechster.forward(xprime);
    }
    
    void print(int cur) {
        System.out.println("Layer "+(cur+1)+" =====");
        System.out.println("Weights tensor: ");
        W.print();
        
        System.out.println("Bias tensor: ");
        b.print();
        System.out.println("==========");
        
        naechster.print(cur+1);
    }
}
