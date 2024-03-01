import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Group implements Group_Class, Serializable,Iterable<Student> {
    private Student[] students;
    private String groupName;
    private Integer groupSize;

    public static final GroupFactory groupFactory = new GroupFactory();

    public Group(){
        this.groupName = "4345";
        this.groupSize = 2;
        this.students = new Student[2];
        this.students[0] = new Student("Danil",20);
        this.students[1] = new Student("Sanya",20);
    }
    public Group(Student[] students, String groupName, Integer groupSize) {
        this.students = students;
        this.groupName = groupName;
        this.groupSize = groupSize;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public String getName() {
        return groupName;
    }

    public void setName(String groupName) {
        this.groupName = groupName;
    }

    public int getSize() {
        return groupSize;
    }

    public void setSize(Integer size) {
        this.groupSize = size;
    }

    public int getAverageCourse() {
        int sum = 0;
        for (Student student : students) {
            sum += student.getAge();
        }
        return sum / groupSize;
    }

    public void full_info(){
        System.out.println("Номер группы "+ groupName + ", Количество людей в группе: "+ groupSize);
        System.out.println("Список cтудентов:");
        for(Student st: students){
            System.out.println("Имя: "+st.getName() + ", Возраст:" + st.getAge());
        }
    }

    @Override
    public String toString() {
        return "Группа: " + groupName + ", количество студентов: " + groupSize;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Group group = (Group) obj;
        return Arrays.equals(this.students, group.students) && this.groupSize == group.getSize() && Objects.equals(this.groupName, group.getName());

//        for (int i = 0; i < this.groupSize; i++){
//            if (!this.students[i].equals(group.students[i])) return false;
//        }
//        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(groupName, groupSize);
        result = 31 * result + Arrays.hashCode(students);
        return result;
    }

    @Override
    public void output(OutputStream out) throws IOException {
        byte[] buffer;
        buffer = ("Group" + '\n').getBytes();
        out.write(buffer, 0, buffer.length);
        buffer = (getName() + '\n').getBytes();
        out.write(buffer, 0, buffer.length);
        buffer = (Integer.toString(groupSize) + '\n').getBytes();
        out.write(buffer, 0, buffer.length);
        for (Student st : this.students) {
            buffer = (st.getName() + '\n').getBytes();
            out.write(buffer, 0, buffer.length);
            buffer = (Integer.toString(st.getAge()) + '\n').getBytes();
            out.write(buffer, 0, buffer.length);
        }
        out.close();
    }
    @Override
    public void write(Writer out) throws IOException {
        out.write("Group"+ '\n');
        out.write(getName()+ '\n');
        out.write(Integer.toString(getSize())+ '\n');
        for(Student st : this.students){
            out.write(st.getName() + '\n');
            out.write(st.toString()+ '\n');
        }
        out.close();
    }


    @Override
    public Iterator<Student> iterator() {
        return new IteratorGroups() {
            int current = 0;
            @Override
            public boolean hasNext() {
                return current != groupSize;
            }

            @Override
            public Student next() {
                if(!hasNext()) throw new NoSuchElementException();
                return students[current++];
            }
        };
    }

    private static class GroupFactory implements GroupClassFactory {
        @Override
        public Group_Class createInstance() {
            return new Group();
        }
    }
}
