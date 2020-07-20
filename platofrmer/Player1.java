import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player1 extends Players
{
    /**
     * Act - do whatever the Player1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public static int p1Lives; 
    public static int p1_bulletMoveSpeed = 2;
    public static float p1TimeLeft;
    private int damageDelay = 0;
    public static int x;
    public static int y;
    public static String p1powerup = "";
    private int p1shootdelay = 45;
    public static int p1shootOffset = 45;
    public static String p1Direction = "right";
    
    public Player1()
    {
        p1shootdelay = 45;
        p1shootOffset = 45;
        p1_bulletMoveSpeed = 2;
        p1powerup = "";
        p1TimeLeft = 0;
        p1Lives = 3;
    }
    
    public void act() 
    {
        movement("w","a","d","p1_left","p1_right","p1_still", "Player1");
        checkFall();
        Shoot();
        getLocation();
        getDirection();
        hpUpdate();
        displayPowerUp();
        fastShoot("Player1");
        rapidShoot("Player1");
        speedBoost("Player1");
    }   

    private void Shoot()
    {
        // Delay can chnage if power up is active
        if(Greenfoot.isKeyDown("space") && p1shootdelay >= p1shootOffset)
        {
            // spawns player bullet and plays sound
            getWorld().addObject(new Player1_Bullet(), getX(), getY());
            //Greenfoot.playSound("shoot.wav");
            p1shootdelay = 0;
            // resets delay

        }
        // will increase the delay until the player is able to shoot
        if (p1shootdelay <= p1shootOffset)
        {
            p1shootdelay++;  
        }
    }

    private void hpUpdate()
    {
        // Health updating and features for each indivudal player
        for (Object Bar1 : getWorld().getObjects(Bar.class))
        {
            Bar player1 = (Bar) Bar1;
            // Reference Check to Player HP bar, neccessary to update the correct bar
            // More efficient to nest "IF" rather then checking each statement every cycle
            if(player1.refName == "Player1")
            {
                if(isTouching(Player2_Bullet.class))
                {
                    player1.subtract(2);
                    removeTouching(Player2_Bullet.class);
                }
                if(isTouching(hpUp.class))
                {
                    player1.add(4);
                    removeTouching(hpUp.class);
                }
                if(isTouching(EnemyAI.class) && damageDelay >= 300)
                {
                    player1.subtract(1);
                    damageDelay = 0;
                }
            }
            // Get Health value refrence from class to determin a life loss
            if(player1.getValue() == 0)
            {
                p1Lives--;
                setLocation(100,540);
                player1.add(10);
            }
            if(p1Lives == 0)
            {                
                endGame("P2");
            }
            damageDelay++;
        }
    }

    private void getDirection()
    {
        // Set indivudial Player direction for bullet class direction reference
        if(Greenfoot.isKeyDown("a"))
        {
            p1Direction = "left";    
        }
        if(Greenfoot.isKeyDown("d"))
        {
            p1Direction = "right";    
        }
    }

    private void getLocation()
    {
        // Update Static variables to players position each act cycle
        x = getX();
        y = getY();
    }

    
}
