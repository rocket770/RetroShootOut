import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FramesPerSecond here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FramesPerSecond extends Actor
{
    private int frames = 0;
    private double lastFrameTime = 0;
    private double fps = 0.0f;
    private int millisElapsed = 0;
    private long lastTime = 0;

    public void act() 
    {
        getTimePassed();
        updateFrams();
        updateImage(); 
    }
    // Calculate time passed since this object was added to the world
    private void getTimePassed() {
        long time = System.currentTimeMillis();
        if(lastTime != 0) {
            // get difference of time between last act cyle and current
            long difference = time - lastTime;
            // add difference to time elapsed
            millisElapsed += difference;
        }
        lastTime = time;       
    }

    private void updateFrams() {
        frames++;
        // if the difference of the timeBetween updates and atleast 10 frames have passed, update the frames
        if (millisElapsed - lastFrameTime > 0.25 && frames > 10)
        {
            // FPS = frames/time
            fps = (double) frames / (millisElapsed - lastFrameTime) * 1000 -1;   
            lastFrameTime = millisElapsed;
            frames = 0;
        }
    }
    // Sets objects image to transparent grey with black text that shows a rounded value of the fps counter
    
    private void updateImage() 
    {
       GreenfootImage textImage = new GreenfootImage("FPS: " +(int)fps, 24, new Color(0, 0, 0), new Color(0, 0, 0, 0));
       GreenfootImage image = new GreenfootImage(textImage.getWidth()+12, 36);
       image.setColor(new Color(128, 128, 128));
       image.fill();
       image.setTransparency(175);
       image.drawImage(textImage, 6, 6);
       setImage(image); 
    }

}
