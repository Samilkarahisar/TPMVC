/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmvc;

import static java.lang.Thread.*;
import java.util.Observable;


public class Jeu extends Observable implements Runnable{
    private Grille grille;
    private final int WIDTH = 10;
    private final int LENGHT = 10;
    
    public Jeu(){
        grille = new Grille();

    }




    public void start(){
    new Thread(this).start();
    }

    public Grille getGrille() {
        return grille;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getLENGHT() {
        return LENGHT;
    }
    
    
    
    @Override
    public void run(){
        try{
            getGrille().getNewE(2,2);
            Fantôme ghost1 = new Fantôme(3,3, getGrille());
            getGrille().GetListE().add(ghost1);
            System.out.println(getGrille().getEntity(0).getX()+" "+getGrille().getEntity(0).getY());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        while(true){
            System.out.println(getGrille().getNbEntity());
            Entity ent = getGrille().getEntity(0); //pacman
            
            setChanged();
            notifyObservers();
            try{
                Thread.sleep(750);
                if(ent.currentDir!=null){
                ent.depl(ent.currentDir);
                }
                for(int i=1;i<getGrille().GetListE().size();i++){
                    System.out.println("ALLO ?!?");
                    Entity Ghost=getGrille().getEntity(i);
                   
                        Ghost.DepAlea();
                        Ghost.depl(Ghost.currentDir);
                    
                }
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());            
            }
        }
    }
}
