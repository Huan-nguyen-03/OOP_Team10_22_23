package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import java.time.LocalTime;

import java.util.Random;

import static bomberman.entities.GlobalVariable.entities;

public class Balloon extends Entity {
    public static int ANIMATE = 60;
    public static int TIME = 10;
    public static final int VELOCITY = 1;
    public static final int DIRECTIONS = 4;
    public boolean isHitWall = false;
    public boolean isValidDir;
    private static final int MAXWIDTHBLOOM = 28;
    private static final int MAXHEIGHTBLOOM = 30;
    private double timeBegin; // when enemy was created

    private int count = 0;
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
        timer2.setTime(LocalTime.now());
        timeBegin = timer2.switchBackToSecond();
        System.out.println("timeBegin: " + timeBegin);

    }
    public double time() {
        timer2.setTime(LocalTime.now());
        double currentTime = timer2.switchBackToSecond();
        return currentTime - timeBegin;
    }
    public void resetTime() {
        timer2.setTime(LocalTime.now());
        timeBegin = timer2.switchBackToSecond();
    }

    public void moveRight() {
        x+=VELOCITY;
        if (collisionChecker.universalCheckCollision(this, listBarrier, MAXWIDTHBLOOM, MAXHEIGHTBLOOM)) {
            x-=VELOCITY;
            isHitWall = true;
            isValidDir = false;
            if (isHitWall && !isValidDir) {
                x-=VELOCITY;
            }
        } else {
            isValidDir = true;
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
            isValidDir = false;
            if (isHitWall && !isValidDir) {
                x+=VELOCITY;
            }
        } else {
            isValidDir = true;
        }
        ANIMATE --;
        if (Balloon.ANIMATE < 0) {
            Balloon.ANIMATE = 30;
        }
    }

    public void moveUp() {
        y-=VELOCITY;
        if (collisionChecker.universalCheckCollision(this, listBarrier, MAXWIDTHBLOOM, MAXHEIGHTBLOOM)) {
            y+=VELOCITY;
            isHitWall = true;
            isValidDir = false;
            if (isHitWall && !isValidDir) {
                y+=VELOCITY;
            }
        } else {
            isValidDir = true;
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
            isValidDir = false;
            if (isHitWall && !isValidDir) {
                y-=VELOCITY;
            }
        } else {
            isValidDir = true;
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
                    if (!isHitWall && isValidDir) {
                        System.out.println("time: " + time());
                        count++;
                        System.out.println("count: " + count);
                        if (time() > 3) {
                            System.out.println("greater than 2");

                            randomDirection = random.nextInt((DIRECTIONS - 2)) + 2;

//                            count = 0;
                            resetTime();
                        }
                    }
                    if (isHitWall && !isValidDir) {
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
                    if (!isHitWall && isValidDir) {
                        System.out.println("time: " + time());
                        count++;
                        System.out.println("count: " + count);
                        if (time() > 3) {
                            System.out.println("greater than 2");

                            randomDirection = random.nextInt((DIRECTIONS - 2)) + 2;

//                            count = 0;
                            resetTime();
                        }
                    }
                    if (isHitWall && !isValidDir) {
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
                    if (!isHitWall && isValidDir) {
                        System.out.println("time: " + time());
                        count++;
                        System.out.println("count: " + count);
                        if (time() > 3) {
                            System.out.println("greater than 2");

                            randomDirection = random.nextInt(DIRECTIONS - 2);

//                            count = 0;
                            resetTime();
                        }
                    }
                    if (isHitWall && !isValidDir) {
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
                    if (!isHitWall && isValidDir) {
                        System.out.println("time: " + time());
                        count++;
                        System.out.println("count: " + count);
                        if (time() > 3) {
                            System.out.println("greater than 2");

                            randomDirection = random.nextInt(DIRECTIONS - 2);

                            while (randomDirection == 3);
//                            count = 0;
                            resetTime();
                        }
                    }
                    if (isHitWall && !isValidDir) {
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
