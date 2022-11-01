package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import bomberman.entities.Item.*;
import java.time.LocalTime;
import java.util.Random;


public class Brick extends Entity {
    enum Type_Item {
        NONE,
        BOMB_ITEM,
        FLAME_ITEM,
        SPEED_ITEM,
        BOMB_PASS_ITEM,
        PORTAL
    }
    public static int numberOfBrick = 0;
    public static boolean hasPortal = false;
    public static boolean hasBombPassItem = false;
    private boolean checkExploded;
    private Random rand; // to calculate the possibility of item appearance
    private boolean checkUpdateAnimate;  //chỉ để cập nhật lại ANIMATE thành 300000.
    public Brick(int x, int y, Image img) {
        super(x, y, img);
        checkExploded = false;
        checkUpdateAnimate = false;
        rand = new Random();
    }

    @Override
    public void update() {
        if (checkExploded) {
            img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded2, Sprite.brick_exploded1, ANIMATE, TIME).getFxImage();
        }
    }

    public boolean checkDone() {
        if (ANIMATE > 300000 - TIME) {    // limit 3 frame rendered
            return false;
        }
        return true;
    }

    public boolean isCheckExploded() {
        return checkExploded;
    }

    public void setCheckExploded(boolean checkExploded) {
        this.checkExploded = checkExploded;
        if (!checkUpdateAnimate) {
            timer1.setTime(LocalTime.now());
            checkUpdateAnimate = true;
        }
    }

    public int itemAppear() {
//        int number = rand.nextInt(10);
//        if (number == 6 && !hasBombPassItem) {
//            hasBombPassItem = true;
//            return Type_Item.BOMB_PASS_ITEM.ordinal();
//        }
//        else if (number == 7)
//            return Type_Item.BOMB_ITEM.ordinal();
//        else if (number == 8)
//            return Type_Item.FLAME_ITEM.ordinal();
//        else if (number == 9)
//            return Type_Item.SPEED_ITEM.ordinal();
        if (true){
//            int r = rand.nextInt(numberOfBrick);
                hasPortal = true;
                return Type_Item.PORTAL.ordinal();

        }
        return Type_Item.NONE.ordinal();
    }

    public void createItem() {
        if (itemAppear() == Type_Item.BOMB_ITEM.ordinal()) {
            BombItem item = new BombItem(getX()/Sprite.SCALED_SIZE, getY()/Sprite.SCALED_SIZE, Sprite.powerup_bombs.getFxImage());
            GlobalVariable.stillObjects.add(item);
            Map.map[getY()/Sprite.SCALED_SIZE][getX()/Sprite.SCALED_SIZE] = 'b';
            Map.mapObjects[getY()/Sprite.SCALED_SIZE][getX()/Sprite.SCALED_SIZE] = item;
            listItem.add(item);
        }

        if (itemAppear() == Type_Item.FLAME_ITEM.ordinal()) {
            FlameItem item = new FlameItem(getX()/Sprite.SCALED_SIZE, getY()/Sprite.SCALED_SIZE, Sprite.powerup_flames.getFxImage());
            GlobalVariable.stillObjects.add(item);
            Map.map[getY()/Sprite.SCALED_SIZE][getX()/Sprite.SCALED_SIZE] = 'f';
            Map.mapObjects[getY()/Sprite.SCALED_SIZE][getX()/Sprite.SCALED_SIZE] = item;
            listItem.add(item);
        }

        if (itemAppear() == Type_Item.SPEED_ITEM.ordinal()) {
            SpeedItem item = new SpeedItem(getX()/Sprite.SCALED_SIZE, getY()/Sprite.SCALED_SIZE, Sprite.powerup_speed.getFxImage());
            GlobalVariable.stillObjects.add(item);
            Map.map[getY()/Sprite.SCALED_SIZE][getX()/Sprite.SCALED_SIZE] = 's';
            Map.mapObjects[getY()/Sprite.SCALED_SIZE][getX()/Sprite.SCALED_SIZE] = item;
            listItem.add(item);
        }

        if (itemAppear() == Type_Item.PORTAL.ordinal()) {
            Portal portal = new Portal(getX()/Sprite.SCALED_SIZE, getY()/Sprite.SCALED_SIZE, Sprite.portal.getFxImage());
            GlobalVariable.stillObjects.add(portal);
            Map.map[getY()/Sprite.SCALED_SIZE][getX()/Sprite.SCALED_SIZE] = 'p';
            Map.mapObjects[getY()/Sprite.SCALED_SIZE][getX()/Sprite.SCALED_SIZE] = portal;
            listItem.add(portal);
        }

        if (itemAppear() == Type_Item.BOMB_PASS_ITEM.ordinal()) {
            BombPassItem bombPass = new BombPassItem(getX()/Sprite.SCALED_SIZE, getY()/Sprite.SCALED_SIZE, Sprite.powerup_bombpass.getFxImage());
            GlobalVariable.stillObjects.add(bombPass);
            Map.map[getY()/Sprite.SCALED_SIZE][getX()/Sprite.SCALED_SIZE] = '_';
            Map.mapObjects[getY()/Sprite.SCALED_SIZE][getX()/Sprite.SCALED_SIZE] = bombPass;
            listItem.add(bombPass);
        }
    }
}
