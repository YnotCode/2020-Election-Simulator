import java.util.HashMap;

//template class for all characters
abstract class Character {
    //amount of money the character has
    public double money;

    //how fervently does the opposition hate this person? (0 - 100)
    public int hateability;
    
    //modifiers that affect how well actions will do in debates and rallies
    public int debate_atk_skill;
    public int debate_def_skill;
    public int rally_skill;

    public boolean usedAbility;

    public HashMap<String, Integer> policy_skills;

    //the unique character id 
    /*
    Donald Trump: 0,
    Joe Biden: 1,
    */
    public int id;
    


    //health for debate
    public int debate_hp;


    public String slogan;
    public Character VP;
    public String portrait;

    //key format: type + " " + category
        //type: atk (attack) or def (defense)
        //debate 1 categories: fp (foreign policy), cvd (COVID), econ (economics)
        //debate 2 categories: hc (healthcare), 
    //
    public HashMap<String, String[]> debate_lines;
    
    //each Character has a special ability
    //returns the ability text
    abstract String ability();

    //any Character can get a debate line which fits the key parameter provided
    //returns a random debate line that fits the criteria of the key
    //example: getDebateLine("atk cvd") would return a random debate line that attacks and is about COVID-19
    public String getDebateLine(String key){
        String[] possibilities = this.debate_lines.get(key);
        int index = (int) (Math.random() * possibilities.length);
        return possibilities[index];
    }
}

