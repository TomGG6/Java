package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        BirthdayInvitationGenerator birthdayGenerator;
        WeedingInvitationGenerator weedingGenerator;
        Scanner input = new Scanner(System.in);
        String inputData;
        boolean run = true;

        while(run){
            showMenu();
            inputData = input.nextLine();
            if(inputData.equals("1")){
                birthdayGenerator = new BirthdayInvitationGenerator();
                birthdayGenerator.main(args);
                System.exit(0);
            }else if(inputData.equals("2")){
                weedingGenerator = new WeedingInvitationGenerator();
                weedingGenerator.main(args);
                System.exit(0);
            }else if(inputData.equals("0")){
                System.exit(0);
            }else{
                System.out.println("Wrong number!");
            }
        }

    }

    public static void showMenu(){
        System.out.println("=== Invitation generator ===");
        System.out.println("1. Birthday invitation");
        System.out.println("2. Weeding invitation");
        System.out.println("0. Quit");
        System.out.println("Enter: ");
    }
}
