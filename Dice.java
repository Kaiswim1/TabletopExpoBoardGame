import java.util.Random;
public class Dice {
    private int sides;
    private Random r;
    private int landedSide;
    public Dice(int sides){
        this.sides = sides;
        this.r = new Random();
        roll();
    }
    public int roll(){
        this.landedSide =  r.nextInt(1, sides);
        return landedSide;
    }

}
