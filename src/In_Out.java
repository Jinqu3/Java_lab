import java.io.*;
import java.util.Objects;

public class In_Out {
    //запись в байтовый поток
    public static void output(Group_Class groups, OutputStream out) throws IOException { groups.output(out); }

    //чтение из байтового потока
    public static Group_Class input(InputStream in) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String type = bufferedReader.readLine();
        String name = bufferedReader.readLine();
        Integer size = Integer.valueOf(bufferedReader.readLine());

        Student [] students = new Student[size];

        for(int i =0;i<size;++i){
            String name_s = bufferedReader.readLine();
            Integer age_s = Integer.valueOf(bufferedReader.readLine());
            students[i] = new Student(name_s,age_s);
        }

        bufferedReader.close();
        if (Objects.equals(type, "SClass")) return new SClass(students,name,size);
        if (Objects.equals(type, "Group")) return new Group(students,name,size);
        return null;
    }
    //записи в символьный поток
    public static void write(Group_Class groups, Writer out) throws IOException {groups.write(out); }

    //чтение из символьного потока
    public static Group_Class read(Reader in) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(in);
        String type = bufferedReader.readLine();
        String name = bufferedReader.readLine();
        Integer size = Integer.parseInt(bufferedReader.readLine());

        Student [] students = new Student[size];

        for(int i =0;i<size;++i){
            String name_s = bufferedReader.readLine();
            Integer age_s = Integer.valueOf(bufferedReader.readLine());
            students[i] = new Student(name_s,age_s);
        }
        bufferedReader.close();
        if (type.equals("SClass")) return new SClass(students,name,size);
        if (type.equals("Group")) return new Group(students,name,size);
        return null;
    }

    public static void serialize(Group_Class groups, String path){
        try (FileOutputStream fos = new FileOutputStream(path)){
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(groups);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static Group_Class deserialize(String path, String type) throws InvalidObjectException {
        try (FileInputStream fis = new FileInputStream(path)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            if (type.equals("SClass")) return (SClass) ois.readObject();
            if (type.equals("Group")) return (Group) ois.readObject();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        throw new InvalidObjectException("Problems with the name of the object type in the file");
    }

}