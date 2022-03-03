package screen;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import main.ApplicationMain;

public class EndScreen extends Screen{
    private final char winner;
    //private JLabel;

    public EndScreen(char c, ApplicationMain app) {
        super(app);
        if (c == '1')
            winner = '2';
        else
            winner = '1';
    }
    @Override
    public void displayOutput(AsciiPanel terminal){
        terminal.write("Winner : Player" + winner, 20, 15);
        terminal.write("Press Enter to play again.", 15, 20);
    }
    
    @Override
    public void changeScreen(char c) {
        app.changeScreen(new StartScreen(app));
    }
}
