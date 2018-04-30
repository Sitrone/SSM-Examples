## HashMap 7 8区别 
### 存储方式 
* 1.7，使用数组+链表 
* 1.8，使用数组+链表+红黑树，当链表长度超过8的时候转换为红黑树，如果key没有实现comparable接口则使用key的hashcode排序 

### hashcode二次加工 
* 1.6 
```java
static int hash(int h) {
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
}
```

* 1.7 
```java
final int hash(Object k) {
    int h = hashSeed;
    if (0 != h && k instanceof String) {
        return sun.misc.Hashing.stringHash32((String) k);
    }
    h ^= k.hashCode();
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
}
```

* 1.8 
```java
static final int hash(Object key) {
     int h;
      return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
``` 

### 落桶 
1.8的数组容量因为都是2的次幂，所以落桶可以直接使用位运算，避免求模运算的开销 
```java
static int indexFor(int h, int length) {
    // assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2";
    return h & (length-1);
} 
``` 

## 使用建议 
1. 初始容量，初始容量，初始容量
2. 如果是`enum`类型，考虑使用`EnumMap`
3. 使用`IntObjectHashMap`或`LongObjectHashMap`，netty的工具包中有

### ref
1. [疫苗：JAVA HASHMAP的死循环](https://coolshell.cn/articles/9606.html?spm=5176.100239.0.0.H5jttK)
2. [高性能场景下，Map家族的优化使用建议](http://calvin1978.blogcn.com/articles/hashmap.html?hmsr=toutiao.io&utm_medium=toutiao.io&utm_source=toutiao.io)