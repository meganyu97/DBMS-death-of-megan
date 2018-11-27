
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.sql.*;
import java.util.Scanner;

//this class has all the methods that take in the input from the CSV file and exports it to the database

public class DbUtil {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;


    public void addStudent(Connection con, String name, String Address, String Email)
    { //allows user to add student details and this method inserts this student into the workbench DB
        try
        {
            PreparedStatement pstInsert = con.prepareStatement("INSERT INTO student(Name, Address, Email) " +
                    " VALUES(?,?,?);");

            pstInsert.clearParameters();
            pstInsert.setString(1, name);
            pstInsert.setString(2, Address);
            pstInsert.setString(3, Email);

            pstInsert.executeUpdate();
            //System.out.println("STUDENT ADD SUCCESSFUL");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addStudentCourse(Connection con, int Studentid_, int Courseid_, int Instructorid_)
    { //allows user to add student course details and this method inserts this student into the workbench DB
        try
        {
            PreparedStatement pstInsert = con.prepareStatement("INSERT INTO studentcourse(StudentId, CourseId, InstructorId) " +
                    " VALUES(?,?,?);");

            pstInsert.clearParameters();
            pstInsert.setInt(1, Studentid_);
            pstInsert.setInt(2, Courseid_);
            pstInsert.setInt(3, Instructorid_);

            pstInsert.executeUpdate();
            //System.out.println("STUDENT COURSE ADD SUCCESSFUL");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public int returnStudentId(Connection con, String name_, String Address_, String Email_)
    {
        int i = 0;

        try
        {
            PreparedStatement pstInsert = con.prepareStatement("SELECT StudentId FROM student WHERE Name =? AND Address =? AND Email = ?");


            pstInsert.clearParameters();
            pstInsert.setString(1, name_);
            pstInsert.setString(2, Address_);
            pstInsert.setString(3, Email_);
            ResultSet rs2 = pstInsert.executeQuery();
            while (rs2.next())
            {
                i =  rs2.getInt("StudentId");

            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return i;

    }

    public int returnCourseId(Connection con, String name_)
    { //finds course name and returns corresponding course ID
        int i =0;

        try
        {
            PreparedStatement pstInsert = con.prepareStatement("SELECT CourseId FROM course WHERE CourseName =? ");


            pstInsert.clearParameters();
            pstInsert.setString(1, name_);
            ResultSet rs2 = pstInsert.executeQuery();
            while (rs2.next())
            {
                i =  rs2.getInt("CourseId");

            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return i;

    }


    public int returnInstructorId(Connection con, String name_, String Address_, String Email_)
    { //returns instructor details from the workbench DB
        int i = 0;

        try
        {
            PreparedStatement pstInsert = con.prepareStatement("SELECT InstructorId FROM Instructor WHERE Name =? AND Address =? AND Email = ?");


            pstInsert.clearParameters();
            pstInsert.setString(1, name_);
            pstInsert.setString(2, Address_);
            pstInsert.setString(3, Email_);
            ResultSet rs2 = pstInsert.executeQuery();
            while (rs2.next())
            {
                i =  rs2.getInt("InstructorId");

            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return i;

    }
    public int returnDepartmentId(Connection con, String name_)
    { //returns department ID from workbench DB
        int i =0;

        try
        {
            PreparedStatement pstInsert = con.prepareStatement("SELECT DepartmentId FROM department WHERE DepartmentName =? ");


            pstInsert.clearParameters();
            pstInsert.setString(1, name_);
            ResultSet rs2 = pstInsert.executeQuery();
            while (rs2.next())
            {
                i =  rs2.getInt("DepartmentId");

            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return i;

    }

    public void addInstructor(Connection con, String iname, String iAddress, String iEmail)
    { //allows user to add instructor details and this method inserts this instructor into the workbench DB
        try
        {
            PreparedStatement pstInsert = con.prepareStatement("INSERT INTO Instructor(Name, Address, Email) " +
                    " VALUES(?,?,?);");

            pstInsert.clearParameters();
            pstInsert.setString(1, iname);
            pstInsert.setString(2, iAddress);
            pstInsert.setString(3, iEmail);

            pstInsert.executeUpdate();
            //System.out.println("INSTRUCTOR ADD SUCCESSFUL");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addDepartment(Connection con, String name)
    { //allows user to add department details and this method inserts this department into the workbench DB
        try
        {
            PreparedStatement pstInsert = con.prepareStatement("INSERT INTO Department(DepartmentName) " +
                    " VALUES(?);");

            pstInsert.clearParameters();
            pstInsert.setString(1, name);

            pstInsert.executeUpdate();
            //System.out.println("DEPARTMENT ADD SUCCESSFUL");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addCourse(Connection con, String name)
    { //allows user to add course details and this method inserts this course into the workbench DB
        try
        {
            PreparedStatement pstInsert = con.prepareStatement("INSERT INTO Course(CourseName) " +
                    " VALUES(?);");

            pstInsert.clearParameters();
            pstInsert.setString(1, name);

            pstInsert.executeUpdate();
            //System.out.println("COURSE ADD SUCCESSFUL");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
