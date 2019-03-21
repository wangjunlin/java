/**
 * 类加载顺序:
 * 虚拟机设置b=0，st=null
 * 初始化阶段，main()方法触发初始化主类StaticTest
 * 按照顺序
 * 初始化StaticTest类的静态部分，即st
 * 此时静态初始化还没完成，就要初始化实例
 * 从java角度，一旦开始初始化静态部分，无论是否完成，后续不再重新触发初始化静态流程
 * 实例初始化，先初始化结构块/变量，输出2
 * 接着初始化构造方法方法，输出3和a=100，b=0（变量未初始化就打断了，只有准备阶段的初始值）
 * 接着静态方法的初始化，打印1
 * 最后执行main()方法，输出4
 */
public class StaticTest {
    public static void main(String[] args) {
        staticFunction();
    }

    static StaticTest st = new StaticTest();

    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    StaticTest() {
        System.out.println("3");
        System.out.println("a=" + a + ",b=" + b);
    }

    public static void staticFunction() {
        System.out.println("4");
    }

    int a = 110;
    static int b = 112;
}
