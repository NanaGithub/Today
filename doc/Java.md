## JAVA学习记录

###String

```
//8大基本数据类型都可
char[] value = Arrays.copyOf(要copoy的数组，新生成的数组长度);
```


```
getChars(int var1, int var2, char[] var3, int var4)
```

str.getChars(int var1, int var2, char[] var3, int var4)

将str字符串，从 var1的位置 到 var2-1 的位置
存放到 数组var3中，从 var3 的 var4 位置开始存放。
