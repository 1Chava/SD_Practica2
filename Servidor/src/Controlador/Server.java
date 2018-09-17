/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Reloj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chava
 */
public class Server {

    private ServerSocket serverSocket;
    private Reloj primario;
    private Boolean flag;

    public void start(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.flag = true;
        for(int x = 0; x < 1; x++){
            new EchoClientHandler(serverSocket.accept()).start();
        }
    }
    
    public void addReloj(Reloj reloj){
        this.primario = reloj;
    }
    
    public void toogleFlag(){
        this.flag = !this.flag;
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    private class EchoClientHandler extends Thread {

        private Socket clientSocket;
        private String tipo;
        private PrintWriter out;
        private BufferedReader in; 

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                this.tipo = in.readLine();
                System.out.println(this.tipo);
                while (true){
                    if(Server.this.flag){
                        if(this.tipo == "Primario"){
                            out.println(Server.this.primario.toString());
                        } else if(this.tipo == "Secundario"){
                            out.println(Server.this.primario.cambiaPrimarioSecundario());
                        } else {
                            out.println(Server.this.primario.cambiaPrimarioTerciario());                            
                        }
                        System.out.println("ENVIADO");
                    }
                }    
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    out.close();
                    clientSocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
