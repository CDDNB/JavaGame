package world;

import java.awt.event.KeyEvent;
import java.security.Key;
import java.awt.Color;

public class Player extends Creature{
    //private int cheat_rest;
    public final char name;
    public Player(World world, char glyph, Color color, char name, int hp, int attack) {
        super(world, glyph, color);
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.start();
    }

    public void respondToUserInput(int keyCode) {
        if(this.isAlive()) {
                if (state != 0)
                    return;
                switch(keyCode) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        this.moveBy(0);
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        this.moveBy(1);
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        this.moveBy(2);
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        this.moveBy(3);
                        break;
                    case KeyEvent.VK_G:
                    case KeyEvent.VK_MINUS:
                        this.attack();
                        break;
                    case KeyEvent.VK_H:
                    case KeyEvent.VK_EQUALS:
                        this.jump();
                        break;
                    case KeyEvent.VK_0:
                        this.hp = -1;
                }

            
        }
    }

    @Override
    public void run() {
        while(hp > 0) {
            try {
            Thread.sleep(200);
            if(this.state == -1){
                Thread.sleep(200);
            }
            if(this.checkState() == 1) {
                this.fall();
            }
            }catch(Exception e) {}
        }
        this.world.endGame(this.name);
        //System.out.println("player died.");
    }

    private synchronized void jump() {
        if (state == 0)
            state = -1;
        else if (state != 0)
            return;
        this.moveBy(direction);
        this.moveBy(2);
        this.moveBy(2);
        this.moveBy(2);
        state = 0;
    }

    /*@Override
    public void attack() {
        int dx = x, dy = y;
        switch(direction) {
            case 0:
                dx += -1;
                break;
            case 1:
                dx += 1;
                break;
            case 2:
                dy += -1;
                break;
            case 3:
                dy += 1;
                break;
        }
        //get_target & handle
        List<Creature> targets_c = new ArrayList<>();//for futher aoe
        List<Tile> targets_t = new ArrayList<>();
        this.lock.lock();
        try {
            Creature t = this.world.creature(dx, dy);
            Tile tt = this.world.tile(dx, dy);
            if (t != null) {
                targets_c.add(t);
            }
            else if (tt == Tile.WALL) {
                targets_t.add(tt);
            }
        
            for (Creature c : targets_c) {
                c.hurt(this.attack);
            }
            for (Tile tile : targets_t) {
                tile = Tile.FLOOR;
            }
        } finally {this.lock.unlock();}
    }*/
}
