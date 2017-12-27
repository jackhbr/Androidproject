package json;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonObjectToString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//创建JSONObject对象
        JSONObject json = new JSONObject();
        
        //向json中添加数据
        json.put("username", "wanglihong");//这个不像哈希表那样要求哈希类型的对应，这个比较方便
        json.put("height", 12.5);
        json.put("age", 24);
        
        //创建JSONArray数组，并将json添加到数组
        JSONArray array = new JSONArray();
        array.put(json);
        
        //转换为字符串
        String jsonStr = array.toString();
        
        System.out.println(jsonStr);

	}

}
