import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        String filepath = "Words.txt";
        ArrayList<String> words = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        } 
        catch (FileNotFoundException e) {
        System.out.println("File not found");
        }
        catch(IOException e){
            System.out.println("An error occurred while reading the file");
        }
        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));
        Scanner s = new Scanner(System.in);
        ArrayList<Character> WordState = new ArrayList<>();
        int wrongguess = 0;
        for(int i=0;i<word.length();i++){
            WordState.add('_');
        }
        System.out.println("*******************");
        System.out.println("Welcome to Hangman!");
        System.out.println("*******************");
        while (wrongguess < 6){
        System.out.println(getHangmanArt(wrongguess));
        System.out.print("Word : ");
        for(char c:WordState){

            System.out.print(c + " ");
            
        }
        System.out.println();
        System.out.println("Guess a Letter: ");
        char guess = s.next().toLowerCase().charAt(0);
        if(word.indexOf(guess) >= 0){
            System.out.println("Correct Guess!\n");
            for(int i=0;i<word.length();i++){
                if(word.charAt(i) == guess){
                    WordState.set(i,guess);

                }
            }
        }
        if(!WordState.contains('_')){
            System.out.println(getHangmanArt(wrongguess));
            System.out.println("You Won!");
            System.out.println("Word : " + word);
            break;
        }
        else{
            wrongguess++;
            System.out.println("Wrong Guess!\n");
        }
        }
        if(wrongguess == 6){
            System.out.println(getHangmanArt(wrongguess));
            System.out.println("You Lost! The word was: " + word);
        }
        else{
            System.out.print("Word : ");
            for(char c:WordState){

                System.out.print(c + " ");
                
            }
            System.out.println("\nCongratulations! You guessed the word!");
        }

        

        s.close();
        }
        static String getHangmanArt(int wrongguess){
            return switch(wrongguess){
                case 0 -> """
                        



                        """;
                case 1 -> """
                           O


                
                         """;
                case 2 -> """
                            O
                            |
                 
                             """;
                case 3 -> """
                            O
                            /|
                              """;
                case 4 -> """
                            O
                            /|\\
                              """;  
                case 5 -> """
                            O
                            /|\\
                            /
                              """;
                case 6 -> """
                            O
                            /|\\
                            / \\
                              """;
                default -> "<h1>Game Over</h1>";
            };
        }
    }
