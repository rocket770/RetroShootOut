import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player2_Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player2_Bullet extends Bullet
{
    /**
     * Act - do whatever the Player2_Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int xvelocity;
    public static int moveSpeed = 2;
    public void act() 
    {
        moveSpeed = Player2.p2_bulletMoveSpeed;
        setLocation(getX() + xvelocity, getY());
        colision();
    }  

    public void addedToWorld(World world)
    { 
        if(Player2.p2Direction == "right")
        { 
            xvelocity = moveSpeed;
        }
        if(Player2.p2Direction == "left")
        { 
            xvelocity = -moveSpeed;
        }
    }  

}
