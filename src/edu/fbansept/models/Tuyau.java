package edu.fbansept.models;

import java.awt.*;

public class Tuyau extends ScrollingSprite implements Collisionnable {

    public Tuyau(int x, int y, int largeur, int hauteur, float vitesseX) {
        super(x, y, largeur, hauteur, vitesseX);
    }

    @Override
    public void deplacement() {
        x -= vitesseX;
        if(x == -80){
            x = 500;
        }
    }

    @Override
    public void dessine(Graphics2D dessin) {

        dessin.setColor(Color.green);
        dessin.fillRect(x,y,largeur, hauteur);
    }
}
