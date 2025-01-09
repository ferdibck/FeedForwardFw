/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feed.forward.framework;

/**
 *
 * @author beckf
 */
public class Optimizer { //SGD-Optimizer
    GradTensor[] params;
    int num_params;
    double ALPHA;
    
    Optimizer(GradTensor[] p, double a) {
        params = p;
        num_params = params.length;
        ALPHA = a;
    }
    
    void update() {
        for(int i = 0; i < num_params; i++) {
            Tensor grad = params[i].getGrad();
            Tensor param = params[i];
            params[i].setEntries(param.weightedAdd(grad, 1.0, -ALPHA).getEntries());
        }
    }
}
