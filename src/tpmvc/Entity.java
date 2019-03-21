/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmvc;

/**
 *
 * @author martin
 */
public class Entity {
    private int x;
    private int TEST;
    private int y;
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
}
