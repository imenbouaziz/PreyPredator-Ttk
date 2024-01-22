import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Map;

import turtlekit.kernel.Turtle;
import turtlekit.kernel.Patch;
import turtlekit.kernel.TurtleKit.Option;
import turtlekit.viewer.PopulationCharter;
import turtlekit.viewer.TKDefaultViewer;

public class Predator extends Turtle {

    private Prey target;
    private int visionRadius = 10;
 // Region bounds variables for each region
    private double xMin1, xMax1, yMin1, yMax1;
    private double xMin2, xMax2, yMin2, yMax2;
    private double xMin3, xMax3, yMin3, yMax3;
    private double xMin4, xMax4, yMin4, yMax4;
    private Set<Predator> coalition;
    
  
    
    private Map<Patch, Integer> traceStrength = new HashMap<>();


    @Override
    protected void activate() {
    	// Set bounds for each region
        setRegionBounds();
        super.activate();
        //setXY(25.0,125.0);
        playRole("predator");
        setNextAction("searchPrey");
        setRegionColor();
        spawnPredators();
        initializeTraceStrength();
        randomHeading();
    }
    
    
    private void constrainedMove() {
        double angle = Math.toRadians(getHeading());
        double x = getX() + Math.cos(angle);
        double y = getY() + Math.sin(angle);

        // Move in the same direction and slightly diagonally
        moveTo(x, y);
    }
    
    private void setRegionBounds() {
        
        xMin1 = 5.0;
        xMax1 = 90.0;
        yMin1 = 5.0;
        yMax1 = 90.0;
        
        xMin2 = 5.0;
        xMax2 = 90.0;
        yMin2 = 110.0;
        yMax2 = 190.0;
        
        xMin3 = 110.0;
        xMax3 = 190.0;
        yMin3 = 5.0;
        yMax3 = 90.0;
       
        xMin4 = 100.0;
        xMax4 = 190.0;
        yMin4 = 110.0;
        yMax4 = 190.0;
        

}
    
    private void setRegionColor() {
        if (isInRegion1()) {
            setColor(Color.red);
        } else if (isInRegion2()) {
            setColor(Color.blue);
        } else if (isInRegion3()) {
            setColor(Color.green);
        } else if (isInRegion4()) {
            setColor(Color.orange);
        }
    }
    
    
 // check if the predator is in each region
    private boolean isInRegion1() {
        return (getX() >= 5 && getX() <= 90 && getY() >= 5 && getY() <= 90);
        
    }

    private boolean isInRegion2() {
        return (getX() >= xMin2 && getX() <= xMax2 && getY() >= yMin2 && getY() <= yMax2);
    }

    private boolean isInRegion3() {
        return (getX() >= xMin3 && getX() <= xMax3 && getY() >= yMin3 && getY() <= yMax3);
    }

    private boolean isInRegion4() {
        return (getX() >= xMin4 && getX() <= xMax4 && getY() >= yMin4 && getY() <= yMax4);
    }
    
    
    
    
    public void move1() {
    	
        // Check bounds and turn if necessary based on the current region
    	if (getX() <= xMin1 || getX() >= xMax1 || getY() <= yMin1 || getY() >= yMax1) {
            setHeading(getHeading() - 45);
        
        }
        // Move forward
        wiggle(20);
    }
    public void move2() {
        // Check bounds and turn if necessary based on the current region
    	if (getX() <= xMin2 || getX() >= xMax2 || getY() <= yMin2 || getY() >= yMax2) {
            setHeading(getHeading() - 45);
        } 
        // Move forward
        wiggle(20);
    }
    
    public void move3() {
        // Check bounds and turn if necessary based on the current region
    	if (getX() <= xMin3 || getX() >= xMax3 || getY() <= yMin3 || getY() >= yMax3) {
            setHeading(getHeading() - 45);
    	}
        // Move forward
        wiggle(20);
    }
    public void move4() {
        // Check bounds and turn if necessary based on the current region
    	if (getX() <= xMin4 || getX() >= xMax4 || getY() <= yMin4 || getY() >= yMax4) {
            setHeading(getHeading() - 45);
        
        }
        // Move forward
        wiggle(20);
    }
    
    public void DivideToConquer() {
        
    	if(isInRegion1() ) {
    		move1();
    		
        	
    	}
    	else if(isInRegion2() )	{
    		move2();
        	
    	}
        	
    	else if(isInRegion3() )	{
    		move3();
        	
    	}
        	
    	else if(isInRegion4() )	{
    		move4();
    		
    	}
    	else {
    		setHeading(getHeading() - 45);
    		wiggle(20);
    	}}
    
    private boolean isInCoalition() {
        return coalition != null && !coalition.isEmpty();
    }
    
    private void spawnPredators() {
        int numberOfPredators = 5; 

        for (int i = 0; i < numberOfPredators; i++) {
            double x = getRandomDoubleBetween(xMin1, xMax1);
            double y = getRandomDoubleBetween(yMin1, yMax1);
            Predator predator = new Predator();
            predator.setXY(x, y);
            predator.activate();
        }

        for (int i = 0; i < numberOfPredators; i++) {
            double x = getRandomDoubleBetween(xMin2, xMax2);
            double y = getRandomDoubleBetween(yMin2, yMax2);
            Predator predator = new Predator();
            predator.setXY(x, y);
            predator.activate();
        }

        for (int i = 0; i < numberOfPredators; i++) {
            double x = getRandomDoubleBetween(xMin3, xMax3);
            double y = getRandomDoubleBetween(yMin3, yMax3);
            Predator predator = new Predator();
            predator.setXY(x, y);
            predator.activate();
        }

        for (int i = 0; i < numberOfPredators; i++) {
            double x = getRandomDoubleBetween(xMin4, xMax4);
            double y = getRandomDoubleBetween(yMin4, yMax4);
            Predator predator = new Predator();
            predator.setXY(x, y);
            predator.activate();
        }
    }

    private double getRandomDoubleBetween(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }
    
    
    private void checkForCoalition() {
        // Check for nearby predators to form a coalition
        Set<Predator> nearbyPredators = findNearbyPredators(visionRadius);
        
        if (nearbyPredators.size() == 5) {
            // Form a coalition only when there are exactly 5 nearby predators
            Set<Predator> coalitionCandidates = new HashSet<>();
            
            for (Predator neighbor : nearbyPredators) {
                if (!neighbor.isInCoalition() && !isInCoalition()) {
                    coalitionCandidates.add(neighbor);
                }
            }
            
            if (coalitionCandidates.size() == 5) {
                // Form the coalition only when there are exactly 5 candidates
                formCoalition(coalitionCandidates);
            }
        }
    }

    
    private Set<Predator> findNearbyPredators(double radius) {
        Set<Predator> nearbyPredators = new HashSet<>();
        for (Turtle turtle : getPatchOtherTurtles()) {
            if (turtle instanceof Predator && turtle != this && distance(turtle) <= radius) {
                nearbyPredators.add((Predator) turtle);
            }
        }
        return nearbyPredators;
    }
    
    private void formCoalition(Set<Predator> coalitionCandidates) {
        coalition = new HashSet<>(coalitionCandidates);
        
        // Set color to the smallest ID in the coalition
        int minID = findMinUniqueID(coalition);
        setColor(new Color(minID));
        
        // Set heading to the average heading of the coalition
        setHeading(getAverageHeading(coalition));
        
        
        wiggle(5);
    }

    
    private void moveInCoalition() {
                wiggle(20);  
    }

    private int findMinUniqueID(Set<Predator> predators) {
        int minID = Integer.MAX_VALUE;
        for (Predator predator : predators) {
            int predatorID = predator.hashCode(); 
            if (predatorID < minID) {
                minID = predatorID;
            }
        }
        return minID;
    }

    
    private double getAverageHeading(Set<Predator> predators) {
        double totalHeading = 0;
        for (Predator predator : predators) {
            totalHeading += predator.getHeading();
        }
        return totalHeading / predators.size();
    }
    
    
 // New method to initialize traceStrength for each patch
    private void initializeTraceStrength() {
        //for (Patch patch : getPatch().patchesArray()) {
          //  traceStrength.put(patch, 0);
        //}
    	
    	
    }
    
 

    // New method to increment trace strength for a specific patch
    private void incrementTraceStrength(Patch patch) {
        int currentStrength = traceStrength.getOrDefault(patch, 0);
        traceStrength.put(patch, currentStrength + 1);
    }
    
      
    
 

    public String searchPrey() {
        Prey prey = findPreyInVision();
        if (prey != null) {
            setTarget(prey);
            return "huntPrey";
        } else {
        	if (isInCoalition()) {
        		 moveInCoalition();
        	}
        	else {
        		checkForCoalition();
        		DivideToConquer();
        		followTrace();
        		
            	//constrainedMove();
        	}
        	
            return "searchPrey";
        }
    }

    public String huntPrey() {
        if (getTarget() != null && getTarget().isAlive()) {
            setHeadingTowards(getTarget());
            //constrainedMove();
            leaveTrace();
            DivideToConquer();
            //wiggle(20);

        } else {
            setTarget(null);
            return "searchPrey";
        }

        return "huntPrey";
    }
    
    private void leaveTrace() {
    	Patch currentPatch = getPatch();
        incrementTraceStrength(currentPatch);
    }

    private void followTrace() {
        // Determine if there is a trace and adjust behavior accordingly
        Patch currentPatch = getPatch();
        int traceStrength = getTraceStrength(currentPatch);

        if (traceStrength > 0) {
            
            wiggle(20); 
        }
    }
    
    private int getTraceStrength(Patch patch) {
        return traceStrength.getOrDefault(patch, 0);
    }

  

    private Prey findPreyInVision() {
        return getNearestTurtle(getVisionRadius(), Prey.class);
    }
    
   
    
    
    
   

    public int getVisionRadius() {
        return visionRadius;
    }

    public void setVisionRadius(int visionRadius) {
        this.visionRadius = visionRadius;
    }

    public Prey getTarget() {
        return target;
    }

    public void setTarget(Prey target) {
        this.target = target;
    }
    
    
    public static void main(String[] args) {
        executeThisTurtle(20,
                Option.turtles.toString(), Prey.class.getName() + ",3",
                Option.viewers.toString(), PopulationCharter.class.getName() + ";" + TKDefaultViewer.class.getName(),
                Option.startSimu.toString());
    }
 
}
