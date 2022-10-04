package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Flame extends Entity{
    public Flame(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (img == Sprite.bomb_exploded.getFxImage() || img == Sprite.bomb_exploded1.getFxImage() || img == Sprite.bomb_exploded2.getFxImage())
            img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, ANIMATE, TIME).getFxImage();
        if (img == Sprite.explosion_horizontal.getFxImage())
            img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, ANIMATE, TIME).getFxImage();
        if (img == Sprite.explosion_vertical.getFxImage())
            img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, ANIMATE, TIME).getFxImage();
        if (img == Sprite.explosion_horizontal_right_last.getFxImage())
            img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, ANIMATE, TIME).getFxImage();
        if (img == Sprite.explosion_horizontal_left_last.getFxImage())
            img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_right_last2, ANIMATE, TIME).getFxImage();

        System.out.println(ANIMATE);
    }
}
