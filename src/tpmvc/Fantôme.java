package tpmvc;

import java.util.ArrayList;

public class Fantôme extends Entity{
    int x;
    int y;
    public Fantôme(int _x, int _y, Grille g){
        this.x=_x;
        this.y=_y;
        this.grid = g;
        this.currentDir=Dir.droite;
    }

    public void DepAlea(){
        Boolean change=false;
        ArrayList<Dir> dispo=new ArrayList<Dir>();
        switch(this.currentDir) {
            case bas:
                if (!grid.getGrille()[this.getX()][this.getY() + 1]) {
                    change = true;
                }
                break;
            case haut:
                if (!grid.getGrille()[this.getX()][this.getY() - 1]) {
                    change = true;
                }
                break;
            case droite:
                if (!grid.getGrille()[this.getX() + 1][this.getY()]) {
                    change = true;
                }
                break;
            case gauche:
                if (!grid.getGrille()[this.getX() - 1][this.getY()]) {
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
