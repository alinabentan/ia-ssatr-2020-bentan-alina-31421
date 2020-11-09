/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.bank.sim;

/**
 *
 * @author Alina
 */
public class AccountsManager {
    BankAccount[] accounts = new BankAccount[10]; // [null null null null ....]
       
    void addAccount(BankAccount a){
    for(int i=0;i<accounts.length;i++){
           if(accounts[i]==null){
               accounts[i] = a;
               System.out.println("New account was created.");
               return;
           }           
       }
    }
    
    int getTotalBalance(){
    int totalBalance=0;
    for(int i=0;i<accounts.length;i++){
        if (accounts[i] == null){
            totalBalance+=0;
        }
        else{
         totalBalance+=accounts[i].getBalance();
        }
    }
    return totalBalance;
    }
    
    
    String getAllAccountsDetails(){
       String all = "";
       for(BankAccount a: accounts){ 
           if(a!=null){
            String line = "Owner = "+a.getOwner()+" balance = "+a.getBalance()+ "\n";
            all=all+line;
           }
       }
       return all;
   }
    boolean transfer(String fromOwnerName, String toOwnerName, int amount){

        int actions=0;
         for(BankAccount a: accounts){ 
            if (a != null) {
            if(a.getOwner().equals(fromOwnerName)){
                if(a.getBalance()<amount)
                    return false;
                else{
                    int newBalance=a.getBalance()-amount;
                    a.setBalance(newBalance);
                    actions++;
                }      
            }
            } 
         }
         for(BankAccount a: accounts){ 
            if (a != null) {
            if(a.getOwner().equals(toOwnerName)){
                    int newBalance=a.getBalance()+amount;
                    a.setBalance(newBalance);
                    actions++;
                }    
            }
            } 
         if (actions==2) return true;
         
         
         return false;

    }
}
