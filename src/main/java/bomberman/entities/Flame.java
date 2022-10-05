package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Flame extends Entity{
    enum Type {
        CENTER,
        HORIZONTAL,
        VERTICAL,
        TOP_LAST,
        DOWN_LAST,
        LEFT_LAST,
        RIGHT_LAST
    }

    private int type;
    public Flame(int x, int y, Image img, int type) {
        super(x, y, img);
        this.type = type;
    }

    @Override
    public void update() {
        if (type == Type.CENTER.ordinal())
            img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, ANIMATE, TIME).getFxImage();
        if (type == Type.HORIZONTAL.ordinal())
            img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, ANIMATE, TIME).getFxImage();
        if (type == Type.VERTICAL.ordinal())
            img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, ANIMATE, TIME).getFxImage();
        if (type == Type.RIGHT_LAST.ordinal())
            img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, ANIMATE, TIME).getFxImage();
        if (type == Type.LEFT_LAST.ordinal())
            img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, ANIMATE, TIME).getFxImage();
        if (type == Type.TOP_LAST.ordinal())
            img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, ANIMATE, TIME).getFxImage();
        if (type == Type.DOWN_LAST.ordinal())
            img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, ANIMATE, TIME).getFxImage();
//        System.out.println(ANIMATE);
    }

    public boolean checkDone() {
        if (ANIMATE > 300000 - TIME) {    // limit 3 frame rendered
            System.out.println(ANIMATE);
            return false;
        }
        return true;
    }

}
