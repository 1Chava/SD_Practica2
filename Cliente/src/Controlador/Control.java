/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Reloj;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author chava
 */
public class Control implements ActionListener {

    private JTextField textPrimario;
    private JTextField textSecundario;
    private JTextField textTerciario;
    private Timer timer;
    private String tipo;
    private Reloj relojPrimario;
    private Reloj relojSecundario;
    private Reloj relojTerciario;
    private int segundosPrimario;
    private int segundosSecundario;
    private int segundosTerciario;
    private Client cliente;

    public Control() {
        super();
        segundosPrimario = 1;
        segundosSecundario = 1;
        segundosTerciario = 1;
    }

    public void agregarElemento(JTextField text, String tipo) {
        try {
            this.textPrimario = text;
            this.tipo = tipo;
            this.cliente = new Client();
            this.cliente.startConnection("127.0.0.1", 2500, tipo);
            this.cliente.start();
            this.relojPrimario = new Reloj(0);
            this.textPrimario.setText(this.relojPrimario.toString());
            startTimer();
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Reloj stringReloj(String t) {
        Reloj reloj = new Reloj(0);
        try {
            DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date datePrimario = sdf.parse(t);
            reloj.addSeconds(datePrimario, 0);
        } catch (ParseException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return reloj;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String horaPrimario = this.textPrimario.getText();
            DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date datePrimario = sdf.parse(horaPrimario);
            this.relojPrimario.addSeconds(datePrimario, this.segundosPrimario);
            this.textPrimario.setText(this.relojPrimario.toString());
        } catch (ParseException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancelTimer() {
        this.timer.stop();
    }

    public void startTimer() {
        this.timer = new Timer(1000, this);
        this.timer.start();
    }

    private class Client extends Thread {

        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public void startConnection(String ip, int port, String msg) throws IOException {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println(msg);
            System.out.println(msg);
        }

        public void run() {
            while (true) {
                try {
                    String resp = in.readLine();
                    Control.this.relojPrimario = Control.this.stringReloj(resp);
                    System.out.println("RECIVED" + resp);
                } catch (IOException ex) {
                    Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public void stopConnection() throws IOException {
            in.close();
            out.close();
            clientSocket.close();
        }
    }

}
