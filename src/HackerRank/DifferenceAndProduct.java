package HackerRank;

import java.util.Scanner;

public class DifferenceAndProduct {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the count of lines ");
        int lines = scanner.nextInt();

        for(int i = 0; i < lines; i++) {
            System.out.print("Enter the first number ");
            int firstNumber = scanner.nextInt();
            System.out.println("Enter the second number ");
            int secondNumber = scanner.nextInt();
           int numberCount =  solve(firstNumber, secondNumber);
            System.out.println("Number of pairs: " + numberCount);
        }

    }

    public static int solve(int d, int p) {
        int a;
        int b = 1;
        int reverse_a;
        int reverse_b;
        int finalCount =0;

        double squareRoot = Math.sqrt(Math.abs(p));
        if(d<0){
            return finalCount;
        }

        if(p<0){
            while(b<= squareRoot){
                a = p/b;
                if(a*b==p){
                    reverse_a = -a;
                    reverse_b = -b;
                    if(Math.abs(a-b)==d){
                        finalCount = finalCount +2 ;
                    }
                    if(Math.abs(reverse_a - reverse_b)==d){
                        finalCount = finalCount +2 ;
                    }
                }
                b++;
            }
        }else if(p>0){
            while(b<=squareRoot){
                a = p/b;
                if(a*b==p){
                    reverse_a = -a;
                    reverse_b = -b;
                    if(Math.abs(a-b)==d){
                        if(a!=b){
                            finalCount = finalCount +2 ;
                            System.out.println(a + " " + b);
                            System.out.println(b + " " + a);
                        }else{
                            finalCount++;
                            System.out.println(a + " " + b);
                        }
                    }
                    if(Math.abs(reverse_a - reverse_b)==d){
                        if(reverse_a!= reverse_b){
                            finalCount = finalCount +2 ;
                            System.out.println(reverse_a + " r " + reverse_b);
                            System.out.println(reverse_b + " r " + reverse_a);
                        }else{
                            finalCount++;
                            System.out.println(reverse_a + " r " + reverse_b);
                        }
                    }

                }
                b++;
            }
        }else{
            if(d == 0){
                finalCount=finalCount+1;
            }else{
                finalCount = finalCount+4;
            }
        }
        System.out.println(finalCount);
        return finalCount;
    }



}
