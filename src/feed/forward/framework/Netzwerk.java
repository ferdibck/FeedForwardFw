/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feed.forward.framework;

/**
 *
 * @author beckf
 */
public class Netzwerk {
    Element erster;
    
    Netzwerk() {
        erster = new Abschluss();
    }
    
    void neuerLayer(Layer l) {
        erster = erster.neuerLayer(l);
    }
    
    Tensor forward(Tensor x) {
        return erster.forward(x);
    }
    
    void print() {
        System.out.println("Struktur des Netzwerkes\n==========\n==========\n==========");
        erster.print(0);
        System.out.println("==========\n==========\n==========");
    }
    
    int numParams() {
        return erster.numParams(0);
    }
    
    GradTensor[] getParams() {
        GradTensor[] params = new GradTensor[numParams()];
        
        return erster.getParams(params, 0);
    }
    
    void compGrads() {
        erster.compGrads();
    }
    
    void backward(Tensor preds, Tensor targets) {
        erster.backward(preds, targets);
    }
}
