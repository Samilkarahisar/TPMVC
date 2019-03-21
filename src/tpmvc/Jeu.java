/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmvc;

import static java.lang.Thread.*;
import java.util.Observable;

/**
 *
 * @author martin
 */
public class Jeu extends Observable implements Runnable{
    private Grille grille;
    private final int WIDTH = 10;
    private final int LENGHT = 10;
    
    public Jeu(){
        grille = new Grille(new Boolean[LENGHT][WIDTH]);

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
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());            
            }
        }
    }
}
