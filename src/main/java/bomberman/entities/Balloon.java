package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static bomberman.entities.GlobalVariable.entities;

public class Balloon extends Entity {
    public static final double VELOCITY = 1;
    private static final int MAXWIDTHBLOOM = 32;
    private static final int MAXHEIGHTBLOOM = 32;
    private double xDouble;
    private double yDouble;
    private int x_map;  // coordinates are saved in map
    private int y_map;  // coordinates are saved in map
    private boolean death;
    public List<Integer> listDirections = new ArrayList<>();

    private int currentDirection;
    private int animationDirection;
    private int oppositeDirection;

    public Balloon(int x, int y, Image img) {
        super(x, y, img);
        death = false;
        xDouble = x * Sprite.SCALED_SIZE;
        yDouble = y * Sprite.SCALED_SIZE;

        currentDirection = Integer.LEFT.ordinal();
        animationDirection = Integer.LEFT.ordinal();
        oppositeDirection = Integer.RIGHT.ordinal();

        x_map = y;
        y_map = x;
    }
    public void moveRight() {
        xDouble+=VELOCITY;
        x = (int) xDouble;
        if (collisionChecker.universalCheckCollision(this, listBarrierForEnemies, MAXWIDTHBLOOM, MAXHEIGHTBLOOM)) {
            xDouble -= VELOCITY;
            x = (int) xDouble;
        }
        animationDirection = Integer.RIGHT.ordinal();
    }

    public void moveLeft() {
        xDouble-=VELOCITY;
        x = (int) xDouble;
        if (collisionChecker.universalCheckCollision(this, listBarrierForEnemies, MAXWIDTHBLOOM, MAXHEIGHTBLOOM)) {
            xDouble += VELOCITY;
            x = (int) xDouble;
        }
        animationDirection = Integer.LEFT.ordinal();
    }

    public void moveUp() {
        yDouble-=VELOCITY;
        y = (int) yDouble;
        if (collisionChecker.universalCheckCollision(this, listBarrierForEnemies, MAXWIDTHBLOOM, MAXHEIGHTBLOOM)) {
            yDouble += VELOCITY;
            y = (int) yDouble;
        }

    }

    public void moveDown() {
        yDouble+=VELOCITY;
        y = (int) yDouble;
        if (collisionChecker.universalCheckCollision(this, listBarrierForEnemies, MAXWIDTHBLOOM, MAXHEIGHTBLOOM)) {
            yDouble -= VELOCITY;
            y = (int) yDouble;
        }
    }
    Random random = new Random();

    @Override
    public void update() {
        if (death) {
            img = Sprite.balloom_dead.getFxImage();
        } else {
            lookUp();
            moveAlgorithm();
            if (currentDirection == Integer.LEFT.ordinal()) {
                moveLeft();
            }
            if (currentDirection == Integer.RIGHT.ordinal()) {
                moveRight();
            }
            if (currentDirection == Integer.UP.ordinal()) {
                moveUp();
            }
            if (currentDirection == Integer.DOWN.ordinal()) {
                moveDown();
            }

            if (animationDirection == Integer.LEFT.ordinal())
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, ANIMATE, TIME).getFxImage();
            if (animationDirection == Integer.RIGHT.ordinal())
                img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, ANIMATE, TIME).getFxImage();
            updateMap();
        }
    }
    public void lookUp() {
        listDirections.clear();
        if (currentDirection == Integer.UP.ordinal())
            listDirections.add(Integer.UP);
        if (currentDirection == Integer.DOWN.ordinal())
            listDirections.add(Integer.DOWN);
        if (currentDirection == Integer.LEFT.ordinal())
            listDirections.add(Integer.LEFT);
        if (currentDirection == Integer.RIGHT.ordinal())
            listDirections.add(Integer.RIGHT);

        ////////////////
        int x_val = this.getX() / Sprite.SCALED_SIZE;
        int y_val = this.getY() / Sprite.SCALED_SIZE;
        if (this.getX()/(double) Sprite.SCALED_SIZE == x_val && this.getY()/(double) Sprite.SCALED_SIZE == y_val) {
            listDirections.clear();
            if (Map.map[y_val][x_val - 1] == ' ')
                listDirections.add(Integer.LEFT);
            if (Map.map[y_val][x_val + 1] == ' ')
                listDirections.add(Integer.RIGHT);
            if (Map.map[y_val + 1][x_val] == ' ')
                listDirections.add(Integer.DOWN);
            if (Map.map[y_val - 1][x_val] == ' ')
                listDirections.add(Integer.UP);

            // delete opposite direction
            if (currentDirection == Integer.UP.ordinal()) {
                oppositeDirection = Integer.DOWN.ordinal();
                listDirections.remove(Integer.DOWN);
            }
            if (currentDirection == Integer.DOWN.ordinal()) {
                oppositeDirection = Integer.UP.ordinal();
                listDirections.remove(Integer.UP);
            }
            if (currentDirection == Integer.LEFT.ordinal()) {
                oppositeDirection = Integer.RIGHT.ordinal();
                listDirections.remove(Integer.RIGHT);
            }
            if (currentDirection == Integer.RIGHT.ordinal()) {
                oppositeDirection = Integer.LEFT.ordinal();
                listDirections.remove(Integer.LEFT);
            }
        }
    }

    public void moveAlgorithm() {
        if (listDirections.size() > 1) {
            int rand = random.nextInt(listDirections.size());
            currentDirection = listDirections.get(rand).ordinal();
        }
        if (listDirections.size() == 0)
        {
            int temp = currentDirection;
            currentDirection = oppositeDirection;
            oppositeDirection = temp;
        }
        if (listDirections.size() == 1) {
            currentDirection = listDirections.get(0).ordinal();
        }
    }

    public void updateMap() {
        int x_val = (int) Math.ceil(this.getX() / Sprite.SCALED_SIZE);
        int y_val = (int) Math.ceil(this.getY() / Sprite.SCALED_SIZE);

        if (x_val != y_map || y_val != x_map) {
            Grass grass = new Grass(y_map, x_map, Sprite.grass.getFxImage());
            Map.mapObjects[x_map][y_map] = grass;
            Map.mapObjects[y_val][x_val]= this;

            Map.map[x_map][y_map] = ' ';
            Map.map[y_val][x_val] = '1';

            x_map = y_val;
            y_map = x_val;
        }
    }
    public void setDeath(boolean death) {
        this.death = death;
    }

}
