package com.ui;

import com.database.DAO;

import java.sql.SQLException;
import java.util.Scanner;

public class ApplicationUI {
    private static DAO databaseDAO = new DAO();

    private static void printActions() {
        System.out.println("Akcje:");
        System.out.println("1. Zarzadzaj osobami");
        System.out.println("2. Zarządzaj wydarzeniami");
        System.out.println("3. Zarządzaj ratami");
        System.out.println("4. Zarządzaj wpłatami");
        System.out.println("5. Wyswietl nieopłacone raty");
        System.out.println("6. Wyjdz");

    }

    private static void printManagePersonsData() {
        System.out.println("Zarządzaj osobami:");
        System.out.println("1. Dodaj");
        System.out.println("2. Usuń");
        System.out.println("3. Aktualizuj");
        System.out.println("4. Wyswietl");
        System.out.println("5. Powrot");
        System.out.println("6. Wyjdz");
    }

    private static void printManageEventsData() {
        System.out.println("Zarządzaj wydarzeniami:");
        System.out.println("1. Dodaj");
        System.out.println("2. Usuń");
        System.out.println("3. Aktualizuj");
        System.out.println("4. Wyswietl");
        System.out.println("5. Powrot");
        System.out.println("6. Wyjdz");
    }

    private static void printManageInstallmentsData() {
        System.out.println("Zarządzaj ratami:");
        System.out.println("1. Dodaj");
        System.out.println("2. Usuń");
        System.out.println("3. Aktualizuj");
        System.out.println("4. Wyswietl");
        System.out.println("5. Powrot");
        System.out.println("6. Wyjdz");
    }

    private static void printManageDonationsData() {
        System.out.println("Zarządzaj wpłatami:");
        System.out.println("1. Dodaj");
        System.out.println("2. Usuń");
        System.out.println("3. Aktualizuj");
        System.out.println("4. Wyswietl");
        System.out.println("5. Powrot");
        System.out.println("6. Wyjdz");
    }

    private boolean running = true;

    public void run() throws SQLException {

        Scanner in = new Scanner(System.in);

        while (running == true) {
            printActions();

            String s = in.nextLine();
            boolean actionSelected = true;
            if (s.equals("1")) {
                while (actionSelected) {
                    printManagePersonsData();
                    s = in.nextLine();
                    if (s.equals("1")) {
                        System.out.println("Podaj imię:");
                        String newFirstName = in.nextLine();
                        System.out.println("Podaj nazwisko:");
                        String newLastName = in.nextLine();
                        databaseDAO.addPerson(newFirstName, newLastName);
                    } else if (s.equals("2")) {
                        System.out.println("Podaj indeks osoby");
                        int index = Integer.parseInt(in.nextLine());
                        databaseDAO.deletePerson(index);
                    } else if (s.equals("3")) {
                        System.out.println("Podaj indeks osoby");
                        int index = Integer.parseInt(in.nextLine());
                        System.out.println("Podaj nowe imię:");
                        String newFirstName = in.nextLine();
                        System.out.println("Podaj nowe nazwisko:");
                        String newLastName = in.nextLine();
                        databaseDAO.updatePerson(newFirstName, newLastName, index);
                    } else if (s.equals("4")) {
                        databaseDAO.showPersons();
                    } else if (s.equals("5")) {
                        actionSelected = false;
                    } else if (s.equals("6")) {
                        actionSelected = false;
                        running = false;
                    }
                }
            } else if (s.equals("2")) {
                while (actionSelected) {
                    printManageEventsData();
                    s = in.nextLine();
                    if (s.equals("1")) {
                        System.out.println("Podaj nazwę:");
                        String newName = in.nextLine();
                        System.out.println("Podaj miejsce:");
                        String newPlace = in.nextLine();
                        System.out.println("Podaj termin:");
                        String newDeadline = in.nextLine();
                        databaseDAO.addEvent(newName, newPlace, newDeadline);
                    } else if (s.equals("2")) {
                        System.out.println("Podaj indeks wydarzenia");
                        int index = Integer.parseInt(in.nextLine());
                        databaseDAO.deleteEvent(index);
                    } else if (s.equals("3")) {
                        System.out.println("Podaj indeks wydarzenia");
                        int index = Integer.parseInt(in.nextLine());
                        System.out.println("Podaj nową nazwę:");
                        String newName = in.nextLine();
                        System.out.println("Podaj nowe miejsce:");
                        String newPlace = in.nextLine();
                        System.out.println("Podaj nowy termin:");
                        String newDeadline = in.nextLine();
                        databaseDAO.updateEvent(newName, newPlace, newDeadline, index);
                    } else if (s.equals("4")) {
                        databaseDAO.showEvents();
                    } else if (s.equals("5")) {
                        actionSelected = false;
                    } else if (s.equals("6")) {
                        actionSelected = false;
                        running = false;
                    }
                }
            } else if (s.equals("3")) {
                while (actionSelected) {
                    printManageInstallmentsData();
                    s = in.nextLine();
                    if (s.equals("1")) {
                        System.out.println("Podaj indeks wydarzenia:");
                        int newEventID = Integer.parseInt(in.nextLine());
                        System.out.println("Podaj numer raty:");
                        int newNumber = Integer.parseInt(in.nextLine());
                        System.out.println("Podaj termin płatności:");
                        String newPaymentDeadline = in.nextLine();
                        databaseDAO.addInstallment(newEventID, newNumber, newPaymentDeadline);
                    } else if (s.equals("2")) {
                        System.out.println("Podaj indeks raty:");
                        int index = Integer.parseInt(in.nextLine());
                        databaseDAO.deleteInstallment(index);
                    } else if (s.equals("3")) {
                        System.out.println("Podaj indeks raty:");
                        int index = Integer.parseInt(in.nextLine());
                        System.out.println("Podaj nowy indeks wydarzenia:");
                        int newEventID = Integer.parseInt(in.nextLine());
                        System.out.println("Podaj nowy numer raty:");
                        int newNumber = Integer.parseInt(in.nextLine());
                        System.out.println("Podaj nowy termin płatności:");
                        String newPaymentDeadline = in.nextLine();
                        databaseDAO.updateInstallment(newEventID, newNumber, newPaymentDeadline, index);
                    } else if (s.equals("4")) {
                        databaseDAO.showInstallments();
                    } else if (s.equals("5")) {
                        actionSelected = false;
                    } else if (s.equals("6")) {
                        actionSelected = false;
                        running = false;
                    }
                }
            } else if (s.equals("4")) {
                while (actionSelected) {
                    printManageDonationsData();
                    s = in.nextLine();
                    if (s.equals("1")) {
                        System.out.println("Podaj datę wpaty:");
                        String newPaymentDate = in.nextLine();
                        System.out.println("Podaj kwotę:");
                        int newAmount = Integer.parseInt(in.nextLine());
                        System.out.println("Podaj indeks osoby:");
                        int newPersonID = Integer.parseInt(in.nextLine());
                        System.out.println("Podaj indeks wydarzenia:");
                        int newEventID = Integer.parseInt(in.nextLine());
                        System.out.println("Podaj numer raty:");
                        int newInstallmentNumber = Integer.parseInt(in.nextLine());
                        databaseDAO.addDonation(newPaymentDate, newAmount, newPersonID, newEventID, newInstallmentNumber);
                    } else if (s.equals("2")) {
                        System.out.println("Podaj indeks wpłaty:");
                        int index = Integer.parseInt(in.nextLine());
                        databaseDAO.deleteDonation(index);
                    } else if (s.equals("3")) {
                        System.out.println("Podaj indeks raty:");
                        int index = Integer.parseInt(in.nextLine());
                        System.out.println("Podaj datę wpaty:");
                        String newPaymentDate = in.nextLine();
                        System.out.println("Podaj kwotę:");
                        int newAmount = Integer.parseInt(in.nextLine());
                        System.out.println("Podaj indeks osoby:");
                        int newPersonID = Integer.parseInt(in.nextLine());
                        System.out.println("Podaj indeks wydarzenia:");
                        int newEventID = Integer.parseInt(in.nextLine());
                        System.out.println("Podaj numer raty:");
                        int newInstallmentNumber = Integer.parseInt(in.nextLine());
                        databaseDAO.updateDonation(newPaymentDate, newAmount, newPersonID, newEventID, newInstallmentNumber, index);
                    } else if (s.equals("4")) {
                        databaseDAO.showDonations();
                    } else if (s.equals("5")) {
                        actionSelected = false;
                    } else if (s.equals("6")) {
                        actionSelected = false;
                        running = false;
                    }
                }
            }else if(s.equals("5")){
                DAO.showUnpaidInstallments();
            }
        }
    }

        public static void main (String[]args) throws SQLException {
            ApplicationUI ui = new ApplicationUI();
            ui.run();
        }
    }
