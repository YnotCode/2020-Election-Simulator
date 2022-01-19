//class to allow for "fancy" printing of text so it appears like it's being typed
public class FancyPrinter {
    //attributes to control the speed of printing and the pause duration before clearing output
    private int speed = 20;
    private int pauseTime = 1000;

    //constructor, sets the private instance attributes based on the two integer parameters
    public FancyPrinter(int speed_given, int pause){
        speed = speed_given;
        pauseTime = pause;
    }

    //setter method for private speed attribute
    public void setSpeed(int s){
        speed = s;
    }

    //setter method for private pause attribute
    public void setPause(int p){
        pauseTime = p;
    }

    //method to print the text while pausing after each character for the "fancy effect"
    //private because it is only used internally within the class by other methods like println and printlnAndKeep that are public    
    private void printRaw(String text){
        int y = 0;
        int x = 0;
        while (y < text.length()){
            if (x == 2){
                y++;
                System.out.print(text.substring(y-1, y));
                x = 0;
            }

            try{
                Thread.sleep(speed);
                x++;
            }
            catch(Exception e){
                System.out.println("ERROR: Unable to use put the thread to sleep.");
            }
        }
    }

    //prints a line with "animated" text, and then pauses for some time (dictated by pauseTime) and then clears the screen
    public void println(String text){
        this.printRaw(text);
        this.pause(pauseTime);
        this.clearScreen();
    }

    //prints a line and doesn't clear the screen afterwards
    //this is useful for printing a prompt for input
    public void printlnAndKeep(String text){
        this.printRaw(text);
        System.out.print("\n");
    }

    //clear the screen using special sequence of characters
    public void clearScreen(){
        System.out.print("\033[H\033[2J");
    }


    //pause for a provided amount of milliseconds
    public void pause(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }
        catch(Exception e){
            System.out.println("Error occurred");
        }
    }

}
