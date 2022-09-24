package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;


public class Bomber extends Entity {

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }
//    Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    @Override
    public void update() {

    }

    public void moveRight() {
        x+=5;
    }

    public void moveLeft() {
        x-=5;
    }

    public void moveUp () {
        y-=5;
    }
    public void moveDown() {
        y+=5;
    }
}
