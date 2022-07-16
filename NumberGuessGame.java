import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame
{

    public static void main(String[] args)
    {
       Scanner sc = new Scanner(System.in);
       Random num = new Random();
       int number = num.nextInt(101);
        System.out.println("******Welcome To Number Guessing Game******");
        System.out.println("Enter your Guess(upto 100):::");
        int guess = sc.nextInt();
        while(guess!=number)
        {
            if(guess<number)
            {
                System.out.println("GUESS HIGHER");
                 guess=sc.nextInt();
            }
            else
            {
                System.out.println("GUESS LOWER");
                 guess=sc.nextInt();
            }
        }
        System.out.println("!!!!!!!!CONGRATULATIONS YOU HAVE GUESSED CORRECTLY!!!!!!!");
    }
}
   
