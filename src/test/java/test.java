import com.lingzst.webo2o.exceptions.ShopOperationException;

public class test extends base{
//    public void testMethodInit() {
//        int i;
//        System.out.printf("extends");
//    }
    public static void main(String[] args) {

        base a = new test();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}


class base {
    int a = 1;
}