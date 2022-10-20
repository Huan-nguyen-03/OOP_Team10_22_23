package bomberman.linhtinh;

import bomberman.entities.*;
import bomberman.entities.Item.BombItem;
import bomberman.entities.Item.FlameItem;
import bomberman.entities.Item.SpeedItem;
import bomberman.graphics.*;

import java.util.List;

public class CollisionChecker {
    private static final int MAXWIDTHBOMBER = 24;
    private static final int MAXHEIGHTBOMBER = 30;
    public boolean checkCollision(Entity entity, List<Entity> listBarrier) {
        for (int i = 0; i < listBarrier.size(); i++) {
            if (!(listBarrier.get(i) instanceof Grass)) {
                if (entity.getX() < listBarrier.get(i).getX() + Sprite.SCALED_SIZE
                        && entity.getX() + MAXWIDTHBOMBER > listBarrier.get(i).getX()
                        && entity.getY() < listBarrier.get(i).getY() + Sprite.SCALED_SIZE
                        && entity.getY() + MAXHEIGHTBOMBER > listBarrier.get(i).getY() ) {
                    return true;
                }
            }
        }
        return false;
    }

    public Entity checkItemCollision(Entity entity, List<Entity> listItem) {
        for (int i = 0; i < listItem.size(); i++) {
            if (entity.getX() < listItem.get(i).getX() + Sprite.SCALED_SIZE
                    && entity.getX() + MAXWIDTHBOMBER > listItem.get(i).getX()
                    && entity.getY() < listItem.get(i).getY() + Sprite.SCALED_SIZE
                    && entity.getY() + MAXHEIGHTBOMBER > listItem.get(i).getY() ) {

                return listItem.get(i);
            }
        }
        return null;
    }
}
