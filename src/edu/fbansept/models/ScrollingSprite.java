package edu.fbansept.models;

public abstract class ScrollingSprite extends MovingSprite {


    public ScrollingSprite(int x, int y, int largeur, int hauteur, float vitesseX) {
        super(x, y, largeur, hauteur, vitesseX, 0);
    }

    public abstract void deplacement();
}
