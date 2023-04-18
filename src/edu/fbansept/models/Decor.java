package edu.fbansept.models;

import java.awt.*;

public class Decor extends ScrollingSprite{
    public Decor(int x, int y, int largeur, int hauteur, float vitesseX) {
        super(x, y, largeur, hauteur, vitesseX);
    }

    @Override
    public void deplacement() {
        x -= vitesseX;
        if(x == -500){
            x = 500;
        }
    }


    @Override
    public void dessine(Graphics2D dessin) {
        dessin.setColor(Color.white);
        dessin.fillRect(x,y,largeur,hauteur);
//        Image img = Toolkit.getDefaultToolkit().getImage("./img/decor.jpg");
//        dessin.drawImage(img, x, y, largeur, hauteur, null);
    }
}
