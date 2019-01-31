package com.pokersolver.main;

import com.pokersolver.algorithms.ExpectedValue;
import com.pokersolver.algorithms.Outs;
import com.pokersolver.algorithms.PotOdds;
import com.pokersolver.solver.Solver;


import java.util.Scanner;

public class Main {

    private static final int numOfAlgorithms = 3;



    public static void main(String[] args) {



        System.out.println("Welcome to Poker Solver.");
        System.out.println("---------------------------");
        System.out.println("Enter 1 to calculate pot odds.");
        System.out.println("Enter 2 to calculate % to hit your outs.");
        System.out.println("Enter 3 to calculate expected value.");
        System.out.println("Enter 0 to terminate the program.");
        System.out.println("---------------------------");

        Solver solver = Solver.getInstance();

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        if (i == 0) {
            System.out.println("Program terminated.");
            return;
        }

        if (i < 1 || i > numOfAlgorithms) {
            System.out.println("******** " + "Please enter valid number.");
            System.out.println("---------------------------");
            main(null);
        }

        else if (i == 1) {
            System.out.println("Enter the total amount in the pot, and the amount you need to call. Example: 100.00 75.10");
            scanner.nextLine();
            double potTotal = scanner.nextDouble();
            double toCall = scanner.nextDouble();
            PotOdds potOdds;
            try {
                potOdds = solver.createPotOdds(potTotal, toCall);
            }
            catch (Exception e) {
                System.out.println("******** " + e.getMessage());
                main(null);
                return;
            }

            System.out.println(potOdds.toString());

            System.out.println("---------------------------");
            main(null);




        }

        else if (i == 2) {

            System.out.println("How many outs do you have?");
            scanner.nextLine();
            int numOuts = scanner.nextInt();

            Outs outs;
            try {
                outs = solver.createOuts(numOuts);
            } catch (Exception e) {
                System.out.println("******** " + e.getMessage());
                return;
            }

            System.out.println(outs.toString());


        }

        else if (i==3) {
            System.out.println("Enter in this order: your equity percentage, " +
                    "how much you need to call, and total pot size before calling. Example: 34.21 75 150");
            double equity = scanner.nextDouble();
            double toCall = scanner.nextDouble();
            double pot = scanner.nextDouble();
            ExpectedValue ev;

            try {
               ev= solver.createEV(equity,toCall,pot);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            System.out.println(ev.toString());

        }

        main(null);









    }
}