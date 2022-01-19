import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//class to manage the Debate battles for the game
public class Debate {

    //instance variables
    JoeBiden player;
    DonaldTrump trump;
    int debateId;
    FancyPrinter printer;
    Scanner input;
    
    //constructor to initialize the player and trump objects and set the number of the debate (1 or 2)
    public Debate(JoeBiden playerObj, DonaldTrump trumpObj, int debateNum, FancyPrinter printer){
        this.player = playerObj;
        this.trump = trumpObj;
        this.debateId = debateNum;
        this.printer = printer;
        input = new Scanner(System.in);
    }

    //print a formatted quote for when anyone talks in the debate
    public void printQuote(String name, String msg){
        System.out.print(name + ":\n");
        printer.println(msg);
    }

    //print the starting text from the host for debate 1
    public void startDebate1(){
        this.printQuote("CHRIS WALLACE", "Welcome to the first presidential debate between President Donald J. Trump and Former Vice President Joe Biden.");
        this.printQuote("CHRIS WALLACE", "Gentlemen, a lot of people have been waiting for this night, so let's get going.");
    }

    //print the starting text from the host for debate 2
    public void startDebate2(){
        this.printQuote("KRISTEN WALKER", "Welcome to the second and final presidential debate for the 2020 presidential election.");
        this.printQuote("KRISTEN WALKER", "Our topics will be IMMIGRATION, HEALTH CARE, and THE SUPREME COURT. Ready gentlemen?");
        this.printQuote("DONALD TRUMP", "I'm always ready");
        this.printQuote("JOE BIDEN", "No malarkey over here, I'm ready.");
    }

    //starts the debate
    public void start(){

        //reset both player and trump hps to 100, also reset ability usage
        player.debate_hp = 100;
        trump.debate_hp = 100;
        player.usedAbility = false;

        //create a new 2d array for the topics and their corresponding keys for use by Character extensions
        //print the starting text for the debate
        String[][] topics = new String[2][2];
        if (debateId == 1){
            this.startDebate1();
            topics = new String[][]{{"COVID-19", "cvd"}, {"FOREIGN POLICY", "fp"}, {"THE ECONOMY", "econ"}};
        }
        else{
            this.startDebate2();
            topics = new String[][]{{"IMMIGRATION", "imm"}, {"HEALTHCARE", "hc"}, {"THE SUPREME COURT", "sc"}};
        }

        //shuffle the order of the topics
        List<String[]> topicsAsList = Arrays.asList(topics);
        Collections.shuffle(topicsAsList);
        //player turns remaining
        int turns = 1;

        //set the host name based on the debate
        String host = "KRISTEN WALKER";
        if (debateId == 1){
            host = "CHRIS WALLACE";
        }

        String[] topicIntros = {"Our first topic is...", "The next topic is...", "Our final topic for tonight is..."};
        for (int i = 0; i <  topicsAsList.size(); i++){

            this.printQuote(host, topicIntros[i] + topicsAsList.get(i)[0]);
            String currentTopic = topicsAsList.get(i)[1];
            for (int z = 0; z < turns; z++){

                //randomly select an action for Trump to take and print output accordingly
                String trumpAction = RandomPicker.generateTrumpDebateAction();
                if (trumpAction.equals("ability")){
                    this.printQuote("DONALD TRUMP", trump.ability());
                }
                else{
                    this.printQuote("DONALD TRUMP", trump.getDebateLine(trumpAction + " " + currentTopic));
                    if (trumpAction.equals("atk")){
                        int dmg = (int) ((20 * (trump.debate_atk_skill/100.0)) - (10 * (player.debate_def_skill/100.0)));
                        if (trump.boosted){
                            dmg = (int) ((30 * (trump.debate_atk_skill/100.0)) - (5 * (player.debate_def_skill/100.0)));
                            trump.boosted = false;
                        }
                        player.debate_hp -= dmg;
                        
                        printer.println("Donald Trump attacked! You're debate HP fell by " + dmg + "\nCurrent HP: " + player.debate_hp);
                    }
                    else{
                        trump.debate_def_skill += 10;
                        trump.debate_hp += 7;
                        printer.println("Donald Trump defended himself. His def pow increased by +10. and HP increased by +7\nTrump HP: " + trump.debate_hp + "\nTrump Defense Skill: " + trump.debate_def_skill);
                    }
                }

                //allow the player to choose their tactic for the debate and print the correct output
                printer.printlnAndKeep("It's your time to speak. What will you do?\n1) Attack Trump\n2) Defend yourself\n3) Use special ability (Can only be used once)");
                int choice = ElectionSimulator.getNumInRange(1, 3);
                printer.clearScreen();
                switch (choice){
                    case 1:
                        this.printQuote("JOE BIDEN", player.getDebateLine("atk" + " " + currentTopic));
                        int dmg = (int) ((20 * (player.debate_atk_skill/100.0)) - (10 * (trump.debate_def_skill/100.0)));
                        trump.debate_hp -= dmg;
                        printer.println("You attacked! Trump's debate HP fell by " + dmg +  "\nTrump HP: " + trump.debate_hp + "\nYour HP: " + player.debate_hp);
                        break;
                    case 2:
                        this.printQuote("JOE BIDEN", player.getDebateLine("def" + " " + currentTopic));
                        player.debate_def_skill += 10;
                        player.debate_hp += 7;
                        printer.println("You defended yourself. Your def pow increased by +10. and HP increased by +7\nYour HP: " + player.debate_hp + "\nYour Defense Skill: " + player.debate_def_skill);
                        break;
                    case 3:
                        if (!player.usedAbility){
                            this.printQuote("JOE BIDEN", player.ability());
                            player.debate_hp += 40;
                            player.usedAbility = true;
                        }
                        else{
                            printer.println("You already used your ability. Turn wasted.");
                        }
                        break;
                }



            }
            
            
        }

        //print final results and change polling for all states accordingly
        this.printQuote(host, "And that's all the time we have. Thank you all for tuning in to the debate!");
        printer.println("DEBATE RESULTS:\nYour final hp: " + player.debate_hp + "\nTrump's final hp: " + trump.debate_hp);
        if (player.debate_hp > trump.debate_hp){
            printer.println("Congratulations! You won the debate. Your polling increased by up to +15% across all states.");
            String[] states = Polls.getAllStates();
            for (int i = 0; i < states.length; i++){
                RandomPicker.changePolling(states[i], 7, 15);
            }
        }
        else{
            printer.println("Defeat. You lost the debate. Your polling decreased by up to 15% across all states.");
            String[] states = Polls.getAllStates();
            for (int i = 0; i < states.length; i++){
                RandomPicker.changePolling(states[i], -7, -15);
            }
        }


    }
}
