## JAVA学习记录

### String
#### String、StringBuffer和StringBuilder的区别

源码阅读推荐：[你知道String、StringBuffer和StringBuilder之间的区别吗？
](https://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650242977&idx=1&sn=dd2ad3a134f1336a5bf2428d42402b1f&chksm=88638ecebf1407d8c7f72981fc1faa9b87c3e9861334a57d145bd10fb1c4cb5bf763fdab8720&scene=38#wechat_redirect)

速记结论：
 * 相同点：String,StringBuffer,StringBuilder最终底层存储与操作的都是char数组；
 * 不同点：
   * String里面的char数组是final的,而StringBuffer,StringBuilder不是。
   
     也就是说,String是不可变的,想要新的字符串只能重新生成String.频繁重新生成容易生成很多垃圾；而 StringBuffer和StringBuilder只需要修改底层的char数组就行.相对来说,开销要小很多.
   
   * StringBuffer是线程安全的,StringBuilder是线程不安全的。
     
     因为StringBuffer的方法是加了synchronized锁起来了的,而StringBuilder没有.增删比较多时用StringBuffer或StringBuilder（注意单线程与多线程。

#### String方法

void getChars(int var1, int var2, char[] var3, int var4)

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

void getChars(char[] value, int start)
* 用于将str复制到value中start处  其最底层实现是native方法(getCharsNoCheck() )

char[] value = Arrays.copyOf(要copoy的数组，新生成的数组长度);「8大基本数据类型都可」

char[] value = Arrays.copyOfRange(char[] var0, int var1, int var2)

* 参数说明 
 
 * var0:原数组
 * var1:开始位置
 * var2:终止位置
 * 将原数组从 开始位置 到 终止位置-1 复制，生成一个新数组

System.arraycopy(Object var0, int var1, Object var2, int var3, int var4);

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
