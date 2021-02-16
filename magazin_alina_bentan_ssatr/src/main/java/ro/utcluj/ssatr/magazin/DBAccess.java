/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.magazin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess {
    private Connection conn;
    
    public DBAccess() throws ClassNotFoundException, SQLException {
         Class.forName("org.apache.derby.jdbc.ClientDriver");
         conn = DriverManager.getConnection("jdbc:derby://localhost/magazin;create=false","APP","APP");
    }
   
    public void insertpProdus(Produs p) throws SQLException{
        Statement s = conn.createStatement();
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PRODUSE " 
    + "(NUME, MATERIAL, PRET) VALUES (?, ?, ?)");
        pstmt.setString(1, p.getNume());
        pstmt.setString(2, p.getMaterial());
        pstmt.setDouble(3, p.getPret());
        pstmt.executeUpdate();
       
        s.close();
    }
        public Produs findByName(String nume) throws SQLException{
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM PRODUSE WHERE NUME='"+nume+"'");
        if(rs.next()){
            return new Produs(rs.getString("nume"), rs.getString("material"),rs.getDouble("pret"));
        }else{
            return null;
        }           
    }
    
    public void deleteByName(String nume) throws SQLException{
        Statement s = conn.createStatement();
        s.executeUpdate("DELETE FROM Produse WHERE NUME='"+nume+"'");   
        s.close();
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBAccess db = new DBAccess();
        Produs p = new Produs("bluza", "bumbac", 50);
        db.insertpProdus(p);
        
        
       
        Produs result = db.findByName("camasa");
        System.out.println(result);
        if(result!=null){
            db.deleteByName(result.getNume());
            System.out.println("Produs sters!");
        }else{
            System.out.println("Produs inexistent!");
        }
        
    }
}