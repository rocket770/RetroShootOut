import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class hpUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class hpUp extends PowersUps
{
    /**
     * Act - do whatever the hpUp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        movement(5);
        despawn(900);
    }    
}
