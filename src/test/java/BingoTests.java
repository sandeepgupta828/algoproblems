import bingo.Defaults;
import bingo.game.GameInput;
import bingo.game.GamePlay;
import org.junit.Test;
import org.springframework.util.Assert;

public class BingoTests {

    /**
     * normal input
     */
    @Test
    public void testGame1() {
        GameInput gameInput = new GameInput(5, 90, 3, 10, 5, Defaults.WINNING_PATTERNS);
        GamePlay gamePlay = new GamePlay(gameInput);
        gamePlay.setup();
        gamePlay.play(false);
        Assert.isTrue(gamePlay.getUniqueNumberGenerator().next() == -1, "Not all numbers are generated");
        Assert.isTrue(gamePlay.getGameState().getTicketList().stream().allMatch(ticket -> ticket.getPatternsWon().size() == 3), "Some tickets didn't win all the patterns");
    }

    /**
     * skewed input (only 1 ticket that takes all possible numbers)
     */
    @Test
    public void testGame2() {
        GameInput gameInput = new GameInput(1, 100, 1, 100, 100, Defaults.WINNING_PATTERNS);
        GamePlay gamePlay = new GamePlay(gameInput);
        gamePlay.setup();
        gamePlay.play(false);
        Assert.isTrue(gamePlay.getUniqueNumberGenerator().next() == -1, "Not all numbers are generated");
        Assert.isTrue(gamePlay.getGameState().getTicketList().stream().allMatch(ticket -> ticket.getPatternsWon().size() == 3), "Some tickets didn't win all the patterns");
    }

    /**
     * scaled up input
     */
    @Test
    public void testGame3() {
        GameInput gameInput = new GameInput(100, 100, 5, 100, 10, Defaults.WINNING_PATTERNS);
        GamePlay gamePlay = new GamePlay(gameInput);
        gamePlay.setup();
        gamePlay.play(false);
        Assert.isTrue(gamePlay.getUniqueNumberGenerator().next() == -1, "Not all numbers are generated");
        Assert.isTrue(gamePlay.getGameState().getTicketList().stream().allMatch(ticket -> ticket.getPatternsWon().size() == 3), "Some tickets didn't win all the patterns");
    }

    /**
     * bad input
     */
    @Test
    public void testGame4() {
        try {
            GameInput gameInput = new GameInput(100, 100, 100, 100, 100, Defaults.WINNING_PATTERNS);
            Assert.isTrue(false, "Expected exception didn't happen");
        } catch (Exception ex) {
        }
    }
}
