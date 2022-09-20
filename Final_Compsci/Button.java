import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Button extends Actor
{
    public static final double FONT_RATIO = 0.58;

    private boolean touching;

    private String myText;

    private int drawX;
    private int drawY;

    private GreenfootImage image;
    private GreenfootImage touchingImage; 

    // Set up a 24-pt, bold Courier New font for drawing text onto my Button
    private Font buttonFont = new Font("Century Gothic",  true,  false,  24);

    public Button (String text) {
        image = new GreenfootImage (192, 39);
        touchingImage = new GreenfootImage (192, 39);
        myText = text;
        // Prepare for centering:
        // How many letters?
        // int wordLength = myText.length();  
        // How many pixels?
        int wordWidth = getStringWidth(buttonFont, text); 
        drawX = (image.getWidth() - wordWidth)/2;
        int textHeight = buttonFont.getSize()/2;
        int difference = image.getHeight() - textHeight;

        drawY = textHeight + (difference/2) + 2;
        //System.out.println("ww: " + wordWidth + " drawX: " + drawX);

        draw();
        setImage (image);
    }

    public void act() 
    {
        if(Greenfoot.mouseClicked(this)&&myText == "Start Game")
        {
            ((StartWorld)getWorld()).stopped();
            MyWorld w = new MyWorld();
            Greenfoot.setWorld(w);
        } else if(Greenfoot.mouseClicked(this)&&myText == "Instructions")
        {
            Instructions i = new Instructions();
            Greenfoot.setWorld(i);
        }else if(Greenfoot.mouseClicked(this)&&myText == "Back to Menu")
        {
            (getWorld()).stopped();
            StartWorld l = new StartWorld();
            Greenfoot.setWorld(l);
        }

    }    

    public boolean touchingPlayer(){
        return touching;
    }

    private void draw () {

        image.setColor(Color.WHITE);
        image.fill();
        image.setColor(Color.BLACK);
        image.setFont (buttonFont);
        image.drawString (myText, drawX, drawY);

        image.setColor(Color.BLUE);

        /** Troubleshooting Code
        int yDraw = 9;
        while (yDraw < image.getHeight()){
        image.drawLine(0, yDraw, image.getWidth(), yDraw);
        yDraw += 10;            
        }
         */

        touchingImage.setColor(Color.YELLOW);
        touchingImage.fill();
        touchingImage.setColor(Color.BLACK);
        touchingImage.setFont (buttonFont);
        touchingImage.drawString (myText, drawX, drawY);
    }

    /**
     * <h3>Finally, draw centered text in Greenfoot!</h3>
     * <p>
     * <b>IMPORTANT:</b> Set your Font in your GreenfootImage before you send it here.
     * </p>
     * <p>Use this instead of Greenfoot.drawString to center your text, or just call getStringWidth
     *    directly and draw it yourself if you prefer the control over the ease of use.</p>
     * 
     * @param canvas    The GreenfootImage that you want to draw onto, often the background of a World, but
     *                  could also be an Actor's image or any other image.
     * @param text      The text to be drawn.
     * @param middleX   the x Coordinate that the text should be centered on
     * @param bottomY   the y Coordinate at the baseline of the text (similar to GreenfootImage.drawString)
     * 
     * @since June 2021
     */
    public static void drawCenteredText (GreenfootImage canvas, String text, int middleX, int bottomY){
        canvas.drawString (text, middleX - (getStringWidth(canvas.getFont(), text)/2), bottomY);
    }

    /**
     * <p>
     * <b>IMPORTANT:</b> Set your Font in your GreenfootImage before you send it here.
     * </p>
     * <p>Similar to the method above, except it always centers the text on the whole image
     *    instead of a specified x position. UNTESTED!</p>
     * 
     * @param canvas    The GreenfootImage that you want to draw onto, often the background of a World, but
     *                  could also be an Actor's image or any other image.
     * @param text      The text to be drawn.
     * @param bottomY   the y Coordinate at the baseline of the text (similar to GreenfootImage.drawString)
     * 
     * @since June 2021
     */
    public static void drawCenteredText (GreenfootImage canvas, String text, int bottomY){
        canvas.drawString (text, canvas.getWidth()/2 - (getStringWidth(canvas.getFont(), text)/2), bottomY);
    }

    /**
     * Get the Width of a String, if it was printed out using the drawString command in a particular
     * Font.
     * 
     * This is not a cheap method, and should not be called from an act method. It is appropriate
     * to call this in the constructor.
     * 
     * In advanced cases, you may want to cache the results during a loading method. You could also
     * call it manually while coding, not the results, and use literal values to avoid having this
     * code called at all.
     * 
     * @param font the GreenFoot.Font which is being used to draw text
     * @param text the actual text to be drawn
     * 
     * @since June 2021
     */
    public static int getStringWidth (Font font, String text){

        // how far past the last sighted text to keep looking
        final int END_MARGIN = 100; 

        // largest font size
        final int MAX_FONT_SIZE = 300;

        // you can make this higher if your world is bigger
        final int MAX_WIDTH = 1000; 

        int fontSize = font.getSize();
        GreenfootImage temp = new GreenfootImage (MAX_WIDTH, MAX_FONT_SIZE);
        temp.setFont(font);
        temp.drawString (text, 0, fontSize);

        int checkX = 0;
        int lastFound = 0;

        //int testValue = 1000;
        boolean running = true;

        int marginOfError = 3; // how many pixels can be skipped scanning vertically for pixels?
        if (fontSize < 18){
            marginOfError = 2;
        }
        while (running){ 
            // new row
            boolean found = false; 
            if (temp != null){
                for (int i = 0; i < fontSize && !found; i+=marginOfError){
                    
                    // This method has a high cost on Greenfoot Gallery... Maybe use this and then precache values 
                    // and never call this method live. 
                    Color c = temp.getColorAt(checkX, i);

                     if (!( c.getAlpha() == 0 && c.getRed() == 0 && c.getBlue() == 0 && c.getGreen() == 0 )){
                        found = true;
                        lastFound = checkX;
                    }
                }
            }
            checkX++;
            if (checkX - lastFound > END_MARGIN){ // if I have run for a certain amount and not found any new               
                running = false;                  // pixels, I'm done.
            }
        }
        return lastFound;
    }

}