/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Reloj;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author chava
 */
public class Control implements ActionListener{
    
    private JTextField textPrimario;
    private JTextField textSecundario;
    private JTextField textTerciario;
    private Timer timer;
    private Reloj relojPrimario;
    private Reloj relojSecundario;
    private Reloj relojTerciario;
    private Server servidor;
    private int segundosPrimario;
    private int segundosSecundario;
    private int segundosTerciario;
    
    
    public Control(){
        super();
        try {
            segundosPrimario = 1;
            segundosSecundario = 1;
            segundosTerciario = 1;
            this.servidor = new Server();
            this.servidor.start(2500);
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarElemento(JTextField text, String tipo){
        if(tipo == "Principal"){
            this.relojPrimario = new Reloj(0);
            this.textPrimario = text;
            this.textPrimario.setText(this.relojPrimario.toString());
        } else if(tipo == "Secundario"){
            this.relojSecundario = new Reloj(1, this.relojPrimario);
            this.textSecundario = text;
            this.textSecundario.setText(this.relojSecundario.toString());
        } else{
            this.relojTerciario = new Reloj(2, this.relojPrimario);
            this.textTerciario = text;
            this.textTerciario.setText(this.relojTerciario.toString());
        }
        startTimer();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String quien = e.getActionCommand();
            System.out.println(quien + this.segundosPrimario);
            if (quien == "Editar"){
                if(this.segundosPrimario == 1){
                    this.segundosPrimario = 0;
                    this.servidor.toogleFlag();
                } else {
                    this.segundosPrimario = 1;
                }
            } else if(quien == "Enviar") {
                this.relojSecundario = new Reloj(1, this.relojPrimario);
                this.textSecundario.setText(this.relojSecundario.toString());
                this.relojTerciario = new Reloj(2, this.relojPrimario);
                this.textTerciario.setText(this.relojTerciario.toString());
                this.segundosPrimario = 1;
                this.servidor.addReloj(this.relojPrimario);
                this.servidor.toogleFlag();
                
                this.servidor.toogleFlag();
            } else {
                String horaPrimario = this.textPrimario.getText();
                String horaSecundario = this.textSecundario.getText();
                String horaTerciario = this.textTerciario.getText();
                DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date datePrimario = sdf.parse(horaPrimario);
                Date dateSecundario = sdf.parse(horaSecundario);
                Date dateTerciario = sdf.parse(horaTerciario);
                this.relojPrimario.addSeconds(datePrimario, this.segundosPrimario);
                this.relojSecundario.addSeconds(dateSecundario, this.segundosSecundario);
                this.relojTerciario.addSeconds(dateTerciario, this.segundosTerciario);
                this.textPrimario.setText(this.relojPrimario.toString());
                this.textSecundario.setText(this.relojSecundario.toString());
                this.textTerciario.setText(this.relojTerciario.toString());
            }
        } catch (ParseException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cancelTimer(){
        this.timer.stop();
        this.servidor.toogleFlag();
    }
    
    public void startTimer(){
        this.timer = new Timer(1000, this);
        this.timer.start();
    }        
}
