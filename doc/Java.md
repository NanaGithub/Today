## JAVA学习记录

### String

#### char[] value = Arrays.copyOf(要copoy的数组，新生成的数组长度);「8大基本数据类型都可」

#### getChars(int var1, int var2, char[] var3, int var4)

* 参数说明
 
  * var1：要存放字符的起始位置
  * var2：要存放字符的终止位置「var-1终止」
  * var3：存放str的数组
  * var4：从 var3 数组的这个位置开始存放
  * 将str字符串，从 var1的位置 到 var2-1 的位置;存放到 数组var3中，从 var3 的 var4 位置开始存放。

* 测试用例
```
    String str = "helloworld";
    char[] c = new char[]{'1','2','4','4'};
    str.getChars(0,3,c,1);
    System.out.print(c);//1hel
```

####  System.arraycopy(Object var0, int var1, Object var2, int var3, int var4);

* 参数说明

  * Object src : 原数组
  * int srcPos : 从元数据的起始位置开始
  * Object dest : 目标数组
  * int destPos : 目标数组的开始起始位置
  * int length  : 要copy的数组的长度
  * 将原数组从 srcPos 位置开始，复制 length 长度的元素，到目标数组 dest中，从目标数组的 destPos 位置开始复制。

* 测试用例

```
byte[]  srcBytes = new byte[]{2,4,0,0,0,0,0,10,15,50};  // 源数组
byte[] destBytes = new byte[5]; // 目标数组
System.arrayCopy(srcBytes,0,destBytes,0,5)//2,4,0,0,0
```
