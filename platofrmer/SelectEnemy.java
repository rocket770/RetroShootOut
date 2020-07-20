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
    if (Greenfoot.mouseClicked(this)) {
        enableAI = !enableAI;
    }
    getWorld().showText("AI: "+enableAI, 250,250);
}    
}
