import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player2 extends Players
{
    /**
     * Act - do whatever the Player1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public static int p2Lives; 
    public static String p2powerup = "";
    public static float p2TimeLeft;
    public static int x;
    private int damageDelay = 0;
    public static int y;
    public static int p2_bulletMoveSpeed;
    private int p2shootdelay;
    public static int p2shootOffset;
    public static String p2Direction = "right";

    public Player2()
    {
        p2shootdelay = 45;
        p2shootOffset = 45;
        p2_bulletMoveSpeed = 2;
        p2powerup = "";
        p2Lives = 3;
        p2TimeLeft = 0;
    }

    public void act() 
    {
        Shoot();
        movement("up","left","right","p2_left","p2_right","p2_still", "Player2");
        getLocation();
        checkFall();
        getDirection();
        fastShoot("Player2");
        rapidShoot("Player2");
        speedBoost("Player2");
        healthDetection();

    }   

    private void Shoot()
    {
        // Delay can chnage if power up is active
        if(Greenfoot.isKeyDown(".") && p2shootdelay >= p2shootOffset)
        {
            // spawns player bullet and plays sound
            getWorld().addObject(new Player2_Bullet(), getX(), getY());
            //Greenfoot.playSound("shoot.wav");
            p2shootdelay = 0;
            // resets delay
            Greenfoot.playSound("gunshot.mp3");

        }
        // will increase the delay until the player is able to shoot
        if (p2shootdelay <= p2shootOffset)
        {
            p2shootdelay++;
        }
    }

    private void healthDetection()
    {
        for (Object Bar2 : getWorld().getObjects(Bar.class))
        {
            Bar player2 = (Bar) Bar2;
            if(player2.refName == "Player2")
            {
                if(isTouching(Player1_Bullet.class))
                {
                    player2.subtract(2);
                    removeTouching(Player1_Bullet.class);
                }
                if(isTouching(hpUp.class))
                {
                    player2.add(4);
                    removeTouching(hpUp.class);
                }
                if(isTouching(EnemyAI.class) && damageDelay >= 300)
                {
                    player2.subtract(1);
                    damageDelay = 0;
                }
            }
            if(player2.getValue() == 0)
            {
                p2Lives--;
                Greenfoot.playSound("deathsound.mp3");
                setLocation(840,360);
                player2.add(10);
            }
            if(p2Lives == 0)
            {                
                endGame("P1");
            }
            damageDelay++;
        }
    }

    private void getDirection()
    {
        if(Greenfoot.isKeyDown("left"))
        {
            p2Direction = "left";    
        }
        if(Greenfoot.isKeyDown("right"))
        {
            p2Direction = "right";    
        }
    }

    private void getLocation()
    {
        x = getX();
        y = getY();
    }
}
