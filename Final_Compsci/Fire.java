import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *Main projectile for the wizard class. Does 2 damage
 * @author Andrew Wang
 * @version 2
 */
public class Fire extends Projectile
{
    double speed = 5;

    double angleX, angleY;
    double xLoc, yLoc;

    int damage = 100;
    int range = 10;
    public Fire()
    {

    }

    public Fire(double angle) {
        this.angleX = Math.cos(angle) * speed;
        this.angleY = Math.sin(angle) * speed;
        setRotation((int)(angle * 180 / Math.PI));
    }

    public void addedToWorld(World w) {
        GreenfootImage image = getImage();
        image.scale(20, 15);
        this.xLoc = getX();
        this.yLoc = getY();
    }

    /**
     * Act - do whatever the Projectile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        xLoc += angleX;
        yLoc += angleY;

        setLocation((int)xLoc, (int)yLoc);

        Enemy inRange = ((MyWorld)(getWorld())).getEnemyContainer().getEnemyWithinRange(getX(), getY(), range);

        if(inRange != null&& this.getWorld()!= null) {
            inRange.damage(damage);
            getWorld().removeObject(this);
        }
        if(this.getWorld()!= null)
        {
            if((isAtEdge())&& this.getWorld()!= null)
            {
                getWorld().removeObject(this);
            }
        }

    }
}
