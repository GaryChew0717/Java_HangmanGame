/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author cjyen
 */
public class Assignment1part2hangman {
    public static void main(String[] args)throws IOException
    {
        Scanner keyboard = new Scanner(System.in);
        File inputFile = new File("WorldCities2024_v4.txt");
        
        String Country;
        int Chance = 10, constant = -1;
        String[] Countries = new String[3914];
        String[] Towns = new String[3914];
        int[] Population = new int[3914];
        boolean Targetlocked = false;
        final int START = 0;
        int Score = 10;
        int option =0;
        String alphabets = "";
        boolean Hint = false;

        Scanner input = new Scanner(inputFile);
        //reading the txt file
        int i =0;
        while(input.hasNext())
        {
            Towns[i] = input.next();
            Countries[i] = input.next();
            Population[i] = input.nextInt();
            i++;
        }
        
        // selecting country
        System.out.println("Please enter a country");
        Country = keyboard.nextLine();
        
        //System.out.println(Countries[0] + Towns[0] + Population[0]);
        while(constant == -1)
        {
            i=0;
            while(i < 3914)
            {
                if(Country.equalsIgnoreCase(Countries[i]))
                {
                    constant = i;
                    Targetlocked = true;
                }
                i++;  
            }    
            if(constant == -1)
            {
                System.out.println("The country is not in the list. Please try again.");
                Country = keyboard.nextLine();
            }
        }
        
        //Outprint whether the country is in the list
        if(Targetlocked == true)
        {
            System.out.println("The country has been locked '" + Countries[constant] + "'");
        }
        
        //Answer target
        String AnsCountry = Countries[constant];
        String AnsTowns = Towns[constant];
        int AnsPopulation = Population[constant];
        
        //outprint how many letters
        String Letters = "";

        for(int p = START; p <AnsTowns.length(); p++)
        {
            Letters = Letters + "_";
        }

        System.out.println("\nThere are " + AnsTowns.length() + " Letters");
        System.out.println(Letters);
        
        
        // Whole guessing section, outprint hint, letters remains, answer checking
        boolean success = false;
        while(Chance != 0)
        {
            boolean Found = false;
            //menu option
            System.out.println("What do you want to guess? (" + Chance + " Guesses left, Score: " + Score + ")");
            System.out.println("[1] Letters");
            System.out.println("[2] Word");
            System.out.println("[3] Hint (-5 Score)");
            option = keyboard.nextInt();
            keyboard.nextLine(); 
            
            if(option == 1)
            {
                System.out.println("\nPlease enter one random letters:");
                alphabets = keyboard.nextLine();
                
                while(alphabets.length() != 1)
                {
                    System.out.println("\nPlease enter just one letters!!!");
                    alphabets = keyboard.nextLine();
                }

                //checking whether the letter is inside the target town
                for(int p = 0;p < AnsTowns.length(); p++)
                {
                    if(alphabets.toLowerCase().charAt(0) == AnsTowns.toLowerCase().charAt(p))
                    {
                        Letters = Letters.substring(0,p) + AnsTowns.charAt(p) + Letters.substring(p+1);
                        Found = true;
                    }
                }
                if(Found)
                {
                    System.out.println("'" + alphabets + "' is in the word");
                }
                else
                {
                    System.out.println("'" + alphabets + "' is not in the word");                            
                }
                Chance--;
                System.out.println(Letters +"\n");
                
                if(Letters.indexOf('_') == -1)
                {
                    success = true;
                    Chance = 0;
                }
            }
            else if(option == 2)
            {
                System.out.println("\nPlease enter your guess:");
                String guess = keyboard.nextLine();
                while (guess.length() != AnsTowns.length())
                {
                    System.out.println("\nPlease enter your guess (" + AnsTowns.length() + " letters):");
                    guess = keyboard.nextLine();
                }
                if(guess.equalsIgnoreCase(AnsTowns))
                {
                    System.out.println("You have the right guess!!");
                    success = true;
                    Chance = 0;
                }
                else
                {
                    System.out.println("You have the wrong guess");
                    Chance--;
                }
            }
            else if(option == 3 && Hint == false)
            {
                System.out.println("\nHint used, reduce 3 guess, the population of the town are " + AnsPopulation);
                Chance = Chance -3;
                Score = Score -5;
                System.out.println(Chance + " remaining\n");
                Hint = true;
            }
            else
            {
                System.out.println("Please insert a valid number!!\n");
            }
        }
        if(success == true)
        {   
            //Score calculation for round
            if(Chance <= 3)
            {
                Score = Score + 40;
            }
            else if(Chance <= 6)
            {
                Score = Score + 60;
            }
            else if(Chance <= 7)
            {
                Score = Score + 70;
            }
            else if(Chance <= 8)
            {
                Score = Score + 80;
            }
            else 
            {
                Score = Score + 90;
            }
            
            System.out.println("Thank you for playing! Congratulation.");
            System.out.println("You have guessed the right town " + AnsTowns);
            System.out.println("Your final Score is " + Score);
        }
    }
}
