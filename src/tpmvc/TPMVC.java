
package tpmvc;


public class TPMVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Jeu j = new Jeu();
        new Thread(j).start();
    }
    
}

