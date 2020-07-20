import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Bullet extends Actor
{
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    protected void colision()
    {
        if(isAtEdge() || isTouching(ground.class) || isTouching(Bullet.class))
        {
            removeTouching(Bullet.class);
            getWorld().removeObject(this);
        }
    }
    
    
    
}
