import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends Buttons
{
    /**
     * Act - do whatever the StartButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    // Sets button to be selected by default
    private boolean canChange = true;
    // Delay insures world is not chnaged to "space world" instalty after help world is spanwed
    private int delay = 0;
    // Animation will only play if selecred
    
    public void act() 
    {
        selectionAnimation();
        switchButton();
        changeWorld();
    }
    
    private void selectionAnimation()
    {
        Buttons buttons = getWorld().getObjects(Buttons.class).get(0);
        boolean Start = buttons.start;
        // Plays animtation if selected in mainmenu
        if(Start == true && getWorld() instanceof StartScreen)
        {
            animation("Images/Start", 1);
            canChange = true;
        }
        // Sets back to regular image if not selected but will only apply in the main menu
        if(Start == false && getWorld() instanceof StartScreen)
        {
            setImage("Images/Start0.png"); 
            canChange = false;
        }
         // Makes sure the animation will always play if in help screen
         // So the user cannot select a button that is not there
        if (getWorld() instanceof Help)
        {
            animation("Images/Start", 1); 
            canChange = true;
        }
    }

    // Chnages world if selected and user pressets enter
    private void changeWorld()
    {
        // Avoids logic error when button pressed upon world being instantly changed
        // Adds a delay so if the user holds down enter for too long it will not instaly
        // Press 2 buttons at once.
        if(canChange && Greenfoot.isKeyDown("Enter") && delay >= 45)
        {
            Greenfoot.setWorld(new PVPArena());
        }
            // increase button timer if not already presse
        delay++;
    }

}
