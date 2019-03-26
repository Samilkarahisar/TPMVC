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
    private int pointcompteur=0;
    public boolean restart;
    public boolean gameover=false;


     private static final Boolean Map[][]={
            {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,true,true,true,true,true,false,true,false,true,false,true,false,true,true,true,false,true,true,true,false},
            {false,true,false,true,false,true,false,false,false,true,false,false,false,true,false,true,true,true,false,true,false},
            {false,true,false,true,false,true,false,false,false,true,false,false,false,true,false,false,false,true,false,true,false},
            {false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,false,true,false},
            {false,true,false,true,false,false,false,false,false,true,false,false,false,true,false,true,false,false,false,true,false},
            {false,true,false,true,true,true,false,true,true,true,true,true,true,true,false,true,true,true,false,true,false},
            {false,true,false,true,false,true,false,true,false,false,false,true,false,true,false,true,false,true,false,true,false},
            {false,true,true,true,false,true,true,true,true,true,false,true,false,true,true,true,false,true,true,true,false},
            {false,false,false,true,false,false,false,true,true,true,false,true,false,false,false,true,false,false,false,true,false},
            {false,true,true,true,false,true,true,true,true,true,false,true,false,true,true,true,false,true,true,true,false},
            {false,true,false,true,false,true,false,true,false,false,false,true,false,true,false,true,false,true,false,true,false},
            {false,true,false,true,true,true,false,true,true,true,true,true,true,true,false,true,true,true,false,true,false},
            {false,true,false,true,false,false,false,false,false,true,false,false,false,true,false,true,false,false,false,true,false},
            {false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,false,true,false},
            {false,true,false,true,false,true,false,false,false,true,false,false,false,true,false,false,false,true,false,true,false},
            {false,true,false,true,false,true,false,false,false,true,false,false,false,true,false,true,true,true,false,true,false},
            {false,true,true,true,true,true,false,true,false,true,false,true,false,true,true,true,false,true,true,true,false},
            {false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}
    };

    private Boolean GrillePoints[][];

    public Jeu(){
        grille = new Grille();
        GrillePoints=Map.clone();
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

    public void CheckPoint(Entity ent){

        if(GrillePoints[ent.getX()][ent.getY()]){
            pointcompteur+=1;
            GrillePoints[ent.getX()][ent.getY()]=false;
        }

    }

    public void start(){
    new Thread(this).start();
    }

    public Boolean[][] getPoints(){return GrillePoints;}

    public Grille getGrille() {
        return grille;
    }
    public int getPointcompteur(){
        return pointcompteur;
    }
    public boolean getGameover(Entity ent){
        return CheckMort(ent);
    }
    public void resetPointcompteur(){
        pointcompteur=0;

        for (int k=0;k<21;k++) {
            for (int i=0; i < 21; i++) {
                GrillePoints[k][i]=true;
            }
        }

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
                Thread.sleep(400);
                if(ent.currentDir!=null){
                ent.depl(ent.currentDir);
                }

                CheckPoint(ent);
                this.gameover= getGameover(ent);
                        Thread.sleep(5);
                setChanged();
                notifyObservers();
                System.out.println("Votre score: " + pointcompteur);
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
