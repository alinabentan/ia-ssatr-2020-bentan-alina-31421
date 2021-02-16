/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.magazin;


public class Produs {
    private String nume;
    private String material;
    private double pret;

    public Produs(String nume, String material,double pret) {
        this.nume = nume;
        this.material = material;
        this.pret = pret;
    }

    public String getNume() {
        return nume;
    }
    
    public String getMaterial() {
        return material;
    }
    
    public double getPret() {
        return pret;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
    
    public void setPret(double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Produs{" + "nume=" + nume + ", material=" + material + ", pret=" + pret + '}';
    }
}
