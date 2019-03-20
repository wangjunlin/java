public class MyInterfaceImpl implements MyInterface, MyInterface1 {
    private String test;

    private void setTest(String test) {
        this.test = test;
        System.out.println("set test : " + test);
    }

    @Override
    public void Test() {
        System.out.println(MyInterfaceImpl.class.getName() + "println test");
    }

    @Override
    public void Test1() {

    }
}
