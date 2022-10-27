package bomberman.entities;

import bomberman.graphics.Sprite;
import bomberman.linhtinh.AStar;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Integer;

import static bomberman.entities.GlobalVariable.entities;

public class Oneal extends Entity {
    public static final double VELOCITY = 1;
    private static final int MAXWIDTHBLOOM = 32;
    private static final int MAXHEIGHTBLOOM = 32;
    private double xDouble;
    private double yDouble;
    private int x_map;  // coordinates are saved in map
    private int y_map;  // coordinates are saved in map

    private int currentDirection;
    private int animationDirection;

    public AStar aStar = new AStar();
    private boolean death;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        death = false;
        xDouble = x * Sprite.SCALED_SIZE;
        yDouble = y * Sprite.SCALED_SIZE;

        x_map = y;
        y_map = x;

        currentDirection = Integer.RIGHT.ordinal();
        animationDirection = Integer.RIGHT.ordinal();
    }
    public void moveRight() {
        xDouble+=VELOCITY;
        x = (int) xDouble;
        if (collisionChecker.universalCheckCollision(this, listBarrier, MAXWIDTHBLOOM, MAXHEIGHTBLOOM)) {
            xDouble -= VELOCITY;
            x = (int) xDouble;
        }
    }

    public void moveLeft() {
        xDouble-=VELOCITY;
        x = (int) xDouble;
        if (collisionChecker.universalCheckCollision(this, listBarrier, MAXWIDTHBLOOM, MAXHEIGHTBLOOM)) {
            xDouble += VELOCITY;
            x = (int) xDouble;
        }
    }

    public void moveUp() {
        yDouble-=VELOCITY;
        y = (int) yDouble;
        if (collisionChecker.universalCheckCollision(this, listBarrier, MAXWIDTHBLOOM, MAXHEIGHTBLOOM)) {
            yDouble += VELOCITY;
            y = (int) yDouble;
        }

    }

    public void moveDown() {
        yDouble+=VELOCITY;
        y = (int) yDouble;
        if (collisionChecker.universalCheckCollision(this, listBarrier, MAXWIDTHBLOOM, MAXHEIGHTBLOOM)) {
            yDouble -= VELOCITY;
            y = (int) yDouble;
        }
    }
    Random random = new Random();

    @Override
    public void update() {
        if (death) {
            img = Sprite.oneal_dead.getFxImage();
        } else {
            int x_val = this.getX() / Sprite.SCALED_SIZE;
            int y_val = this.getY() / Sprite.SCALED_SIZE;
            if (this.getX()/(double) Sprite.SCALED_SIZE == x_val && this.getY()/(double) Sprite.SCALED_SIZE == y_val) {
                moveAlgorithm();
            }
            if (currentDirection == Integer.LEFT.ordinal()) {
//                System.out.println("LEFT");
                moveLeft();
            }
            if (currentDirection == Integer.RIGHT.ordinal()) {
//                System.out.println("RIGHT");
                moveRight();
            }
            if (currentDirection == Integer.UP.ordinal()) {
//                System.out.println("UP");
                moveUp();
            }
            if (currentDirection == Integer.DOWN.ordinal()) {
//                System.out.println("DOWN");
                moveDown();
            }

            if (animationDirection == Integer.LEFT.ordinal())
                img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, ANIMATE, TIME).getFxImage();
            if (animationDirection == Integer.RIGHT.ordinal())
                img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, ANIMATE, TIME).getFxImage();
            updateMap();
        }

    }

    public void moveAlgorithm() {
        int xPlayer = 0;
        int yPlayer = 0;
        // getPlayerCoordinates
        for (int i = 0; i < Map.row; i++) {
            for (int j = 0; j <Map.column; j++) {
                if (Map.map[i][j] == '0') {
                    xPlayer = i;
                    yPlayer = j;
                    break;
                }
            }
        }
//        System.out.println("xp = " + xPlayer);
//        System.out.println("yp = " + yPlayer);
        //

        int xOneal = x_map;
        int yOneal = y_map;

//        System.out.println("xO = " + xOneal);
//        System.out.println("yO = " + yOneal);
        if ((xOneal != xPlayer) || (yOneal != yPlayer)) {
            List<Pair<java.lang.Integer, java.lang.Integer>> path = new ArrayList<>();
            path = aStar.aStarSearch(Map.map, Map.row, Map.column, xOneal, yOneal, xPlayer, yPlayer);
            if (path != null && path.size() != 0) {
                int x_next = path.get(0).getKey();
                int y_next = path.get(0).getValue();

                // xét trên tọa độ map nên ngược với screen
                if (x_next > xOneal)
                    currentDirection = Integer.DOWN.ordinal();
                else if (x_next < xOneal)
                    currentDirection = Integer.UP.ordinal();
                else if (y_next > yOneal)
                    currentDirection = Integer.RIGHT.ordinal();
                else if (y_next < yOneal)
                    currentDirection = Integer.LEFT.ordinal();
            }
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
            Map.map[y_val][x_val] = '2';

            x_map = y_val;
            y_map = x_val;
        }

    }

    public void setDeath(boolean death) {
        this.death = death;
    }

}
