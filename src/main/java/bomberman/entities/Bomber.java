package bomberman.entities;

import bomberman.graphics.Sprite;
import bomberman.linhtinh.CollisionChecker;
import javafx.scene.image.Image;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.List;


public class Bomber extends Entity {
    public static int ANIMATE = 30;
    public static int TIME = 10;
    public static final int VELOCITY = 2;

//    CollisionChecker collisionChecker = new CollisionChecker(this, );

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }
//    Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    @Override
    public void update() {
        if(Entity.listEvent.contains(Integer.LEFT)) {
            img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, Bomber.ANIMATE, Bomber.TIME).getFxImage();
//            Bomber bomberman = new Bomber(1, 1, Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, Bomber.ANIMATE, Bomber.TIME).getFxImage());
            moveLeft();
        }
        if(Entity.listEvent.contains(Integer.RIGHT)) {
            img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, Bomber.ANIMATE, Bomber.TIME).getFxImage();
//            Bomber bomberman = new Bomber(1, 1, Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, Bomber.ANIMATE, Bomber.TIME).getFxImage());
            moveRight();

        }
        if(Entity.listEvent.contains(Integer.UP)) {
            img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, Bomber.ANIMATE, Bomber.TIME).getFxImage();
//            Bomber bomberman = new Bomber(1, 1, Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, Bomber.ANIMATE, Bomber.TIME).getFxImage());
            moveUp();

        }
        if(Entity.listEvent.contains(Integer.DOWN)) {
            img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, Bomber.ANIMATE, Bomber.TIME).getFxImage();
//            Bomber bomberman = new Bomber(1, 1, Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, Bomber.ANIMATE, Bomber.TIME).getFxImage());
            moveDown();
        }
    }

    public void moveRight() {
        x+=VELOCITY;
        if (collisionChecker.checkCollision(this, listBarrier))
            x-=VELOCITY;
        ANIMATE --;
        if (Bomber.ANIMATE < 0) {
            Bomber.ANIMATE = 30;
        }
    }

    public void moveLeft() {
        x-=VELOCITY;
        if (collisionChecker.checkCollision(this, listBarrier))
            x+=VELOCITY;
        ANIMATE --;
        if (Bomber.ANIMATE < 0) {
            Bomber.ANIMATE = 30;
        }
    }

    public void moveUp () {
        y-=VELOCITY;
        if (collisionChecker.checkCollision(this, listBarrier))
            y+=VELOCITY;
        ANIMATE --;
        if (Bomber.ANIMATE < 0) {
            Bomber.ANIMATE = 30;
        }
    }
    public void moveDown() {
        y+=VELOCITY;
        if (collisionChecker.checkCollision(this, listBarrier))
            y-=VELOCITY;
        ANIMATE --;
        if (Bomber.ANIMATE < 0) {
            Bomber.ANIMATE = 30;
        }
    }
}
