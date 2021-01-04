import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        //user1	111
        //user2	user222222
        //user3	1234.5
        boolean bool = true;
        while (bool)
        {
            System.out.println("请输入用户名：");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();
            System.out.println("请输入密码：");
            String password=sc.next();

            //File file = new File("G:\\Lanqiao\\ConsoleShop\\src\\list.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/list.xlsx");
            InputStream inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
            ReadExcel readExcel = new ReadExcel();
            User users[] = readExcel.readExcel(in);
            for(int i=0;i<users.length;i++)
            {
                if(username.equals(users[i].getUsername())&&password.equals(users[i].getPassword()))
                {
                    System.out.println("登陆成功");
                    bool=false;
                    /*
                    显示商品
                     */
                    ReadProductExcel readProductExcel = new ReadProductExcel();
                    Product products[] = readProductExcel.getALLProduct(inProduct);
                    for (Product product : products){
                        System.out.print(product.getId());
                        System.out.print("\t" + product.getName());
                        System.out.print("\t" + product.getPrice());
                        System.out.println("\t" + product.getDesc());
                    }
                    /*
                    选择商品ID
                     */
                    System.out.println("请输入商品ID把该商品加入购物车");
                    String pId = sc.next();
                    int count = 0;
                    /*
                    创建一个购物车的数组：存的是商品，根据此ID去Excel中去查找是否有该ID的商品信息，如果有则返回该商品即可
                     */
                    Product carts[] = new Product[3];
                    inProduct = null;
                    inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
                    Product product = readProductExcel.getProductById(pId, inProduct);
                    System.out.println("要购买的商品价格：" + product.getPrice());
                    break;
                }else {
                    System.out.println("登录失败,请重新登录");
                }
            }
        }
    }
}
