package world;

import asciiPanel.AsciiPanel;
import java.awt.Color;

public enum Tile {

    SPACE((char) 250, AsciiPanel.green),

    WALL((char) 177, AsciiPanel.cyan),

    LADDER((char) 185, AsciiPanel.red),

    BOUNDS('x', AsciiPanel.magenta);

    private char glyph;

    public char glyph() {
        return glyph;
    }

    private Color color;

    public Color color() {
        return color;
    }

    /*public boolean isGround() {
        return this != Tile.WALL && this != Tile.BOUNDS;
    }*/

    Tile(char glyph, Color color) {
        this.glyph = glyph;
        this.color = color;
    }
}
