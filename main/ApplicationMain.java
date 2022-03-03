package main;
import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;
import main.ApplicationMain;
import screen.*;

public class ApplicationMain extends JFrame implements KeyListener{
    private AsciiPanel terminal;
    private Screen screen;
    //private JLabel label;
    
    public ApplicationMain() {
        super();
        this.setTitle("Java Game");
        //Timer timer = new Timer(this);
        //this.setSize(80, 65);
        terminal = new AsciiPanel(60, 60, AsciiFont.TALRYTH_15_15);
        
        //terminal.add(label);
        add(terminal);
        pack();
        screen = new StartScreen(this);
        //screen = new PlayScreen(60, 60, this);
        addKeyListener(this);
        repaint();
    }

    @Override
    public void repaint() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    public void keyPressed(KeyEvent e) {
        screen.respondToUserInput(e);
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

    public void changeScreen(Screen screen) {
        this.screen = screen;
        //this.add(new JLabel("end game"));
        repaint();
    }
}