package bomberman.entities;

import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import java.time.LocalTime;
import java.util.List;

public class Brick extends Entity {
    private boolean checkExploded;
    private boolean checkUpdateAnimate;  //chỉ để cập nhật lại ANIMATE thành 300000.
    public Brick(int x, int y, Image img) {
        super(x, y, img);
        checkExploded = false;
        checkUpdateAnimate = false;
    }

    @Override
    public void update() {
        if (checkExploded) {
            img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, ANIMATE, TIME).getFxImage();
        }
    }

    public boolean checkDone() {
        if (ANIMATE > 300000 - TIME) {    // limit 3 frame rendered
            return false;
        }
        return true;
    }

    public boolean isCheckExploded() {
        return checkExploded;
    }

    public void setCheckExploded(boolean checkExploded) {
        this.checkExploded = checkExploded;
        if (!checkUpdateAnimate) {
            timer1.setTime(LocalTime.now());
            checkUpdateAnimate = true;
        }
    }
}
