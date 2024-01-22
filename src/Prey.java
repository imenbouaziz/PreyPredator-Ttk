import java.awt.Color;
import java.util.List;

import turtlekit.kernel.Turtle;
import turtlekit.kernel.TurtleKit.Option;
import turtlekit.viewer.PopulationCharter;
import turtlekit.viewer.TKDefaultViewer;

public class Prey extends Turtle {

    private int visionRadius = 10;
    private int fleeDistance = 0; // Distance to flee from colored prey
    private Color originalColor = Color.white; // Original color of the prey
    private Color fleeColor = Color.yellow; // Color when chased by a predator
    private int fleeDuration = 1; // Number of iterations to flee
    private int fleeCounter = 0; // Counter for flee duration

    @Override
    protected void activate() {
        super.activate();
        playRole("prey");
        randomHeading();
        randomLocation();
        setColor(originalColor);
        setNextAction("look");
    }
    public int countNearbyPredators() {
        int count = 0;
        List<Predator> predatorsHere = getPatch().getTurtles(Predator.class);

        for (Predator predator : predatorsHere) {
            // Check if the predator is following or is close to the prey
            if (predator.getTarget() == this || distance(predator) <= visionRadius) {
                count++;
            }
        }

        return count;
    }
 // State: Look
    public String look() {
    	if (!isAlive()) {
            if (logger != null) logger.info("killed by predators");
            return "die"; // die
        }

        int predatorsCount = countNearbyPredators();

        if (predatorsCount >= 4) {
            if (logger != null) logger.info("killed by predators");
            return "die"; // die
        }

        Predator nearestPredator = getNearestTurtle(visionRadius, Predator.class);
        if (nearestPredator != null && predatorsCount == 4) {
            setNextAction("die");
            return "die";}
        if (nearestPredator != null ) {
        	
        		setNextAction("flee");
                return "flee";
        
        	
            
        } else if (getColor().equals(fleeColor)) {
            // If the prey is colored, flee
            setNextAction("flee");
            return "flee";
        } else {
            // If not hunted or colored, check if it's time to go back to the original color
            if (fleeCounter > fleeDuration) {
                setColor(originalColor);
                fleeCounter = 0; // Reset counter
            } else {
                fleeCounter++;
            }

            setNextAction("move");
            return "move";
        }
    }

    // State: Move
    public String move() {
        wiggle(20);
        setNextAction("look");
        return "look";
    }

    // State: Flee
    public String flee() {
        Predator nearestPredator = getNearestTurtle(visionRadius, Predator.class);
        if (nearestPredator != null) {
            // Flee from the nearest predator
            setHeading(towards(nearestPredator) + 180);
            fd(fleeDistance);
            // Change color to indicate fleeing
            setColor(fleeColor);
        }
        
        move();
        
        
        setNextAction("look");
        return "look";
    }

    // State: Die
    public String die() {
        if (logger != null) logger.info("killed by predators");
        return null;
    }

    public static void main(String[] args) {
        executeThisTurtle(1,
                Option.turtles.toString(), Predator.class.getName() + ",5",
                Option.startSimu.toString(),
                Option.viewers.toString(), PopulationCharter.class.getName() + ";" + TKDefaultViewer.class.getName()
        );
    }
}
