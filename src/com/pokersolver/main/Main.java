package com.pokersolver.main;

import com.pokersolver.algorithms.ExpectedValue;
import com.pokersolver.algorithms.Outs;
import com.pokersolver.algorithms.PotOdds;
import com.pokersolver.algorithms.SPR;
import com.pokersolver.solver.Solver;


import java.util.Scanner;

public class Main {

    private static final int numOfAlgorithms = 3;



    public static void main(String[] args) {

        boolean mainNotDone = true;
        while (mainNotDone) {

            System.out.println("---------------------------");
            System.out.println("Welcome to Poker Solver.");
            System.out.println("---------------------------");
            System.out.println("Enter 1 to calculate pot odds.");
            System.out.println("Enter 2 to calculate % to hit your outs.");
            System.out.println("Enter 3 to calculate expected value.");
            System.out.println("Enter 4 to calculate SPR");
            System.out.println("Enter 0 to terminate the program.");
            System.out.println("---------------------------");

            Solver solver = Solver.getInstance();

            Scanner scanner = new Scanner(System.in);
            int i = -1;

            try {
                i = scanner.nextInt();
            } catch (Exception e) {

                // input needs to be a valid number,
                // if exception is caught, program will loop and prompt user to re-enter a valid number.

            }



            if (i == 1) {
                boolean notDone = true;
                while (notDone) {


                    System.out.println("Enter the total amount in the pot, and the amount you need to call. Example: 100.00 75.10");
                    scanner.nextLine();
                    double potTotal = scanner.nextDouble();
                    double toCall = scanner.nextDouble();
                    PotOdds potOdds = null;
                    try {
                        potOdds = solver.createPotOdds(potTotal, toCall);
                        notDone = false;
                    } catch (Exception e) {
                        System.out.println("******** " + e.getMessage());

                    }

                    if (!notDone) {
                        System.out.println(potOdds.toString());



                    }
                }


            } else if (i == 2) {

                boolean notDone = true;

                while (notDone) {


                    System.out.println("How many outs do you have?");
                    scanner.nextLine();
                    int numOuts = scanner.nextInt();

                    Outs outs = null;
                    try {
                        outs = solver.createOuts(numOuts);
                        notDone = false;
                    } catch (Exception e) {
                        System.out.println("******** " + e.getMessage());
                    }

                    if (!notDone) {
                        System.out.println(outs.toString());

                    }

                }


            } else if (i == 3) {

                boolean notDone = true;
                while (notDone) {


                    System.out.println("Enter in this order: your equity percentage, " +
                            "how much you need to call, and total pot size before calling. Example: 34.21 75 150");
                    double equity = scanner.nextDouble();
                    double toCall = scanner.nextDouble();
                    double pot = scanner.nextDouble();
                    ExpectedValue ev = null;

                    try {
                        ev = solver.createEV(equity, toCall, pot);
                        notDone = false;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());

                    }
                    if (!notDone) {
                        System.out.println(ev.toString());

                    }

                }
            } else if (i ==4) {
                boolean notDone = true;
                while(notDone) {
                    System.out.println("Enter stack size and pot size");
                    double stack = scanner.nextDouble();
                    double pot = scanner.nextDouble();
                    SPR spr = new SPR(stack, pot);
                    System.out.println(spr.calcSPR());
                    notDone = false;

                }
            }

            else if (i == 0) {
                System.out.println("Program terminated.");
                mainNotDone = false;

            }

            else if (i < 1 || i > numOfAlgorithms){
                System.out.println("******** " + "Please enter a valid number.");


            }



        }











    }
}
