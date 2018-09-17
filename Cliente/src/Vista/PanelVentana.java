/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Control;
import Modelo.Reloj;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author chava
 */
public class PanelVentana extends JPanel{
    
    private JTextField relojes;
    private JButton bEditar;
    private JButton bEnviar;
    private Control control;
    private String[] tipos = {"Principal", "Secundario", "Terciario"};
    
    public PanelVentana(String title){
        initComponents(title);
    }
    
    private void initComponents(String title){
        setLayout(null);
        this.relojes = new JTextField();
        this.control = new Control();
        int x = 10;
        int y = 10;
        int incY = 30;
        int ancho = 170;
        int alto = 30;
            relojes.setBounds(x, y  * incY, ancho, alto);
            this.control.agregarElemento(relojes, title);
            relojes.addActionListener(this.control);
            add(relojes);
    }
}
