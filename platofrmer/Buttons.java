import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Buttons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Buttons extends Actor
{
    /**
     * Act - do whatever the Buttons wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // animation
    private int imageDelay = 0;
    private int ImageChangeSpeed = 22;
    private int imageNumber = 0;
    // Sets default selected button
    protected boolean start = true;

    public void act() 
    {
        switchButton();
    }    

    // Animation for button
    protected void animation(String imagename, int images)
    {
        // a delay to how fast the image updates 
        if (imageDelay == ImageChangeSpeed)
        {
            imageNumber++;
            if(imageNumber > images)
            {
                imageNumber = 0;
            }
            imageDelay = 0;
        }
        imageDelay++;
        setImage(imagename + imageNumber + ".png");
    }
    
    // Toggles between which button is selected using the up and down arrow key
    protected void switchButton()
    {
        //System.out.println("Button Selected: " +start);
        String key = Greenfoot.getKey();
        if ("up".equals(key) || "down".equals(key))
        {
            start = !start;
        }

    }
}
