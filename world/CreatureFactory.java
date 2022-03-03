package world;

import asciiPanel.AsciiPanel;

public class CreatureFactory extends Thread{
    private World world;

    public CreatureFactory (World world) {
        this.world = world;
        for(int i = 0; i < 2; i++) {
            world.addAtEmptyLocation(new Monster(this.world, (char)3, AsciiPanel.brightBlue));
        }
        start();
    }

    public void run() {
        try {
            Thread.sleep(100000);
        } catch (Exception e) {}
        if(this.world.monsterNum() < 5) {
            world.addAtEmptyLocation(new Monster(this.world, (char)3, AsciiPanel.brightBlue));
        }
    }
}
