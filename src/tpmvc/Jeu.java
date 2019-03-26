/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmvc;

import static java.lang.Thread.*;
import java.util.ArrayList;
import java.util.Observable;


public class Jeu extends Observable implements Runnable{
    private Grille grille;
    private final int WIDTH = 10;
    private final int LENGHT = 10;
    private int pointcompteur=0;
    public boolean restart;
    public boolean gameover=false;
    public ArrayList<SuperGomme> ListBonus;
    public int SuperMode;
    public int MaxPoint;
    public boolean gagne;
    
     private static final Boolean Map[][]={
            {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
         {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
         {false,true,true,true,true,true,false,true,false,true,false,true,false,true,true,true,false,true,true,true,false},
         {false,true,false,true,false,true,true,true,true,true,true,true,true,true,false,true,true,true,false,true,false},
         {false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,false,false,true,false,true,false},
         {false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,false},
         {false,true,false,true,false,false,false,false,false,true,false,false,false,true,false,true,false,false,false,true,false},
         {false,true,false,true,true,true,false,true,true,true,true,true,true,true,false,true,true,true,false,true,false},
         {false,true,false,true,false,true,false,true,false,false,false,true,false,true,false,true,false,true,false,true,false},
         {false,true,true,true,false,true,true,true,true,true,false,true,false,true,true,true,false,true,true,true,false},
         {false,false,false,true,false,false,false,true,true,true,false,true,false,false,false,true,false,false,false,true,false},
         {false,true,true,true,false,true,true,true,true,true,false,true,false,true,true,true,false,true,true,true,false},
         {false,true,false,true,false,true,false,true,false,false,false,true,false,true,false,true,false,true,false,true,false},
         {false,true,false,true,true,true,false,true,true,true,true,true,true,true,false,true,true,true,false,true,false},
         {false,true,false,true,false,false,false,false,false,true,false,false,false,true,false,true,false,false,false,true,false},
         {false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,false},
         {false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,false,false,true,false,true,false},
         {false,true,false,true,false,true,true,true,true,true,true,true,true,true,false,true,true,true,false,true,false},
         {false,true,true,true,true,true,false,true,false,true,false,true,false,true,true,true,false,true,true,true,false},
         {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
         {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}
    };

    private Boolean GrillePoints[][];
    
    
    
    void InitGomme(){
        ListBonus=new ArrayList<SuperGomme>();
        SuperGomme gomme1= new SuperGomme();
        ListBonus.add(gomme1);
        SuperGomme gomme2= new SuperGomme(18,18);
        ListBonus.add(gomme2);
        SuperGomme gomme3= new SuperGomme(4,19);
        ListBonus.add(gomme3);
        SuperGomme gomme4= new SuperGomme(18,3);
        ListBonus.add(gomme4);
    }
    
    public Jeu(){
        grille = new Grille();
        GrillePoints=Map.clone();
        ListBonus=new ArrayList<SuperGomme>();
        InitGomme();
        startPointCompteur();
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
    
    public void CheckFantôme(Entity ent){
        int x=ent.x;
        int y=ent.y;
        for(int i = 1; i<this.grille.GetListE().size();i++){
                if(grille.getEntity(i).x==x&&grille.getEntity(i).y==y){
                    grille.getEntity(i).x=10;
                    grille.getEntity(i).y=10;
                    System.out.println("ON MANGE LE FANTOME "+i);
                }
        }   
    }
    
    public void CheckGomme(Entity ent){
        for(int l=0;l<ListBonus.size();l++){
            SuperGomme current=ListBonus.get(l);
            if(ent.x==current.x&&ent.y==current.y){
                SuperMode+=50;
                ListBonus.remove(l);
            }
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
    
    public void startPointCompteur(){
        MaxPoint=0;
        for(int i=0;i<21;i++){
            for(int j=0;j<21;j++){
                if(GrillePoints[i][j]){
                    MaxPoint++;
                }
            }
        }
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
            getGrille().getNewE(10,11);
            Fantôme ghost1 = new Fantôme(11,9, getGrille());
            Fantôme ghost2 = new Fantôme(12,9, getGrille());
            getGrille().GetListE().add(ghost1);
            getGrille().GetListE().add(ghost2);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        while(true){
            Entity ent = getGrille().getEntity(0); //pacman
            
            setChanged();
            notifyObservers();

            try{
                CheckPoint(ent);
                if(pointcompteur==MaxPoint){
                    this.gagne=true;
                    setChanged();
                    notifyObservers();
                    while(this.gagne){
                        Thread.sleep(20);
                    }
                }
                CheckGomme(ent);
                if(SuperMode>0){
                    CheckFantôme(ent);
                    SuperMode--;
                }
                else    if (CheckMort(ent)){
                    this.gameover= true;
                    setChanged();
                    notifyObservers();
                    while(!this.restart){
                        Thread.sleep(100);
                    }
                    ent.x=10;
                    ent.y=11;
                    this.restart=false;
                    this.gameover=false;
                }
                Thread.sleep(700);
                if(ent.currentDir!=null){
                    ent.depl(ent.currentDir);
                }

                
                Thread.sleep(5);
                setChanged();
                notifyObservers();
                System.out.println("Votre score: " + pointcompteur);
                
                CheckGomme(ent);
                if(SuperMode>0){
                    CheckFantôme(ent);
                    SuperMode--;
                    System.out.println("Valeur bonus: "+SuperMode);
                }
                else if (CheckMort(ent)){
                    this.gameover= true;
                    setChanged();
                    notifyObservers();
                    while(!this.restart){

                        Thread.sleep(100);
                    }
                    ent.x=10;
                    ent.y=11;
                    this.restart=false;
                    this.gameover=false;
                }
                setChanged();
                notifyObservers();
                
                for(int i=1;i<getGrille().GetListE().size();i++){
                    Entity Ghost=getGrille().getEntity(i);
                        Ghost.DepAlea(ent.getX(),ent.getY());
                        Ghost.depl(Ghost.currentDir);
                }
                
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());            
            }
        }
    }
}
