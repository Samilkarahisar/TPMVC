/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmvc;

import java.util.ArrayList;


public class Grille {
    private Boolean grille[][];
    private final int WIDTH = 21;
    private final int LENGHT = 21;
    private ArrayList<Entity> list;
    private int nbEntity;
    
     Boolean grilleBase[][]={
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


    
    public Grille(){
        list = new ArrayList();
        nbEntity = 0;
        grille=this.grilleBase;
    }
    
    public Grille(Boolean[][] b){
        grille = b;
        list = new ArrayList();
        nbEntity = 0;

        for(int i=0; i<=LENGHT-1;i++){
            for(int j=0; j<WIDTH;j++){
                grille[i][j] = true;
                if(j==WIDTH-1){
                    grille[i][j] = false;
                }
                if(i==LENGHT-1){
                    grille[i][j] = false;
                }
            }
        }

    }

    public ArrayList<Entity> GetListE(){
        return this.list;
    }

    public Grille(Grille g){
        grille = g.getGrille();
        for(int k = 0; k<g.getNbEntity();k++){
            list.add(g.getEntity(k));
        }
        nbEntity=g.getNbEntity();
    }
    

    public Boolean[][] getGrille() {
        return grille;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getLENGHT() {
        return LENGHT;
    }
    
    public Entity getEntity(int i){
        return list.get(i);
    }
    
    public int getNbEntity(){
        return nbEntity;
    }
    
    public void getNewE(int x,int y) throws Exception{
        if(grille[x][y]){
            list.add(new Entity(x,y,this));
            nbEntity++;
        }
        else{
            throw new Exception("la case est prise");
        }
    }
    
    
}
