/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feed.forward.framework;

/**
 *
 * @author beckf
 */
public abstract class Layer {
    Element naechster;
    
    void setzeNaechster(Element n) {
        naechster = n;
    }
    
    Layer neuerLayer(Layer l) {
        naechster = naechster.neuerLayer(l);
        return this;
    }
    
    abstract Tensor forward(Tensor x);
}
