package bingo;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

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
