package json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//������������JSON��Ŷ!
public class StringToJSON {
    public static void main(String[] args) throws JSONException{
        
        System.out.println("abc");
        //����JSON�ַ���
        String jsonStr = "{\"id\": 2," + 
                " \"title\": \"json title\", " + 
                "\"config\": {" +
                    "\"width\": 34," +
                    "\"height\": 35," +
                "}, \"data\": [" +
                    "\"JAVA\", \"JavaScript\", \"PHP\"" +
                "]}";
        
        //ת����ΪJSONObject����
        JSONObject jsonObj = new JSONObject(jsonStr);
        
        //��JSONObject�����л�ȡ����
        JavaBean bean = new JavaBean();
        
        //�����������ƻ�ȡint������;
        bean.setId(jsonObj.getInt("id"));     
        
        //������������ȡString����;
        bean.setTitle(jsonObj.getString("title")); 
        
        //������������ȡJSONObject��         jsonobject�����ô����Ű�װ�����ģ�array�����������Ű�װ�����ġ�ͨ�����ƹ�ϣ���ķ�ʽȥ��ȡ
        JSONObject config = jsonObj.getJSONObject("config");//object���ٰ�����object,������ȡ��object������ϸ��ȡ
        bean.setWidth(config.getInt("width"));
        bean.setHeight(config.getInt("height"));
        
        System.out.println(bean.getHeight());
        System.out.println(bean.getId());
        System.out.println(bean.getTitle());
        System.out.println(bean.getWidth());
        System.out.println(bean.getClass());
        System.out.println(bean.getLanguages());
       
        //������������ȡJSONArray����
        JSONArray data = jsonObj.getJSONArray("data");//��json�������ʽ��ȡ��������ϣ��������ţ���Ķ���
        for(int index = 0, length = data.length(); index < length; index++) {
        	System.out.println(data.getString(index));
 
        					   //������org.json.JSONArray�����о�Ȼû���ҵ�toArray���������λ���Ѹ�������취����
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

        //����ʡ�����������ͷ�����
}