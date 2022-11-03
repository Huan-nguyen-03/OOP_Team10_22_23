package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static bomberman.entities.GlobalVariable.entities;

public class Mob extends Entity {
    public static int RANGE = 3;
    public static double previousTime = 0.0;
    public static boolean checkDoAlgorithm = false;
    private int x_map;  // coordinates are saved in map
    private int y_map;  // coordinates are saved in map
    private boolean death;

    public Mob(int x, int y, Image img) {
        super(x, y, img);
        death = false;

        x_map = y;
        y_map = x;
    }
    Random random = new Random();

    @Override
    public void update() {
        if (death) {
            img = Sprite.mob_dead1.getFxImage();
        } else {
            img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, ANIMATE, TIME).getFxImage();
            timer2.setTime(LocalTime.now());
            if (((int) (timer2.switchBackToSecond() - timer1.switchBackToSecond()) % 3 == 0) && !checkDoAlgorithm) {
                moveAlgorithm();
                checkDoAlgorithm = true;
                previousTime = timer2.switchBackToSecond();
            }
            if (checkDoAlgorithm && ((previousTime + 1) < timer2.switchBackToSecond())) {
                checkDoAlgorithm = false;
            }

            updateMap();
        }
    }

    public void moveAlgorithm() {
        while(true) {
            int i = (int) (random.nextInt(RANGE * 2 + 1)) - RANGE;
            int j = (int) (random.nextInt(RANGE * 2 + 1)) - RANGE;
            if ((x_map + i) > 0 && (x_map + i) < Map.row && (y_map + j) > 0 && (y_map + j) < Map.column) {
                if (Map.map[x_map + i][y_map + j] == ' ') {
                    x = (y_map + j) * Sprite.SCALED_SIZE;
                    y = (x_map + i) * Sprite.SCALED_SIZE;
                    break;
                }
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
            Map.map[y_val][x_val] = '5';

            x_map = y_val;
            y_map = x_val;
        }
    }
    public void setDeath(boolean death) {
        this.death = death;
    }

}
