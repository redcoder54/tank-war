package redcoder.tank.model;

public class GameModelWrapper {

    private static GameModel gameModel;

    private GameModelWrapper() {
    }

    public static GameModel getGameModel() {
        if (gameModel == null) {
            gameModel = GameModelFactory.getGameModel();
        }
        return gameModel;
    }

    public static void setGameModel(GameModel model) {
        gameModel = model;
    }

    public static void recreateGameModel() {
        gameModel = GameModelFactory.getGameModel();
    }
}
