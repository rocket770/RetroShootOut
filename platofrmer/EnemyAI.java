import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class enemyAI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EnemyAI extends Actor
{
    /**
     * Act - do whatever the enemyAI wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int xVel = 4;
    private int ySpeed; //current vertical speed
    private int acceleration = 1;
    private int jumpHeight = -20;
    private int moveDelay = 0;
    private int bounceCount = 0;
    private int aiDelay =100;
    private int imageDelay = 0;
    private int imageChangeSpeed = 10;
    // The image the animation should start at
    private int imageNumber = 1;

    // Non-Abstrsct Object position in world passed to other classes
    public static int x;
    public static int y;
    public void act()
    {
        defaultMove();
        followEnemy();
        animateOnWalk();
        removeTouching(PowersUps.class);
        checkFall();
        stuckCheck();
        getLocation();
        healthLoss();
    }

    private void defaultMove()
    {
        ground leftfloor = (ground) getOneObjectAtOffset(-getImage().getWidth()/2-1, 0, ground.class); // Check cell left of current position for a floor
        ground rightfloor = (ground) getOneObjectAtOffset(getImage().getWidth()/2+1, 0 , ground.class); // Check cell right of current position for a floor
        setLocation(getX()+xVel,getY());
        //getWorld().showText("Bounce: " +bounceCount, 100,100);
        if(moveDelay >=30 && rightfloor != null || leftfloor !=null && moveDelay >=30 || getX() <= 5 || getX() >= 995)
        {
            xVel = -xVel;
            moveDelay = 0;
            bounceCount++;
        }
        if(bounceCount > 2 && rightfloor != null || bounceCount > 2 && leftfloor != null)
        {
            int SearchPhaseOffset = Greenfoot.getRandomNumber(25);
            if(SearchPhaseOffset < bounceCount || bounceCount == 10)
            {
                xVel = -xVel;    //reset to original value before next bounce
                ySpeed = jumpHeight;
                fall();
                bounceCount = 0;
            }
        }
        moveDelay++;
    }

    protected void stuckCheck()
    {
        // Fixes greenfoots broken collosions where bojects can sometimes get stuck in the ground
        while(getOneObjectAtOffset(0,getImage().getHeight()/2-1, ground.class)!=null)
        {
            setLocation(getX(),getY()-1);
        }
        while(getOneObjectAtOffset(0,-getImage().getHeight()/2+1, ground.class)!=null)
        {
            setLocation(getX(),getY()+1);
        }
    }

    private void animateOnWalk()
    {
        switch(xVel)
        {
            case 4: Animation("enemy_right", 6);
            break;
            case -4: Animation("enemy_left", 6);
            break;
        }
    }

    private void Animation(String imagename, int images)
    {
        // Animation for object, image file name should look like: FILENAME0 FILENAME1 etc...
        if (imageDelay == imageChangeSpeed)
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
        //getImage().scale(getImage().getWidth()/4, getImage().getHeight()/4);
    }

    private void checkFall()
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

    private void getLocation() {
        x = getX();
        y = getY();   
    }

    private void followEnemy()
    {
        // Group all player objects and power up objects into a list
        List<Players> playergroup = getObjectsInRange(300,Players.class);
        //getWorld().showText("right: " +rightTarget, 390,450);
        //getWorld().showText("left: " +leftTarget, 390,430);
        //getWorld().showText("aiDelay: " +aiDelay, 390,410);
        List<PowersUps> powerups = getObjectsInRange(300,PowersUps.class);
        if(playergroup.size() > 0 )  {
            Actor Player = playergroup.get(0);
            // Get closests players position (Prioritises player 1)
            int pX = Player.getX();
            int pY = Player.getY();
            //Dont jump if player is below enemy
            pathFind(pX, pY);
        }
        else if(powerups.size() > 0) {
            Actor PowersUps = powerups.get(0);
            int tX = PowersUps.getX();
            int tY = PowersUps.getY();
            pathFind(tX, tY);

        }  else if (!getWorld().getBackground().equals("background.png")) getWorld().setBackground(new GreenfootImage("background.png"));
    }

    private void pathFind(int targetX, int targetY) {
        ground leftfloor = (ground) getOneObjectAtOffset(-getImage().getWidth()/2-1, 0, ground.class); // Check cell left of current position for a floor
        ground rightfloor = (ground) getOneObjectAtOffset(getImage().getWidth()/2+1, 0 , ground.class); // Check cell right of current position for a floor
        if(onGround() && targetY < getY() && (leftfloor != null || rightfloor != null))
        {
            //System.out.println("Above and left side");
            ySpeed = jumpHeight;
            fall();
            aiDelay = 15;
        }
        // Check if AI has moved near player (Attacked) so it someitmes moves away and does not constantly stay on player
        if(getX() == targetX - 3 || getX() == targetX +3)
        {
            aiDelay = 50;
        }
        //if(getY() < targetY && getX() == targetX) aiDelay  = 5; /* FIX PATH FINDING BUT LIMIT TRACE*/
        // If can move, set direction relative to the players position 
        if (aiDelay <= 0 && targetY > getY() -5)
        {
            xVel = (targetX < getX()) ? -4 : 4;
        }

        getWorld().setBackground(new GreenfootImage("background.png"));
        getWorld().getBackground().drawLine(targetX, targetY, x, y);

        aiDelay--;
    }

    private void healthLoss()
    {
        for (Object Bar3 : getWorld().getObjects(Bar.class))
        {
            Bar enemy = (Bar) Bar3;
            if(enemy.refName == "Enemy " && isTouching(Bullet.class))
            {
                enemy.subtract(2);
                removeTouching(Bullet.class);
            }
            // drops random powerup on death that is not already in world
            if(enemy.getValue() == 0)
            {
                int spawnType = Greenfoot.getRandomNumber(4);
                // Will ensure there is no power up already activated and if the chance is met.
                //System.out.println("spawnChance/n: " +spawnChance +"spawnType: " +spawnType);                         // check both types in seperate lines with /n
                // can change to x,y staitc vairable declared above (Change)
                switch(spawnType)
                {
                    case 0:  getWorld().addObject(new hpUp(), getX(), getY());
                    break;
                    case 1:  getWorld().addObject(new fastBullets(),getX(), getY());
                    break;
                    case 2:  getWorld().addObject(new rapidFire(), getX(), getY());
                    break;
                    case 3:  getWorld().addObject(new SpeedBoost(), getX(), getY());
                    break;
                }
                getWorld().removeObject(enemy);
                getWorld().removeObject(this);
            }
        }
    }

    private void fall()
    {
        setLocation(getX(),getY()+ySpeed);
        ySpeed += acceleration;
    }

    private boolean onGround()
    {
        Actor floor = getOneObjectAtOffset(0,getImage().getHeight()/2+1, ground.class);
        return floor != null;
    }

    private boolean onRoof()
    {
        Actor roof = getOneObjectAtOffset(0,-getImage().getHeight()/2-1, ground.class);
        return roof != null;
    }

}
