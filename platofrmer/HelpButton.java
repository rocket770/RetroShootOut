import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HelpButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HelpButton extends Buttons
{
    /**
     * Act - do whatever the HelpButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        selectionAnimation();
        changeWorld();
        switchButton();
    } 
    // Sets button to be unselected by default
    private boolean canChange = false;

    // Will run animation only if this button is selected
    private void selectionAnimation()
    {
        Buttons buttons = getWorld().getObjects(Buttons.class).get(0); // Converts actor value into object
        boolean Start = buttons.start;
        
        
        // Change to switch statement maybe - faster efficent code
        if(Start == false)
        {
            animation("Images/Help", 1);
            canChange = true;   
        }
        else
        {
            setImage("Images/Help0.png"); // Set image to default if not selected
            canChange = false;
        }
    } 
    
    //  changes to helpMeu world if it is selected and the user presses enter
    private void changeWorld()
    {
        if(canChange && Greenfoot.isKeyDown("Enter"))
        {
            //Greenfoot.setWorld(new HelpMenu());
        }
    }

}