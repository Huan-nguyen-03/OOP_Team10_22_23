package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import javax.swing.plaf.PanelUI;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {
    public static int MAX_BOMB_NUMBER = 1;
    public static int SIZE = 1;
    public static double TIME_EXPLOSION = 3; // second
    public static List<Bomb> listBomb = new ArrayList<>();

    private double timeBegin;       // the time the bomb was created
    private boolean checkExplosion;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        timer2.setTime(LocalTime.now());
        timeBegin = timer2.switchBackToSecond();

        checkExplosion = false;
    }

    @Override
    public void update() {
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, ANIMATE, TIME).getFxImage();

        if (listEvent.contains(Integer.SPACE)) {
            listEvent.remove(Integer.SPACE);
        }

        if (time() > TIME_EXPLOSION && !checkExplosion) {
            explosion();
            checkExplosion = true;

        }
        if(removeFlame()) {
            GlobalVariable.stillObjects.remove(listBomb.get(0));
            listBomb.remove(0);
        }
    }

    public void explosion() {
        Flame center = new Flame(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE, Sprite.bomb_exploded.getFxImage(), Flame.Type.CENTER.ordinal());
        GlobalVariable.stillObjects.add(center);

        for (int i=1; i <= SIZE - 1; i++) {
            Flame top = new Flame(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE - i, Sprite.explosion_vertical.getFxImage(), Flame.Type.VERTICAL.ordinal());
            Flame down = new Flame(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE + i, Sprite.explosion_vertical.getFxImage(), Flame.Type.VERTICAL.ordinal());
            Flame right = new Flame(this.getX()/Sprite.SCALED_SIZE + i, this.getY()/Sprite.SCALED_SIZE, Sprite.explosion_horizontal.getFxImage(), Flame.Type.HORIZONTAL.ordinal());
            Flame left = new Flame(this.getX()/Sprite.SCALED_SIZE - i, this.getY()/Sprite.SCALED_SIZE, Sprite.explosion_horizontal.getFxImage(), Flame.Type.HORIZONTAL.ordinal());
            GlobalVariable.stillObjects.add(top);
            GlobalVariable.stillObjects.add(down);
            GlobalVariable.stillObjects.add(right);
            GlobalVariable.stillObjects.add(left);
        }
        Flame topLast = new Flame(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE - SIZE, Sprite.explosion_vertical_top_last.getFxImage(), Flame.Type.TOP_LAST.ordinal());
        Flame downLast = new Flame(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE + SIZE, Sprite.explosion_vertical_down_last.getFxImage(), Flame.Type.DOWN_LAST.ordinal());
        Flame rightLast = new Flame(this.getX()/Sprite.SCALED_SIZE + SIZE, this.getY()/Sprite.SCALED_SIZE, Sprite.explosion_horizontal_right_last.getFxImage(), Flame.Type.RIGHT_LAST.ordinal());
        Flame leftLast = new Flame(this.getX()/Sprite.SCALED_SIZE - SIZE, this.getY()/Sprite.SCALED_SIZE, Sprite.explosion_horizontal_left_last.getFxImage(), Flame.Type.LEFT_LAST.ordinal());

        GlobalVariable.stillObjects.add(topLast);
        GlobalVariable.stillObjects.add(downLast);
        GlobalVariable.stillObjects.add(rightLast);
        GlobalVariable.stillObjects.add(leftLast);


    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    public double time() {
        timer2.setTime(LocalTime.now());
        double currentTime = timer2.switchBackToSecond();
        return currentTime - timeBegin;
    }

    public boolean removeFlame() {
        boolean checkRemove = false;
        for (int i = 0; i < GlobalVariable.stillObjects.size(); i++) {
            if (GlobalVariable.stillObjects.get(i) instanceof Flame) {
                Flame f = (Flame) GlobalVariable.stillObjects.get(i);
                if (f.checkDone()) {
                    System.out.println(i);
                    GlobalVariable.stillObjects.remove(i);
                    i--;
                    checkRemove = true;
                }
            }
        }
        return checkRemove;
    }

//    public boolean checkExplosionColision(int x, int y) {
//        for (int i = 0; i < GlobalVariable.stillObjects.size(); i++) {
//            if (GlobalVariable.stillObjects.get(i) instanceof Brick)
//                return true;
//        }
//        return false;
//    }
}
