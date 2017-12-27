package json;

import java.util.ArrayList;
import java.util.HashMap;  
import java.util.Map;  //һ���������������
import java.util.List;

import org.json.JSONArray;


public class GatherToString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 //��ʼ��ArrayList���ϲ���������
        List<String> list = new ArrayList<String>();
        list.add("username");
        list.add("age");
        list.add("sex");
        //��ʼ��HashMap���ϲ���������
        Map<String, String> map = new HashMap<String, String>(); 
        map.put("bookname", "island");
        map.put("price", "69.0");//����һ�������Ͷ�Ӧ����
        
        
        //��ʼ��JSONArray���󣬲���������
        JSONArray array = new JSONArray();
        array.put(list);
        array.put(map);
        System.out.println(array.toString());
        //���ɵ�JSON�ַ���Ϊ��[["username","age","sex"],{"price":69,"bookname":"CSS3ʵս"}]
	}

}