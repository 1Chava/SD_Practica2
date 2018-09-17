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
    
    private JTextField[] relojes;
    private JButton bEditar;
    private JButton bEnviar;
    private Control control;
    private String[] tipos = {"Principal", "Secundario", "Terciario"};
    
    public PanelVentana(){
        initComponents();
    }
    
    private void initComponents(){
        setLayout(null);
        this.relojes = new JTextField[4];
        this.control = new Control();
        int x = 10;
        int y = 10;
        int incY = 30;
        int ancho = 170;
        int alto = 30;
        for (int i = 0; i < 3; i++) {
            relojes[i] = new JTextField();
            relojes[i].setBounds(x, y + (i * incY), ancho, alto);
            this.control.agregarElemento(relojes[i], tipos[i]);
            relojes[i].addActionListener(this.control);
            add(relojes[i]);
        }
        
        this.bEditar = new JButton("Editar");
        this.bEnviar = new JButton("Enviar");
        add(this.bEditar);
        add(this.bEnviar);
        this.bEditar.setBounds(x + 200, y + incY, ancho, alto);
        this.bEnviar.setBounds(x + 200, y + (2 * incY), ancho, alto);
        this.bEditar.addActionListener(this.control);
        this.bEnviar.addActionListener(this.control);
    }
}
