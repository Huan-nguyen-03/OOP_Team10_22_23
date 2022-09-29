package bomberman.entities;

import bomberman.Main.BombermanGame;
import bomberman.linhtinh.CollisionChecker;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    public static enum Integer {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        SPACE
    }

    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    protected Image img;

    public static List <Entity> listBarrier = new ArrayList<>();
    CollisionChecker collisionChecker = new CollisionChecker();

    public static List<Integer> listEvent = new ArrayList<>();
    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
//    public abstract void update(List<Entity> stillObjects);
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
