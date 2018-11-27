import org.fluttercode.datafactory.NameDataValues;
import org.fluttercode.datafactory.impl.DefaultNameDataValues;

public class CourseDeptNames  {

    //first name values to use
    String[] courseNames = {"History","Calculus","Economics","Biology"};
    String[] deptNames = {"Social Science","Math","Business","Science"};

    //delegate to the default implementation for the other values
    //NameDataValues defaults = new DefaultNameDataValues();

    public String getCourseNames(int i) {
        //return our custom list of names
        return courseNames[i];
    }

   // for the other values, just use the defaults
    public String getDeptNames(int i) {
        return deptNames[i];
    }


}
