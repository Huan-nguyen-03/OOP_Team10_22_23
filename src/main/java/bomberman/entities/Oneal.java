package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Oneal extends Entity {
    private boolean death;
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        death = false;
    }

    @Override
    public void update() {
        if (death) {
            img = Sprite.oneal_dead.getFxImage();
        }
    }

    public void setDeath(boolean death) {
        this.death = death;
    }
}
