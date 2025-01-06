/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feed.forward.framework;

/**
 *
 * @author beckf
 */
public class Datapoint {
    String data;
    
    Datapoint(String[] d) {
        data = d[0];
    }
    
    String getData() {
        return data;
    }
}
