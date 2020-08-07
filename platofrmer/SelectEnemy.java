import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectEnemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectEnemy extends Buttons
{
    public static boolean enableAI = false;
    /**
     * Act - do whatever the SelectEnemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        checkSwitch();
    }   

    private void checkSwitch() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null) {        
            // Calculate distance between mouse and centre of object 
            // find radius by getting half of the horizontal length
            int radius = getImage().getWidth()/2;
            double dist = Math.hypot(mouse.getX() - this.getX(), mouse.getY() - this.getY());
            if (Greenfoot.mouseClicked(this) && dist <= radius) {
                enableAI = !enableAI;
            }
             getWorld().showText(""+dist, 250,250);
             getWorld().showText(""+radius, 250,270);

        }
        updateImage();
        getImage().scale(getImage().getWidth()/2, getImage().getHeight()/2);
    }

    private void updateImage() {
        int imageType = (enableAI) ? 1 : 0;  // switch with if else equal switch 
        switch(imageType) {
            case 1: setImage("ai enable.png"); break;
            case 0: setImage("ai disable.png"); break;
        }
    }

}
