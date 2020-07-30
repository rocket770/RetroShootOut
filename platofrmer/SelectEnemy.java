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
         if (Greenfoot.mouseClicked(this)) {
            enableAI = !enableAI;
        }
        updateImage();
        getImage().scale(getImage().getWidth()/2, getImage().getHeight()/2);
    }
    
    private void updateImage() {
       int imageType = (enableAI) ? 1 : 0;  // Cast refernce 
       switch(imageType) {
           case 1: setImage("ai enable.png"); break;
           case 0: setImage("ai disable.png"); break;
        }
    }
    
}
