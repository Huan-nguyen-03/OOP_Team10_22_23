package bomberman.Sound;

import javax.xml.transform.Source;

public class Sound {
    public final LoadSound titleScreen;
    public final LoadSound item;
    public final LoadSound gameOver;
    public final LoadSound levelComplete;
    public final LoadSound findDoor;
    public final LoadSound bonusStage;
    public final LoadSound stageTheme;
    public final LoadSound bom;
    public final LoadSound makeBom;
    public final LoadSound click;
    public final LoadSound victory;



    public Sound() {
//        titleScreen = new LoadSound("/res/sound/TitleScreen.wav");
//        item = new LoadSound("/res/sound/item.wav");
//        gameOver = new LoadSound("/res/sound/GameOver.wav");
//        levelComplete = new LoadSound("/res/sound/LevelComplete.wav");
//        findDoor = new LoadSound("/res/sound/FindDoor.wav");
//        bonusStage = new LoadSound("/res/sound/BonusStage.wav");
//        stageTheme = new LoadSound("/res/sound/StageTheme.wav");
//        bom = new LoadSound("/res/sound/BOM_11_L.wav");
//        makeBom = new LoadSound("/res/sound/Link bonuslife.wav");
//        click = new LoadSound("/res/sound/Link bonuslife.wav");
//        victory = new LoadSound("/res/sound/EndingTheme.wav");

        titleScreen = new LoadSound("/sound/TitleScreen.wav");
        item = new LoadSound("/sound/item.wav");
        gameOver = new LoadSound("/sound/GameOver.wav");
        levelComplete = new LoadSound("/sound/LevelComplete.wav");
        findDoor = new LoadSound("/sound/FindDoor.wav");
        bonusStage = new LoadSound("/sound/BonusStage.wav");
        stageTheme = new LoadSound("/sound/StageTheme.wav");
        bom = new LoadSound("/sound/BOM_11_L.wav");
        makeBom = new LoadSound("/sound/Link bonuslife.wav");
        click = new LoadSound("/sound/Link bonuslife.wav");
        victory = new LoadSound("/sound/EndingTheme.wav");
    }
    public void stopAllSound() {
        titleScreen.stop();
        item.stop();
        gameOver.stop();
        levelComplete.stop();
        findDoor.stop();
        bonusStage.stop();
        stageTheme.stop();
        bom.stop();
        makeBom.stop();
        victory.stop();
    }
}
