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
        Actor floor = getOneObjectAtOffset(0,30, ground.class);
        if (MoveDelay >= 5 && floor == null)
        {
            setLocation(getX(), getY()+ movementSpeed);
            MoveDelay = 0;
        }
        MoveDelay++; 
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
