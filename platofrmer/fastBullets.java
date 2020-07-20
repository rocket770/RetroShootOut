import greenfoot.*;
/**
 * Write a description of class RapidFire here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class fastBullets extends PowersUps 
{

    // Power up deactivated by default on spawn
    // Despawn variables to check if the powerup can be removed or not
    public void act()
    {
      movement(4); 
      despawn(900);
    }

}
