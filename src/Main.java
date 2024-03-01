import java.io.*;
import java.util.*;

class ClassException extends Exception {
    public ClassException(String message){super(message);}
}

class ClassRuntimeException extends RuntimeException {
    public ClassRuntimeException(String message){super(message);}
}


public class Main  {
    public static Student [] creator(int num,int max,int min){
        Random rand = new Random();
        Student [] students = new Student[num];
        for(int i =0;i < num;++i){
            students[i] = new Student(Integer.toString(i),rand.nextInt(max - min + 1) + min);
        }
        return students;
    }
    public static void ex1() throws ClassException {
        Random rand = new Random();
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество групп/классов");
        int size_groups =in.nextInt();
        if(size_groups < 0)throw new ClassException("size cannot be negative or zero");
        Group_Class [] groups = new Group_Class[size_groups];
        int count_group = 0,count_class = 0;
        for(int i =0;i < size_groups;++i){
            System.out.println("Создать группу - 1 или Создать класс - 2");
            if(in.nextInt() == 1){
                int num = rand.nextInt(9599 - 1111 + 1) + 1111;
                int size = rand.nextInt(40 - 14 + 1) + 14;
                groups[i]=new Group(creator(size,25,18),Integer.toString(num),size);
                System.out.println("Группа " + num + " создана");
                count_group++;
            }
            else {
                int size = rand.nextInt(30 - 14 + 1) + 14;
                int num = rand.nextInt(11 - 1 + 1) + 1;
                groups[i]=new SClass(creator(size,18,7),Integer.toString(num),size);
                System.out.println(num + " класс создан");
                count_class++;
            }
        }

        //Разбиение на массива по разным классам
        int classes_i = 0,groups_i = 0;
        Group_Class [] classes_ = new SClass[count_class];
        Group_Class [] groups_ = new Group[count_group];

        for(Group_Class gr : groups){
            if(gr.getClass()==SClass.class){
                classes_[classes_i] = gr;
                classes_i++;
            }
            else {
                groups_[groups_i] = gr;
                groups_i++;
            }
        }
    }

    public static void ex2() throws IOException {
        System.out.println();

        Group_Class a = new Group(creator(2,25,18),"test1",2);
        OutputStream o = new FileOutputStream("F:\\oop_1\\test1.bin");
        In_Out.output(a,o);
        FileInputStream i =new FileInputStream("F:\\oop_1\\test1.bin");
        Group_Class b = In_Out.input(i);
        System.out.println(a.equals(b));

        Group_Class c = new Group(creator(3,25,18),"test2",3);
        In_Out.write(c, new FileWriter("F:\\oop_1\\test3.txt"));
        Group_Class d = In_Out.read(new FileReader("F:\\oop_1\\test3.txt"));
        System.out.println(c.equals(d));

        Group_Class e = new Group(creator(4,25,18),"test3",4);
        In_Out.serialize(c, "F:\\oop_1\\test2.data");
        Group_Class f = In_Out.deserialize("F:\\oop_1\\test2", "Group");
        System.out.println(e.equals(f));
    }

    private static void ex3() {
        System.out.println();

        Group e = new Group(creator(4,25,18),"test4",4);
        for (Student st : e) {
            System.out.print(st.getName());
            System.out.println(" "+st.getAge());
        }


        Group_Class.Decorator h = new Group_Class.Decorator(e);


        System.out.println();

        Factory.setGroupFactory(Group.groupFactory);
        Group_Class a = Factory.createInstance();
        System.out.println(a);
    }


    public static void main(String[] args) throws IOException, ClassException {
        //ex1();
        //ex2();
        //ex3();
    }
}
