package bingo;

import bingo.game.GameInput;
import bingo.game.GamePlay;
import bingo.game.InputParser;

public class Solution {
    public static void main(String[] args) {
        InputParser inputParser = new InputParser();
        GameInput gameInput = inputParser.parseInput(false);
        System.out.println(gameInput);
        GamePlay gamePlay = new GamePlay(gameInput);
        gamePlay.setup();
        gamePlay.play(true);
    }
}
