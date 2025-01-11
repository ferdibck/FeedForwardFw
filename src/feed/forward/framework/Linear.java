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
    GradTensor W, b;
    Tensor last_x;
    Tensor J; // static, used for computing bias gradient
    Tensor backpass;
    
    Linear(int num_in, int num_out) {
        W = new GradTensor(num_out, num_in);
        b = new GradTensor(num_out, 1);
        
        J = new Tensor(1, num_out, 1.0);
    }
    
    Tensor forward(Tensor x) {
        last_x = x;
        
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
    
    int numParams(int cur) {
        return naechster.numParams(cur + 2);
    }
    
    GradTensor[] getParams(GradTensor[] params, int cur) {
        params[cur] = W;
        params[cur+1] = b;
        
        return naechster.getParams(params, cur + 2);
    }
    
    void compGrads() {
        backpass.print();
        
        W.setGrad(backpass.matmul(last_x.transpose()));
        
        System.out.println("W grad computed");
        
        System.out.println("Debug");
        System.out.println("J =====================");
        J.print();
        
        System.out.println("backpass =====================");
        backpass.print();
        
        b.setGrad(backpass.matmul(J.transpose()));
    }
    
    Tensor backward(Tensor preds, Tensor targets) {
        backpass = naechster.backward(preds, targets);
        
        return W.transpose().matmul(backpass);
    }
}
