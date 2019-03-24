package tpmvc;

import java.util.ArrayList;
import java.util.Random;

public class Fantôme extends Entity{

    int x;
    int y;
    public Fantôme(int _x, int _y, Grille g){
        this.x=_x;
        this.y=_y;
        this.grid = g;
        this.currentDir=Dir.droite;
    }

    int tristantisbogossno;
    @Override
    public void DepAlea(){System.out.println("Fantôme: "+this.getX()+" "+this.getY());
        Boolean change=false;
        ArrayList<Dir> dispo=new ArrayList<>();
        switch(this.currentDir) {
            case bas:
                if (this.getY()==grid.getWIDTH()-1||!grid.getGrille()[this.getX()][this.getY() + 1]) {
                    change = true;
                }
                break;
            case haut:
                if (this.getY()==0||!grid.getGrille()[this.getX()][this.getY() - 1]) {
                    change = true;
                }
                break;
            case droite:
                if (this.getX()==grid.getLENGHT()-1||!grid.getGrille()[this.getX() + 1][this.getY()]) {
                    change = true;
                }
                break;
            case gauche:
                if (this.getX()==0){
                    if(!grid.getGrille()[this.getX() - 1][this.getY()]) {
                    change = true;
                    }
                }
                break;
            default:
                break;
        }
        if(change) {
            if(!(this.getY()==grid.getWIDTH()-1)&&grid.getGrille()[this.getX()][this.getY() + 1]){
                dispo.add(Dir.bas);
            }
            if(!(this.getY()==0)&&grid.getGrille()[this.getX()][this.getY() - 1]){
                dispo.add(Dir.haut);
            }
            if(!(this.getX()==0)&&grid.getGrille()[this.getX()+1][this.getY()]){
                dispo.add(Dir.droite);
            }
            if(!(this.getX()==grid.getLENGHT()-1)&&grid.getGrille()[this.getX()-1][this.getY() ]){
                dispo.add(Dir.gauche);
            }
            System.out.println(dispo.toString());
            int lim=dispo.size();
            System.out.println("Limite: "+dispo.size());
            int Max=lim-1;
            int Min=0;
            int  nombreAleatoire = Min + (int)(Math.random()) * ((Max-Min)+1);
            Random rand = new Random();
             nombreAleatoire = rand.nextInt(Max - Min + 1) + Min;
            System.out.println("Direction numéro: "+nombreAleatoire);
            this.currentDir=dispo.get(nombreAleatoire);


        }

    }
}
