/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feed.forward.framework;

/**
 *
 * @author beckf
 */
public class GradTensor extends Tensor {
    Tensor grad;
    
    GradTensor(int m, int n) {
        super(m ,n);
        grad = new Tensor(m, n);
    }
    
    Tensor getGrad() {
        return grad;
    }
    
    void setGrad(Tensor g) {
        grad = g;
    }
}
