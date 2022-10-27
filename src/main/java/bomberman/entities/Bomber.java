package bomberman.entities;

import bomberman.entities.Item.*;
import bomberman.graphics.Sprite;
import javafx.scene.image.Image;


public class Bomber extends Entity {
    public static int ANIMATE = 30;
    public static int TIME = 10;
    public static int VELOCITY = 2;

    private int x_map;  // coordinates are saved in map
    private int y_map;  // coordinates are saved in map
    public static boolean checkBombPass = false;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);

        x_map = y;
        y_map = x;
    }
    @Override
    public void update() {
        setPlaySound();
        updateMap();
        addBombToListBarrier();
        if(Entity.listEvent.contains(Integer.LEFT)) {
            img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, Bomber.ANIMATE, Bomber.TIME).getFxImage();
            moveLeft();
        }
        if(Entity.listEvent.contains(Integer.RIGHT)) {
            img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, Bomber.ANIMATE, Bomber.TIME).getFxImage();
            moveRight();

        }
        if(Entity.listEvent.contains(Integer.UP)) {
            img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, Bomber.ANIMATE, Bomber.TIME).getFxImage();
            moveUp();

        }
        if(Entity.listEvent.contains(Integer.DOWN)) {
            img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, Bomber.ANIMATE, Bomber.TIME).getFxImage();
            moveDown();
        }

        Entity e = collisionChecker.checkItemCollision(this, listItem);
        if (e != null) {
            Item i = (Item) e;
            i.setPlaySound();
            if (e instanceof BombItem) {
                Bomb.MAX_BOMB_NUMBER++;
                listItem.remove(e);
                GlobalVariable.stillObjects.remove(e);
                Map.map[e.getY()/Sprite.SCALED_SIZE][e.getX()/Sprite.SCALED_SIZE] = ' ';
                Grass g = new Grass(e.getX()/Sprite.SCALED_SIZE, e.getY()/Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
                Map.mapObjects[e.getY()/Sprite.SCALED_SIZE][e.getX()/Sprite.SCALED_SIZE] = g;
            }
            else if (e instanceof FlameItem) {
                Bomb.SIZE ++;
                listItem.remove(e);
                GlobalVariable.stillObjects.remove(e);
                Map.map[e.getY()/Sprite.SCALED_SIZE][e.getX()/Sprite.SCALED_SIZE] = ' ';
                Grass g = new Grass(e.getX()/Sprite.SCALED_SIZE, e.getY()/Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
                Map.mapObjects[e.getY()/Sprite.SCALED_SIZE][e.getX()/Sprite.SCALED_SIZE] = g;
            }
            else if (e instanceof SpeedItem) {
                VELOCITY ++;
                listItem.remove(e);
                GlobalVariable.stillObjects.remove(e);
                Map.map[e.getY()/Sprite.SCALED_SIZE][e.getX()/Sprite.SCALED_SIZE] = ' ';
                Grass g = new Grass(e.getX()/Sprite.SCALED_SIZE, e.getY()/Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
                Map.mapObjects[e.getY()/Sprite.SCALED_SIZE][e.getX()/Sprite.SCALED_SIZE] = g;
            }
            else if (e instanceof BombPassItem) {
                checkBombPass = true;
                listItem.remove(e);
                GlobalVariable.stillObjects.remove(e);
                Map.map[e.getY()/Sprite.SCALED_SIZE][e.getX()/Sprite.SCALED_SIZE] = ' ';
                Grass g = new Grass(e.getX()/Sprite.SCALED_SIZE, e.getY()/Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
                Map.mapObjects[e.getY()/Sprite.SCALED_SIZE][e.getX()/Sprite.SCALED_SIZE] = g;
            }
        }
    }
    // listEv LEFT
    public void moveRight() {
        x+=VELOCITY;
        if (collisionChecker.checkCollision(this, listBarrier))
            x-=VELOCITY;
        ANIMATE --;
        if (Bomber.ANIMATE < 0) {
            Bomber.ANIMATE = 30;
        }
    }

    public void moveLeft() {
        x-=VELOCITY;
        if (collisionChecker.checkCollision(this, listBarrier))
            x+=VELOCITY;
        ANIMATE --;
        if (Bomber.ANIMATE < 0) {
            Bomber.ANIMATE = 30;
        }
    }

    public void moveUp () {
        y-=VELOCITY;
        if (collisionChecker.checkCollision(this, listBarrier))
            y+=VELOCITY;
        ANIMATE --;
        if (Bomber.ANIMATE < 0) {
            Bomber.ANIMATE = 30;
        }
    }
    public void moveDown() {
        y+=VELOCITY;
        if (collisionChecker.checkCollision(this, listBarrier))
            y-=VELOCITY;
        ANIMATE --;
        if (Bomber.ANIMATE < 0) {
            Bomber.ANIMATE = 30;
        }
    }

    void addBombToListBarrier() {
        if (!checkBombPass) {
            for (int i = 0; i < Bomb.listBomb.size(); i++) {
                if (!collisionChecker.checkCollisionWithBomb(this, Bomb.listBomb.get(i))
                        && !listBarrier.contains(Bomb.listBomb.get(i))) {
                    listBarrier.add(Bomb.listBomb.get(i));
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
            Map.map[y_val][x_val] = '0';

            x_map = y_val;
            y_map = x_val;
        }
    }

    public void setPlaySound() {
        if (Entity.listEvent.size() != 0 && !sound.click.isRunning()) {
            sound.click.play();
        }
    }

    public void setStopSound() {

    }
}
