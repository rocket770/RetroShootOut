import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;
/**
 * Write a description of class MyWorld here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PVPArena extends World
{
    private String[] arena =
        {
            "                         ",
            "             ggggg       ",
            "gg             dd         ",
            "d                    gggg",
            "                       dd",
            "   g     g              d",
            "g  dgggggd               ",
            "d              ggg       ",
            "d               d    e   ",
            "d                        ",
            "dgg                gggggg",
            "dddggg                ddd",
            "d        g   gg         d",
            "  p     gd  gddgg        ",
            "        ddggdddddg       ",
            "ggggggggddddddddddggggggg",
            "ddddddddddddddddddddddddd",
            "ddddddddddddddddddddddddd"
        };

    private int x;
    private int y;
   // Sets vairable to control sound file through other classes or loop it
    GreenfootSound backgroundMusic = new GreenfootSound("MenuMusic.mp3");
    public PVPArena()
    {
        // Create a new world with 1000x700 cells with a cell size of 1x1 pixels.
        super(1000, 700, 1);
        setPaintOrder(Players.class, Bar.class, EnemyAI.class, PowersUps.class, ground.class, dirt.class);
        Bar Bar1 = new Bar("Player1", 1, 1);
        addObject(Bar1, 295,350);
        // Loops music to cycle when playing
        backgroundMusic.playLoop();
        Bar Bar2 = new Bar("Player2", 1, 1);
        addObject(Bar2, 845,320);
        enableEnemy();
        Players.gotPowerUp = false;
        drawMap();
        Greenfoot.start();
    }

    public void act()
    {
        spawnPowerUp(100);
        spawnEnemy();
    }

    private void enableEnemy()
    {
        if(SelectEnemy.enableAI)
        {
            // Spawns enemy object on initlization, called in the constructor
            Bar Bar3 = new Bar("Enemy ", 6, 6);
            addObject(Bar3, 500,320);
            addObject(new EnemyAI(), 250,250);
        }
    }

    // Method will randomly spawn a powerup with the given delay passed through the method
    private void spawnPowerUp(int ChanceOffset)
    {
        int spawnChance = Greenfoot.getRandomNumber(ChanceOffset);
        // vairable to chose power up spawn type, based on the different power ups avaible
        int spawnType = Greenfoot.getRandomNumber(4);

        // Will ensure there is no power up already activated and if the chance is met.
        //System.out.println("spawnChance/n: " +spawnChance +"spawnType: " +spawnType);                         // check both types in seperate lines with /n
        if (spawnChance == 1 && getObjects(PowersUps.class).isEmpty() && Players.gotPowerUp == false)
        {
            if (!getObjects(ground.class).isEmpty())    // Prevents runtime error when trying to fetch ground object; always true
            {
                int i = Greenfoot.getRandomNumber(900);
                int j = Greenfoot.getRandomNumber(600);
                Random r = new Random();
                Actor ground = (ground) getObjects(ground.class).get(0); // get reference to ground objects
                if (i !=  r.nextInt(ground.getX()) + 10 || i != r.nextInt(ground.getX()) - 10) x = i;
                if (j != r.nextInt(ground.getY()) + 10  ||j != r.nextInt(ground.getY()) - 10) y = j;    // Grabs random ground object in world and spwans power up at a +/- 10 offset
                switch(spawnType)
                {
                    case 0:  addObject(new hpUp(), x, y);
                    break;
                    case 1:  addObject(new fastBullets(), x, y);
                    break;
                    case 2:  addObject(new rapidFire(),x,y);
                    break;
                    case 3:  addObject(new SpeedBoost(), x, y );
                    break;
                }
            }
        }
    }

    private void spawnEnemy()
    {
        List<EnemyAI> group = getObjects(EnemyAI.class);    // Gets all enemy classes in the world
        //spawn enemy randomly after a while if its dead
        if(group.size() == 0 && SelectEnemy.enableAI)   // if there are no enemys and enemy spawing is enabled
        {
            //enemyableToSpawn = true;
            int spawnDelay = Greenfoot.getRandomNumber(1000);   // 60/1000 chance to spawn enemy every second (1000 per act cycle assuming 60fps)
            if (spawnDelay == 1)
            {
                addObject(new EnemyAI(), 260, 180); // Add enemy object and its corrersponding health bar
                Bar Bar3 = new Bar("Enemy ", 6, 6);
                addObject(Bar3, 500,320);
            }
        }

        //showText("enemy: " +enemyableToSpawn, 300,300);
    }

    private void drawMap()
    {
        for(int i=0; i < arena.length;i++){
            String mapLine = arena[i];
            for(int j = 0; j < mapLine.length(); j++){
                char mapChar = mapLine.charAt(j);
                // spaces each object when spawned. Equation must equal the overall environment/world size
                int y = i * 40 + 20;
                int x = j * 40 + 20;
                switch(mapChar){
                    // CASEWHERE defines each object based on the given character in the string array above.
                    case 'g':
                    addObject(new ground(),x ,y);
                    break;
                    case 'd':
                    addObject(new dirt(),x ,y);
                    break;
                    case 'e':
                    addObject(new Player2(),x ,y);
                    break;
                    case 'p':
                    addObject(new Player1(),x ,y);
                    break;

                }
            }
        }
    }
}
