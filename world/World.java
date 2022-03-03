package world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import main.Timer;
import screen.PlayScreen;
import java.io.*;

public class World {
    private Tile[][] tiles;
    private int width, height;
    private List<Creature> creatures;
    private PlayScreen screen;

    public World(int w, int h, PlayScreen screen) {
        this.width = w;
        this.height = h;
        this.screen = screen;
        this.creatures = new ArrayList<>();
        tiles = new Tile[w][h];
        //map
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
					"resources/map.txt"));
			for(int j = 0; j < height; j++) {
                String line = reader.readLine();
                //System.out.println(line);
                String[] splitLine = line.split(" ");
                for(int i = 0; i < width; i++) {
                    if(splitLine[i].equals("1")) {
                        tiles[i][j] = Tile.WALL;
                    }
                    else if(splitLine[i].equals("2")) {
                        tiles[i][j] = Tile.LADDER;
                    }
                    else
                        tiles[i][j] = Tile.SPACE;
                }
            }
            reader.close();
        } catch(IOException e) {}

        /*MazeGenerator mazeGenerator = new MazeGenerator(width<height?width:height);
        int maze[][] = mazeGenerator.getMaze();
        tiles = new Tile[w][h];
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if (maze[i][j] == 1) {
                    tiles[i][j] = Tile.FLOOR;
                }
                else
                    tiles[i][j] = Tile.WALL;
            }
        }*/

        //player
        //this.addAtEmptyLocation(new Player(this, (char)2, AsciiPanel.brightWhite, '1', 100, 25));
        //this.addAtEmptyLocation(new Player(this, (char)2, AsciiPanel.brightYellow, '2', 100, 25));
        this.addNewCreature(new Player(this, (char)2, AsciiPanel.brightWhite, '1', 100, 25));
        this.addNewCreature(new Player(this, (char)2, AsciiPanel.brightYellow, '2', 100, 25));

        //monster
        new CreatureFactory(this);
    }

    public void addNewCreature(Creature creature) {
        int x = (int) (Math.random() * this.width);
        creature.setX(x);
        creature.setY(0);
        this.creatures.add(creature);
    }

    public void addAtEmptyLocation(Creature creature) {
        int x;
        int y;
        do {
            x = (int) (Math.random() * this.width);
            y = (int) (Math.random() * this.height);
        } while (tiles[x][y] != Tile.SPACE || this.creature(x, y) != null);
        creature.setX(x);
        creature.setY(y);
        this.creatures.add(creature);
        System.out.println(x + "," + y);
    }

    public Creature creature(int x, int y) {
        for (Creature c : this.creatures) {
            if (c != null && c.x() == x && c.y() == y) {
                return c;
            }
        }
        return null;
    }
    public Tile tile(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y >= height)
            return Tile.BOUNDS;
        return tiles[x][y];
    }

    public char glyph(int x, int y) {
        return tiles[x][y].glyph();
    }
    public Color color(int x, int y) {
        return tiles[x][y].color();
    }

    public List<Creature>getCreatures() {
        return this.creatures;
    }
    public int monsterNum() {
        return this.creatures.size() - 2;
    }
    public void killMonster(Creature c) {
        try {
            creatures.remove(c);
            System.out.println("remove monster 1ed");
        } catch(Exception e) {System.out.println("remove monster failed");}
    }

    public void respondToUserInput(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_P) {
            
        }
        if (keyCode > 64)
            ((Player)creatures.get(0)).respondToUserInput(keyCode);
        else
            ((Player)creatures.get(1)).respondToUserInput(keyCode);
    }

    public void endGame(char name) {
        this.screen.changeScreen(name);
        for(Creature c : creatures) {
            if (c != null && c.isAlive()) {
                //System.out.println("killed one");
                c.hurt(9999);
            }
        }
    }
}
