import java.text.DecimalFormat;
import java.util.HashMap;

//class for Joe Biden character (player)
public class JoeBiden extends Character{
    
    //instance of Polls to track the polls for the entire game
    public Polls polls;
    DecimalFormat formatter;
    
    //constructor to set initial stats and create the debate lines for use in battle
    public JoeBiden(){
        //initialize character attributes
        this.money = 10000;
        this.debate_hp = 100;
        this.hateability = 50;
        this.rally_skill = 30;
        this.debate_atk_skill = 60;
        this.debate_def_skill = 40;
        this.usedAbility = false;
        this.debate_lines = new HashMap<String, String[]>();

        //create new Polls object to print polling data
        this.polls = new Polls();

        //initialize formatter
        this.formatter = new DecimalFormat();
         
        //add debate lines to map
        this.debate_lines.put("def fp", new String[]{
            "When I was VP we didn't need to have a good relationship with North Korea because we didn't legitimize them like you've done!",
            "I've been a Senator a long time and VP too. I know how to handle foreign policy."
        });

        this.debate_lines.put("atk fp", new String[]{
           "Folks, do we have any idea what this clown is doing? He's going abroad and making friends with dictators, that's what."
        });

        this.debate_lines.put("def cvd", new String[]{
            "COVID-19 is a dangerous virus, and we need to be careful with reopening so when we do fully reopen we don't have to go back into lockdown"
        });

        this.debate_lines.put("atk cvd", new String[]{
            "You've said people are learning to \"live with it\" Well let me tell you, people are learning to die with it!"
        });

        this.debate_lines.put("atk econ", new String[]{
            "The fact of the matter is that the economy is worse than it's been in a while, and you're not doing anything about it Trump!",
            "Keep yapping, man. We both know that it was Obama's efforts for 8 years that got the economy back and running."
        });

        this.debate_lines.put("def econ", new String[]{
            "My spending plan is large, but it is justified. We're in unprecedented times and it's about time to invest in the American people",
            "Let me tell you, the economy is about more than the markets. Where I come from Scranton people don't live on the market. We've got to do more for the average person instead of watching the stock market."
        });

        this.debate_lines.put("atk sc", new String[]{
            "Roe v. Wade is on the ballot. If Trump continues as president-- the fact of the matter is-- it'll get undone!"
        });

        this.debate_lines.put("def sc", new String[]{
            "I'm not going to answer questions about packing the court, because it's not my policy..."
        });

        this.debate_lines.put("atk hc", new String[]{
            "We all know this president just lies, and he's a liar, no other way of putting it. The simple fact is that he has no plan for healthcare!"
        });

        this.debate_lines.put("def hc", new String[]{
            "Obamacare was no disaster, it helped so many people with pre-existing conditions..."
        });

        this.debate_lines.put("atk imm", new String[]{
            "Look, no matter what our immigration policy is, it's not acceptable to put children in cages!"
        });

        this.debate_lines.put("def imm", new String[]{
            "I will not have open borders. You're debating me, Trump, not Bernie or anybody else!"
        });
        

    }

    //implementation of Character ability method
    //Biden's ability is to appear unifying and regain health
    public String ability(){
        this.debate_hp += 10;
        if (this.debate_hp > 100){
            this.debate_hp = 100;
        }
        String line = "*Stares into the camera* Is this who we are as a country? We need to do better! We're in a battle for the soul of the nation here.";
        String res = "Biden appeals to the TV audience directly. It's super effective! +10 HP recovered.";
        return line + "\n" + res;
    }

    //overloaded toString to print out candidate stats
    public String toString(){

        //create a formatted statement for campaign funds
        
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        String moneyStatement = "Campaign Funds: $" + formatter.format(this.money);

        //create formatted statements for skill
        String skillStatement1 = "Debate Attacking Skill: " + this.debate_atk_skill + "%";
        String skillStatement2 = "Debate Defense Skill: " + this.debate_def_skill + "%";

        //create a formatted statement for rally skill
        String rallyStatement = "Rallying skills: " + this.rally_skill + "%";

        return "JOE BIDEN STATS: " + "\n" + moneyStatement + "\n" + skillStatement1 + "\n" + skillStatement2 + "\n" + rallyStatement + "\n";

    }

    //return the polling data using the toString method of Polls
    public String getPollingData(){
        return "Polling by state: \n" + this.polls.toString();
    }

    //returns the current campaign balance formatted to two decimal places with commas and a $ sign
    public String getMoney(){
        return "$" + formatter.format(this.money);
    }
}
