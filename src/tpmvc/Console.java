/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmvc;

import java.util.Observable;
import java.util.Observer;


public class Console implements Observer{
    private Jeu game;
    
    public Console(Jeu j){
        game = j;
        game.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        boolean b = false;
        
        for(int i=0;i<game.getLENGHT();i++){
            for(int j=0;j<game.getWIDTH();j++){
                for(int k = 0; k<game.getGrille().getNbEntity(); k++){
                    //System.out.println(game.getGrille().getEntity(0).getX() + " " + game.getGrille().getEntity(0).getY());
                    if(game.getGrille().getEntity(k).getX() == i && game.getGrille().getEntity(k).getY() == j){
                        System.out.print("_E");
                        b = true;
                    }
                }
                if(!b){
                    System.out.print("_"+game.getGrille().getGrille()[i][j]);
                }
                b=false;
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
