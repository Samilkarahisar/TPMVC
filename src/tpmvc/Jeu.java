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
    public boolean restart;
    public Jeu(){
        grille = new Grille();

    }


    public boolean CheckMort(Entity ent){
        boolean res=false;
        for(int i=1;i<getGrille().GetListE().size();i++){
            Entity current=getGrille().getEntity(i);
            if(current.getX()==ent.getX()&&current.getY()==ent.getY()){
                res=true;
                System.out.println("Le Fantôme "+i+"  vous a dévoré !!");
            }
        }
        
        return res;
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
        boolean end;
        boolean restart=false;
        try{
            getGrille().getNewE(7,7);
            Fantôme ghost1 = new Fantôme(9,9, getGrille());
            Fantôme ghost2 = new Fantôme(11,7, getGrille());
            getGrille().GetListE().add(ghost1);
            getGrille().GetListE().add(ghost2);
            System.out.println(ghost2.getX()+" "+ghost2.getY()+"       "+getGrille().GetListE().size());
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
                if (CheckMort(ent)){
                    System.out.println("MORT ! MORT ! MORT ! IDIOT");
                    while(!this.restart){
                        Thread.sleep(100);
                    }
                    ent.x=3;
                    ent.y=3;
                    this.restart=false;
                }
                Thread.sleep(750);
                if(ent.currentDir!=null){
                ent.depl(ent.currentDir);
                }
                Thread.sleep(5);
                setChanged();
                notifyObservers();
                if (CheckMort(ent)){
                    System.out.println("MORT ! MORT ! MORT ! IDIOT");
                    while(!this.restart){
                        Thread.sleep(100);
                    }
                    ent.x=3;
                    ent.y=3;
                    this.restart=false;
                }
                for(int i=1;i<getGrille().GetListE().size();i++){
                    System.out.println("Fantôme num: "+i);
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
