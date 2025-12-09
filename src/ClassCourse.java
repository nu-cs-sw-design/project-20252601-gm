import java.util.ArrayList;
import java.util.List;

public class ClassCourse {
    public String name;
    public String meetingDay;
    public String meetingTime;
    public List<Assignment> assignments;

    public ClassCourse(String name, String meetingDay, String meetingTime) {
        this.name = name;
        this.meetingDay = meetingDay;
        this.meetingTime = meetingTime;
        this.assignments = new ArrayList<>();
    }
}
