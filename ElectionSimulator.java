/*
Name: Anthony Varkey
Due Date: 1/19/2021
Assignment: Semester 1 Final Project
Hour: 3
*/


//import Scanner class for input collection
import java.util.Scanner;

//main driver class
public class ElectionSimulator {

    //loops until the user picks an input between the two provided parameters, inclusive
    //returns the integer the user chose
    //uses a Scanner object
    public static int getNumInRange(int low, int high){
        Scanner input = new Scanner(System.in);
        boolean answered = false;
        while (!answered){
            try{
                int res = input.nextInt();
                if (res >= low && res <= high){
                    return res;
                }
                else{
                    System.out.println("Enter a valid number between " + low + " and " + high);
                    input.nextLine();
                }
            }
            catch(Exception e){
                System.out.println("Enter a number please!");
                input.nextLine();
            }
        }
        return 0;
    }

    //loops until the user picks an input included in the array of String options given as the first parameter
    //takes a boolean parameter indicating whether the user must match the case of one of the provided options
    //takes a String parameter for the message to print if the user fails to provide a correct input
    public static String getStringInList(String[] options, boolean caseSensitive, String msg){
        Scanner input = new Scanner(System.in);
        boolean answered = false;
        while (!answered){
            try{
                String res = input.nextLine();
                for (String opt : options){
                    if (opt.equals(res)){
                        return opt;
                    }
                    if (!caseSensitive){
                        if (opt.equalsIgnoreCase(res)){
                            return opt;
                        }
                    }
                }
                System.out.println(msg);
            }
            catch(Exception e){
                System.out.println(msg);
            }
        }
        return "";
    }

    //print the introduction using the FancyPrinter object provided as a parameter
    public static void printIntro(FancyPrinter printer){
        printer.clearScreen();
        printer.println("*********WELCOME TO THE 2020 ELECTION SIMULATOR*********");
        printer.println("You're Joe Biden, and you've decided to run for president again. Third time's the charm!");
        printer.println("Let's meet your Republican opponent....");
        printer.println("Standing at 6' 3\", weighing over 240 lbs, it's... DONALD TRUMP!");
    }

    //static method to view the stats of the player object provided using the FancyPrinter object provided
    public static void viewStats(FancyPrinter printer, JoeBiden player){
        printer.clearScreen();
        System.out.println(player.toString());
        System.out.println("What would you like to do? (Enter number of choice)\n1) View polling by state\n2) Return to decision making");
        int ans = getNumInRange(1, 2);
        if (ans == 1){
            System.out.println(player.getPollingData());
            System.out.println("Enter 1 to return to the game when you are ready:");
            ans = getNumInRange(1, 1);
            printer.clearScreen();
        }
        else{
            printer.clearScreen();
        }            
    }

    //static method to add campaign funds to the player object using the FancyPrinter object provided
    public static void acquireFunds(FancyPrinter printer, JoeBiden player){
        printer.clearScreen();
        //choose a random amount of money and add it to the total
        printer.println("Acquiring funds....");
        printer.println("You got...");
        double money = RandomPicker.getMoney();
        printer.println("$" + money);
        player.money += money;
        printer.println("Your new campaign total is: " + player.getMoney());
        printer.printlnAndKeep("Would you like to sell your soul to gain $5000? (Exchange -5 debate skill for +5000 money)\n1) Sell soul\n2) Don't sell soul");
        //allow the user to choose whether to exchange debate skill for extra money
        int choice = getNumInRange(1, 2);
        if (choice == 1){
            player.debate_atk_skill -= 5;
            player.debate_def_skill -= 5;
            player.money += 5000;
            printer.println("Congratulations, you lost -5 debate skill and gained $5000");
            printer.println("Your new total is: " + player.getMoney());
        }
        printer.clearScreen();
    }

    //static method to hold rally using the player object provided and print content with the FancyPrinter object given
    public static void holdRally(FancyPrinter printer, JoeBiden player){
        printer.clearScreen();

        System.out.println(player.getPollingData());
        printer.printlnAndKeep("Enter the 2 letter abbreviation of the state where you would like to rally:");
        String ans = getStringInList(Polls.getAllStates(), false, "Enter a valid two letter state abbreviation please!");
        printer.println("You had a rally in " + ans + ", and it was...");
        double newPoll = 0;
        switch(RandomPicker.getRallySuccess(ans, player)){
            case 0:
                printer.println("a DISASTER.");
                newPoll = RandomPicker.changePolling(ans, 0, 1);
                break;
            case 1:
                printer.println("moderately succesful.");
                newPoll = RandomPicker.changePolling(ans, 0, 2);
                break;
            case 2:
                printer.println("a SUCCESS!");
                newPoll = RandomPicker.changePolling(ans, 2, 4);
                break;

        }
        printer.println("New approval for " + ans + ": " + newPoll);
        int xp = RandomPicker.getRallyXP();
        player.rally_skill += xp;
        printer.println("Gained +" + xp + " rally skill, new skill level: " + player.rally_skill);
    }

    //static method to advertise using the player object and FancyPrinter objects provided
    public static void advertise(FancyPrinter printer, JoeBiden player){
        
        printer.clearScreen();

        //create a new Scanner since getNumInRange is not applicable
        Scanner inpt = new Scanner(System.in);
        boolean answered = false;
        double res = 0;
        while (!answered){
            printer.printlnAndKeep("Current campaign balance: " + player.getMoney() + "\nHow much would you like to spend on advertising?");
            try{
                double x = inpt.nextDouble();
                if (x > player.money){
                    System.out.println("Please enter a value less than or equal to your campaign balance, " + player.getMoney());
                }
                else{
                    answered = true;
                    res = x;
                }
            }
            catch(Exception e){
                System.out.println("please enter a valid number!");
            }
        }
        String[] states = Polls.getAllStates();

        double[][] pricings = {
            {1000.00, 0.02},
            {2500.00, 2},
            {4000.00, 4},
            {Double.MAX_VALUE, 5}
        };
        double printAmt = 0;
        for (int i = 0; i < states.length; i++){
            for (int y = 0; y < pricings.length; y++){
                if (res < pricings[y][0]){
                    printAmt = pricings[y][1];
                    RandomPicker.changePolling(states[i], pricings[y][1]/2, pricings[y][1]);
                    
                    y = pricings.length + 1;
                    break;
                }
            }
        }
        player.money -= res;
        printer.println("Based on how much you spent...you improved your standing by up to " + printAmt + "% across all states."); 

    }

    //static method to practice for the debate using the player object and FancyPrinter objects provided
    public static void practiceForDebate(FancyPrinter printer, JoeBiden player){
        printer.clearScreen();
        printer.println("Debate Practice\n");
        Scanner input = new Scanner(System.in);
        boolean providedValidAnswer = false;
        do{
            printer.printlnAndKeep("Would you like to practice attacking or defending? (Type the letter of your choice)\nA) Attacking\nB) Defending");
            String raw_ans = input.nextLine();
            char parsed = raw_ans.charAt(0);
            if (parsed == 'A' || parsed == 'a'){
                printer.println("You practiced attacking Donald Trump's character and his policies....+20 debate attack skill");
                player.debate_atk_skill += 20;
                providedValidAnswer = true;
            }
            else if (parsed == 'B' || parsed == 'B'){
                printer.println("You defending yourself against Trump's attacks....+20 debate defense skill");
                player.debate_def_skill += 20;
                providedValidAnswer = true;
            }
            else{
                System.out.println("Invalid answer. Type A or B please!");
            }
        }
        while(!providedValidAnswer);
    }


    //main method
    public static void main(String[] args){
        boolean running = true;

        //actions String to print every turn
        String actions = "1) Acquire funds\n2) Hold a rally\n3) Practice for the debate\n4) Advertise\n5) View Stats\n6) Quit Game";
        
        //create Scanner object for reading input
        //create FancyPrinter instance for animated text output
        Scanner input = new Scanner(System.in);
        FancyPrinter printer = new FancyPrinter(20, 1000);

        //integer variable to track which month it is
        //String array to hold the text to display for each date
        String[] dates = { 
            "August 18, 2020",
            "August 25, 2020",
            "September 1, 2020",
            "September 8, 2020",
            "September 15, 2020",
            "September 22, 2020",
            "September 29, 2020",
            "October 6 2020",
            "October 13 2020",
            "October 20 2020",
            "October 27 2020",
            "November 3 2020"
        };


        printer.clearScreen();
        
        //main game loop
        while (running){

            //integer to track current time
            int time = 0;

            //create player character object
            JoeBiden player = new JoeBiden();
            DonaldTrump trump = new DonaldTrump();
            
            //print the intro unless the user skips it
            printer.printlnAndKeep("Skip intro? (Enter number of your choice) \n1) Yes\n2) No");
            int ans = getNumInRange(1, 2);
            if (ans != 1){
                printIntro(printer);
                printer.println("You've just been nominated as the Democratic candidate for president- You have about 3 months to go before the election.\nFor this game, you get to make decisions for each week. That's 11 decisions!\nPlus, you'll have to face off with Donald Trump in 2 debates\nYour first debate is September 29.\nWill you be able to become president? Good luck!");
            }
            
            printer.clearScreen();
            //allow user to disable animated text
            printer.printlnAndKeep("Would you like to turn off animated text to speed up gameplay? (Enter 1 or 2)\n1) Yes, turn it off\n2) No, keep it");
            int ans2 = getNumInRange(1, 2);
            if (ans2 == 1){
                printer.setSpeed(0);
                printer.setPause(2000);
            }
            else{
                printer.setSpeed(20);
                printer.setPause(1000);
            }
            
            printer.clearScreen();
            //loop for each turn in the game
            while (time <= 11){
                if (time == 11){
                    printer.println("It's election day! This is your final decision....");
                }
                //print instructions and get input
                printer.printlnAndKeep("It's " + dates[time] + ", time to push your campaign forward!\nWhat will you do?\n" + actions);
                int action = getNumInRange(1, 6);

                //take an action by running the correct static method based on the users choice
                switch (action){
                    case 1:
                        acquireFunds(printer, player);
                        break;
                    case 2:
                        holdRally(printer, player);
                        break;
                    case 3:
                        practiceForDebate(printer, player);
                        break;
                    case 4:
                        advertise(printer, player);
                        break;
                    case 5:
                        viewStats(printer, player);
                        time--;
                        break;
                    case 6:
                        //quit the game by changing the value of both variables being checked for in the while loops
                        time = 100;
                        running = false;
                        break;
                }

                time++;

                if (action < 5){
                    printer.println(trump.campaign());
                }
                

                //start debate for September 29 or October 22 if the time is right 
                if (time == 7 && action < 5){
                    printer.println("*****September 29 SPECIAL EVENT: FIRST PRESIDENTIAL DEBATE*****");
                    Debate debate1 = new Debate(player, trump, 1, printer);
                    debate1.start();
                }
                else if (time == 10 && action < 5){
                    printer.println("*****October 22 SPECIAL EVENT: SECOND PRESIDENTIAL DEBATE*****");
                    Debate debate2 = new Debate(player, trump, 2, printer);
                    debate2.start();
                }

                
            }

            printer.clearScreen();

            if (running){

                printer.println("All of your decisions have come to this....let's see the final results....");

                //use the Polls class to get the resuls + the electoral map
                System.out.println(Polls.getResults());

                //calculate winner with static method in Polls class
                if (Polls.wonElection() && running){
                    System.out.println("\n\nYOU WON THE ELECTION! Congratulations.\nWould you like to play again?\n1) Yes\n2) No");
                }
                else{
                    System.out.println("GAME OVER. You lost #Trump2020.\nWould you like to play again?\n1) Yes\n2) No");
                }

            }
            else{
                System.out.println("GAME TERMINATED.\nWould you like to start over and play again?\n1) Yes\n2) No");
            }
            

            //quit game if the user doesn't want to play again
            int ans3 = getNumInRange(1, 2);
            if (ans3 == 2){
                running = false;
            }
            else{
                running = true;
            }
            
        }

        //clear screen and print final message for the end of the program
        printer.clearScreen();
        System.out.println("******Program ended. Thanks for playing!******");
    }
}
