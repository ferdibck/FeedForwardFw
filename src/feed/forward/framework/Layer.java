/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feed.forward.framework;

/**
 *
 * @author beckf
 */
public abstract class Layer extends Element {
    Element naechster;
    
    void setzeNaechster(Element n) {
        naechster = n;
    }
    
    Element neuerLayer(Layer l) {
        naechster = naechster.neuerLayer(l);
        return this;
    }
    
    abstract Tensor forward(Tensor x);
    
    abstract void print(int cur);
    
    abstract int numParams(int cur);
    
    abstract GradTensor[] getParams(GradTensor[] params, int cur);
}
