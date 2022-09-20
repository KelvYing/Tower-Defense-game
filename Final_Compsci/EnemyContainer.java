import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 *This is used for the towers to get the first enemy in the world and target it
 *
 * 
 * @author Andrew Wang 
 * @version 2
 */
public class EnemyContainer extends Actor
{
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public void add(Enemy e) {
        enemies.add(e);
    }

    public void remove(Enemy e) {
        enemies.remove(e);
    }

    public Enemy getFurthestEnemyWithinRange(int x, int y, int range) {
        Enemy furthest = null;

        double max = Double.NEGATIVE_INFINITY;
        double minDist = range * range;

        for(int i = 0; i < enemies.size(); i++) {
            Enemy current = enemies.get(i);
            if(current.getWorld() != null)
            {
                double distance = distanceSquared(x, current.getX(), y ,current.getY());

                if(distance < minDist) {
                    if(current.getDistanceTravelled() > max) {
                        max = current.getDistanceTravelled();
                        furthest = current;
                    }
                }
            }

        }

        return furthest;
    }

    public Enemy getEnemyWithinRange(int x, int y, int range) {
        double minDist = range * range;

        for(int i = 0; i < enemies.size(); i++) {
            Enemy current = enemies.get(i);
            if(current != null && current.getWorld() != null)
            {
                double distance = distanceSquared(x, current.getX(), y ,current.getY());
                if(distance < minDist) {
                    return current;
                }
            }

        }

        return null;
    }

    private static double distanceSquared(int x1, int x2, int y1, int y2) {
        return (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
