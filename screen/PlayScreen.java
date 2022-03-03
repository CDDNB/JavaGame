package screen;

import asciiPanel.AsciiPanel;
import world.World;
import world.Creature;
import java.awt.event.KeyEvent;
import main.Timer;

import main.ApplicationMain;

public class PlayScreen extends Screen {
    private int width, height;
    private World world;
    private Timer timer;
    //private ApplicationMain app;

    public PlayScreen(int w, int h, ApplicationMain app) {
        super(app);
        this.width = w;
        this.height = h;
        this.timer = new Timer(app);
        this.world = new World(width, height, this);
    }

    /*public void displayOutput(AsciiPanel terminal) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                terminal.write((char)16, x, y, world.color(x, y));
            }
        }
        
    }*/

    public void displayOutput(AsciiPanel terminal) {
        //map
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                terminal.write(world.glyph(x, y), x, y, world.color(x, y));
            }
        }

        //creature
        for (Creature c : world.getCreatures()) {
            if (c != null && c.isAlive() && c.x() > 0 && c.x() < width && c.y() > 0 && c.y() < height)
                terminal.write(c.glyph(), c.x(), c.y(), c.color());
        }
    }

    public void respondToUserInput(KeyEvent e) {
        world.respondToUserInput(e);
    }

    public void changeScreen(char c) {
        timer.off();
        app.changeScreen(new EndScreen(c, this.app));
    }
}
