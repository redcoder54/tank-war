package redcoder.tank.model;

public class GameModelWrapper {

    private static GameModel gameModel;

    private GameModelWrapper() {
    }

    public static GameModel getGameModel() {
        if (gameModel == null) {
            throw new IllegalStateException("GameModel not sets, please call setGameModel method to set GameModel first.");
        }
        return gameModel;
    }

    public static void setGameModel(GameModel model) {
        gameModel = model;
    }
}
