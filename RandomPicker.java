import java.text.DecimalFormat;

//class with many static methods for all random events in the game
public class RandomPicker {

    //gets the success of a given rally given the player and the state
    //2- Highly successful
    //1- Moderate success
    //0- DISASTER
    public static int getRallySuccess(String state, JoeBiden player){
        
        double score = 100;
        if (Math.abs(Polls.getSupport(state)) < 5){
            score -= 50;
        }
        else if (Math.abs(Polls.getSupport(state)) < 15){
            score -= 30;
        }
        else{
            score -= 10;
        }

        score -= 20.0 * (player.rally_skill/100.0);

        int chosen = (int) (Math.random() * 100) + 1;
        if (score < chosen){
            return 2;
        }
        else if (score - 20 < chosen){
            return 1;
        }
        else{
            return 0;
        }
    }

    //picks a random number between 2 and 10
    public static int getRallyXP(){
        return (int) ((Math.random() * 9) + 2);
    }

    //modifies polling for a given state by a random value between the minimum and maximum
    //returns the new number for the state
    public static double changePolling(String state, double min, double max){
        double change = (Math.random() * (max - min)) + min;
        double newOne = Polls.getSupport(state) + change;
        Polls.setSupport(state, newOne);
        return newOne;
    }

    //generate a random amount of money for the campaign to receive
    public static double getMoney(){
        DecimalFormat formatter = new DecimalFormat();
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        String rounded = formatter.format(((Math.random() * 1000) + 1000));
        return Double.parseDouble(rounded.replace(",", ""));
    }

    //returns a random integer in a range, inclusive
    //CHECK IF NEEDED
    public static int randIntInclusive(int min, int max){
        return (int) (Math.random() * (max + 1 - min)) + min;
    }

    //returns a randomized action for Trump to take in a debate
    public static String generateTrumpDebateAction(){
        double selection = (Math.random() * 100);
        if (selection > 90){
            return "ability";
        }
        if (selection > 35){
            return "atk";
        }
        return "def";
    }


}

