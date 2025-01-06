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
    
    Layer neuerLayer(Layer l) {
        l.setzeNaechster(this);
        return l;
    }
    
    Tensor forward(Tensor x) {
        return x;
    }
}
