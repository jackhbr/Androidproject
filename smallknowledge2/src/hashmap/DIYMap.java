package hashmap;

public interface  DIYMap<K,V> {  
    //Map˫�м��� ���������� ����ȡ  
    public V put(K k,V v);  
    //����ȡ  
    public V get(K k);  
  
    //����һ���ڲ��ӿ�  
    public interface Entry<K,V>{  
        public K getKey();  
          
        public V getValue();  
    }  
}  