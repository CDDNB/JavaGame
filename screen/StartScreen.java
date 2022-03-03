package screen;
import asciiPanel.AsciiPanel;
import main.ApplicationMain;

public class StartScreen extends Screen{
    public StartScreen(ApplicationMain app) {
        super(app);
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
            terminal.write("Welcome to my Java Game!", 17, 10, AsciiPanel.white);
            terminal.write("Press Enter to start.", 18, 20, AsciiPanel.white);
    }

    

    @Override
    public void changeScreen(char c) {
        app.changeScreen(new PlayScreen(60, 60, app));
    }
}
