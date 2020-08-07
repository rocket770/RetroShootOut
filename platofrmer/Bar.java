import greenfoot.*;

public class Bar extends Actor
{
    private final int bar_width = 100; // the color portion of the bar (can never be modified once in world)
    private final int bar_height = 10; // the color portion of the bar
    private final int break_percent = 20; // the percentage amount that changes the color of the bar
    private final boolean break_low = true; // true: with low-percent values bar is red, else green; false: vise versa
    public int value = 0; // updated value of bar
    private int lastValue = 0; // previously recorded value of bar
    private int maxValue = 0; // the maximum value of bar
    public String refName = ""; // the title string (who or what the meter/bar is for)

    public Bar(String referenceName, int initValue, int maximumValue)
    {
        // I realize i can use less varables whilst delcaring each passed through the constuctur with "this.referenceName"
        refName = referenceName;
        maxValue = maximumValue; //The highest the bar amount the bar will go
        add(initValue); //Sets initial value of health,
    }
    
      public void act()
    {
        switch(refName)
        {
            case "Player1": setLocation(Player1.x-5, Player1.y-50);  break;  // Teleports bar to correct object it is displaiyng forcase "Player1": setLocation(Player1.x+-5, Player1.y-50);  break;  // Teleports bar to correct object it is displaiyng for
            case "Player2": setLocation(Player2.x-5, Player2.y-50); break;   // Teleports bar to correct object it is displaiyng for
            case "Enemy ":  setLocation(EnemyAI.x-5, EnemyAI.y-50); break;
        }
    }

    private void newImage()
    {
        int imgWidth = bar_width + 10 * (refName.length() + 2); // Creates an image with the size of the given width, accounting for the text it will display at the end
        int barValue = (int) (bar_width * value / maxValue);    // gets % value of health compared to max health (converted/casted from a float to an int)
        GreenfootImage image = new GreenfootImage(imgWidth, 20);
        switch(refName)
        {
            case "Player1": image.setColor(Color.RED); break;   //Sets didfferent colors based on the object it is representing/displaying for
            case "Player2": image.setColor(Color.BLUE); break;
            case "Enemy ":  image.setColor(Color.YELLOW); break;
        }
        image.drawString(refName, 5, 14);   //draws what object the health bar is representing in text next to the actual bar
        image.drawString("" + value + " " , refName.length() * 10 + bar_width, 14); // Draws the the value of the bar is to the right of the bar
        image.drawRect(refName.length() * 8 - 2, (int) (8 - bar_height / 2), bar_width + 3, bar_height + 3);    //Draws shape of the bar, a rectangle based on given dimesnions
        if (value > 0)
        {
            if (break_low)
            {
                if (value > (int) (break_percent * maxValue / 100)) image.setColor(Color.GREEN);
                else image.setColor(Color.RED);
            }
            else
            {
                if (value < (int) (break_percent * maxValue / 100)) image.setColor(Color.GREEN);
                else image.setColor(Color.RED);
            }
            image.fillRect(refName.length() * 8, (int) (10 - bar_height / 2), barValue, bar_height);
        }
        setImage(image);
    }

    public void add(int amount)
    {
        value += amount;    //adds amount passed through to the value
        checkValue();
        newImage(); //resets bar image with new added value
    }

    public void subtract(int amount)
    {
        value -= amount;    //Subtracts amount passed through to the value
        checkValue();
        newImage();     //resets bar image with new subtracted value
    }

    private void checkValue()
    {
        if (value < 0)
        {
            value = 0;  //Insures health bar value will never go below 0
        }
        if (value > maxValue)
        {
            value = maxValue; // Inusred health bar value will never go above its maxvalue, in this case 10
        }
    }

  

    public int getValue()
    {
        return value;   // Creates a function so other classes can get each objects value
    }
}
