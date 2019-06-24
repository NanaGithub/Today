### 是什么？
集合的由来
  * 数组长度是固定,当添加的元素超过了数组的长度时需要对数组重新定义,太麻烦,java内部给我们提供了集合类,能存储任意对象,长度是可以改变的,随着元素的增加而增加,随着元素的减少而减少 
  
数组和集合的区别
  
  * 区别1 : 
      * 数组既可以存储基本数据类型,又可以存储引用数据类型,基本数据类型存储的是值,引用数据类型存储的是地址值
      * 集合只能存储引用数据类型(对象)集合中也可以存储基本数据类型,但是在存储的时候会自动装箱变成对象 
  * 区别2:
      * 数组长度是固定的,不能自动增长
      * 集合的长度的是动态维护可变的,可以根据元素的增加而增长

  * 数组和集合什么时候用？
      * 如果元素个数是固定的推荐用数组
      * 如果元素个数不是固定的推荐用集合

### 集合元素遍历实例
List & Set 遍历方法示例

```
    //方法一增强for语句
    public static void method1(ArrayList<Student> list){
        for(Student s:list){
            System.out.print(s);
        }

    }
    //方法二：List独有的
    public static void method2(ArrayList<Student> list){
        for(int i = 0;i < list.size();i++){
            System.out.print( list.get(i) + " ");
        }

    }
    //方法三：迭代器
    public static void method3(ArrayList<Student> list){
        Iterator<Student>it = list.iterator();
        while(it.hasNext()){
            Student stu = it.next();
            System.out.println(stu);
        }
    }
```
Map遍历方法示例

```
        //方法一：Map集合中所有的键构成keySet
        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            String key = it.next();
            //根据key获取map集合中的value
            String value = map.get(key);
            syso(key + "--->" + value);
        }

        //方法二：Map集合中一个键、值构成Entry,多个键值对的形式构成Set
        Set<Map.Entry<String,String>> set = map.entrySet();
        Iterator<Map.Entry<String,String>> it = set.iterator();
        while(it.hasNext()){
            Map.Entry<String,String> entry = it.next();
            String key = entry.getKey();
            String value = entry.getValue();
            syso(key + "--->" + value);
        }

        //方法三:Map集合中所有的值构成Collection
        Collection<String> c = map.values();
        Iterator<String> it = c.iterator();
        while(it.hasNext()){
            String value = it.next();
            syso(value);
        }

```

### List集合原理
#### 源码分析
ArrayList
 * [从源码角度彻底搞懂ArrayList](https://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650243010&idx=1&sn=b281043006cac59266ae64638f238824&chksm=88638eadbf1407bb8734b9c0f0e4cf00252986d4268ac53201c4ad439171aaf8fccc73eddbd1&scene=38#wechat_redirect)  
 
   推荐理由：文章细致的分析了ArrayList增删改查源码方法，充分理解各个方法的底层操作流程，我们就能更好的理解ArrayList的特点。
 
 * 速记 ArrayList源码分析 关键点
``` 
   底层是Object数组存储数据

   扩容机制:默认大小是10,扩容是扩容到之前的1.5倍的大小,每次扩容都是将原数组的数据复制进新数组中. 我的领悟:如果是已经知道了需要创建多少个元素,那么尽量用new ArrayList<>(13)这种明确容量的方式创建ArrayList.避免不必要的浪费.

   添加:如果是添加到数组的指定位置,那么可能会挪动大量的数组元素,并且可能会触发扩容机制;如果是添加到末尾的话,那么只可能触发扩容机制.

   删除:如果是删除数组指定位置的元素,那么可能会挪动大量的数组元素;如果是删除末尾元素的话,那么代价是最小的. ArrayList里面的删除元素,其实是将该元素置为null「list在指定位置添加某个元素时不是覆盖而是插入，原位置对象后移」.

   查询和改某个位置的元素是非常快的( O(1) ). 
```
#### 速记结论：

List(有序,不唯一)的三个儿子的特点 
   
  * ArrayList：底层数据结构是数组，查询快，增删慢。线程不安全，效率高。

  * Vector：底层数据结构是数组，查询快，增删慢。线程安全，效率低。

  * LinkedList：底层数据结构是链表，查询慢，增删快。线程不安全，效率高。

Vector和ArrayList的区别
    
   * Vector相对ArrayList查询慢(线程安全的)

   * Vector相对LinkedList增删慢(数组结构)

   * Vector是线程安全的,效率低

   * ArrayList是线程不安全的,效率高

   * 共同点:都是数组实现的

ArrayList和LinkedList的区别
    
   * 不同点：
      
      ArrayList底层是数组结构,可以根据索引访问，速度快,适用于查找；插入删除时，前后位置都要改变，速度慢。

      LinkedList底层是链表结构的,适用于插入删除，直接断开前后箭头，速度快。

   * 共同点:都是线程不安全的

