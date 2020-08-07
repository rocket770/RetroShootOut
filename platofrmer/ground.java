import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ground extends Actor
{
    /**
     * Act - do whatever the ground wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public ground(String tile) 
    {
        switch(tile) {
            case "Dirt" : setImage("dirt.jpg"); break; 
            case "Grass": setImage("grass.jpg"); break;
        }
        GreenfootImage image = getImage();
        image.scale(40,40);
    } 
    
    
}
