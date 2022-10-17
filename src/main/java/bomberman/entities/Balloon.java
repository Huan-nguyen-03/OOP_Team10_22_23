package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static bomberman.entities.GlobalVariable.entities;

public class Balloon extends Entity {
    public static int ANIMATE = 60;
    public static int TIME = 10;
    public static final int VELOCITY = 1;
    public static final int DIRECTIONS = 4;
    public boolean isHitWall = false;
    private static final int MAXWIDTHBLOOM = 28;
    private static final int MAXHEIGHTBLOOM = 30;
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    public void moveRight() {
        x+=VELOCITY;
        if (collisionChecker.universalCheckCollision(this, listBarrier, MAXWIDTHBLOOM, MAXHEIGHTBLOOM)) {
            x-=VELOCITY;
            isHitWall = true;
            if (isHitWall) {
                x-=VELOCITY;
            }
        }
        ANIMATE --;
        if (Balloon.ANIMATE < 0) {
            Balloon.ANIMATE = 30;
        }
    }

    public void moveLeft() {
        x-=VELOCITY;
        if (collisionChecker.universalCheckCollision(this, listBarrier, MAXWIDTHBLOOM, MAXHEIGHTBLOOM)) {
            x+=VELOCITY;
            isHitWall = true;
            if (isHitWall) {
                x+=VELOCITY;
            }
        }
        ANIMATE --;
        if (Balloon.ANIMATE < 0) {
            Balloon.ANIMATE = 30;
        }
    }

    public void moveUp () {
        y-=VELOCITY;
        if (collisionChecker.universalCheckCollision(this, listBarrier, MAXWIDTHBLOOM, MAXHEIGHTBLOOM)) {
            y+=VELOCITY;
            isHitWall = true;
            if (isHitWall) {
                y+=VELOCITY;
            }
        }
        ANIMATE --;
        if (Bomber.ANIMATE < 0) {
            Bomber.ANIMATE = 30;
        }
    }

    public void moveDown() {
        y+=VELOCITY;
        if (collisionChecker.universalCheckCollision(this, listBarrier, MAXWIDTHBLOOM, MAXHEIGHTBLOOM)) {
            y-=VELOCITY;
            isHitWall = true;
            if (isHitWall) {
                y-=VELOCITY;
            }
        }
        ANIMATE --;
        if (Bomber.ANIMATE < 0) {
            Bomber.ANIMATE = 30;
        }
    }
    Random random = new Random();
    int randomDirection = random.nextInt(4);

    @Override
    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Balloon) {
                if (randomDirection == 0) {
                    img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, Balloon.ANIMATE, Balloon.TIME).getFxImage();
                    moveRight();
                    if (isHitWall) {
                        do {
                            randomDirection = random.nextInt(DIRECTIONS);
                        }
                        while (randomDirection == 0);
                        isHitWall = false;
                    }
                }

                if (randomDirection == 1) {
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, Balloon.ANIMATE, Balloon.TIME).getFxImage();
                    moveLeft();
                    if (isHitWall) {
                        do {
                            randomDirection = random.nextInt(DIRECTIONS);
                        }
                        while (randomDirection == 1);
                        isHitWall = false;
                    }
                }
                if (randomDirection == 2) {
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, Balloon.ANIMATE, Balloon.TIME).getFxImage();
                    moveUp();
                    if (isHitWall) {
                        do {
                            randomDirection = random.nextInt(DIRECTIONS);
                        }
                        while (randomDirection == 2);
                        isHitWall = false;
                    }
                }
                if (randomDirection == 3) {
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, Balloon.ANIMATE, Balloon.TIME).getFxImage();
                    moveDown();
                    if (isHitWall) {
                        do {
                            randomDirection = random.nextInt(DIRECTIONS);
                        }
                        while (randomDirection == 3);
                        isHitWall = false;
                    }
                }
            }

        }



    }
}
