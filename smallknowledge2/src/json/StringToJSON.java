package json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//别忘了添加上JSON包哦!
public class StringToJSON {
    public static void main(String[] args) throws JSONException{
        
        System.out.println("abc");
        //定义JSON字符串
        String jsonStr = "{\"id\": 2," + 
                " \"title\": \"json title\", " + 
                "\"config\": {" +
                    "\"width\": 34," +
                    "\"height\": 35," +
                "}, \"data\": [" +
                    "\"JAVA\", \"JavaScript\", \"PHP\"" +
                "]}";
        
        //转换成为JSONObject对象
        JSONObject jsonObj = new JSONObject(jsonStr);
        
        //从JSONObject对象中获取数据
        JavaBean bean = new JavaBean();
        
        //根据属性名称获取int型数据;
        bean.setId(jsonObj.getInt("id"));     
        
        //根据属性名获取String数据;
        bean.setTitle(jsonObj.getString("title")); 
        
        //根据属性名获取JSONObject类         jsonobject就是用大括号包装起来的，array就是用中括号包装起来的。通过类似哈希表的方式去获取
        JSONObject config = jsonObj.getJSONObject("config");//object中再包含了object,所以先取出object，在仔细获取
        bean.setWidth(config.getInt("width"));
        bean.setHeight(config.getInt("height"));
        
        System.out.println(bean.getHeight());
        System.out.println(bean.getId());
        System.out.println(bean.getTitle());
        System.out.println(bean.getWidth());
        System.out.println(bean.getClass());
        System.out.println(bean.getLanguages());
       
        //根据属性名获取JSONArray数组
        JSONArray data = jsonObj.getJSONArray("data");//用json数组的形式来取出这个集合（用中括号）里的东西
        for(int index = 0, length = data.length(); index < length; index++) {
        	System.out.println(data.getString(index));
 
        					   //这里在org.json.JSONArray对象中居然没有找到toArray方法，求各位网友给出解决办法啊！
//            bean.setLanguages(String[]);
        }        
    }
}

class JavaBean{
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String[] getLanguages() {
		return languages;
	}
	public void setLanguages(String[] languages) {
		this.languages = languages;
	}
	private int id ;
    private String title;
    private int width;
    private int height;
    private String[] languages;

        //这里省略了设置器和访问器
}
