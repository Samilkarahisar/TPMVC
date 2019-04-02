/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmvc;

import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Graphique extends Application{
    //modèle ( le jeu qui s'execute)

    public final int SIZE_X =21;
    public final int SIZE_Y = 21;
    
 Jeu game;
 int column=0;
 int row=0;
 
 @Override
 public void start(Stage primaryStage){
   
    game= new Jeu();
 
    BorderPane border = new BorderPane();
    GridPane gPane = new GridPane();
 
    Image imPM_g = new Image("pacman_g.png"); // préparation des images
     Image imPM_d = new Image("pacman_d.png");
     Image imPM_h = new Image("pacman_h.png");
     Image imPM_b = new Image("pacman_b.png");

    Image imVide = new Image("Vide.png");
    Image imMur = new Image("mur.png");
    Image imBonus=new Image("Bonus.png");
     Image imYem = new Image("gomme.png");
     Image imGhostScared= new Image("ghost_chased.png");
     Image imGhost = new Image("ghost.png");

     Text t = new Text();
     t.setFont(Font.font ("Verdana", 30));
     t.setFill(Color.WHITE);


     Text t_gameover = new Text();
     t_gameover.setFont(Font.font ("Verdana", 30));
     t_gameover.setFill(Color.WHITE);


     t_gameover.setText("Game over. Appuyer sur Entree, pour recommencer.");
     
     Text t_win = new Text();
     t_win.setFont(Font.font ("Verdana", 30));
     t_win.setFill(Color.WHITE);


     t_win.setText("Vous avez Gagné ! Appuyer sur Entree, pour recommencer.");

    ImageView[][] tab = new ImageView[SIZE_X][SIZE_Y];



        for (int i = 0; i < SIZE_X; i++) { // initialisation de la grille (sans image)
            for (int j = 0; j < SIZE_Y; j++) {
                ImageView img = new ImageView();
                
                tab[i][j] = img;
                
                gPane.add(img, i, j);
            }
            
        }
        
    
    
         
        Observer o =  new Observer() { // l'observer observe l'obervable (update est exécuté dès notifyObservers() est appelé côté modèle )
            @Override
            public void update(Observable o, Object arg) {
                if(game.gameover){
                    t_gameover.setVisible(true);
                }
                else{
                    t_gameover.setVisible(false);
                }
                if(game.gagne){
                    t_win.setVisible(true);
                }
                else{
                    t_win.setVisible(false);
                }
                t.setText("Score:" + game.getPointcompteur());

                for (int i = 0; i < SIZE_X; i++) { // rafraichissement graphique
                    for (int j = 0; j < SIZE_Y; j++) {
                        try{
                            if(game.getGrille().getGrille()[i][j]){
                                if(game.getPoints()[i][j]){
                                    tab[i][j].setImage(imYem);
                                    for(int l=0;l<game.ListBonus.size();l++){
                                        SuperGomme current=game.ListBonus.get(l);
                                        if(i==current.x&&j==current.y){
                                            tab[i][j].setImage(imBonus);
                                        }
                                    }
                                }
                                else {
                                    tab[i][j].setImage(imVide);
                                }
                            }
                            else{
                                 tab[i][j].setImage(imMur);
                            } 
                            if (game.getGrille().getEntity(0).getX() == i && game.getGrille().getEntity(0).getY() == j) { // spm est à la position i, j => le dessiner
                                Dir orientation=game.getGrille().getEntity(0).currentDir;

                                tab[i][j].setImage(imPM_d);

                                if(orientation==Dir.gauche){

                                    tab[i][j].setImage(imPM_g);
                                }
                                else if(orientation==Dir.droite){

                                    tab[i][j].setImage(imPM_d);
                                }
                                else if(orientation==Dir.haut){

                                    tab[i][j].setImage(imPM_h);
                                }
                                else if(orientation==Dir.bas){

                                    tab[i][j].setImage(imPM_b);
                                }

                            }
                             for(int k = 1; k<game.getGrille().GetListE().size(); k++){
                    //System.out.println(game.getGrille().getEntity(0).getX() + " " + game.getGrille().getEntity(0).getY());
                         //dans le cas de pacman k vaut 0
                         
                             if (game.getGrille().getEntity(k).getX() == i && game.getGrille().getEntity(k).getY() == j) { // spm est à la position i, j => le dessiner

                                if(game.SuperMode>0){
                                    tab[i][j].setImage(imGhostScared);
                                }
                                else tab[i][j].setImage(imGhost);

                             }

                         
                         }

                           
                        }catch(IndexOutOfBoundsException exception){
                        System.out.println("Probleme de taille de tableau?");
                        }
                       
                        
                    }
                }    
            }
        };
        
        game.addObserver(o);
        game.start();
        StackPane root = new StackPane();
        root.getChildren().add(gPane);
        root.getChildren().add(t);
        root.getChildren().add(t_gameover);
        root.getChildren().add(t_win);


        StackPane.setAlignment(t_gameover,Pos.CENTER);
        StackPane.setAlignment(t_win,Pos.CENTER);
        StackPane.setAlignment(t, Pos.BOTTOM_LEFT);
        gPane.setGridLinesVisible(true);

        
    Scene scene = new Scene(root,840,840);
    
          
    primaryStage.setTitle("PACMAN");
    primaryStage.setScene(scene);
    primaryStage.show();

  
         root.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() { // on écoute le clavier
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                Entity pacMan=game.getGrille().getEntity(0);
                switch(event.getCode()){
                    case UP:
                        pacMan.SetCurrentDir(Dir.haut);
                    break;
                    case DOWN:
                        pacMan.SetCurrentDir(Dir.bas);
                    break;
                    case RIGHT:
                        pacMan.SetCurrentDir(Dir.droite);
                    break;
                    case LEFT:
                        pacMan.SetCurrentDir(Dir.gauche);
                    break;
                    case ENTER:
                        game.restart=true;
                        game.gameover=false;
                        game.gagne=false;
                        game.resetPointcompteur();
                        game.InitGomme();
                    break;
                    default:
                    break;
                }
            }
        });
    
    
    gPane.requestFocus();//?
 }
   
     public static void main(String[] args){
      launch(args);
    } 
   
   
}

