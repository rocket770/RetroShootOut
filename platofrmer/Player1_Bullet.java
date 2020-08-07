import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player1_Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player1_Bullet extends Bullet
{
    /**
     * Act - do whatever the Player1_Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int xVelocity;
    public static int moveSpeed = 2;

    // bullet constructor applied each time spawned, more efficent then constructor

    public void addedToWorld(World world)
    { 
        switch(Player1.p1Direction) {
            case "left": xVelocity = -moveSpeed; break;
            case "right": xVelocity = moveSpeed; break;
        }
    }

    public void act() 
    {
        moveSpeed = Player1.p1_bulletMoveSpeed;
        setLocation(getX() + xVelocity, getY());
        colision();
    }  

}
    

