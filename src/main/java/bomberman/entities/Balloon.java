package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Random;

import static bomberman.entities.GlobalVariable.entities;

public class Balloon extends Entity {
    public static int ANIMATE = 30;
    public static int TIME = 10;
    public static final int VELOCITY = 1;
    public static final int DIRECTIONS = 2;

    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    public void moveRight() {
        x+=VELOCITY;
        System.out.println("x1 = " + x);
        if (collisionChecker.checkCollision(this, listBarrier)) {
            moveLeft();
            System.out.println("x2 = " + x);
        }
        ANIMATE --;
        if (Balloon.ANIMATE < 0) {
            Balloon.ANIMATE = 30;
        }
    }

    public void moveLeft() {
        x-=VELOCITY;
        if (collisionChecker.checkCollision(this, listBarrier)) {
            moveRight();
        }
        ANIMATE --;
        if (Balloon.ANIMATE < 0) {
            Balloon.ANIMATE = 30;
        }
    }

    Random random = new Random();
    int randomDirection = random.nextInt(DIRECTIONS);

    @Override
    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Balloon) {

                if (randomDirection == 0) {
                    img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, Balloon.ANIMATE, Balloon.TIME).getFxImage();
                    moveRight();
                }
                if (randomDirection == 1) {
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, Balloon.ANIMATE, Balloon.TIME).getFxImage();
                    moveLeft();
                }
//                System.out.println(random);
            }

        }



    }
}
