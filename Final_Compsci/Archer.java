import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Archer towers shoot arrows that kill enemies
 * 
 * @Andrew Wang
 * @June 17,2021
 */
public class Archer extends Towers
{
    static int totalMoney;
    static int RELOAD = 50;
    int reload = 0;
    int range = 150;
    private GreenfootSound[] shootSounds;
    private int shootSoundsIndex;
    public Archer()
    {
        shootSoundsIndex = 0;
        shootSounds = new GreenfootSound[10];
        for(int i = 0 ; i < shootSounds.length; i++)
        {
            shootSounds[i] = new GreenfootSound("BOW_HIT.wav");
            shootSounds[i].setVolume(50);
        }
    }
    /**
     * Act - do whatever the Archer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        sell();
        reload -= 1;
        
        if(reload < 0) {
            reload = RELOAD;
            shoot();
        }
    }    
    /**
     * Shoots a projectile at targetted enemy
     */
    private void shoot() {
        EnemyContainer enemies = ((MyWorld)getWorld()).getEnemyContainer();
        
        Enemy target = enemies.getFurthestEnemyWithinRange(getX(), getY(), range);
        
        if(target == null) return;
        
        double angle = getAngle(getX(), target.getX(), getY(), target.getY());
        
        getWorld().addObject(new Arrow(angle), getX(), getY());
        shootSounds[shootSoundsIndex].play();
        shootSoundsIndex++;
        if(shootSoundsIndex >= shootSounds.length)
        {
            shootSoundsIndex = 0;
        }
    }
    /**
     * Returns float of angle that is needed to shoot projectile at the enemy
     * @int originX
     * @int destinationX
     * @int originY
     * @int destinationY
     */
    private static float getAngle(int originX, int destinationX, int originY, int destinationY) {
        return (float) Math.atan2((destinationY - originY), (destinationX - originX));
    }
}
