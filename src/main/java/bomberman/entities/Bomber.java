package bomberman.entities;

import bomberman.entities.Item.BombItem;
import bomberman.entities.Item.FlameItem;
import bomberman.entities.Item.SpeedItem;
import bomberman.graphics.Sprite;
import bomberman.linhtinh.CollisionChecker;
import javafx.scene.image.Image;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.List;
import java.util.zip.GZIPOutputStream;


public class Bomber extends Entity {
    public static int ANIMATE = 30;
    public static int TIME = 10;
    public static int VELOCITY = 3;

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

        Entity e = collisionChecker.checkItemCollision(this, listItem);
        if (e != null) {
            if (e instanceof BombItem) {
                Bomb.MAX_BOMB_NUMBER++;
                listItem.remove(e);
                GlobalVariable.stillObjects.remove(e);
                Map.map[e.getY()/Sprite.SCALED_SIZE][e.getX()/Sprite.SCALED_SIZE] = ' ';
                Grass g = new Grass(e.getX()/Sprite.SCALED_SIZE, e.getY()/Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
                Map.mapObjects[e.getY()/Sprite.SCALED_SIZE][e.getX()/Sprite.SCALED_SIZE] = g;
            }
            else if (e instanceof FlameItem) {
                Bomb.SIZE ++;
                listItem.remove(e);
                GlobalVariable.stillObjects.remove(e);
                Map.map[e.getY()/Sprite.SCALED_SIZE][e.getX()/Sprite.SCALED_SIZE] = ' ';
                Grass g = new Grass(e.getX()/Sprite.SCALED_SIZE, e.getY()/Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
                Map.mapObjects[e.getY()/Sprite.SCALED_SIZE][e.getX()/Sprite.SCALED_SIZE] = g;
            }
            else if (e instanceof SpeedItem) {
                VELOCITY ++;
                listItem.remove(e);
                GlobalVariable.stillObjects.remove(e);
                Map.map[e.getY()/Sprite.SCALED_SIZE][e.getX()/Sprite.SCALED_SIZE] = ' ';
                Grass g = new Grass(e.getX()/Sprite.SCALED_SIZE, e.getY()/Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
                Map.mapObjects[e.getY()/Sprite.SCALED_SIZE][e.getX()/Sprite.SCALED_SIZE] = g;
            }
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
