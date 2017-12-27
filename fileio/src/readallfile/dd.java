package readallfile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class dd {
        public dd() {
        }
        /**
         * 读取某个文件夹下的所有文件
         */
        public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
                try {

                        File file = new File(filepath);
                        if (!file.isDirectory()) {
                                System.out.println("文件");
                                System.out.println("path=" + file.getPath());
                                System.out.println("absolutepath=" + file.getAbsolutePath());
                                System.out.println("name=" + file.getName());

                        } else if (file.isDirectory()) {  //如果这个路径是一个目录
                                System.out.println("文件夹");
                                String[] filelist = file.list();//返回一个数组，这个数组包含了文件夹里面的所有文件名
                                for (int i = 0; i < filelist.length; i++) {
                                	System.out.println("数组里面的东西filelist[i]是"+filelist[i]);
                   System.out.println("第一种方法"+filelist[i].subSequence(0, 1));  //获取字符串的第一个字母的方法    String.subSequence(beginIndex（开始字节数）, endIndex（结束字节数）)
                                     System.out.println("第二种方法"+filelist[i].charAt(0));    //直接获取第几个字符。。。
                                     
                   File readfile = new File(filepath + "\\" + filelist[i]);
                                        if (!readfile.isDirectory()) {
                                                System.out.println("path=" + readfile.getPath());
                                                System.out.println("absolutepath="
                                                                + readfile.getAbsolutePath());
                                                System.out.println("name=" + readfile.getName());

                                        } else if (readfile.isDirectory()) {
                                                readfile(filepath + "\\" + filelist[i]);
                                        }
                                }

                        }

                } catch (FileNotFoundException e) {
                        System.out.println("readfile()   Exception:" + e.getMessage());
                }
                return true;
        }

        /**
         * 删除某个文件夹下的所有文件夹和文件
         */
        
        
        /*public static boolean deletefile(String delpath)
                        throws FileNotFoundException, IOException {
                try {

                        File file = new File(delpath);
                        if (!file.isDirectory()) {
                                System.out.println("1");
                                file.delete();
                        } else if (file.isDirectory()) {
                                System.out.println("2");
                                String[] filelist = file.list();
                                for (int i = 0; i < filelist.length; i++) {
                                        File delfile = new File(delpath + "\\" + filelist[i]);
                                        if (!delfile.isDirectory()) {
                                                System.out.println("path=" + delfile.getPath());
                                                System.out.println("absolutepath="
                                                                + delfile.getAbsolutePath());
                                                System.out.println("name=" + delfile.getName());
                                                delfile.delete();
                                                System.out.println("删除文件成功");
                                        } else if (delfile.isDirectory()) {
                                                deletefile(delpath + "\\" + filelist[i]);
                                        }
                                }
                                file.delete();

                        }

                } catch (FileNotFoundException e) {
                        System.out.println("deletefile()   Exception:" + e.getMessage());
                }
                return true;
        }*/
        
        public static void main(String[] args) {
                try {
                        readfile("d:\\java\\filesave");
                        // deletefile("D:/file");
                } catch (FileNotFoundException ex) {
                } catch (IOException ex) {
                }
                System.out.println("ok");
        }

}
