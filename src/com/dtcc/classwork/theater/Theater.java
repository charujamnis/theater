package com.dtcc.classwork.theater;

import java.io.*;

public class Theater {
    private int rows;
    private int columns;
    private char [][] seats;
    static double[] ticketPrices;

    public Theater(){
        this.rows=5;
        this.columns=5;
        seats=new char[rows][columns];
        ticketPrices=new double[rows];
        initialize();
    }
    public Theater(int rows, int columns){
        this.rows=rows;
        this.columns=columns;
        seats=new char[rows][columns];
        ticketPrices=new double[rows];
        initialize();
    }

    public double[] readTicketPriceFile() throws IOException {

        //double[] price=new double[this.rows];
       // ticketPrices=new double[this.rows];
        int index=0;
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("src/com/dtcc/classwork/theater/ticketprice.txt"));

            while(bufReader.ready()){
                String line = bufReader.readLine();
                ticketPrices[index]= Double.parseDouble(String.valueOf(line));
                index++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return ticketPrices;
    }

    public void initialize(){
        for(int i=0;i<seats.length;i++){
            for(int j=0;j<seats[i].length;j++){
                seats[i][j]='#';
            }
        }
    }

    public boolean available(int rows, int columns){
        if(seats!=null){
            if(seats[rows-1][columns-1]=='#'){
                return true;
            }
            else return false;
        }
        return false;
    }

    public boolean confirmed(int r, int c, int number){
        if(seats!=null) {
            int count=0;
            int columnCheck=this.columns-c+1;
            if(columnCheck<number){
                return false;
            }
            else{
                for(int i=c-1; i<c-1+number;i++){
                    if(seats[r-1][i] == '#'){
                        count++;
                    }
                }
                if(count==number){ return true; } else return false;
            }
        }
        return false;
    }

    public void setSeat(int rows, int columns,int number){
        if(seats!=null){
            for(int i=columns-1; i<columns-1+number;i++){
                seats[rows-1][i]='*';
            }
        }
    }

    public void displaySeatChart(){
        System.out.print("       ");
        for(int c=0;c<columns;c++) {
            System.out.print(((c + 1) % 10) + " ");
        }
        System.out.println();

        for(int i=0;i<seats.length;i++){
            System.out.printf("Row %2d ",i+1);
            for(int j=0;j<seats[i].length;j++){
                System.out.print(seats[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void displayARow(int row){
        int count=0;
        System.out.print("       ");
        for(int c=0;c<columns;c++) {
            System.out.print(((c + 1) % 10) + " ");
        }
        System.out.println();
            System.out.printf("Row %2d ",row);
            for(int j=0;j<seats[row-1].length;j++){
                System.out.print(seats[row-1][j]+" ");
                if(seats[row-1][j]=='#'){
                    count++;
                }
            }
            System.out.println("\n");
        System.out.println("Row "+row+" has total "+count+" number of seats available.\n");
    }

    public double totalTicketsSales() throws IOException {
       // double[] ticketPrice=readTicketPriceFile();
        double total=0;
        for(int i=0;i<seats.length;i++){
            for(int j=0;j<seats[i].length;j++){
                if(seats[i][j]=='*'){
                    total=total+ticketPrices[i];
                }
            }
        }
        return total;
    }

    public int noOfTicketsSold(){
        int count=0;
        for(int i=0;i<seats.length;i++){
            for(int j=0;j<seats[i].length;j++){
                if(seats[i][j]=='*'){
                    count++;
                }
            }
        }
        return count;
    }

    public int totalSeatsAvailable(){
        int count=0;
        for(int i=0;i<seats.length;i++){
            for(int j=0;j<seats[i].length;j++){
                if(seats[i][j]=='#'){
                    count++;
                }
            }
        }
        return count;
    }

    public int[] seatsAvailableInRow(){
        int[] ticketInARow=new int[rows];
        for(int i=0;i<seats.length;i++){
            int count=0;
            for(int j=0;j<seats[i].length;j++){
                if(seats[i][j]=='#'){
                    count++;
                }
                ticketInARow[i]=count;
            }
        }
        return ticketInARow;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public char[][] getSeats() {
        return seats;
    }

    public static double[] getTicketPrices() {
        return ticketPrices;
    }
}
