package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import java.util.List;

public class BombItem extends Entity {
    public BombItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (listEvent.contains(Integer.SPACE)) {
            img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, Bomber.ANIMATE, Bomber.TIME).getFxImage();
            listEvent.remove(Integer.SPACE);
        }
    }
}
