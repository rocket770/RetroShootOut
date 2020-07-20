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
    private int xvelocity;
    public static int moveSpeed = 2; 
    public void act() 
    {
        moveSpeed = Player1.p1_bulletMoveSpeed;
        setLocation(getX() + xvelocity, getY());
        colision();
    }  

    public void addedToWorld(World world)
    { 
        if(Player1.p1Direction == "right")
        { 
            xvelocity = moveSpeed;
        }
        if(Player1.p1Direction == "left")
        { 
            xvelocity = -moveSpeed;
        }
        // USE CAQSEWHERE IT MORE EFFIENCET
    }
    
    
    
}
