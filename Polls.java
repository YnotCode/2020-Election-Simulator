import java.text.DecimalFormat;
import java.util.HashMap;

public class Polls {
    //HashMap is used to map eacb state String to its corresponding polling value as a double
    private static HashMap<String, Double> polling;

    //String array is used to hold the two letter abbreviations for all the states + D.C.
    private static String[] stateList;

    //HashMap is used to map each state String to its corresponding number of votes in the Electoral College
    private static HashMap<String, Integer> ecVotes;

    //helper method to put all the starting values for state polls in the HashMap
    private static void initStatePolling(){
        polling.put("AL", -18.3);
        polling.put("AK", -5.5);
        polling.put("AZ", 3.5);
        polling.put("AR", -2.5);
        polling.put("CA", 32.0);
        polling.put("CO", 13.3);
        polling.put("CT", 21.5);
        polling.put("DE", 19.0);
        polling.put("DC", 75.0);
        polling.put("FL", 5.3);
        polling.put("GA", -0.9);
        polling.put("HI", 26.4);
        polling.put("ID", -24.7);
        polling.put("IL", 13.1);
        polling.put("IN", -14.3);
        polling.put("IA", -1.4);
        polling.put("KS", -9.1);
        polling.put("KY", -16.1);
        polling.put("LA", -11.9);
        polling.put("ME", 10.4);
        polling.put("MD", 26.6);
        polling.put("MA", 32.9);
        polling.put("MI", 7.4);
        polling.put("MN", 5.5);
        polling.put("MS", -12.5);
        polling.put("MO", -5.5);
        polling.put("MT", -8.9);
        polling.put("NE", -12.1);
        polling.put("NV", 6.7);
        polling.put("NH", 9.3);
        polling.put("NJ", 20.1);
        polling.put("NM", 11.9);
        polling.put("NY", 25.5);
        polling.put("NC", 1.3);
        polling.put("ND", -14.4);
        polling.put("OH", 0.5);
        polling.put("OK", -23.4);
        polling.put("OR", 12.0);
        polling.put("PA", 6.5);
        polling.put("RI", 22.2);
        polling.put("SC", -6.5);
        polling.put("SD", 20.3);
        polling.put("TN", -11.6);
        polling.put("TX", -2.0);
        polling.put("UT", -11.1);
        polling.put("VT", 23.3);
        polling.put("VA", 10.8);
        polling.put("WA", 27.0);
        polling.put("WV", -26.9);
        polling.put("WI", 6.7);
        polling.put("WY", -39.5);
    }
    
    private static void initEcVotes(){
        ecVotes.put("AL", 9);
        ecVotes.put("AK", 3);
        ecVotes.put("AZ", 11);
        ecVotes.put("AR", 6);
        ecVotes.put("CA", 55);
        ecVotes.put("CO", 9);
        ecVotes.put("CT", 7);
        ecVotes.put("DE", 3);
        ecVotes.put("DC", 3);
        ecVotes.put("FL", 29);
        ecVotes.put("GA", 16);
        ecVotes.put("HI", 4);
        ecVotes.put("ID", 4);
        ecVotes.put("IL", 20);
        ecVotes.put("IN", 11);
        ecVotes.put("IA", 6);
        ecVotes.put("KS", 6);
        ecVotes.put("KY", 8);
        ecVotes.put("LA", 8);
        ecVotes.put("ME", 4);
        ecVotes.put("MD", 10);
        ecVotes.put("MA", 11);
        ecVotes.put("MI", 16);
        ecVotes.put("MN", 10);
        ecVotes.put("MS", 6);
        ecVotes.put("MO", 10);
        ecVotes.put("MT", 3);
        ecVotes.put("NE", 5);
        ecVotes.put("NV", 6);
        ecVotes.put("NH", 4);
        ecVotes.put("NJ", 14);
        ecVotes.put("NM", 5);
        ecVotes.put("NY", 29);
        ecVotes.put("NC", 15);
        ecVotes.put("ND", 3);
        ecVotes.put("OH", 18);
        ecVotes.put("OK", 7);
        ecVotes.put("OR", 7);
        ecVotes.put("PA", 20);
        ecVotes.put("RI", 4);
        ecVotes.put("SC", 9);
        ecVotes.put("SD", 3);
        ecVotes.put("TN", 11);
        ecVotes.put("TX", 38);
        ecVotes.put("UT", 6);
        ecVotes.put("VT", 3);
        ecVotes.put("VA", 13);
        ecVotes.put("WA", 12);
        ecVotes.put("WV", 5);
        ecVotes.put("WI", 10);
        ecVotes.put("WY", 3);
    }


    //constructor, takes no parameters and initializes both polling and stateList with the correct starting values
    public Polls(){
        polling = new HashMap<>();
        ecVotes = new HashMap<>();
        stateList = new String[]{
            "AL",
            "AK",
            "AZ",
            "AR",
            "CA",
            "CO",
            "CT",
            "DE",
            "DC",
            "FL",
            "GA",
            "HI",
            "ID",
            "IL",
            "IN",
            "IA",
            "KS",
            "KY",
            "LA",
            "ME",
            "MD",
            "MA",
            "MI",
            "MN",
            "MS",
            "MO",
            "MT",
            "NE",
            "NV",
            "NH",
            "NJ",
            "NM",
            "NY",
            "NC",
            "ND",
            "OH",
            "OK",
            "OR",
            "PA",
            "RI",
            "SC",
            "SD",
            "TN",
            "TX",
            "UT",
            "VT",
            "VA",
            "WA",
            "WV",
            "WI",
            "WY"
        };
        initStatePolling();
        initEcVotes();
    }

    //returns all the current polls for every state in a grid format
    public String toString(){
        String output = "\n";
        int counter = 0;
        DecimalFormat formatter = new DecimalFormat();
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        for (int i = 0; i < stateList.length; i++){
            String state = stateList[i];
            String percentage = formatter.format(polling.get(state)) + "%";
            if (polling.get(state) > 0){
                percentage = "+" + formatter.format(polling.get(state)) + "%";
            }
            output += state + ": " + percentage + "\t";
            counter++;
            if (counter == 5){
                output += "\n";
                counter = 0;
            }
        }   
        return output;
    }

    //getter method for the state list
    public static String[] getAllStates(){
        return stateList;
    }

    //gets the polling for a given state
    public static double getSupport(String state){
        return polling.get(state);
    }

    //sets the support for a given state to a given value
    public static void setSupport(String state, double value){
        polling.put(state, value);
    }

    //returns whether Biden won the election based on the values in the polling HashMap and the electoral college
    public static boolean wonElection(){
        int total = 0;
        for (int i = 0; i < stateList.length; i++){
            String state = stateList[i];
            if (polling.get(state) > 0){
                total += ecVotes.get(state);
            }
        }

        return (total >= 270);
    }

    //prints out the final electoral map and the electoral votes for each candidate
    public static String getResults(){
        String output = "Electoral Map:\n";
        output += "Joe Biden (D)\nDonald Trump (R)\n";
        int counter = 0;
        int joeTotal = 0;
        int trumpTotal = 0;
        for (int i = 0; i < stateList.length; i++){
            String state = stateList[i];
            if (polling.get(state) > 0){
                output += state + ": D\t";
                joeTotal += ecVotes.get(state);
            }
            else{
                output += state + ": R\t";
                trumpTotal += ecVotes.get(state);
            }
            counter++;
            if (counter == 5){
                output += "\n";
                counter = 0;
            }
        }   
        output += "\nJoe Biden Total Electoral College Votes: " + joeTotal + "\t(needed 270 to win)";
        output += "\nDonald Trump Total Electoral College Votes: " + trumpTotal + "\t(needed 270 to win)";
        return output;
    }
}
