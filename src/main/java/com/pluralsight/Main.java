package com.pluralsight;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try
        {
// create a FileReader object connected to the File
            FileReader fileReader = new FileReader("csvfiles/employees.csv");
// create a BufferedReader to manage input stream
            BufferedReader bufReader = new BufferedReader(fileReader);
            String input;

            Scanner keyboard = new Scanner(System.in);
            System.out.println("What is the name of the file?");
            String customEmployee = keyboard.nextLine();

            while((input = bufReader.readLine()) != null) {
                if (input.startsWith("id")){
                    continue;
                }
                 String[] parts = input.split("\\|");

                String employeeID = parts[0];
                String name = parts[1];
                double hoursWorked = Double.parseDouble(parts [2]);
                double payRate = Double.parseDouble(parts[3]);

                Employee employee = new Employee(employeeID, name, hoursWorked, payRate);
                //

                try {


                    FileWriter fileWriter = new FileWriter("src/main/resources/" +customEmployee , true);
                    // create a BufferedWriter
                    BufferedWriter bufWriter = new BufferedWriter(fileWriter);
                    // write to the file
                    String text;
                    {
                text = String.format("Employee ID: %s, Name: %s, Gross Pay: $%.2f\n",
                        employee.getEmployeeID(), employee.getName(), employee.getGrossPay());
                        bufWriter.write(text);
                    }
                    // close the writer
                    bufWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
// close the stream and release the resources
            bufReader.close();
        }
        catch(IOException e) {
// display stack trace if there was an error
            e.printStackTrace();
        }
    }
}