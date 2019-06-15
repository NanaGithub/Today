## JAVA学习记录

### String

#### char[] value = Arrays.copyOf(要copoy的数组，新生成的数组长度);「8大基本数据类型都可」

#### getChars(int var1, int var2, char[] var3, int var4)

* 参数说明
 
  * var1：调用字符的起始位置
  * var2：调用字符的终止位置
  * var3：存放str的数组
  * var4：从数组的这个位置开始存放
  * 将str字符串，从 var1的位置 到 var2-1 的位置;存放到 数组var3中，从 var3 的 var4 位置开始存放。

* 测试用例
```
    String str = "helloworld";
    char[] c = new char[]{'1','2','4','4'};
    str.getChars(0,3,c,1);
    System.out.print(c);//1hel
```
