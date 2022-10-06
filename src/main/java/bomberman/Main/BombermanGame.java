package bomberman.Main;

import bomberman.entities.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import bomberman.graphics.Sprite;
import java.io.*;



import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;
//    private List<Entity> entities = new ArrayList<>();
//    private List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        Map map = new Map();
        map.loadMap();
        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());


//        Bomber bomberman = new Bomber(1, 1, Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, Bomber.ANIMATE, Bomber.TIME).getFxImage());
//        entities.add(bomberman);

        // Tao scene
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode code = keyEvent.getCode();
                switch (code) {
                    case RIGHT: {
                        if (!Entity.listEvent.contains(Entity.Integer.RIGHT))
                            Entity.listEvent.add(Entity.Integer.RIGHT);
                        break;
                    }
                    case LEFT: {
                        if (!Entity.listEvent.contains(Entity.Integer.LEFT))
                            Entity.listEvent.add(Entity.Integer.LEFT);
                        break;
                    }
                    case UP: {
                        if (!Entity.listEvent.contains(Entity.Integer.UP))
                            Entity.listEvent.add(Entity.Integer.UP);
                        break;

                    }
                    case DOWN: {
                        if (!Entity.listEvent.contains(Entity.Integer.DOWN))
                            Entity.listEvent.add(Entity.Integer.DOWN);
                        break;
                    }
                    case SPACE: {
                        if (!Entity.listEvent.contains(Entity.Integer.SPACE))
                            Entity.listEvent.add(Entity.Integer.SPACE);
                        Bomb bomb = new Bomb(bomberman.getX()/Sprite.SCALED_SIZE, bomberman.getY()/Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
//                        Flame f =  new Flame(bomb.getX()/Sprite.SCALED_SIZE, bomb.getY()/Sprite.SCALED_SIZE, Sprite.bomb_exploded.getFxImage(), 0);
//                        GlobalVariable.stillObjects.add(f);
                        GlobalVariable.stillObjects.add(bomb);
                        Bomb.listBomb.add(bomb);
                        break;
                    }
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode code = keyEvent.getCode();
                switch (code) {
                    case RIGHT: {
                        if (Entity.listEvent.contains(Entity.Integer.RIGHT))
                            Entity.listEvent.remove(Entity.Integer.RIGHT);
                        break;
                    }
                    case LEFT: {
                        if (Entity.listEvent.contains(Entity.Integer.LEFT))
                            Entity.listEvent.remove(Entity.Integer.LEFT);
                        break;
                    }
                    case UP: {
                        if (Entity.listEvent.contains(Entity.Integer.UP))
                            Entity.listEvent.remove(Entity.Integer.UP);
                        break;

                    }
                    case DOWN: {
                        if (Entity.listEvent.contains(Entity.Integer.DOWN))
                            Entity.listEvent.remove(Entity.Integer.DOWN);
                        break;
                    }
                    case SPACE: {

                        break;
                    }
                }
            };
        });
        GlobalVariable.entities.add(bomberman);
        // Them scene vao stage

        stage.setScene(scene);
        stage.setTitle("Game Rác");
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();


    }

    public void createMap() throws IOException {
//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < HEIGHT; j++) {
//                Entity object;
//                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
//                    object = new Wall(i, j, Sprite.wall.getFxImage());
//                }
//                else {
//                    object = new Grass(i, j, Sprite.grass.getFxImage());
//                }
//                stillObjects.add(object);
//            }
//        }
        createMapFromFile();
    }

    public void createMapFromFile () throws IOException {
//        BufferedReader bufferedReader = null;
//
//        try {
//            Reader reader = new FileReader("res/levels/Level1.txt");
//
//            bufferedReader = new BufferedReader(reader);
//
//
//            String firstLine = bufferedReader.readLine();
//            System.out.println(firstLine);
//
//            int level = 0;
//            int row = 0;
//            int column = 0;
//
//            String[] tokens = firstLine.split(" ");
//            level = Integer.parseInt(tokens[0]);
//            row = Integer.parseInt(tokens[1]);
//            column = Integer.parseInt(tokens[2]);
//
//            GlobalVariable.map = new char[row][column];
//
//            for (int i = 0; i<row; i++) {
//                String line = bufferedReader.readLine();
//                for (int j = 0; j<column; j++) {
//                    char character = line.charAt(j);
//                    GlobalVariable.map[i][j] = character;
//                }
//            }

            for (int i = 0; i<Map.row; i++) {
                for (int j = 0; j<Map.column; j++) {
                    char character = Map.map[i][j];
                    switch (character) {
                        case '#' : {
                            Entity object = new Wall(j, i, Sprite.wall.getFxImage());
                            GlobalVariable.stillObjects.add(object);
                            Entity.listBarrier.add(object);
                            Map.mapObjects[i][j] = object;  // để ý lỗi chỗ này
                            break;
                        }
                        case '*' : {
                            Entity object1 = new Grass(j, i, Sprite.grass.getFxImage());
                            GlobalVariable.stillObjects.add(object1);
                            Entity object = new Brick(j, i, Sprite.brick.getFxImage());
                            GlobalVariable.stillObjects.add(object);
                            Entity.listBarrier.add(object);
                            Map.mapObjects[i][j] = object;
                            break;
                        }
                        case '1' : {
                            Entity object1 = new Grass(j, i, Sprite.grass.getFxImage());
                            GlobalVariable.stillObjects.add(object1);
                            Entity object = new Balloon(j, i, Sprite.balloom_left1.getFxImage());
                            GlobalVariable.entities.add(object);
                            Map.mapObjects[i][j] = object;
                            break;
                        }
                        case '2' : {
                            Entity object1 = new Grass(j, i, Sprite.grass.getFxImage());
                            GlobalVariable.stillObjects.add(object1);
                            Entity object = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                            GlobalVariable.entities.add(object);
                            Map.mapObjects[i][j] = object;
                        }

                        default: {
                            Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                            GlobalVariable.stillObjects.add(object);
                            Map.mapObjects[i][j] = object;
                            break;
                        }
                    }
                }
            }

//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (bufferedReader != null) {
//                bufferedReader.close();
//            }
//        }
    }
    public void update() {
        GlobalVariable.entities.forEach(Entity::update);
//        GlobalVariable.stillObjects.forEach(Entity::update);
        for (int i = 0; i < GlobalVariable.stillObjects.size(); i++) {
            GlobalVariable.stillObjects.get(i).update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        GlobalVariable.stillObjects.forEach(g -> g.render(gc));
        GlobalVariable.entities.forEach(g -> g.render(gc));
    }
}
