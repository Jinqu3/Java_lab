import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;

public interface Group_Class {
    // Методы доступа к полям объектов
    Student[] getStudents();

    void setStudents(Student[] students);

    String getName();

    void setName(String name);

    int getSize();

    void setSize(Integer size);

    int getAverageCourse();

    void full_info();

    void output(OutputStream out) throws IOException;

    void write(Writer out) throws IOException;


    abstract class IteratorGroups implements Iterator<Student> {
        @Override
        public abstract boolean hasNext();

        @Override
        public abstract Student next();
    }

    class Decorator implements Group_Class {
        Group_Class obj;

        public Decorator(Group_Class obj) {
            this.obj = obj;
        }
        @Override
        public Student[] getStudents() {
            return obj.getStudents();
        }

        @Override
        public void setStudents(Student[] students) {
            throw new UnsupportedOperationException();
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public void setName(String name) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int getSize() {
            return 0;
        }

        @Override
        public void setSize(Integer size) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int getAverageCourse() {
            return 0;
        }

        @Override
        public void full_info() {

        }
        @Override
        public void output(OutputStream out) throws IOException {
            obj.output(out);
        }

        @Override
        public void write(Writer out) throws IOException {
            obj.write(out);
        }

    }
}

