package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static bomberman.entities.GlobalVariable.entities;

public class Balloon extends Entity {
    public static int ANIMATE = 30;
    public static int TIME = 10;
    public static final int VELOCITY = 1;
    public static final int DIRECTIONS = 4;

    public List<java.lang.Integer> listDirection = new ArrayList<java.lang.Integer>();

    public void addDirection() {
        for (int i = 0; i < DIRECTIONS; i++) {
            listDirection.add(i);
        }
    }

    public boolean isHitWall = false;
    private static final int MAXWIDTHBLOOM = 28;
    private static final int MAXHEIGHTBLOOM = 30;
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    public static List get(List numbers, int noOfRandomNumbers) {
        List list = new ArrayList(numbers);
        List randomList = new ArrayList();

        Random rd = new Random();
        for (int i = 0; i < noOfRandomNumbers; i++) {
            int index = (int) (rd.nextDouble() * list.size());
            randomList.add(list.get(index));
            list.remove(index);
        }
        return randomList;
    }
    public void moveRight() {
        x+=VELOCITY;
        if (collisionChecker.checkCollision(this, listBarrier)) {
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
        if (collisionChecker.checkCollision(this, listBarrier)) {
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

    public void moveUp () {
        y-=VELOCITY;
        if (collisionChecker.checkCollision(this, listBarrier)) {
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
        if (collisionChecker.checkCollision(this, listBarrier)) {
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
                        List randomList = get(listDirection, 3);
                        randomDirection = (int) randomList.get(0);
                        isHitWall = false;
                    }

                }

                if (randomDirection == 1) {
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, Balloon.ANIMATE, Balloon.TIME).getFxImage();
                    moveLeft();
                    if (isHitWall) {
                        List randomList = get(listDirection, 3);
                        randomDirection = (int) randomList.get(0);
                        isHitWall = false;
                    }
                }
                if (randomDirection == 2) {
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, Balloon.ANIMATE, Balloon.TIME).getFxImage();
                    moveUp();
                    if (isHitWall) {
                        List randomList = get(listDirection, 3);
                        randomDirection = (int) randomList.get(0);
                        isHitWall = false;
                    }
                }
                if (randomDirection == 3) {
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, Balloon.ANIMATE, Balloon.TIME).getFxImage();
                    moveDown();
                    if (isHitWall) {
                        List randomList = get(listDirection, 3);
                        randomDirection = (int) randomList.get(0);
                        isHitWall = false;
                    }
                }
            }

        }



    }
}
