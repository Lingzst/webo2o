import com.lingzst.webo2o.exceptions.ShopOperationException;

public class Test extends Parent{
//    public void testMethodInit() {
//        int i;
//        System.out.printf("extends");
//    }


    public Test() {
        normalMethod();
    }

    public void normalMethod() {
        System.out.println("test normalMethodCall");
    }

    @Override
    public void callParentMethod() {
        System.out.println("overrided method");
    }

    public void howToCallParent() {
        super.callParentMethod();
    }

    public static void main(String[] args) {

        Parent a =(Parent) new Test();
        a.callParentMethod();
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println("f");
        System.out.println(" ");

    }
}


class Parent {
    int a = 1;

    public void callParentMethod() {
        System.out.println("call  parent method");
    }
}