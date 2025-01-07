/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feed.forward.framework;

/**
 *
 * @author beckf
 */
public abstract class Element {
    abstract Element neuerLayer(Layer l);
    
    abstract Tensor forward(Tensor x);
    
    abstract void print(int cur);
}
