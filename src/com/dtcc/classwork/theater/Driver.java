package com.dtcc.classwork.theater;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Driver {

    public static int getMenuChoice() {
        int choice = 0;
        Scanner input = new Scanner(System.in);
        do {
            displayMenu();
            choice = input.nextInt();

            if (choice < 1 || choice > 6)
                System.out.println("Invalid option. Please select again.");
        } while (choice < 1 || choice > 6);

        return choice;
    }

    public static void main(String[] args) throws IOException {
        int option;
        int choice = 0;
        Scanner input = new Scanner(System.in);
        Theater theater = new Theater(15, 30);

        do {
            option = getMenuChoice();
            switch (option) {
                case 1:
                    //System.out.println("Calling purchase tickets module.");
                    purchaseTickets(theater);
                    break;
                case 2:
                    //System.out.println("Calling total ticket sales module.");
                    totalSales(theater);
                    break;
                case 3:
                    //System.out.println("Calling total number of tickets sold module.");
                    totalNoOfTicketsSold(theater);
                    break;
                case 4:
                    System.out.println("Enter row number to check available seats.");
                    choice = input.nextInt();
                    seatsInRow(theater,choice);
                    break;
                case 5:
                    //System.out.println("Calling seat availability in the auditorium module.");
                    seatsAvailable(theater);
                    break;
                case 6:
                    System.out.println("Application ends.");
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (option != 6);


    }

    public static void displayMenu() {
        System.out.println("Menu");
        System.out.println("1. Purchase Tickets");
        System.out.println("2. Total ticket sales in Dollars");
        System.out.println("3. Total number of tickets sold");
        System.out.println("4. Seat availability in each row");
        System.out.println("5. Seat availability in the auditorium");
        System.out.println("6. Exit\n");
        System.out.println("Please select an option: ");

    }


    public static void purchaseTickets(Theater t) throws IOException {
        double total = 0.0;
        double[] price = t.readTicketPriceFile();

        t.displaySeatChart();
        System.out.println("Do you want to purchase ticket(s) Y/N?");
        Scanner input = new Scanner(System.in);
        char yesOrNo = input.next().trim().charAt(0);

        if (yesOrNo == 'Y' || yesOrNo == 'y') {
            System.out.println("You can only purchase one row at a time. Please enter a row number for the seat(s):");
            int rowNum = input.nextInt();

            System.out.println("You can purchase any number of tickets in this row as long as there are consecutive seats available. Please enter a column number for the seat(s): ");
            int columnNum = input.nextInt();

            System.out.println("How many tickets do you want to purchase starting from this seat: ");
            int noOfTickets = input.nextInt();

            //if(t.available(rowNum,columnNum)){t.setSeat(rowNum,columnNum,noOfTickets);}
            if (t.confirmed(rowNum, columnNum, noOfTickets)) {
                System.out.printf("Total ticket(s) price is $%.2f\n", (price[rowNum - 1] * noOfTickets));
                t.setSeat(rowNum, columnNum, noOfTickets);
            } else {
               // int columnCheck = t.getColumns() - noOfTickets + 1;
                int availableSeats=t.getColumns()-columnNum+1;
                if (availableSeats < noOfTickets) {
                    System.out.println("Sorry,there are not enough seats in the row.");
                } else {
                    System.out.println("Sorry, requested seat/seats are sold out.");
                }
            }
        } else {
            System.out.println("Sending to the Main menu");
        }
    }

    public static void totalSales(Theater t) throws IOException {
        System.out.printf("Total ticket sales in Dollars $%.2f\n", t.totalTicketsSales());
    }

    public static void totalNoOfTicketsSold(Theater t) throws IOException {
        System.out.println("Total number of Tickets sold " + t.noOfTicketsSold());
    }

    public static void seatsAvailable(Theater t){
        System.out.println("Total number of seats available "+t.totalSeatsAvailable());
    }

    public static void seatsInRow(Theater t,int choice){
       t.displayARow(choice);
    }
}
