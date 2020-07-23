import greenfoot.*;
/**
 * Write a description of class PowersUps here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PowersUps extends Actor  
{
    // intilize movement delay vairble to 0
    private int MoveDelay = 0;
    protected int timeAlive;
    /**
     * Constructor for objects of class PowersUps
     */
    // Movement of powerups
    protected void movement(int movementSpeed)
    {
        // Convert actor to object if is touching the floor at an offset of 30 pixels from the powerup object
        Actor floor = getOneObjectAtOffset(0,30, ground.class);
        // Basic essiental falling movement, moving x units 12 times a second (act cylces / 5)
        if (MoveDelay >= 5 && floor == null)
        {
            setLocation(getX(), getY()+ movementSpeed);
            MoveDelay = 0;
        }
        MoveDelay++; 
        // Since greenfoot can not quickly detect objects reletive to an offset,
        // this while loop checks if the outside edges of the object slightly intersect
        // with the ground and will instantly push it above
        while(getOneObjectAtOffset(0,getImage().getHeight()/2-1, ground.class)!=null)
        {
            setLocation(getX(),getY()-1);
        }
    }

    protected void despawn(int MAX_TIME_ALIVE)
    {
        timeAlive++;
        if(timeAlive == MAX_TIME_ALIVE) 
        {
            getWorld().removeObject(this);
        }
    }

}
