/**
 * 初始化Book类
 * 先初始化静态代码块和类变量
 * 打印 “书的静态代码块”
 * amount = 0
 * 执行入口函数，打印" Hello ShuYi."
 */
public class Book {
    public static void main(String[] args)    {
        System.out.println("Hello ShuYi.");
    }

    /*并没有new 关键则，没有实例化Book类，只是访问了main方法*/
    Book()
    {
        System.out.println("书的构造方法");
        System.out.println("price=" + price +",amount=" + amount);
    }

    {
        System.out.println("书的普通代码块");
    }
    int price = 110;
    static {
        System.out.println("书的静态代码块");
    }
    static int amount = 112;
}
