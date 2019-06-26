

[](https://upload-images.jianshu.io/upload_images/3804905-c471a31ab5cc8c28.png?imageMogr2/auto-orient/)
```
    //序列化id
    private static final long serialVersionUID = 362498820763181265L;
    //默认数组大小
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    //默认最大阈值
    static final int MAXIMUM_CAPACITY = 1073741824;
    //默认负载因子，系统给定值 一般不需要改
    static final float DEFAULT_LOAD_FACTOR = 0.75F;
    static final int TREEIFY_THRESHOLD = 8;
    static final int UNTREEIFY_THRESHOLD = 6;
    static final int MIN_TREEIFY_CAPACITY = 64;
    
    //数组
    transient HashMap.Node<K, V>[] table;
    transient Set<Entry<K, V>> entrySet;
    transient int size;
    //记录操作该类次数
    transient int modCount;
    //阈值
    int threshold;
    //负载因子
    final float loadFactor;
```
