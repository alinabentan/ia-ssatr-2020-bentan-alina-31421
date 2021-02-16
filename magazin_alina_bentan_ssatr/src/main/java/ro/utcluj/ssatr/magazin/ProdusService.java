/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.magazin;

import java.sql.SQLException;

public class ProdusService {

    private DBAccess db;

    public ProdusService() throws ClassNotFoundException, SQLException {
        db = new DBAccess();
    }

    public synchronized String handleProdusNou(String nume,String material, double pret) throws SQLException{

        Produs p = new Produs(nume, material,pret);
        db.insertpProdus(p);
        return "Produs nou adaugat, nume: "+p.getNume()+ " material: "+ p.getMaterial()+" pret: "+ p.getPret();
      }
    
    public synchronized String deleteEntity(String nume) throws SQLException{
           Produs p = db.findByName(nume);
           if(p!=null){
           db.deleteByName(nume);
           return "Produs sters";
           }
           else{
           return " Produsul: "+nume+" nu exista!";
           }
    }
public static void main(String[] args) throws Exception {
    ProdusService p = new ProdusService();
       System.out.println(p.handleProdusNou("camasa", "in", 110));
    }
}


