
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import CampApplication.users.*;
import CampApplication.Camps.*;
import CampApplication.Utilities.*;

import java.util.ArrayList;

public class menu {
    // the ArrayList used to store all staff objects created
    static ArrayList<Staff> staffList = new ArrayList<>();
    // the ArrayList used to store all student objects created
    static ArrayList<Student> studentList = new ArrayList<>();
    // the ArrayList used to store all camp objects created
    static ArrayList<Camp> campList = new ArrayList<>();

    /**
     * print out all the camps created by all staff
     */
    public static void printAllCamps() {
        for (Camp camp : campList) {
            System.out.println(camp.getCampName());
        }
    }

    /**
     * generate userID from their email, which is the characters before '@'
     * character
     * 
     * @param email user's email
     * @return return userID as String
     */
    public static String getUsernamefromEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex != -1) {
            return email.substring(0, atIndex);
        } else {
            return email; // If there's no "@" symbol, return the whole email as the username
        }
    }

    /**
     * reads in external file to create staff and student objects
     * 
     * @throws IOException throws error message when unexpected input is read in
     */
    public static void createObjects() throws IOException {
        String searchLine;
        BufferedReader br = new BufferedReader(new FileReader("./staff_list.csv"));
        while ((searchLine = br.readLine()) != null) {
            String[] staffValue = searchLine.split(",");
            String storedUserID = getUsernamefromEmail(staffValue[1]);
            // new object of staff with parameters name, email, faculty, userID, and2
            // password
            staffList.add(new Staff(staffValue[0], staffValue[1], staffValue[2], storedUserID, "password"));
        }
        br.close();

        BufferedReader br2 = new BufferedReader(new FileReader("./student_list.csv"));
        while ((searchLine = br2.readLine()) != null) {
            String[] studentValue = searchLine.split(",");
            String storedUserID = getUsernamefromEmail(studentValue[1]);
            // new object of staff with parameters name, email, faculty, userID, and
            // password
            studentList.add(new Student(studentValue[0], studentValue[1], studentValue[2], storedUserID, "password"));
        }
        br2.close();
    }

    /**
     * checks the existence of user object in staffList and studentList
     * 
     * @param staff indicate whether a staff object is input
     * @return return the staff or studenet object iffound, -1 otherwise
     */
    public static int checkUserDatabase(boolean staff) {
        ArrayList<? extends User> userList = new ArrayList<>();
        if (staff) {
            userList = staffList;
        } else {
            userList = studentList;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Input \"/\" to exit");
        String userIDInput = GetString.getString("UserID: ");
        while (userIDInput.compareTo("/") != 0) {
            int i = 0;
            while (i < userList.size()) {
                if (userIDInput.equals(userList.get(i).getUserId())) {
                    int triesleft = 3;
                    do {
                        System.out.println("Password: ");
                        String passwordInput = sc.nextLine();
                        if (passwordInput.equals(userList.get(i).getPassword())) {
                            System.out.println("\nLogin Successful!\n");
                            // go to login page for staff
                            return i;
                        } else {
                            System.out.println("Incorrect password! Try again! Tries left:" + --triesleft);
                        }
                    } while (triesleft > 0);
                    System.out.println("Password Incorrect, directing to login page...");
                    return -1;
                }
                i++;
            }

            System.out.println("No such available UserID in database!");
            userIDInput = GetString.getString("UserID: ");

        }

        return -1;
    }

    /**
     * displays the student user interface and prompt student to make decisions
     * 
     * @param student returns the student object
     */
    public static void StudentMenu(Student student) {
        int choice;
        final int logOutIndex = 5;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome " + student.getName() + "!");
        if (student.getPassword() == "password") {
            System.out.println("Please change your password on your first login.");
            student.changePassword();
            System.out.println("Logging you out. Please log in again.\n\n");
        } else
            do {
                PrintStudentMenu.printStudentMenu(student.getName());
                choice = GetInt.getInt("\nEnter your choice:");
                switch (choice) {
                    case (1):
                        student.changePassword();
                        System.out.println("Logging you out. Please log in again.\n\n");
                        choice = logOutIndex;
                        break;
                    case (2):
                        student.viewCamp(campList, sc);
                        break;
                    case (3):
                        int registerCampIndex = student.registerCamp(campList);
                        if (registerCampIndex > 0) {
                            campList.get(registerCampIndex).addAttendee(student);
                        }
                        break;
                    case (4):
                        student.campInterface(campList);
                        break;
                    case (logOutIndex):
                        System.out.println("Logging out...\n\n");
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            } while (choice != logOutIndex);
    }

    /**
     * displays staff user interface and prompt staff to make decisions
     * 
     * @param staff returns the staff object
     */
    public static void StaffMenu(Staff staff) {
        int choice;
        final int logOutIndex = 5;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome " + staff.getName() + "!");
        if (staff.getPassword() == "password") {
            System.out.println("Please change your password on your first login.");
            staff.changePassword();
            System.out.println("Logging you out. Please log in again.\n\n");
        } else
            do {
                PrintStaffMenu.printStaffMenu(staff.getName());
                choice = GetInt.getInt("\nEnter your choice:");
                switch (choice) {
                    case (1):
                        staff.changePassword();
                        System.out.println("Logging you out. Please log in again.\n\n");
                        choice = logOutIndex;
                        break;
                    case (2):
                        staff.viewCamp(campList, sc);
                        break;
                    case (3):
                        staff.viewOwnCamp();
                        break;
                    case (4):
                        Camp camp = staff.createCamp();
                        campList.add(camp);
                        break;
                    case (logOutIndex):
                        System.out.println("Logging out...\n\n");
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            } while (choice != logOutIndex);
    }

    /**
     * function to initiate the application, it will first display the login page
     */
    public static void login2() {
        Scanner sc = new Scanner(System.in);
        int temp;
        // starting screen for users
        do {
            do {
                System.out.println("======   LOG IN PAGE ======\n");
                temp = GetInt.getInt("Enter (1) if you are a student, Enter (2) if you are a staff");
                if (temp != 1 && temp != 2) {
                    System.out.println("Invalid entry! Please enter 1 or 2!");
                }
            } while (temp != 2 && temp != 1);
            int index;

            if (temp == 2) {
                index = checkUserDatabase(true);
                if (index == -1) {
                    continue;
                }
                Staff staff = staffList.get(index);
                StaffMenu(staff);

            } else {
                index = checkUserDatabase(false);
                if (index == -1) {
                    continue;
                }
                Student student = studentList.get(index);
                StudentMenu(student);
            }
        } while (true);

    }

    public static void main(String[] args) throws IOException {
        createObjects();
        login2();
    }

}