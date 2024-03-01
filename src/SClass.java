import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SClass implements Group_Class, Serializable,Iterable<Student>  {
    private Student[] students;
    private String className;
    private Integer classSize;

    public static final SClassFactory classFactory = new SClassFactory();

    public SClass(){
        this.className = "11";
        this.classSize = 2;
        this.students = new Student[2];
        this.students[0] = new Student("Sam",20);
        this.students[1] = new Student("Alex",29);
    }

    public SClass(Student[] students, String className, Integer classSize) {
        this.students = students;
        this.className = className;
        this.classSize = classSize;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public String getName() {
        return className;
    }

    public void setName(String name) {
        this.className = name;
    }

    public int getSize() {
        return classSize;
    }

    public void setSize(Integer classSize) {
        this.classSize = classSize;
    }

    public int getAverageCourse() {
        int sum = 0;
        for (Student student : students) {
            sum += student.getAge();
        }
        return sum / classSize;
    }

    public void full_info(){
        System.out.println("Номер класса "+ className + ", Количество людей в классе: "+ classSize);
        System.out.println("Список учеников:");
        for(Student st: students){
            System.out.println("Имя: "+st.getName() + ", Возраст:" + st.getAge());
        }
    }

    @Override
    public String toString() {
        return "Класс: " + className + ", количество учеников: " + classSize;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SClass sclass = (SClass) obj;
        for (int i = 0; i < this.classSize; i++){
            if (!this.students[i].equals(sclass.students[i])) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(className, classSize);
        result = 31 * result + Arrays.hashCode(students);
        return result;
    }

    @Override
    public void output(OutputStream out) throws IOException {
        byte[] buffer;
        buffer = ("SClass" + '\n').getBytes();
        out.write(buffer, 0, buffer.length);
        buffer = (getName()+'\n').getBytes();
        out.write(buffer,0,buffer.length);
        buffer = (Integer.toString(classSize)+'\n').getBytes();
        out.write(buffer,0,buffer.length);
        for(Student st: this.students){
            buffer = (st.getName()+'\n').getBytes();
            out.write(buffer,0,buffer.length);
            buffer = (Integer.toString(st.getAge())+'\n').getBytes();
            out.write(buffer,0,buffer.length);
        }
        out.close();
    }
    @Override
    public void write(Writer out) throws IOException {
        out.write("SClass"+ '\n');
        out.write(getName()+ '\n');
        out.write(getSize()+ '\n');
        for(Student st : this.students){
            out.write(st.getName()+ '\n');
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

                return current != classSize;
            }

            @Override
            public Student next() {
                if(!hasNext()) throw new NoSuchElementException();
                return students[current++];
            }
        };
    }

    private static class SClassFactory implements GroupClassFactory {
        @Override
        public Group_Class createInstance() {
            return new SClass();
        }
    }
}
