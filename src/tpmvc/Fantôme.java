package tpmvc;

import java.util.ArrayList;
import java.util.Random;

public class Fantôme extends Entity{

    public Fantôme(int _x, int _y, Grille g){
        this.x=_x;
        this.y=_y;
        this.grid = g;
        this.currentDir=Dir.droite;
    }

    int tristantisbogossno;
    @Override
    public void DepAlea(int x,int y){
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
                if (this.getX()==0||!grid.getGrille()[this.getX() - 1][this.getY()]) {
                    change = true;
                    
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

            Random choix = new Random();
            boolean randouchase=choix.nextBoolean(); //generates a random boolean. 50/50 chance.

            if(randouchase){
                System.out.println("Le ghost va se deplacer aleatoirement");
            }else{
                int actualx=this.getX();
                int actualy=this.getY();
                int diffx=actualx-x;
                int diffy=actualy-y;

                if(diffy>diffx){//il faut se deplacer en horizontale

                    //si la diff est positif faut aller à gauche sinon faut aller à droite.
                    if(diffy>0){ // faut aller à gauche
                        if(dispo.contains(Dir.gauche)) {
                            this.currentDir = Dir.gauche; // si on peut dispo.contains(Dir.gauche)
                        }else{

                        }

                    }else{ // faut aller à droite si possible

                        if(dispo.contains(Dir.droite)) {
                            this.currentDir = Dir.droite; // si on peut dispo.contains(Dir.gauche)
                        }else{ //voir la meilleur des deux possibilites verticales s'ils sont possible, sinon prendre la seule possible.


                        }


                    }


                }else{//il faut se deplacer en verticale

                }
                System.out.println("Chase pacman: il est au: X:"+ x);

            }
            int lim=dispo.size();      
            int Max=lim-1;
            int Min=0;
            int  nombreAleatoire = Min + (int)(Math.random()) * ((Max-Min)+1);
            Random rand = new Random();
             nombreAleatoire = rand.nextInt(Max - Min + 1) + Min;

            this.currentDir=dispo.get(nombreAleatoire);


        }

    }
}
