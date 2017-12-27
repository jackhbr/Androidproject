package hashmap;

import java.util.ArrayList;  
import java.util.List;  
/* 
    了解hashmap中entry实体的结构 
    crc16算法 
    hashmap底层=数组+链表 
    通过hash算法带来的好处， 快存快取  / 数组在存的时候是需要遍历的 
    HashMap底层是怎么回事? 
    
    了解HashMap底层=数组+链表
   HashMap底层源码通过 链表法 来解决hash冲突，找到hash值对应位置不为空,维护一个链表
   ThreadLocal底层源码，ThreadLocalMap中通过 线性探测 解决hash冲突，找到hash值对应位置不为空，依次向后找不为空为止
         
 */  
public class DIYHashMap<K, V> implements DIYMap<K, V>{  
    //定义默认数组大小  
    private  int defaultLenth=16;  
    //负载因子，扩容标准    useSize/数组长度>0.75扩容  
    private double defaultAddSizeFactor=0.75;  
    //使用数组位置的总数  
    private double useSize;  
    //定义Map 骨架之一数组  
    private Entry<K, V>[] table;    //entry是节点，每个元素都是一个节点
  
    @SuppressWarnings("unchecked")
	public DIYHashMap(int defaultLenth, double defaultAddSizeFactor) {  
        if(defaultLenth<0){  
            throw new IllegalArgumentException("数组长度为负数"+defaultLenth);  
        }  
        if(defaultAddSizeFactor<=0 || Double.isNaN(defaultAddSizeFactor)){  
            throw new IllegalArgumentException("扩容标准必须大于0的数字"+defaultLenth);  
        }  
      
        this.defaultLenth = defaultLenth;  
        this.defaultAddSizeFactor = defaultAddSizeFactor;  
          
        table=new Entry[defaultLenth];  
    }  
  
    //快速存取 hash算法  
    public V put(K k, V v) {  
        if(useSize>defaultAddSizeFactor*defaultLenth){  
            //扩容  
            up2Size();  
        }  
        //通过key来计算出 存储的位置  
        int index=getIndex(k,table.length);  //额，这个的目的不应该是尽量放在链表长度最短的地方吗？可是看他后面的方法却没显示出这个目的
      
        Entry<K, V> entry=table[index];  
        Entry<K, V> newEntry=new Entry<K, V>(k, v, null);  
        if(entry==null){          //如果原来数组上的这个位置不存在元素的话，就把这个位置放上这个新的元素
            table[index]=newEntry;  
            useSize++;  
        }else{//维护数组相同位置队列             如果原来数组上的这个位置存在元素的话，就把它放到这个数组对应的链表的最后一个
            Entry<K, V> tmp;  
            while((tmp=table[index])!=null){    //链表进行一次遍历
                tmp=tmp.next;  
            }  
            tmp.next=newEntry;  
        }  
        return newEntry.getValue();  //返回这个节点对应的数据
    }  
  
    private int getIndex(K k, int length) {  
        //通常hashCode 取膜法  
        int m=length-1;    //数组的长度
        int index=hash(k.hashCode()) & m;  
        return index >= 0 ? index : -index;  
    }  
    
    /*
     * hashCode是jdk根据对象的地址或者字符串或者数字算出来的int类型的数值 
     *  public int hashCode()返回该对象的哈希码值。
     *  支持此方法是为了提高哈希表（例如 java.util.Hashtable 提供的哈希表）的性能。
     *  一致性
                      在 Java 应用程序执行期间，在对同一对象多次调用 hashCode 方法时，
                      必须一致地返回相同的整数，前提是将对象进行hashcode比较时所用的信息没有被修改
     */
    
  
    //创建自己的hash算法，保证计算出的位置 在数组中均匀分布        这个看不太懂。。。
    private int hash(int hashCode) {  
        hashCode=hashCode^((hashCode>>>20)^(hashCode>>>12));  
        return hashCode^((hashCode>>>7)^(hashCode>>>4));  
    }  
  
    //扩容数组  
    private void up2Size() {  
        Entry<K, V>[] newTable=new Entry[defaultLenth*2];  //他的扩容直接扩展成了二倍
        //将原table中的entry重新，散列到新的table中  
        againHash(newTable);  
    }  
  
    //将原table中的entry重新，散列到新的table中  
    private void againHash(Entry<K, V>[] newTable) {  
        //数组里面对象 封装到list中,包括同一位置 有列表结构的都解析出来  
        List<Entry<K,V>> entryList=new ArrayList<Entry<K,V>>();  
        for(int i=0;i<table.length;i++){  
            if(table[i]==null){  
                continue;  
            }  
            findEntryByNext(table[i],entryList);  //把每个数组元素里面的链表都一一拆解放进这个数组队列当中
        }  
        if(entryList.size()>0){  
            useSize=0;  
            defaultLenth=defaultLenth*2;  
            table=newTable;  
            for (Entry<K, V> entry : entryList) {  
                if(entry.next!=null){  
                    entry.next=null;  
                }  
                put(entry.getKey(), entry.getValue());  //完全的重新放置一次全部节点
            }  
        }  
    }  
  
  //把每个数组元素里面的链表都一一拆解放进这个数组队列当中
    private void findEntryByNext(Entry<K, V> entry, List<Entry<K, V>> entryList) {  
        if(entry!=null && entry.next!=null){  
            //这个entry对象已经形成链表结构  
            entryList.add(entry);  
            //递归 将链表中的entry实体 都一次封装到entryList链表中  
            findEntryByNext(entry.next, entryList);  
        }else{  
            entryList.add(entry);  //如果链表的下一个节点为null，那么就直接添加到数组队列当中
        }  
    }  
  
    //快取  
    public V get(K k) {  
        //通过key来计算出 存储的位置  
        int index=getIndex(k,table.length);  
              
        Entry<K, V> entry=table[index];  
          
        if(entry==null){  
            throw new NullPointerException();  
        }  
  
        return findValueByKey(k,entry);  
    }  
      
    private V findValueByKey(K k, Entry<K, V> entry) {  
          
        if(k == entry.getKey() || k.equals(entry.getKey())){  
            return entry.v;  
        }else if(entry.next!=null){  
            return findValueByKey(k,entry.next);  
        }  
        return null;  
    }  
  
  
    class Entry<K, V> implements DIYMap.Entry<K, V>{  
  
        K k;  
        V v;  
        //指向被this挤压下去的entry  
        Entry<K, V> next;  
          
        public Entry(K k, V v, Entry<K, V> next) {  
            this.k = k;  
            this.v = v;  
            this.next = next;  
        }  
  
        @Override  
        public K getKey() {  
            return k;  
        }  
  
        @Override  
        public V getValue() {  
            return v;  
        }  
          
    }  
}  
