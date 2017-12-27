package hashmap;

import java.util.ArrayList;  
import java.util.List;  
/* 
    �˽�hashmap��entryʵ��Ľṹ 
    crc16�㷨 
    hashmap�ײ�=����+���� 
    ͨ��hash�㷨�����ĺô��� ����ȡ  / �����ڴ��ʱ������Ҫ������ 
    HashMap�ײ�����ô����? 
    
    �˽�HashMap�ײ�=����+����
   HashMap�ײ�Դ��ͨ�� ���� �����hash��ͻ���ҵ�hashֵ��Ӧλ�ò�Ϊ��,ά��һ������
   ThreadLocal�ײ�Դ�룬ThreadLocalMap��ͨ�� ����̽�� ���hash��ͻ���ҵ�hashֵ��Ӧλ�ò�Ϊ�գ���������Ҳ�Ϊ��Ϊֹ
         
 */  
public class DIYHashMap<K, V> implements DIYMap<K, V>{  
    //����Ĭ�������С  
    private  int defaultLenth=16;  
    //�������ӣ����ݱ�׼    useSize/���鳤��>0.75����  
    private double defaultAddSizeFactor=0.75;  
    //ʹ������λ�õ�����  
    private double useSize;  
    //����Map �Ǽ�֮һ����  
    private Entry<K, V>[] table;    //entry�ǽڵ㣬ÿ��Ԫ�ض���һ���ڵ�
  
    @SuppressWarnings("unchecked")
	public DIYHashMap(int defaultLenth, double defaultAddSizeFactor) {  
        if(defaultLenth<0){  
            throw new IllegalArgumentException("���鳤��Ϊ����"+defaultLenth);  
        }  
        if(defaultAddSizeFactor<=0 || Double.isNaN(defaultAddSizeFactor)){  
            throw new IllegalArgumentException("���ݱ�׼�������0������"+defaultLenth);  
        }  
      
        this.defaultLenth = defaultLenth;  
        this.defaultAddSizeFactor = defaultAddSizeFactor;  
          
        table=new Entry[defaultLenth];  
    }  
  
    //���ٴ�ȡ hash�㷨  
    public V put(K k, V v) {  
        if(useSize>defaultAddSizeFactor*defaultLenth){  
            //����  
            up2Size();  
        }  
        //ͨ��key������� �洢��λ��  
        int index=getIndex(k,table.length);  //������Ŀ�Ĳ�Ӧ���Ǿ���������������̵ĵط��𣿿��ǿ�������ķ���ȴû��ʾ�����Ŀ��
      
        Entry<K, V> entry=table[index];  
        Entry<K, V> newEntry=new Entry<K, V>(k, v, null);  
        if(entry==null){          //���ԭ�������ϵ����λ�ò�����Ԫ�صĻ����Ͱ����λ�÷�������µ�Ԫ��
            table[index]=newEntry;  
            useSize++;  
        }else{//ά��������ͬλ�ö���             ���ԭ�������ϵ����λ�ô���Ԫ�صĻ����Ͱ����ŵ���������Ӧ����������һ��
            Entry<K, V> tmp;  
            while((tmp=table[index])!=null){    //�������һ�α���
                tmp=tmp.next;  
            }  
            tmp.next=newEntry;  
        }  
        return newEntry.getValue();  //��������ڵ��Ӧ������
    }  
  
    private int getIndex(K k, int length) {  
        //ͨ��hashCode ȡĤ��  
        int m=length-1;    //����ĳ���
        int index=hash(k.hashCode()) & m;  
        return index >= 0 ? index : -index;  
    }  
    
    /*
     * hashCode��jdk���ݶ���ĵ�ַ�����ַ������������������int���͵���ֵ 
     *  public int hashCode()���ظö���Ĺ�ϣ��ֵ��
     *  ֧�ִ˷�����Ϊ����߹�ϣ������ java.util.Hashtable �ṩ�Ĺ�ϣ�������ܡ�
     *  һ����
                      �� Java Ӧ�ó���ִ���ڼ䣬�ڶ�ͬһ�����ε��� hashCode ����ʱ��
                      ����һ�µط�����ͬ��������ǰ���ǽ��������hashcode�Ƚ�ʱ���õ���Ϣû�б��޸�
     */
    
  
    //�����Լ���hash�㷨����֤�������λ�� �������о��ȷֲ�        �������̫��������
    private int hash(int hashCode) {  
        hashCode=hashCode^((hashCode>>>20)^(hashCode>>>12));  
        return hashCode^((hashCode>>>7)^(hashCode>>>4));  
    }  
  
    //��������  
    private void up2Size() {  
        Entry<K, V>[] newTable=new Entry[defaultLenth*2];  //��������ֱ����չ���˶���
        //��ԭtable�е�entry���£�ɢ�е��µ�table��  
        againHash(newTable);  
    }  
  
    //��ԭtable�е�entry���£�ɢ�е��µ�table��  
    private void againHash(Entry<K, V>[] newTable) {  
        //����������� ��װ��list��,����ͬһλ�� ���б�ṹ�Ķ���������  
        List<Entry<K,V>> entryList=new ArrayList<Entry<K,V>>();  
        for(int i=0;i<table.length;i++){  
            if(table[i]==null){  
                continue;  
            }  
            findEntryByNext(table[i],entryList);  //��ÿ������Ԫ�����������һһ���Ž����������е���
        }  
        if(entryList.size()>0){  
            useSize=0;  
            defaultLenth=defaultLenth*2;  
            table=newTable;  
            for (Entry<K, V> entry : entryList) {  
                if(entry.next!=null){  
                    entry.next=null;  
                }  
                put(entry.getKey(), entry.getValue());  //��ȫ�����·���һ��ȫ���ڵ�
            }  
        }  
    }  
  
  //��ÿ������Ԫ�����������һһ���Ž����������е���
    private void findEntryByNext(Entry<K, V> entry, List<Entry<K, V>> entryList) {  
        if(entry!=null && entry.next!=null){  
            //���entry�����Ѿ��γ�����ṹ  
            entryList.add(entry);  
            //�ݹ� �������е�entryʵ�� ��һ�η�װ��entryList������  
            findEntryByNext(entry.next, entryList);  
        }else{  
            entryList.add(entry);  //����������һ���ڵ�Ϊnull����ô��ֱ����ӵ�������е���
        }  
    }  
  
    //��ȡ  
    public V get(K k) {  
        //ͨ��key������� �洢��λ��  
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
        //ָ��this��ѹ��ȥ��entry  
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
