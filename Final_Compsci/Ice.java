import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ice here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ice extends Towers
{
    static int totalMoney;
    static int RELOAD = 50;
    int reload = 0;
    int range = 150;
    /**
     * Act - do whatever the Ice wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootSound[] shootSounds;
    private int shootSoundsIndex;
    public Ice()
    {
        shootSoundsIndex = 0;
        shootSounds = new GreenfootSound[10];
        for(int i = 0 ; i < shootSounds.length; i++)
        {
            shootSounds[i] = new GreenfootSound("Iceeffect.wav");
            shootSounds[i].setVolume(50);
        }
    }

    public void act() 
    {
        // Add your action code here.
        reload -= 1;

        if(reload < 0) {
            reload = RELOAD;
            shoot();
        }
        sell();
    }    

    private void shoot() {
        EnemyContainer enemies = ((MyWorld)getWorld()).getEnemyContainer();

        Enemy target = enemies.getFurthestEnemyWithinRange(getX(), getY(), range);

        if(target == null) return;

        double angle = getAngle(getX(), target.getX(), getY(), target.getY());

        getWorld().addObject(new Icicle(angle), getX(), getY());
        shootSounds[shootSoundsIndex].play();
        shootSoundsIndex++;
        if(shootSoundsIndex >= shootSounds.length)
        {
            shootSoundsIndex = 0;
        }
    }

    private static float getAngle(int originX, int destinationX, int originY, int destinationY) {
        return (float) Math.atan2((destinationY - originY), (destinationX - originX));
    }
}
