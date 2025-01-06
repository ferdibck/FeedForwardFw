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
        W = new Tensor(1, 1); // Platzhalter
        b = new Tensor(1, 1);
    }
    
    Tensor forward(Tensor x) {
        // 
        return x;
    }
}
