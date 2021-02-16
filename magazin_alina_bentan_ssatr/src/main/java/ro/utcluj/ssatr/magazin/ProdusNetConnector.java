/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.magazin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;


public class ProdusNetConnector {
    
   ProdusService pService;

    public ProdusNetConnector() throws ClassNotFoundException, SQLException {
        pService = new ProdusService();
    }
    
    public void startServer(){
        
        try{
        
        ServerSocket ss =new ServerSocket(4050);
        
        while(true){
            System.out.println("Astept conexiune de la client...");
            Socket s = ss.accept(); //metoda blocanta
            System.out.println("Clientul s-a conectat!");
            //...... 
            BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
            //......
 
            String resp=fluxIn.readLine();
            System.out.println(resp);
            
            if("new".equals(resp))
            {
                String nume = fluxIn.readLine();
                String material = fluxIn.readLine();
                double pret = Double.parseDouble(fluxIn.readLine());
                String result = pService.handleProdusNou(nume,material,pret);
                fluxOut.println(result);
            }
            else if("delete".equals(resp))
            {
                String nume2 = fluxIn.readLine();
                String result2 =pService.deleteEntity(nume2);
                fluxOut.println(result2);
            }
            s.close();
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ProdusNetConnector netCon = new ProdusNetConnector();
        netCon.startServer();
    }
}