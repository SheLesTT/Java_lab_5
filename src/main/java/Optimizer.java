import java.util.HashMap;

/**
 * Class that remember the behavior of a player and helps to predict his next move
 */
public class Optimizer {

    HashMap<String, Integer> move_stats = CreateStats();

    public void setLast_move(int last_move) {
        this.last_move = last_move;
    }

    public int last_move =-1;

    /**
     * Creates a hashMap with info how many times each combination of  attack and defence (10 11 00 01) was played
     * @return
     */
    private HashMap<String, Integer> CreateStats(){
        HashMap<String, Integer> move_stats= new HashMap<>();
        move_stats.put("00",0);
        move_stats.put("01", 0);
        move_stats.put("10", 0);
        move_stats.put("11", 0);
        return move_stats;
    }

    /**
     * Adding info to a hashmap with move count
     * @param a
     */
    public  void AddMoveStats( int a ){
        if(last_move !=-1) {
            String variant = Integer.toString(last_move) + Integer.toString(a);
            move_stats.put(variant, move_stats.get(variant) + 1);

        }
    }

    /**
     * Counts how many data points are in hashmap
     * @return
     */
    private int CountDataPoints(){
        int points= 0;
        for (int value : move_stats.values()) {
            points += value;
        }
//        System.out.println(points + "total data boints");
        return points;
    }

    /**
     * decides should enemy attack base on predicted human next move
     * @return
     */
    private int PredictMove(){
        if(last_move == 1){
            if(move_stats.get("11") >= move_stats.get("10")){
                return 0;
            }
            else{ return 1;}

        }
        if(last_move ==0){
            if(move_stats.get("01")>= move_stats.get("00")){
                return 0;
            }
            else {return 1;}
        }
        return 1;
    }

    /**
     * decides should wee change standard move for optimized one
     * @param default_move
     * @return
     */
    public int OptimizeAttack(int default_move){
        int predicted_move = PredictMove();
        int data_points = move_stats.size();
        double i = Math.random();
//        System.out.println(i);
        if(i > (double) CountDataPoints()/20){
            return default_move;
        }
        else {
//            System.out.println("not a standart move");
            return predicted_move;}

    }
}
