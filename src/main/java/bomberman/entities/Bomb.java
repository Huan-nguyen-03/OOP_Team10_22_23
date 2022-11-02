package bomberman.entities;

import bomberman.BombermanGame;
import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import javax.swing.plaf.PanelUI;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {
    public static int MAX_BOMB_NUMBER = 1;
    public static int SIZE = 1;
    public static double TIME_EXPLOSION = 2; // second
    public static List<Bomb> listBomb = new ArrayList<>();

    private double timeBegin;       // the time the bomb was created
    private boolean checkExplosion;
    private boolean isSoundPlay;

    private List<Brick> listBrickIsDestroyed = null;
    private List<Entity> listEnemyIsDestroyed = null;
    enum OBJECT_IS_COLLIDED {
        NONE,
        BRICK,
        WALL
    }

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        timer2.setTime(LocalTime.now());
        timeBegin = timer2.switchBackToSecond();
        listBrickIsDestroyed = new ArrayList<>();
        listEnemyIsDestroyed = new ArrayList<>();
        checkExplosion = false;
        isSoundPlay = false;
    }

    @Override
    public void update() {
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, ANIMATE, TIME).getFxImage();

        if (listEvent.contains(Integer.SPACE)) {
            listEvent.remove(Integer.SPACE);
        }

        if(checkExplosion && listBrickIsDestroyed.size() != 0) {
            removeBrick();
            removeBrick();

        }
        if (time() > TIME_EXPLOSION && !checkExplosion) {
            explosion();
            setSoundPlay();
            checkExplosion = true;

        }
        if(removeFlame()) {
            GlobalVariable.stillObjects.remove(listBomb.get(0));
            listBarrier.remove(listBomb.get(0));
            listBomb.remove(0);
            removeEnemy();
        }

    }

    public void explosion() {
        Flame center = new Flame(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE, Sprite.bomb_exploded.getFxImage(), Flame.Type.CENTER.ordinal());
        checkExplosionCollision(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE);
        GlobalVariable.stillObjects.add(center);

        for (int i = 1; i <= SIZE; i++) {       // check right
            if (checkExplosionCollision(this.getX()/Sprite.SCALED_SIZE + i, this.getY()/Sprite.SCALED_SIZE) != 0) {
                if (checkExplosionCollision(this.getX()/Sprite.SCALED_SIZE + i, this.getY()/Sprite.SCALED_SIZE) == OBJECT_IS_COLLIDED.BRICK.ordinal())
                    destroyBrick(this.getX()/Sprite.SCALED_SIZE + i, this.getY()/Sprite.SCALED_SIZE);
                break;
            }
            if (i == SIZE) {
                Flame rightLast = new Flame(this.getX()/Sprite.SCALED_SIZE + SIZE, this.getY()/Sprite.SCALED_SIZE, Sprite.explosion_horizontal_right_last.getFxImage(), Flame.Type.RIGHT_LAST.ordinal());
                GlobalVariable.stillObjects.add(rightLast);
            }
            else {
                Flame right = new Flame(this.getX() / Sprite.SCALED_SIZE + i, this.getY() / Sprite.SCALED_SIZE, Sprite.explosion_horizontal.getFxImage(), Flame.Type.HORIZONTAL.ordinal());
                GlobalVariable.stillObjects.add(right);
            }
        }

        for (int i = 1; i <= SIZE; i++) {       // check left
            if (checkExplosionCollision(this.getX()/Sprite.SCALED_SIZE - i, this.getY()/Sprite.SCALED_SIZE) != 0) {
                if (checkExplosionCollision(this.getX()/Sprite.SCALED_SIZE - i, this.getY()/Sprite.SCALED_SIZE) == OBJECT_IS_COLLIDED.BRICK.ordinal())
                    destroyBrick(this.getX()/Sprite.SCALED_SIZE - i, this.getY()/Sprite.SCALED_SIZE );
                break;
            }
            if (i == SIZE) {
                Flame leftLast = new Flame(this.getX()/Sprite.SCALED_SIZE - SIZE, this.getY()/Sprite.SCALED_SIZE, Sprite.explosion_horizontal_left_last.getFxImage(), Flame.Type.LEFT_LAST.ordinal());
                GlobalVariable.stillObjects.add(leftLast);
            }
            else {
                Flame left = new Flame(this.getX()/Sprite.SCALED_SIZE - i, this.getY()/Sprite.SCALED_SIZE, Sprite.explosion_horizontal.getFxImage(), Flame.Type.HORIZONTAL.ordinal());
                GlobalVariable.stillObjects.add(left);
            }
        }

        for (int i = 1; i <= SIZE; i++) {       // check  top
            if (checkExplosionCollision(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE - i) != 0) {
                if (checkExplosionCollision(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE - i) == OBJECT_IS_COLLIDED.BRICK.ordinal())
                    destroyBrick(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE - i);
                break;
            }
            if (i == SIZE) {
                Flame topLast = new Flame(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE - SIZE, Sprite.explosion_vertical_top_last.getFxImage(), Flame.Type.TOP_LAST.ordinal());
                GlobalVariable.stillObjects.add(topLast);
            }
            else {
                Flame top = new Flame(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE - i, Sprite.explosion_vertical.getFxImage(), Flame.Type.VERTICAL.ordinal());
                GlobalVariable.stillObjects.add(top);
            }
        }

        for (int i = 1; i <= SIZE; i++) {       // check down
            if (checkExplosionCollision(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE + i) != 0) {
                if (checkExplosionCollision(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE + i) == OBJECT_IS_COLLIDED.BRICK.ordinal())
                    destroyBrick(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE + i);
                break;
            }
            if (i == SIZE) {
                Flame downLast = new Flame(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE + SIZE, Sprite.explosion_vertical_down_last.getFxImage(), Flame.Type.DOWN_LAST.ordinal());
                GlobalVariable.stillObjects.add(downLast);
            }
            else {
                Flame down = new Flame(this.getX()/Sprite.SCALED_SIZE, this.getY()/Sprite.SCALED_SIZE + i, Sprite.explosion_vertical.getFxImage(), Flame.Type.VERTICAL.ordinal());
                GlobalVariable.stillObjects.add(down);
            }
        }
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
                    GlobalVariable.stillObjects.remove(i);
                    i--;
                    checkRemove = true;
                }
            }
        }
        return checkRemove;
    }

    public int checkExplosionCollision(int x, int y) { // x, y ngược do tọa độ scene với matrix ngược nhau
        destroyEnemy(x, y);
        if (Map.map[y][x] == '*') {
            return OBJECT_IS_COLLIDED.BRICK.ordinal();
        }

        if (Map.map[y][x] == '#')
            return OBJECT_IS_COLLIDED.WALL.ordinal();
        return OBJECT_IS_COLLIDED.NONE.ordinal();
    }

    public void destroyBrick(int x, int y) {
        Brick brickIsDestroyed = (Brick) Map.mapObjects[y][x];
        listBrickIsDestroyed.add(brickIsDestroyed);
        brickIsDestroyed.setCheckExploded(true);
    }

    public boolean removeBrick() {
        boolean checkRemoveBrick = false;

        for (int i = 0; i< listBrickIsDestroyed.size(); i++) {
            if (listBrickIsDestroyed.get(i).checkDone()) {
                listBrickIsDestroyed.get(i).createItem();
                GlobalVariable.stillObjects.remove(listBrickIsDestroyed.get(i));
                listBarrier.remove(listBrickIsDestroyed.get(i));
                Map.map[listBrickIsDestroyed.get(i).getY()/Sprite.SCALED_SIZE][listBrickIsDestroyed.get(i).getX()/Sprite.SCALED_SIZE] = ' ';
                listBrickIsDestroyed.remove(i);
                Brick.numberOfBrick--;
                i--;
                checkRemoveBrick = true;
            }
        }

        return checkRemoveBrick;
    }

    public void destroyEnemy(int x, int y) {
        if (Map.map[y][x] == '1') {
            Balloon balloon = (Balloon) Map.mapObjects[y][x];
            BombermanGame.score += 100;
            balloon.setDeath(true);
            Map.map[y][x] = ' ';
            Grass grass = new Grass(x, y, Sprite.grass.getFxImage());
            Map.mapObjects[y][x] = grass;
            listEnemyIsDestroyed.add(balloon);
        }

        if (Map.map[y][x] == '2') {
            Oneal oneal = (Oneal) Map.mapObjects[y][x];
            oneal.setDeath(true);
            Map.map[y][x] = ' ';
            Grass grass = new Grass(x, y, Sprite.grass.getFxImage());
            Map.mapObjects[y][x] = grass;
            listEnemyIsDestroyed.add(oneal);
        }

        if(BombermanGame.bomberman.getX()/Sprite.SCALED_SIZE == x && BombermanGame.bomberman.getY()/Sprite.SCALED_SIZE == y) {
            BombermanGame.bomberman.death = true;
            timer2.setTime(LocalTime.now());
            Bomber.dieTime = timer2.switchBackToSecond();
        }
    }

    public void removeEnemy() {
        for (int i = 0; i < listEnemyIsDestroyed.size(); i++) {
            GlobalVariable.entities.remove(listEnemyIsDestroyed.get(i));
            listEnemyIsDestroyed.remove(i);
            i--;
        }
    }
    void setSoundPlay() {
        sound.bom.play();
    }


}








