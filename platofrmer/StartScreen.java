import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Meny here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartScreen extends World
{

    /**
     * Constructor for objects of class Meny.
     * 
     */
    public StartScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 700, 1); 
        Greenfoot.start();
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        HelpButton helpButton = new HelpButton();
        addObject(helpButton,314,224);
        StartButton startButton = new StartButton();
        addObject(startButton,454,222);
        Buttons buttons = new Buttons();
        addObject(buttons,285,120);
        SelectEnemy selectEnemy = new SelectEnemy();
        addObject(selectEnemy,89,340);
    }
}
