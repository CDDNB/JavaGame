package world;

import java.awt.Color;
import java.util.concurrent.locks.*;
import java.util.ArrayList;
import java.util.List;

public class Creature extends Thread {
    protected World world;
    protected int x, y, direction;
    private char glyph;
    private Color color;
    protected int hp;
    protected int attack;
    protected int state;
    protected Lock lock;
    
    public Creature(World world, char glyph, Color color) {
        this.world = world;
        this.glyph = glyph;
        this.color = color;
        this.lock = new ReentrantLock();
        this.hp = 100;
        this.direction = -1;
        this.state = 1;
    }

    public void run(){}
    public void attack(){
        Node node = next_position(direction);
        //get_target & handle
        //this.lock.lock();
        //try {
            Creature c = this.world.creature(node.x, node.y);
            if (c != null) {
                c.hurt(attack);
            }
        //} finally {this.lock.unlock();}
    }
    public synchronized int checkState() {
        if(state == 0 && world.tile(x, y+1) == Tile.SPACE) {
            state = 1;
            return 1;
        }
        else if (state == 1) {
            if(world.tile(x, y+1) == Tile.WALL) {
                state = 0;
                direction = -1;
                return 0;
            }
            return 1;
        }
        return state;
    }
    public synchronized void fall() {//hurt中锁direction
        if(direction == 0 || direction == 1)
            this.moveBy(direction);
        this.moveBy(3);
        this.moveBy(3);
        this.moveBy(3);
    }
    public void hurt(int attack) {
        this.hp -= attack;
    }

    public synchronized void moveBy(int dir) {
        if(dir == 0 || dir == 1)
            this.direction = dir;
        Node node = next_position(dir);
        if(world.tile(node.x, node.y) == Tile.BOUNDS) {
            hurt(9999);
        }
        else if(world.tile(node.x, node.y) != Tile.WALL && world.creature(node.x, node.y) == null) {
            x = node.x;
            y = node.y;
        }
        else if(dir == 3 && state == 0 && world.tile(x, y+1) == Tile.WALL && world.tile(x, y+2) == Tile.SPACE) {
            y = y+2;
        }
    }

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public int x() {return x;}
    public int y() {return y;}
    public char glyph() {return glyph;}
    public Color color() {return color;}
    public Node next_position(int dir) {
        switch(dir) {//左右下上
            case 0:
                return new Node(x-1, y);
            case 1:
                return new Node(x+1, y);
            case 2:
                return new Node(x, y-1);
            case 3:
            default:
                return new Node(x, y+1);
        }
    }
}
