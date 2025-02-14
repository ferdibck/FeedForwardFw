/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feed.forward.framework;

/**
 *
 * @author beckf
 */
public class Abschluss extends Element {
    Abschluss() {
    }
    
    Element neuerLayer(Layer l) {
        l.setzeNaechster(this);
        return l;
    }
    
    Tensor forward(Tensor x) {
        return x;
    }
    
    void print(int cur) {
        System.out.println("Netzwerk mit "+cur+" Layern");
    }
    
    int numParams(int cur) {
        return cur;
    }
    
    GradTensor[] getParams(GradTensor[] params, int cur) {
        return params;
    }
    
    void compGrads() {
    }
    
    Tensor backward(Tensor preds, Tensor targets) {
        // loss function implementation, here: MSE
        double constant = 2.0 * targets.getShape()[1];
        Tensor J = new Tensor(targets.getShape()[1], 1);
        
        Tensor backward = preds.weightedAdd(targets, 1/constant, -1/constant);
        
        return backward;
    }
}
