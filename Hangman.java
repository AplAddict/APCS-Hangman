import java.lang.Thread;
public class Hangman {
    static final int livesPerGame = 5;

    public static void main(String[] args) {
        HangmanGui frame = null;
        try {
            frame = new HangmanGui(livesPerGame, "");
            frame.setVisible(true);
            frame.setNewGame(false);
        } catch (Exception e) {
            System.out.println("Error setting up GUI "+ e.getMessage());
            e.printStackTrace();
        }
        char letter;

        do {


            if(!frame.getNewGame()){
                try{
                    Thread.sleep(500);
                }
                catch (Exception e) {
                    System.out.println("Error sleeping "+ e.getMessage());
                    e.printStackTrace();
                }
                continue;
            }

            frame.setNewGame(false);
            try{
                Thread.sleep(500);
            }
            catch (Exception e) {
                System.out.println("Error sleeping "+ e.getMessage());
                e.printStackTrace();
            }
            HangmanGame hangmanGame = new HangmanGame(frame, livesPerGame); // Sets up hangman
            frame.initGameGui(livesPerGame, hangmanGame.getWord());

            //TODO maybe make playOneGame a separate method
            while (!frame.getNewGame()) {
                letter = frame.getNextChar();
                if (!Character.isLetter(letter)) {
                    continue;
                }
                hangmanGame.isGuessed(letter, frame); // Check input
                hangmanGame.complete(frame); // Check if/why game over
                if (hangmanGame.isGameOver()) {
                    break; // if gameover stop game
                }
            }
        } while (true); // Only close when they say quit or close 
    }

}