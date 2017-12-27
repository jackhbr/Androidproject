package json;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonObjectToString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//����JSONObject����
        JSONObject json = new JSONObject();
        
        //��json����������
        json.put("username", "wanglihong");//��������ϣ������Ҫ���ϣ���͵Ķ�Ӧ������ȽϷ���
        json.put("height", 12.5);
        json.put("age", 24);
        
        //����JSONArray���飬����json���ӵ�����
        JSONArray array = new JSONArray();
        array.put(json);
        
        //ת��Ϊ�ַ���
        String jsonStr = array.toString();
        
        System.out.println(jsonStr);

	}

}