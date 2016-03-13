package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.*;

/**
 * Created by ChenLei on 2016/3/13.
 */
public class Clorus extends Creature{
    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;

    /** creates Clorus with energy equal to E. */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /** creates a Clorus with energy equal to 1. */
    public Clorus() {
        this(1);
    }

    /** Should return a color with red = 99, blue = 76, and green that varies
     *  linearly based on the energy of the Clorus. If the Clorus has zero energy,
     *  it should have a green value of 63. If it has max energy, it should
     *  have a green value of 255. The green value should vary with energy
     *  linearly in between these two extremes. It's not absolutely vital
     *  that you get this exactly correct.
     */
    public Color color() {
        return color(34, 0, 231);
    }

    /**if a Clorus attacks another creature, it should gain that creature's energy.*/
    public void attack(Creature c) {
        this.energy = energy + c.energy();
    }

    /** Cloruss should lose 0.03 units of energy when moving. If you want to
     *  to avoid the magic number warning, you'll need to make a
     *  private static final variable. This is not required for this lab.
     */
    public void move() {
        energy = energy - 0.03;
    }


    /** Cloruss lose 0.01 energy when staying due to photosynthesis. */
    public void stay() {
        energy = energy - 0.01;
    }

    /** Cloruss and their offspring each get 50% of the energy, with none
     *  lost to the process. Now that's efficiency! Returns a baby
     *  Clorus.
     */
    public Clorus replicate() {
        this.energy = energy / 2.0;
        return new Clorus(energy);
    }

    /** Cloruss take exactly the following actions based on NEIGHBORS:
     *  1. If no empty adjacent spaces, STAY.
     *  2. Otherwise, if energy >= 1, REPLICATE.
     *  3. Otherwise, if any Cloruses, MOVE with 50% probability.
     *  4. Otherwise, if nothing else, STAY
     *
     *  Returns an object of type Action. See Action.java for the
     *  scoop on how Actions work. See SampleCreature.chooseAction()
     *  for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        //System.out.print("I got called:Clorus.java:86" + "\n");
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        //rule 1
        if (empties.size() == 0) {
            //System.out.print("I got called:Clorus.java:104" + "\n");
            return new Action(Action.ActionType.STAY);
        }
        //rule 2
        //List<Direction> impassibles = getNeighborsOfType(neighbors, "impassible");
        List<Direction> plipsList = new ArrayList<>();
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            //System.out.print(d.name());
            String occupantName = entry.getValue().name();
            if (occupantName.equals("plip")) {
                plipsList.add(entry.getKey());
            }
        }
        if (plipsList.size() > 0) {
            Direction moveDir = HugLifeUtils.randomEntry(plipsList);
            return new Action(Action.ActionType.ATTACK, moveDir);
        }
        //rule 3
        if (energy >= 1) {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, moveDir);
        }
        //rule 4
        Direction moveDir = HugLifeUtils.randomEntry(empties);
        return new Action(Action.ActionType.MOVE, moveDir);
    }

}
