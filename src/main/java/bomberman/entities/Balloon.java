package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Balloon extends Entity {
    private boolean death;
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
        death = false;
    }

    @Override
    public void update() {
        if (death) {
            img = Sprite.balloom_dead.getFxImage();
        }

    }
    public void setDeath(boolean death) {
        this.death = death;
    }

}
