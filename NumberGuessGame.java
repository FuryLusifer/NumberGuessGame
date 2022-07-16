import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame
{

    public static void main(String[] args)
    {
       Scanner sc = new Scanner(System.in);
       Random num = new Random();
       int number = num.nextInt(101);
       int count=1;
	System.out.println("\n\n\t\t\t\t**********************************");
        System.out.println("\t\t\t\t  Welcome To Number Guessing Game");
	System.out.println("\t\t\t\t**********************************");
        System.out.println("\n\nEnter your Guess(upto 100):::");
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
            count++;
        }
        System.out.println("\t\t\t!!!!!!!!CONGRATULATIONS YOU HAVE GUESSED CORRECTLY!!!!!!!");
        System.out.println("\t\t\t\tIt Took You "+count+" tries to correctly guess");
    }
}
