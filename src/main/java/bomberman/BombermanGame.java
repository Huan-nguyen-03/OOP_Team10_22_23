package bomberman;

import bomberman.Sound.Sound;
import bomberman.entities.*;
import bomberman.linhtinh.CollisionChecker;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import bomberman.graphics.Sprite;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.*;


import java.net.MalformedURLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BombermanGame extends Application {
    public final List<Sound> sounds = new ArrayList<>();
    public static Bomber bomberman;
    private static Scene scene;
    public static Stage stage;

    public static Stage oldStage;

    public Group roots = new Group();
    @FXML
    private ImageView playBtn;
    @FXML
    private ImageView quitBtn;
    @FXML
    private ImageView continueBtn;

    public static String username = "";

    @FXML
    private ImageView howToPlayBtn;

    @FXML
    private ImageView backBtn;

    @FXML
    private ImageView leaderboardBtn;

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;

    private Text text = new Text();
    private Rectangle statusBoard = new Rectangle();

    private Rectangle pause = new Rectangle();
    public static boolean gameState = false;

    Sound sound = new Sound();
    private boolean playSound = false;

    public static int level = 1;

    public final int maxLevel = 2;

    public static boolean isWinGame = false;

    public static boolean loginSuccess = false;

    public static int score = 0;

    public boolean pauseGame = false;

    public Map map = new Map();



    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\Menu.fxml").toURI().toURL());
            Parent root = loader.load();
            stage.setTitle("Bomberman");
//            stage.setScene(new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT));
//            stage.setScene(new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT));
//            stage.setScene(new Scene(root, 800, 500));
            stage.setScene(new Scene(root, 600, 370));
            stage.show();
            if (!playSound) {
                sound.titleScreen.play();
                sound.titleScreen.loop();
                this.playSound = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void showHowToPlay(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\HowToPlay.fxml").toURI().toURL());
        Parent root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();;
        stage.setScene(new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT));
        stage.show();

    }

    public void endGame(Stage stage) throws IOException {
        FXMLLoader loaders = new FXMLLoader(new File("src\\main\\java\\bomberman\\End.fxml").toURI().toURL());
        Parent root = loaders.load();

        stage.setTitle("Bomberman");
        stage.setScene(new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT));
        stage.show();

    }

    public void winGame(Stage stage) throws IOException {
        FXMLLoader loaders = new FXMLLoader(new File("src\\main\\java\\bomberman\\winGame.fxml").toURI().toURL());
        Parent root = loaders.load();

        stage.setTitle("Bomberman");
        stage.setScene(new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT));
        stage.show();

    }

    public void pauseGame(Stage stage) throws IOException {
        FXMLLoader loaders = new FXMLLoader(new File("src\\main\\java\\bomberman\\pauseGame.fxml").toURI().toURL());
        Parent root = loaders.load();

        stage.setTitle("Bomberman");
        stage.setScene(new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT));
        stage.show();
    }

    public void continueGame(MouseEvent event) throws IOException {
        pauseGame = false;
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        pause.setX(800);
        pause.setY(500);
        pause.setWidth(15);
        pause.setHeight(15);
        pause.setFill(Color.RED);
        statusBoard.setX(0);
        statusBoard.setY(Sprite.SCALED_SIZE * HEIGHT);
        statusBoard.setHeight(Sprite.SCALED_SIZE * HEIGHT);
        statusBoard.setWidth(Sprite.SCALED_SIZE * WIDTH);
        statusBoard.setFill(Color.BLACK);


        text.setText(String.valueOf(score));
        text.setX(50);
        text.setY(454);
        text.setFont(Font.font("Verdana",50));
        text.setFill(Color.LIMEGREEN);




        // Tao root container
        System.out.println("Truoc Khi canvas");
        roots.getChildren().add(canvas);
        System.out.println("Truoc Khi text");
        roots.getChildren().add(text);
        System.out.println("Truoc Khi statusboard");
        roots.getChildren().add(statusBoard);
        roots.getChildren().add(pause);

        pause.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("mouse click detected!");
                pauseGame = true;
            }
        });



        if (!playSound) {
            sound.stageTheme.play();
            sound.stageTheme.loop();
        }


        // Tao scene
        scene = new Scene(roots, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT + 100);

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
                        if (Bomb.listBomb.size() < Bomb.MAX_BOMB_NUMBER) {
                            if (!Entity.listEvent.contains(Entity.Integer.SPACE))
                                Entity.listEvent.add(Entity.Integer.SPACE);
                            Bomb bomb = new Bomb(bomberman.getX() / Sprite.SCALED_SIZE, bomberman.getY() / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
                            GlobalVariable.stillObjects.add(bomb);
                            Bomb.listBomb.add(bomb);
                        }
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
                        if (Entity.listEvent.contains(Entity.Integer.RIGHT)) {
                            Entity.listEvent.remove(Entity.Integer.RIGHT);
                            bomberman.setImg(Sprite.player_right.getFxImage());
                        }
                        break;
                    }
                    case LEFT: {
                        if (Entity.listEvent.contains(Entity.Integer.LEFT)) {
                            Entity.listEvent.remove(Entity.Integer.LEFT);
                            bomberman.setImg(Sprite.player_left.getFxImage());
                        }
                        break;
                    }
                    case UP: {
                        if (Entity.listEvent.contains(Entity.Integer.UP)) {
                            Entity.listEvent.remove(Entity.Integer.UP);
                            bomberman.setImg(Sprite.player_up.getFxImage());
                        }
                        break;

                    }
                    case DOWN: {
                        if (Entity.listEvent.contains(Entity.Integer.DOWN)) {
                            Entity.listEvent.remove(Entity.Integer.DOWN);
                            bomberman.setImg(Sprite.player_down.getFxImage());
                        }
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
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                roots.getChildren().remove(text);

                statusBoard.setX(0);
                statusBoard.setY(Sprite.SCALED_SIZE * HEIGHT);
                statusBoard.setHeight(Sprite.SCALED_SIZE * HEIGHT);
                statusBoard.setWidth(Sprite.SCALED_SIZE * WIDTH);
                statusBoard.setFill(Color.BLACK);

                text.setText(String.valueOf(score));
                text.setX(50);
                text.setY(454);
                text.setFont(Font.font("Verdana",50));
                text.setFill(Color.LIMEGREEN);

                roots.getChildren().add(text);

                if (pauseGame) {
                    stop();
                    oldStage = stage;
                    try {
                        pauseGame(stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (isWinGame) {
                    isWinGame = false;

                    GlobalVariable.entities = new ArrayList<>();
                    GlobalVariable.stillObjects = new ArrayList<>();
                    Bomb.listBomb = new ArrayList<>();
                    Entity.listBarrier = new ArrayList<>();
                    Entity.listEvent =  new ArrayList<>();;
                    Brick.hasPortal = false;
                    Entity.listItem = new ArrayList<>();
                    level++;
                    if (level > maxLevel) {

                        stop();
                        Connection con;
                        PreparedStatement pst;
                        ResultSet rs;
                        String query = "UPDATE adminn SET highestScore = ? where adminAcc = ?";

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");

                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            con = DriverManager.getConnection("jdbc:mysql://localhost/qrabiloo", "root", "");
                            pst = con.prepareStatement(query);
                            pst.setInt(1,score);
                            pst.setString(2,username);
                            pst.executeUpdate();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }



                        score = 0;
                        level = 1;
                        GlobalVariable.entities = new ArrayList<>();
                        GlobalVariable.stillObjects = new ArrayList<>();
                        Bomb.listBomb = new ArrayList<>();
                        Entity.listBarrier = new ArrayList<>();
                        Entity.listEvent =  new ArrayList<>();
                        Entity.listItem = new ArrayList<>();
                        try {
                            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                            gameState = false;
                            winGame(stage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    try {
                        map.loadMap(level);
                        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
                        Map.map[1][1] = '0';
                        GlobalVariable.entities.add(bomberman);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        createMap();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(!gameState) {
                    render();
                    update();}
                else {
                    stop();
                    Connection con;
                    PreparedStatement pst;
                    ResultSet rs;
                    String query = "UPDATE adminn SET highestScore = ? where adminAcc = ?";

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");

                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost/qrabiloo", "root", "");
                        pst = con.prepareStatement(query);
                        pst.setInt(1,score);
                        pst.setString(2,username);
                        pst.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }



                    score = 0;
                    level = 1;
                    GlobalVariable.entities = new ArrayList<>();
                    GlobalVariable.stillObjects = new ArrayList<>();
                    Bomb.listBomb = new ArrayList<>();
                    Entity.listBarrier = new ArrayList<>();
                    Entity.listEvent =  new ArrayList<>();;
                    Entity.listItem = new ArrayList<>();
                    Bomb.MAX_BOMB_NUMBER = 1;
                    Bomb.SIZE = 1;
                    Bomber.checkBombPass = false;
                    try {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        gameState = false;
                        endGame(stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }


            }
        };
        timer.start();

    }

    public void handlePauseGame(MouseEvent event) {

    }




    public void backToMenu(MouseEvent event) throws IOException {

        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String query = "UPDATE adminn SET highestScore = ? where adminAcc = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/qrabiloo", "root", "");
            pst = con.prepareStatement(query);
            pst.setInt(1,score);
            pst.setString(2,username);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        score = 0;
        level = 1;
        GlobalVariable.entities = new ArrayList<>();
        GlobalVariable.stillObjects = new ArrayList<>();
        Bomb.listBomb = new ArrayList<>();
        Entity.listBarrier = new ArrayList<>();
        Entity.listEvent =  new ArrayList<>();;
        Entity.listItem = new ArrayList<>();
        Bomb.MAX_BOMB_NUMBER = 1;
        Bomb.SIZE = 1;
        Bomber.checkBombPass = false;

        FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\Menu.fxml").toURI().toURL());
        Parent root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();;
        stage.setScene(new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT));
        stage.show();

    }

    @FXML
    void showLeaderBoard(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\leaderboard.fxml").toURI().toURL());
        Parent root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();;
        stage.setScene(new Scene(root, 800,500));
        stage.show();
    }


    public void startGame(MouseEvent event) throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        pause.setX(800);
        pause.setY(500);
        pause.setWidth(15);
        pause.setHeight(15);
        pause.setFill(Color.RED);
        statusBoard.setX(0);
        statusBoard.setY(Sprite.SCALED_SIZE * HEIGHT);
        statusBoard.setHeight(Sprite.SCALED_SIZE * HEIGHT);
        statusBoard.setWidth(Sprite.SCALED_SIZE * WIDTH);
        statusBoard.setFill(Color.BLACK);


        text.setText(String.valueOf(score));
        text.setX(50);
        text.setY(454);
        text.setFont(Font.font("Verdana",50));
        text.setFill(Color.LIMEGREEN);




        // Tao root container

        roots.getChildren().add(canvas);
        roots.getChildren().add(text);
        roots.getChildren().add(statusBoard);
        roots.getChildren().add(pause);

        pause.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("mouse click detected!");
                pauseGame = true;
            }
        });




        map.loadMap(level);
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        Map.map[1][1] = '0';

        if (!playSound) {
            sound.stageTheme.play();
            sound.stageTheme.loop();
        }


        // Tao scene
        scene = new Scene(roots, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT + 100);

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
                        if (Bomb.listBomb.size() < Bomb.MAX_BOMB_NUMBER) {
                            if (!Entity.listEvent.contains(Entity.Integer.SPACE))
                                Entity.listEvent.add(Entity.Integer.SPACE);
                            Bomb bomb = new Bomb(bomberman.getX() / Sprite.SCALED_SIZE, bomberman.getY() / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
                            GlobalVariable.stillObjects.add(bomb);
                            Bomb.listBomb.add(bomb);
                        }
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
                        if (Entity.listEvent.contains(Entity.Integer.RIGHT)) {
                            Entity.listEvent.remove(Entity.Integer.RIGHT);
                            bomberman.setImg(Sprite.player_right.getFxImage());
                        }
                        break;
                    }
                    case LEFT: {
                        if (Entity.listEvent.contains(Entity.Integer.LEFT)) {
                            Entity.listEvent.remove(Entity.Integer.LEFT);
                            bomberman.setImg(Sprite.player_left.getFxImage());
                        }
                        break;
                    }
                    case UP: {
                        if (Entity.listEvent.contains(Entity.Integer.UP)) {
                            Entity.listEvent.remove(Entity.Integer.UP);
                            bomberman.setImg(Sprite.player_up.getFxImage());
                        }
                        break;

                    }
                    case DOWN: {
                        if (Entity.listEvent.contains(Entity.Integer.DOWN)) {
                            Entity.listEvent.remove(Entity.Integer.DOWN);
                            bomberman.setImg(Sprite.player_down.getFxImage());
                        }
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
        Stage stage = (Stage) playBtn.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                roots.getChildren().remove(text);

                statusBoard.setX(0);
                statusBoard.setY(Sprite.SCALED_SIZE * HEIGHT);
                statusBoard.setHeight(Sprite.SCALED_SIZE * HEIGHT);
                statusBoard.setWidth(Sprite.SCALED_SIZE * WIDTH);
                statusBoard.setFill(Color.BLACK);

                text.setText(String.valueOf(score));
                text.setX(50);
                text.setY(454);
                text.setFont(Font.font("Verdana",50));
                text.setFill(Color.LIMEGREEN);

                roots.getChildren().add(text);

                if (pauseGame) {
                    stop();
                    oldStage = stage;
                    try {
                        pauseGame(stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (isWinGame) {
                    isWinGame = false;

                    GlobalVariable.entities = new ArrayList<>();
                    GlobalVariable.stillObjects = new ArrayList<>();
                    Bomb.listBomb = new ArrayList<>();
                    Entity.listBarrier = new ArrayList<>();
                    Entity.listBarrierForEnemies = new ArrayList<>();
                    Entity.listEvent =  new ArrayList<>();;
                    Brick.hasPortal = false;
                    Entity.listItem = new ArrayList<>();
                    level++;
                    if (level > maxLevel) {

                        stop();
                        Connection con;
                        PreparedStatement pst;
                        ResultSet rs;
                        String query = "UPDATE adminn SET highestScore = ? where adminAcc = ?";

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");

                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            con = DriverManager.getConnection("jdbc:mysql://localhost/qrabiloo", "root", "");
                            pst = con.prepareStatement(query);
                            pst.setInt(1,score);
                            pst.setString(2,username);
                            pst.executeUpdate();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }



                        score = 0;
                        level = 1;
                        GlobalVariable.entities = new ArrayList<>();
                        GlobalVariable.stillObjects = new ArrayList<>();
                        Bomb.listBomb = new ArrayList<>();
                        Entity.listBarrier = new ArrayList<>();
                        Entity.listBarrierForEnemies = new ArrayList<>();
                        Entity.listEvent =  new ArrayList<>();
                        Entity.listItem = new ArrayList<>();
                        try {
                            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                            gameState = false;
                            winGame(stage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    try {
                        map.loadMap(level);
                        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
                        Map.map[1][1] = '0';
                        GlobalVariable.entities.add(bomberman);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        createMap();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(!gameState) {
                    render();
                    update();}
                else {
                    stop();
                    Connection con;
                    PreparedStatement pst;
                    ResultSet rs;
                    String query = "UPDATE adminn SET highestScore = ? where adminAcc = ?";

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");

                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            con = DriverManager.getConnection("jdbc:mysql://localhost/qrabiloo", "root", "");
                            pst = con.prepareStatement(query);
                            pst.setInt(1,score);
                            pst.setString(2,username);
                            pst.executeUpdate();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }



                    score = 0;
                    level = 1;
                    GlobalVariable.entities = new ArrayList<>();
                    GlobalVariable.stillObjects = new ArrayList<>();
                    Bomb.listBomb = new ArrayList<>();
                    Entity.listBarrier = new ArrayList<>();
                    Entity.listBarrierForEnemies = new ArrayList<>();
                    Entity.listEvent =  new ArrayList<>();;
                    Entity.listItem = new ArrayList<>();
                    Bomb.MAX_BOMB_NUMBER = 1;
                    Bomb.SIZE = 1;
                    Bomber.checkBombPass = false;
                    try {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        gameState = false;
                        endGame(stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }


            }
        };
        timer.start();

        createMap();


    }


    public void createMap() throws IOException {
        createMapFromFile();
    }

    public void createMapFromFile () throws IOException {
        for (int i = 0; i<Map.row; i++) {
            for (int j = 0; j<Map.column; j++) {
                char character = Map.map[i][j];
                switch (character) {
                    case '#' : {
                        Entity object = new Wall(j, i, Sprite.wall.getFxImage());
                        GlobalVariable.stillObjects.add(object);
                        Entity.listBarrier.add(object);
                        Entity.listBarrierForEnemies.add(object);
                        Map.mapObjects[i][j] = object;  // để ý lỗi chỗ này
                        break;
                    }
                    case '*' : {
                        Entity object1 = new Grass(j, i, Sprite.grass.getFxImage());
                        GlobalVariable.stillObjects.add(object1);
                        Entity object = new Brick(j, i, Sprite.brick.getFxImage());
                        GlobalVariable.stillObjects.add(object);
                        Entity.listBarrier.add(object);
                        Entity.listBarrierForEnemies.add(object);
                        Map.mapObjects[i][j] = object;
                        Brick.numberOfBrick ++;
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
                        break;
                    }
                    case '3' : {
                        Entity object1 = new Grass(j, i, Sprite.grass.getFxImage());
                        GlobalVariable.stillObjects.add(object1);
                        Entity object = new Kondoria(j, i, Sprite.kondoria_left1.getFxImage());
                        GlobalVariable.entities.add(object);
                        Map.mapObjects[i][j] = object;
                        break;
                    }
                    case '4' : {
                        Entity object1 = new Grass(j, i, Sprite.grass.getFxImage());
                        GlobalVariable.stillObjects.add(object1);
                        Entity object = new Doll(j, i, Sprite.doll_left1.getFxImage());
                        GlobalVariable.entities.add(object);
                        Map.mapObjects[i][j] = object;
                        break;
                    }
                    case '5' : {
                        Entity object1 = new Grass(j, i, Sprite.grass.getFxImage());
                        GlobalVariable.stillObjects.add(object1);
                        Entity object = new Mob(j, i, Sprite.mob_dead1.getFxImage());
                        GlobalVariable.entities.add(object);
                        Map.mapObjects[i][j] = object;
                        break;
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
