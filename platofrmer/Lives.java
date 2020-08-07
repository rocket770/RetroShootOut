import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lives here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lives extends Actor
{
    /**
     * Act - do whatever the Lives wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // Converts Player string from contsutocr into non-staric variable that the object can use
    private String player; 
    // Used to determin the remaing lives of the object that this class is represneting 
    private int remainingHP;
    
       public Lives(String player)
    {
        this.player = player;
    }
    
    public void act() 
    {
       updateLives();
    }  

    // Update heart image based on the remaing health this object is representing. 
    public void updateLives()
    {
        switch(player)
        {
            case "Player1": remainingHP = Player1.p1Lives; break;
            case "Player2": remainingHP = Player2.p2Lives; break;
        }
        if(remainingHP > 0) setImage("heart"+remainingHP+".png");
    }
}
