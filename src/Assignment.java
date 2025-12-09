public class Assignment {
    public String title;
    public String description;
    public String dueDate;
    public boolean completed;

    public Assignment(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false;
    }
}
