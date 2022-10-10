package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import java.util.List;

import static bomberman.entities.GlobalVariable.entities;

public class Balloon extends Entity {
    public static int ANIMATE = 30;
    public static int TIME = 10;
    public static final int VELOCITY = 3;
    public static final int MAX = 3;
    public static final int MIN = 0;
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    public void moveRight() {
        x+=VELOCITY;
        if (collisionChecker.checkCollision(this, listBarrier)) {
            x -= VELOCITY;
//            generateRandom();
        }
        ANIMATE --;
        if (Balloon.ANIMATE < 0) {
            Balloon.ANIMATE = 30;
        }
    }

    public void moveLeft() {
        x-=VELOCITY;
        if (collisionChecker.checkCollision(this, listBarrier)) {
            x += VELOCITY;
//            generateRandom();
        }
        ANIMATE --;
        if (Balloon.ANIMATE < 0) {
            Balloon.ANIMATE = 30;
        }
    }

    public void moveUp() {
        y-=VELOCITY;
        if (collisionChecker.checkCollision(this, listBarrier)) {
            y += VELOCITY;
//            generateRandom();
        }
        ANIMATE --;
        if (Balloon.ANIMATE < 0) {
            Balloon.ANIMATE = 30;
        }
    }

    public void moveDown() {
        y+=VELOCITY;
        if (collisionChecker.checkCollision(this, listBarrier)) {
            y -= VELOCITY;
//            generateRandom();
        }
        ANIMATE --;
        if (Balloon.ANIMATE < 0) {
            Balloon.ANIMATE = 30;
        }
    }

    public int generateRandom() {
        return (int) Math.floor(Math.random() * (MAX - MIN + 1) + MIN);
    }

    @Override
    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Balloon) {
                int random = generateRandom();
                if (random == 0) {
                    img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, Balloon.ANIMATE, Balloon.TIME).getFxImage();
                    moveRight();
                }
                if (random == 1) {
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, Balloon.ANIMATE, Balloon.TIME).getFxImage();
                    moveLeft();
                }
                if (random == 2) {
                    img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, Balloon.ANIMATE, Balloon.TIME).getFxImage();
                    moveUp();
                }
                if (random == 3) {
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, Balloon.ANIMATE, Balloon.TIME).getFxImage();
                    moveDown();
                }
                System.out.println(random);
            }

        }



    }
}
