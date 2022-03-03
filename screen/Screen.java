package screen;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import main.ApplicationMain;

public class Screen {
    protected final ApplicationMain app;
    public Screen(ApplicationMain app) { this.app = app; }

    public void displayOutput(AsciiPanel terminal){}

    public void respondToUserInput(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            changeScreen('c');
        }
    }

    public void changeScreen(char c){}
}
