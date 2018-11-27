/*
   Megan Yu
   ID: 2263079
   yu204@mail.chapman.edu
   CPSC-408
   Assignment 3
   4/16/18
*/

import org.fluttercode.datafactory.impl.DataFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Scanner sc = new Scanner(System.in);
        System.out.println("Please write the csv file name: ");

        String fileName = sc.nextLine();//"/Users/irismgne/Desktop/data_generate.csv";

        try
        {

            Scanner sc2 = new Scanner(System.in);
            System.out.println("Please write number of tuples: ");

            Integer numberofTuples = sc2.nextInt();

        FileWriter writer = new FileWriter(fileName);

        writer.append("StudentName");
        writer.append(',');
        writer.append("StudentAddress");
        writer.append(',');
        writer.append("StudentEmail");
        writer.append(',');
        writer.append("InstructorName");
        writer.append(',');
        writer.append("InstructorAddress");
        writer.append(',');
        writer.append("InstructorEmail");
        writer.append(',');
        writer.append("CourseName");
        writer.append(',');
        writer.append("DepartmentName");
        writer.append('\n');

        DataFactory df = new DataFactory();
        DataFactory df2 = new DataFactory();
        DataFactory df3 = new DataFactory();

        int course_counter = 0;

        for (int i = 0; i < numberofTuples; i++) {
            String s_name = df.getFirstName() + " "+ df.getLastName();
            String s_address = df.getAddress()+" - "+df.getCity()+" - "+df.getNumberText(5);
            String s_email = s_name.replace(" ","_") + "@gmail.com";

            String i_name = df.getFirstName() + " "+ df.getLastName();
            String i_address = df.getAddress()+" - "+df.getCity()+" - "+df.getNumberText(5);
            String i_email = i_name.replace(" ","_") + "@gmail.com";


            CourseDeptNames df5 = new CourseDeptNames();
            String coursename = df5.getCourseNames(course_counter);
            String DepartmentName = df5.getDeptNames(course_counter);

            course_counter++;
            if (course_counter>3){
                course_counter = 0;
            }

            writer.append(s_name);
            writer.append(',');
            writer.append(s_address);
            writer.append(',');
            writer.append(s_email);
            writer.append(',');
            writer.append(i_name);
            writer.append(',');
            writer.append(i_address);
            writer.append(',');
            writer.append(i_email);
            writer.append(',');
            writer.append(coursename);
            writer.append(',');
            writer.append(DepartmentName);
            writer.append('\n');

            }

            writer.flush();
            writer.close();

        } catch(IOException e) {
            e.printStackTrace();
        }

        Connection con = null;
        try {
            con = dbConfig.getMySqlConnection();

            if (con.isClosed()) {
                System.out.println("Connection was closed. creating new connection to MySQL");
                con = dbConfig.getMySqlConnection();
            }

            DbUtil class2 = new DbUtil();


            String csvFile = fileName;
            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";
            int counter = 0;

            CourseDeptNames cd =  new CourseDeptNames();

            for (String s : cd.courseNames){
                class2.addCourse(con, s);
            }

            for (String s : cd.deptNames){
                class2.addDepartment(con, s);
            }

            try {

                br = new BufferedReader(new FileReader(csvFile));
                while ((line = br.readLine()) != null) {

                    if (counter > 0) {
                        // use comma as separator
                        String[] row = line.split(cvsSplitBy);

                        String name = row[0];
                        String address = row[1];
                        String email = row[2];
                        class2.addStudent(con, name, address, email);
                        int studentid = class2.returnStudentId(con, name, address, email);

                        String i_name = row[3];
                        String i_address = row[4];
                        String i_email = row[5];
                        class2.addInstructor(con, i_name, i_address, i_email);
                        int instructor_id = class2.returnInstructorId(con, i_name, i_address, i_email);

                        String coursename_ =  row[6];
                        int CourseId = class2.returnCourseId( con, coursename_);

                        String deptname_ =  row[7];
                        int DeptId = class2.returnDepartmentId( con, deptname_);

                        class2.addStudentCourse(con, studentid, CourseId, instructor_id) ;
                    }
                    counter++;
                    }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

}
