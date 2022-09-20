import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.*;
/**
 * Building menu of the game. When the Building nodes is clicked on, spawns
 * the menu. Clicking on certain ones will build a certain tower in the position
 * 
 * @author Kelvin Ying
 * @version 2
 */
public class BuildingMenu extends Interface
{
    /**
     * Act - do whatever the BuildingMenu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage myImage;
    private String type;
    public BuildingMenu(String type)
    {
        if(type == "Ice")
        {

            myImage = new GreenfootImage("Ice Menu.png");
            myImage.scale(30,33);
            setImage(myImage);
            this.type = "I";
        }else if(type == "normal")
        {
            myImage = new GreenfootImage("Archer Menu.png");
            myImage.scale(30,33);
            setImage(myImage);
            this.type = "A";

        }else if(type == "Wiz")
        {
            myImage = new GreenfootImage("Wizard Menu.png");
            myImage.scale(30,33);
            setImage(myImage);
            this.type = "W";
        }else if(type == "S")
        {
            myImage = new GreenfootImage("SellButton.png");
            myImage.scale(30,33);
            setImage(myImage);
            this.type = "s";
        }
    }

    public void act() 
    {
        // Add your action code here.
        Place();
        
    }    
    /**
     *If clicked will place the different towers
     * 
     */
    public void Place()
    {
        if (Greenfoot.mouseClicked(this)&& this.type == "W"&&getWorld()!= null&& (((MyWorld)getWorld()).totalGold()) >=300){
            ((MyWorld)getWorld()).addGold(-300);
            getWorld().addObject(new Wizard(), getX()+30, getY());
            
        }else if(Greenfoot.mouseClicked(this)&& this.type == "I"&&getWorld()!= null&& (((MyWorld)getWorld()).totalGold()) >=150)
        {
            ((MyWorld)getWorld()).addGold(-150);
            getWorld().addObject(new Ice(), getX()-30, getY());
            
        }else if(Greenfoot.mouseClicked(this)&& this.type == "A"&&getWorld()!= null&& (((MyWorld)getWorld()).totalGold()) >=100)
        {
            ((MyWorld)getWorld()).addGold(-100);
            getWorld().addObject(new Archer(), getX(), getY()+30);
            
        }else if(Greenfoot.mouseClicked(this)&& this.type == "s"&&getWorld()!= null)
        {
            Towers t = (Towers)getOneObjectAtOffset(+50,0,Towers.class);
            if (t!=null)
            {
                for(Actor obje : getWorld().getObjectsAt(t.getX(),t.getY(),Buildingnodes.class))
                    {
                        Buildingnodes te=(Buildingnodes) obje;
                        if(te!= null)
                        {
                            te.appear();
                        }
                    }
                ((MyWorld)getWorld()).addGold(75);
                getWorld().removeObject(t);
                getWorld().removeObject(this);
            }
        }
    }

    
}
