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
        Greenfoot.setSpeed(50);
        addObject(new HelpButton(),500,525);
        addObject(new StartButton(),500,350);
        addObject(new SelectEnemy(),89,340);
        showText("Click me!",90,430);
    }


}
