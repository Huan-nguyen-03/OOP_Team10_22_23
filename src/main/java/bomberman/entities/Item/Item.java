package bomberman.entities.Item;

import bomberman.entities.*;
import javafx.scene.image.Image;

import static bomberman.BombermanGame.playSound;

public class Item extends Entity {

    public Item(int x, int y, Image img) {
        super(x, y, img);

    }

    @Override
    public void update() {

    }

    public void setPlaySound() {
        if (playSound) {
            sound.item.play();
        }
    }
}
