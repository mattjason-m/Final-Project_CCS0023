package com.ccs0023.librarymanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Test, Matt JaSON!");

        Person student = new Person();

        ArrayList<Person> students = new ArrayList<>();

        student.setIdNumber(202511982);
        System.out.println(student.getIdNumber());

        input.close();

    }
}
