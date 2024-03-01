interface GroupClassFactory {
    Group_Class createInstance();
}

public class Factory{
    private static GroupClassFactory factory = null;

    Group_Class unmodifiable(Group_Class o) {
        return new Group_Class.Decorator(o);
    }

    public static void setGroupFactory(GroupClassFactory f) {
        factory = f;
    }

    public static Group_Class createInstance() {
        return factory.createInstance();
    }
}
