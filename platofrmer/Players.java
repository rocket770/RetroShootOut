import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Players here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Players extends Actor
{
    /**
     * Act - do whatever the Players wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    private double time = 0.0F;
    public static boolean gotPowerUp = false;

    private int speed =3;
    private boolean gotSpeedBoost = false;
    private final int speed_boost_timer = 300;
    private int speedBoostTimeLeft = speed_boost_timer;

    private boolean gotFastShoot = false;
    private final int fast_shoot_timer = 300;
    private int fastShootTimeLeft = fast_shoot_timer;

    private boolean gotRapidShoot = false;
    private final int rapid_shoot_timer = 150;
    private int rapidShootTimeLeft = rapid_shoot_timer;

    private int ySpeed; //current vertical speed
    private int acceleration = 1;
    private int jumpHeight = -20;

    // Variables used for animation speed and number of images
    private int imageDelay = 0;
    private int ImageChangeSpeed = 10;
    // The image the animation should start at
    private int imageNumber = 1;

    protected void movement(String moveUp, String moveLeft, String moveRight, String animationLeft, String animationRight, String playerStill, String direction)
    {
        ground leftfloor = (ground) getOneObjectAtOffset(-getImage().getWidth()/2-1, 0, ground.class); // Check cell left of current position for a floor
        ground rightfloor = (ground) getOneObjectAtOffset(getImage().getWidth()/2+1, 0 , ground.class); // Check cell right of current position for a floor
        if (Greenfoot.isKeyDown(moveLeft) && leftfloor == null)
        {
            setLocation(getX() -speed, getY()); // One cell left
            Animation(animationLeft, 3);
            direction = "left";
        }
        else if (Greenfoot.isKeyDown(moveRight) && rightfloor == null)
        {
            setLocation(getX() +speed, getY()); // One cell right
            Animation(animationRight, 3);
            direction = "right";
        }
        if(Greenfoot.isKeyDown(moveUp) && onGround() == true)
        {
            // Fall if not on the ground
            ySpeed = jumpHeight;
            fall();
        }
        while(getOneObjectAtOffset(0,getImage().getHeight()/2-1, ground.class)!=null)
        {
            setLocation(getX(),getY()-1);
        }
        //getImage().scale(getImage().getWidth()-10,getImage().getHeight()-20);
    }

    protected void endGame(String Class)
    {
        getWorld().setBackground(new GreenfootImage(Class +"won.png"));
        getWorld().removeObjects(getWorld().getObjects(ground.class));
        getWorld().removeObjects(getWorld().getObjects(Bullet.class));
        getWorld().removeObjects(getWorld().getObjects(PowersUps.class));
        getWorld().removeObjects(getWorld().getObjects(Bar.class));
        getWorld().removeObjects(getWorld().getObjects(EnemyAI.class));
        getWorld().removeObjects(getWorld().getObjects(Lives.class));
        // Iterate through each player object in the world by casting the list into a for loop
        for (Object obj : (java.util.List<Players>)getWorld().getObjects(Players.class))
        {
            // Cast each player object in the world into a single variable
            Players player = (Players) obj;
            player.setImage(new GreenfootImage(1,1));
        }
        getWorld().showText("", 305,760);
        getWorld().showText("", 675,760);
        ((PVPArena)getWorld()).backgroundMusic.stop();  // Cast world type to static refernece
        Greenfoot.stop();
    }

    protected void checkFall()
    {
        if (onGround() == false)
        {
            fall();
        }
        else
        {
            ySpeed = 0;
        }
        if(onRoof())
        {
            setLocation(getX(),getY()-ySpeed+1);
            ySpeed += acceleration*2;
        }
    }

    protected void Animation(String imagename, int images)
    {
        // Animation for object, image file name should look like: FILENAME0 FILENAME1 etc...
        if (imageDelay == ImageChangeSpeed)
        {
            // Adds a delay between images
            imageNumber++;
            if(imageNumber > images)
            {
                // resets the image it must change to back to the start when cycle is complete
                imageNumber = 1;
                //getWorld().showText("imageDelay check: " + imageDelay, 250, 250);
            }
            // reset delay
            imageDelay = 0;
        }
        imageDelay++;
        setImage(imagename + imageNumber + ".png");
        getImage().scale(getImage().getWidth()-10,getImage().getHeight()-20);
    }

    private void fall()
    {
        // Simulates gravity but moving the object down faster each cycle while it is not in the air 
        setLocation(getX(),getY()+ySpeed);
        ySpeed += acceleration;
    }

    private boolean onGround()
    {
        // If object is standing above the ground
        // Set false if on ground 
        Actor floor = getOneObjectAtOffset(0,getImage().getHeight()/2+1, ground.class);
        return floor != null;
    }

    private boolean onRoof()
    {
        // if object is directly under or touching the bottom of the ground tile
        Actor roof = getOneObjectAtOffset(0,-getImage().getHeight()/2- 1, ground.class);
        return roof != null;
    }

    protected void displayPowerUp()
    {
        if(getWorld().getBackground().equals("background.png")) {
            // Converts float to 2DP, ignores negative values when 2 power ups are active at once.
            getWorld().showText(Player1.p1powerup +"\n\n"+Math.abs(Math.round(Player1.p1TimeLeft/60 * 100.0)) / 100.0, 305,760);
            getWorld().showText(Player2.p2powerup +"\n\n" +Math.abs(Math.round(Player2.p2TimeLeft/60 * 100.0)) / 100.0, 675,760);
            //System.out.println(p2powerup);
            //getWorld().showText("p1 Lives: " +Player1.p1Lives,450,250);
            //getWorld().showText("p2 Lives: " +Player2.p2Lives,450,230);
        }
        time++;
        getWorld().showText("Time: " +Math.round(time/60 * 100.0)/100.0, 500,750);     
    }

    protected void speedBoost(String ClassName)
    {
        Actor actor = getOneObjectAtOffset(0, 0, SpeedBoost.class);
        // Acitvate affects of powerup if is touching it
        if (actor != null) {
            getWorld().removeObject(actor);
            gotSpeedBoost = true;
            gotPowerUp = true;
            speed = 7;
            // Apply affect to the player that is touching it
            switch (ClassName)
            {  
                case "Player2": Player2.p2powerup = "Speed Boost!"; Player2.p2TimeLeft = speed_boost_timer; break;
                case "Player1": Player1.p1powerup = "Speed Boost!"; Player1.p1TimeLeft = speed_boost_timer; break;
            }
        }
        if (gotSpeedBoost)
        {
            // Count down timer for the class that has it
            switch (ClassName)
            {  
                case "Player2": Player2.p2TimeLeft--; break;             
                case "Player1": Player1.p1TimeLeft--; break;             
            }
            //System.out.println("Speed Boost: " +gotSpeedBoost +" / "+speedBoostTimeLeft);
            speedBoostTimeLeft--;
            if (speedBoostTimeLeft <= 0)
            {
                // remove effects, reset time
                gotSpeedBoost = false;
                gotPowerUp = false;
                speed = 3;
                speedBoostTimeLeft = speed_boost_timer;
                switch (ClassName)
                {   case "Player2": Player2.p2powerup = "";  Player2.p2TimeLeft = 0 ;break;
                    case "Player1": Player1.p1powerup = "";  Player1.p1TimeLeft = 0 ;break; 
                }
            }
        }
    }
    
    // Explenation for each powerup explained above
    protected void fastShoot(String ClassName)
    {
        //getWorld().showText("p1 speed: " +Player1.p1_bulletMoveSpeed, 250, 250);
        //getWorld().showText("p2 speed: " +Player2.p2_bulletMoveSpeed, 250, 280);
        Actor actor = getOneObjectAtOffset(0, 0, fastBullets.class);
        if (actor != null) {
            switch (ClassName)
            {  
                case "Player1": Player1.p1_bulletMoveSpeed = 9; Player1.p1powerup = "Fast Bullets!"; Player1.p1TimeLeft = fast_shoot_timer; break;
                case "Player2": Player2.p2_bulletMoveSpeed = 9; Player2.p2powerup = "Fast Bullets!"; Player2.p2TimeLeft = fast_shoot_timer; break;
            }
            getWorld().removeObject(actor);
            gotFastShoot = true;
            gotPowerUp = true;
        }
        if (gotFastShoot)
        {
            switch (ClassName)
            {  
                case "Player2": Player2.p2TimeLeft--; break;             
                case "Player1": Player1.p1TimeLeft--; break;             
            }
            fastShootTimeLeft--;
            if (fastShootTimeLeft <= 0)
            {
                gotFastShoot = false;
                gotPowerUp = false;
                switch (ClassName)
                {   case "Player1": Player1.p1_bulletMoveSpeed = 2; Player1.p1powerup = ""; Player1.p1TimeLeft = 0; break;
                    case "Player2": Player2.p2_bulletMoveSpeed = 2; Player2.p2powerup = ""; Player2.p2TimeLeft = 0; break;
                }
                fastShootTimeLeft = fast_shoot_timer;
            }
        }
    }

    protected void rapidShoot(String ClassName)
    {
        //getWorld().showText("p1 speed: " +Player1.p1_bulletMoveSpeed, 250, 250);
        //getWorld().showText("p2 speed: " +Player2.p2_bullp1powetMoveSpeed, 250, 280);
        Actor actor = getOneObjectAtOffset(0, 0, rapidFire.class);
        if (actor != null) {
            switch (ClassName)
            {  case "Player1": Player1.p1shootOffset = 10; Player1.p1powerup = "Rapid Fire!"; Player1.p1TimeLeft = rapid_shoot_timer; break;
                case "Player2": Player2.p2shootOffset = 10; Player2.p2powerup = "Rapid Fire!"; Player2.p2TimeLeft = rapid_shoot_timer; break;
            }
            getWorld().removeObject(actor);
            gotRapidShoot = true;
            gotPowerUp = true;
        }
        if (gotRapidShoot)
        {
            switch (ClassName)
            {  
                case "Player2": Player2.p2TimeLeft--; break;             
                case "Player1": Player1.p1TimeLeft--; break;             
            }
            rapidShootTimeLeft--;
            if (rapidShootTimeLeft <= 0)
            {
                gotRapidShoot = false;
                gotPowerUp = false;
                switch (ClassName)
                {   case "Player1": Player1.p1shootOffset = 45; Player1.p1powerup = ""; Player1.p1TimeLeft = 0; break;
                    case "Player2": Player2.p2shootOffset = 45; Player2.p2powerup = ""; Player2.p2TimeLeft = 0; break;
                }
                rapidShootTimeLeft = rapid_shoot_timer;
            }
        }
    }

}
