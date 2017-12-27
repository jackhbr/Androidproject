package md5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.codec.digest.DigestUtils;

public class Md5CaculateUtil2 {

    private Md5CaculateUtil2(){
        
    }
    
    private static char[] hexChar = {
        '0','1','2','3','4','5','6','7','8','9',
        'a','b','c','d','e','f'
    };
    
    public static String getHash(String fileName,String hashType) throws IOException, NoSuchAlgorithmException{
        
        File f = new File(fileName);
        System.out.println(" -------------------------------------------------------------------------------");
        System.out.println("|当前文件名称:"+f.getName());
        System.out.println("|当前文件大小:"+(f.length()/1024/1024)+"MB");
        System.out.println("|当前文件路径[绝对]:"+f.getAbsolutePath());
        System.out.println("|当前文件路径[---]:"+f.getCanonicalPath());
        System.out.println(" -------------------------------------------------------------------------------");
        
        InputStream ins = new FileInputStream(f);
        
        byte[] buffer = new byte[8192];//在内存里开创一个8KB的缓存来重复读取数据，8192/1024=8KB
        MessageDigest md5 = MessageDigest.getInstance(hashType);
        
        int len;
        int i=0;
        while((len = ins.read(buffer)) != -1){
        	i++;
        	 System.out.println("  len="+len);//每运行一次这个len都是8192，直到最后
            md5.update(buffer, 0, len);//不断地加在这个对象上。
            //每运行一次，就是
        }
        System.out.println("循环次数为"+i+"  len="+len);//i为1818次，文件大小14MB，1818*8KB=14MB
        
        ins.close();
//        也可以用apache自带的计算MD5方法
        return DigestUtils.md5Hex(md5.digest());
//        自己写的转计算MD5方法
//        return toHexString(md5.digest());
    }
    
    public static String getHash2(String fileName){
        File f = new File(fileName);
        return String.valueOf(f.lastModified());
    }
    
    
    protected static String toHexString(byte[] b){
        StringBuilder sb = new StringBuilder(b.length*2);
        for(int i=0;i<b.length;i++){
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }
    
    /*
     * 获取MessageDigest支持几种加密算法
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static String[] getCryptolmpls(String serviceType){
        
        Set result = new HashSet();
//        all providers
        Provider[] providers = Security.getProviders();
        for(int i=0;i<providers.length;i++){
//            get services provided by each provider
            Set keys = providers[i].keySet();
            for(Iterator it = keys.iterator();it.hasNext();){
                String key = it.next().toString();
                key = key.split(" ")[0];
                
                if(key.startsWith(serviceType+".")){
                    result.add(key.substring(serviceType.length()+1));
                }else if(key.startsWith("Alg.Alias."+serviceType+".")){
                    result.add(key.substring(serviceType.length()+11));
                }
            }
        }
        return (String[]) result.toArray(new String[result.size()]);
    }
    
    
    public static void main(String[] args) throws Exception, Exception {
//        调用方法
//        String[] names = getCryptolmpls("MessageDigest");
//        for(String name:names){
//            System.out.println(name);
//        }
        long start = System.currentTimeMillis();
        System.out.println("开始计算文件MD5值,请稍后...");
        String fileName = "g://1.pdf";
////        String fileName = "E:\\SoTowerStudio-3.1.0.exe";
        String hashType = "MD5";
        String hash = getHash(fileName,hashType);
        System.out.println("MD5:"+hash);
        long end = System.currentTimeMillis();
        System.out.println("一共耗时:"+(end-start)+"毫秒");
    }
}
