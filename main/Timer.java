package main;

public class Timer extends Thread {
    private ApplicationMain app;
    private boolean on;

    public Timer(ApplicationMain app) {
        this.app = app;
        on = true;
        start();
    }
    
    public void run() {
        while (on) {
            try {
                Thread.sleep(40);
            } catch (Exception e) {}
            app.repaint();
        }
    }

    public void off() {
        this.on = false;
    }
}