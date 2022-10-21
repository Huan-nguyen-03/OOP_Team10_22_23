package bomberman.linhtinh;

import bomberman.entities.*;
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

    public boolean universalCheckCollision(Entity entity, List<Entity> listBarrier, int width, int height) {
        for (int i = 0; i < listBarrier.size(); i++) {
            if (!(listBarrier.get(i) instanceof Grass)) {
                if (entity.getX() < listBarrier.get(i).getX() + Sprite.SCALED_SIZE
                        && entity.getX() + width > listBarrier.get(i).getX()
                        && entity.getY() < listBarrier.get(i).getY() + Sprite.SCALED_SIZE
                        && entity.getY() + height > listBarrier.get(i).getY() ) {
                    return true;
                }
            }
        }
        return false;
    }

<<<<<<< Updated upstream
//    public boolean changeDirection(Entity entity, List<Entity> listBarrier) {
//        for (int i = 0; i < listBarrier.size(); i++) {
//            if (!(listBarrier.get(i) instanceof Grass)) {
//                if ( entity.getX() >= listBarrier.get(i).getX() + Sprite.SCALED_SIZE
//                        && entity.getY() >=  listBarrier.get(i).getY() + Sprite.SCALED_SIZE
//                || entity.getY() >= listBarrier.get(i).getY() + Sprite.SCALED_SIZE
//                        && entity.getX() >= listBarrier.get(i).getX() - Sprite.SCALED_SIZE
//                || entity.getX() >= listBarrier.get(i).getX() + Sprite.SCALED_SIZE
//                        && entity.getY() + Sprite.SCALED_SIZE <= listBarrier.get(i).getY()
//                || entity.getY() >= listBarrier.get(i).getY() + Sprite.SCALED_SIZE
//                        && entity.getX() >= listBarrier.get(i).getX() + Sprite.SCALED_SIZE ) {
//                    return true;
//                    }
//                }
//            }
//        return false;
//    }
//    public boolean checkHitRight(Entity entity, List<Entity> listBarrier, int width) {
//        for (int i = 0; i < listBarrier.size(); i++) {
//            if (!(listBarrier.get(i) instanceof Grass)) {
//                if (entity.getX() + width > listBarrier.get(i).getX()) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public boolean checkHitLeft(Entity entity, List<Entity> listBarrier) {
//        for (int i = 0; i < listBarrier.size(); i++) {
//            if (!(listBarrier.get(i) instanceof Grass)) {
//                if (entity.getX() < listBarrier.get(i).getX() + Sprite.SCALED_SIZE) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public boolean checkHitUp(Entity entity, List<Entity> listBarrier) {
//        for (int i = 0; i < listBarrier.size(); i++) {
//            if (!(listBarrier.get(i) instanceof Grass)) {
//                if (entity.getY() < listBarrier.get(i).getY() + Sprite.SCALED_SIZE) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public boolean checkHitDown(Entity entity, List<Entity> listBarrier, int height) {
//        for (int i = 0; i < listBarrier.size(); i++) {
//            if (!(listBarrier.get(i) instanceof Grass)) {
//                if (entity.getY() + height > listBarrier.get(i).getY()) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
=======
    public boolean universalCheckCollision(Entity entity, List<Entity> listBarrier, int width, int height) {
        for (int i = 0; i < listBarrier.size(); i++) {
            if (!(listBarrier.get(i) instanceof Grass)) {
                if (entity.getX() < listBarrier.get(i).getX() + Sprite.SCALED_SIZE
                        && entity.getX() + width > listBarrier.get(i).getX()
                        && entity.getY() < listBarrier.get(i).getY() + Sprite.SCALED_SIZE
                        && entity.getY() + height > listBarrier.get(i).getY() ) {
                    return true;
                }
            }
        }
        return false;
    }
>>>>>>> Stashed changes
}
