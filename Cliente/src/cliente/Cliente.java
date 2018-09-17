/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import Vista.Ventana;

/**
 *
 * @author chava
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String[] input = args;
        Ventana v = new Ventana(args[0]);
        v.setVisible( true );
        v.setSize(400, 200);
        v.setDefaultCloseOperation(v.EXIT_ON_CLOSE);
    }
    
}
