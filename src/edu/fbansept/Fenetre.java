package edu.fbansept;

import edu.fbansept.models.Decor;
import edu.fbansept.models.Flappy;
import edu.fbansept.models.Tuyau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fenetre extends Canvas {

    protected Flappy flappy;
    protected int nbrTuyaux = 3;
    protected Tuyau[] listeTuyauxHaut = new Tuyau[nbrTuyaux];
    protected Tuyau[] listeTuyauxBas = new Tuyau[nbrTuyaux];
    public static int LARGEUR = 500;
    public static int HAUTEUR = 500;
    protected Decor decor1= new Decor(0, 0, LARGEUR, HAUTEUR, 1);
    protected Decor decor2= new Decor(LARGEUR, HAUTEUR, LARGEUR, HAUTEUR, 1);




    protected boolean spacePressed = false;


    public Fenetre() {
        setSize(LARGEUR,HAUTEUR);

        JFrame frame = new JFrame("Super jeu !");
        JPanel panneau = (JPanel) frame.getContentPane();
        panneau.setSize(LARGEUR,HAUTEUR);
        panneau.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
        setBounds(0, 0, LARGEUR , HAUTEUR);
        panneau.add(this);

        frame.setSize(LARGEUR,HAUTEUR);
        frame.pack();
        frame.setResizable(false);
        frame.setIgnoreRepaint(true);
        frame.requestFocus();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {

                    if(!spacePressed) {
                        flappy.setVitesseY(-3);
                    }
                    spacePressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    spacePressed = false;
                }
            }
        });

        createBufferStrategy(2);
        this.setFocusable(false);

        frame.setVisible(true);

        demarrer();
    }

    public void demarrer() {

        boolean shouldStop = false;

        flappy = new Flappy();

        for(int i = 0; i < nbrTuyaux; i++) {

            Random random = new Random();
            int interTuyaux = 125;
            int hauteurTuyau = random.nextInt(500 - interTuyaux + 1);;

            listeTuyauxHaut[i] = new Tuyau(
                    LARGEUR + 200 * i,
                    HAUTEUR - hauteurTuyau,
                    80,
                    hauteurTuyau,
                    2);

            listeTuyauxBas[i] = new Tuyau(
                    LARGEUR + 200 * i,
                    0,
                    80,
                    HAUTEUR - hauteurTuyau - interTuyaux,
                    2);
        }

        try {
            long frame = 0;

            while(!shouldStop) {
                frame ++;

                Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

//                decor1.deplacement();
                decor1.dessine(dessin);
//                decor2.deplacement();
//                decor2.dessine(dessin);

                flappy.deplacement();
                flappy.dessine(dessin);



                for(int i = 0; i < nbrTuyaux; i++) {
                    Tuyau tuyauHaut = listeTuyauxHaut[i];
                    Tuyau tuyauBas = listeTuyauxBas[i];
                    tuyauHaut.deplacement();
                    tuyauBas.deplacement();
                    tuyauHaut.dessine(dessin);
                    tuyauBas.dessine(dessin);
                    if(flappy.collision(tuyauHaut) || flappy.collision(tuyauBas)) {
                        System.out.println("Score: " + frame);
                        shouldStop = true;
                    }
                }

                dessin.dispose();
                getBufferStrategy().show();
                Thread.sleep(17);//environ 1000 / 60
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Fenetre();
    }

}
