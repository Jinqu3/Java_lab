import java.io.Serializable;
import java.util.Iterator;

public class Student implements Serializable {
    private String name;
    private Integer age;


    public Student( String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.age.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Student pare = (Student) obj;
        return this.name.equals(pare.name) && this.age == pare.age;
    }


}

