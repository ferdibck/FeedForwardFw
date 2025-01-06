/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feed.forward.framework;

/**
 *
 * @author beckf
 */
public class Tensor {
    double[] entries;
    int[] shape;
    int[] strides;
    int size = 1;
    
    Tensor(int m, int n) {
        shape = new int[]{m, n};
        size = m*n;
        
        strides = new int[2];
        strides[0] = 1;
        strides[1] = shape[0];
        
        entries = new double[size];
        for(int i = 0; i < size; i++) {
            entries[i] = (int)(Math.random()*10);
        }
    }
    
    int flattenIndex(int x, int y) {
        int flatIndex = x;
        flatIndex += (y - 1) * strides[1];
        
        return flatIndex-1;
    }
    
    int[] unravelIndex(int i) {
        i += 1;
        
        int[] indices = new int[2];
        
        if(i % shape[0] == 0) indices[0] = shape[0];
        else indices[0] = i % shape[0];
        
        i -= indices[0];
        i /= shape[0];
        
        if(i % shape[1] == 0) indices[1] = shape[1];
        else indices[1] = i % shape[1];
        
        return indices;
    }
    
    void print() {
        System.out.println(shape[0]+" x "+shape[1]+":");
        
        for(int i = 1; i <= shape[0]; i++) {
            System.out.println("");
            
            for(int j = 1; j <= shape[1]; j++) {
                System.out.print(entries[flattenIndex(i, j)]+" ");
            }
        }
        
        System.out.println("\n");
    }
    
    double getEntry(int x, int y) {
        return entries[flattenIndex(x, y)];
    }
    
    double getEntryBroadcasted(int x, int y) {
        return entries[flattenIndexBroadcasted(x, y)];
    }
    
    void setEntry(int x, int y, double e) {
        entries[flattenIndex(x,y)] = e;
    }
    
    int[] getShape() {
        return shape;
    }
    
    Tensor matmul(Tensor t) {
        if(shape[1] != t.getShape()[0]) {
            throw new Error("Inner shapes must match");
        }
        
        else {
            Tensor res = new Tensor(shape[0], t.getShape()[1]);
            
            for(int i = 1; i <= shape[0]; i++) {
                
                for(int j = 1; j <= t.getShape()[1]; j++) {
                    double entry = 0;
                    
                    for(int k = 1; k <= shape[1]; k++) {
                        
                        entry += entries[flattenIndex(i, k)] * t.getEntry(k, j);
                    }
                    
                    res.setEntry(i, j, entry);
                }
            }
            
            return res;
        }
    }
    
    Tensor isBroadcastable(Tensor t) {
        for(int i = 0; i <= 1; i++) {
            int thisDim = shape[i];
            int otherDim = t.getShape()[i];
            
            if(thisDim != otherDim && (thisDim != 1 && otherDim != 1)) return null;
        }
        
        return new Tensor(Math.max(shape[0], t.getShape()[0]), Math.max(shape[1], t.getShape()[1]));
    }
    
    Tensor add(Tensor t) {
        Tensor res = isBroadcastable(t);
        if(res == null) {
            throw new Error("Tensor shapes are not broadcastable for addition.");
        }
        
        else {
            int[] resShape = res.getShape();
            
            for(int i = 1; i <= resShape[0]; i++) {
                
                for(int j = 1; j<= resShape[1]; j++) {
                    double entry = entries[flattenIndexBroadcasted(i, j)] + t.getEntryBroadcasted(i, j);
                    
                    res.setEntry(i, j, entry);
                }
            }
            
            return res;
        }
    }
    
    Tensor multiply(Tensor t) {
        Tensor res = isBroadcastable(t);
        if(res == null) {
            throw new Error("Tensor shapes are not broadcastable for addition.");
        }
        
        else {
            int[] resShape = res.getShape();
            
            for(int i = 1; i <= resShape[0]; i++) {
                
                for(int j = 1; j<= resShape[1]; j++) {
                    double entry = entries[flattenIndexBroadcasted(i, j)] * t.getEntryBroadcasted(i, j);
                    
                    res.setEntry(i, j, entry);
                }
            }
            
            return res;
        }
    }
    
    int flattenIndexBroadcasted(int x, int y) {
        int xb;
        int yb;
        
        if(x > shape[0]) xb = 1;
        else xb = x;
        
        if(y > shape[1]) yb = 1;
        else yb = y;
        
        return flattenIndex(xb, yb);
    }
}
