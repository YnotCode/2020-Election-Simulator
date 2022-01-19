import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DonaldTrump extends Character {

    //boolean to track whether Trump's next attack should be boosted or not
    boolean boosted = false;
    
    //constructor, initializes Trump with his debate lines and starting stats
    public DonaldTrump(){
        this.money = 10000;
        this.debate_hp = 100;
        this.hateability = 100;
        this.rally_skill = 100;
        this.debate_atk_skill = 90;
        this.debate_def_skill = 40;
        this.debate_lines = new HashMap<String, String[]>();
         
        this.debate_lines.put("def fp", new String[]{
            "Look, North Korea and us have a good relationship. We're not at the start of some nuclear war here. I see this as an absolute win.",
            "Putin's been kept in check, and there was absolutely no collusion in the previous election. None. It was perfectly fair and this witch hunt is really…just pathetic."
        });

        this.debate_lines.put("atk fp", new String[]{
            "You look at Joe's track record. You look and what do you see? He's always wrong. Absolutely terrible at foreign policy… couldn't make the right choice to save his life. If it had been him running this country Bin Laden would still be out there.",
            "Frankly your ideas don't make any sense. How are you going to stand up to China, Joe? How? Don't even bother answering. We all know you've got nothing because you're a crooked commie just like them.",
            "He's a wimp when it comes to China. Never done anything as Senator that was worthwhile against China. What more is there to say? The libs just don't have what it takes to step up and stop them."
        });

        this.debate_lines.put("def cvd", new String[]{
            "Operation Warpspeed has been a HUGE success. End of story.",
            "Look, it's not my fault, and it's not the Dems fault that Covid came here. It was CHINA'S fault...",

        });

        this.debate_lines.put("atk cvd", new String[]{
            "If you elect Joe, he's gonna keep everyone locked indoors forever! That's not what we want as a country!"
        });

        this.debate_lines.put("atk econ", new String[]{
            "If Joe's elected the markets are gonna tank, and if I'm elected they'll boom. It's really that simple.",
            "Why don't you tell them about how you're going to destroy all of our coal mining and fossil fuel jobs Joe?",
        });

        this.debate_lines.put("def econ", new String[]{
            "The economy had risen tremendously since I've become president until COVID-19. If you ignore that it was [H]UGE."
        });

        this.debate_lines.put("atk sc", new String[]{
            "THE RADICAL LEFT and Joe are gonna pack the court and remove LAW and ORDER from this country!"
        });

        this.debate_lines.put("def sc", new String[]{
            "Elections have consequences, Joe. A president's term is 4 years, not 3, so I see no problem with me appointing a judge in the last year if the opening comes."
        });

        this.debate_lines.put("atk hc", new String[]{
            "Obamacare was a DISASTER Joe and you know it! So much money wasted, but I'm creating a better system..."
        });

        this.debate_lines.put("def hc", new String[]{
            "I have my BEAUTIFUL healthcare plan on the way and we're working really hard to make it the best we can. You can't rush greatness..."
        });

        this.debate_lines.put("atk imm", new String[]{
            "Under Joe we'll have completely open borders and this country will be ruined. The radical Democrats have pushed this too far."
        });

        this.debate_lines.put("def imm", new String[]{
            "These illegal immigrants are breaking the law, and we have to uphold the law and enforce it, Joe. What else do you want us to do?"
        });


        

    }

    //implementation of Character ability method
    public String ability(){
        this.boosted = true;
        String line = "C'mon Joe, you're such a loser.";
        String res = "Trump boosts his confidence with a jab. It's super effective! His next attack will have twice the punch!";
        return line + "\n" + res;
    }

    //randomly decreases all polls in all states by a small amount to simulate Trump's campaign working against the player
    //OR targets a random state that Biden has a small lead (<= 5%) on
    //returns the message describing what happened
    public String campaign(){
        String[] states = Polls.getAllStates();
        //choose a random double so that 50% of the time one action occurs and the other 50% another occurs
        if (Math.random() > 0.5){

            //decrease polling by a random amount that varies based on how close the race is
            for (String state : states){
                if (Math.abs(Polls.getSupport(state)) < 5){
                    RandomPicker.changePolling(state, -1.5, -2);
                }
                else{
                    RandomPicker.changePolling(state, -2, -5);
                }
            }

            //return message
            return "Trump launched new ads...the new ad campaign decreased your polling in all states by up to 5%";
        }
        else{

            //store all the states where Biden has a lead but under 5% in a list
            ArrayList<String> options = new ArrayList<String>();
            for (String state : states){
                if (Polls.getSupport(state) < 5 && Polls.getSupport(state) > 0){
                    options.add(state);
                } 
            }
            
            //choose one random state from the list if possible, and set the support to 0
            String randState = states[0];
            if (options.size() > 0){
                randState = options.get((int) (Math.random() * options.size()));
            }
            Polls.setSupport(randState, 0);
        
            //return the explanation message
            return "Trump is on the move! He just held a rally in " + randState + " and now the race is dead even there!";
        }
        
    }


}
