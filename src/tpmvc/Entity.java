/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmvc;

import java.util.ArrayList;


public class Entity {
    public int x;
    private int TEST;
    public int y;
    Dir currentDir; 
    Grille grid;

    public Entity(){
        x=3;
        y=3;
        currentDir=Dir.droite;
    }

    public Entity(int _x, int _y, Grille g){
        x=_x;
        y=_y;
        grid = g;
    }
    
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void SetCurrentDir(Dir NewDir){
        this.currentDir=NewDir;
    }
    
    public boolean depl(Dir d){
        Boolean[][] temp = grid.getGrille();
        switch(d){
            case droite:
                if(x+1<grid.getLENGHT()){
                    if(temp[x+1][y]){
                        this.x++;
                        return true;
                    }
                }
                break;
            case gauche:
                if(x-1>=0){
                    if(temp[x-1][y]){
                        this.x--;
                        return true;
                    }
                }
                break;
            case bas:
                if(y+1<grid.getWIDTH()){
                    if(temp[x][y+1]){
                        this.y++;
                        System.out.println(x+' '+y);
                        return true;
                    }
                }
                break;
            case haut:
                if(y-1>=0){
                    if(temp[x][y-1]){
                        this.y--;
                        System.out.println(x+' '+y);
                        return true;
                        
                    }
                }
                break;
        }
        return false;
    }
    public void DepAlea(int x,int y){
        Boolean change=false;
        ArrayList<Dir> dispo=new ArrayList<Dir>();
        switch(this.currentDir) {
            case bas:
                if (this.getY()==0||!grid.getGrille()[this.getX()][this.getY() + 1]) {
                    change = true;
                }
                break;
            case haut:
                if (this.getY()==grid.getWIDTH()-1||!grid.getGrille()[this.getX()][this.getY() - 1]) {
                    change = true;
                }
                break;
            case droite:
                if (this.getX()==grid.getLENGHT()-1||!grid.getGrille()[this.getX() + 1][this.getY()]) {
                    change = true;
                }
                break;
            case gauche:
                if (this.getX()==0||!grid.getGrille()[this.getX() - 1][this.getY()]) {
                    change = true;
                }
                break;
            default:
                break;
        }
        if(change) {
            if(grid.getGrille()[this.getX()][this.getY() + 1]){
                dispo.add(Dir.bas);
            }
            if(grid.getGrille()[this.getX()][this.getY() - 1]){
                dispo.add(Dir.haut);
            }
            if(grid.getGrille()[this.getX()+1][this.getY()]){
                dispo.add(Dir.droite);
            }
            if(grid.getGrille()[this.getX()-1][this.getY() ]){
                dispo.add(Dir.gauche);
            }
            int lim=dispo.size();
            int Max=lim-1;
            int Min=0;
            int  nombreAleatoire = Min + (int)(Math.random()) * ((Max-Min)+1);
            this.currentDir=dispo.get(nombreAleatoire);


        }
    }
}
