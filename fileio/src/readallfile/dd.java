package readallfile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class dd {
        public dd() {
        }
        /**
         * ��ȡĳ���ļ����µ������ļ�
         */
        public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
                try {

                        File file = new File(filepath);
                        if (!file.isDirectory()) {
                                System.out.println("�ļ�");
                                System.out.println("path=" + file.getPath());
                                System.out.println("absolutepath=" + file.getAbsolutePath());
                                System.out.println("name=" + file.getName());

                        } else if (file.isDirectory()) {  //������·����һ��Ŀ¼
                                System.out.println("�ļ���");
                                String[] filelist = file.list();//����һ�����飬�������������ļ�������������ļ���
                                for (int i = 0; i < filelist.length; i++) {
                                	System.out.println("��������Ķ���filelist[i]��"+filelist[i]);
                   System.out.println("��һ�ַ���"+filelist[i].subSequence(0, 1));  //��ȡ�ַ����ĵ�һ����ĸ�ķ���    String.subSequence(beginIndex����ʼ�ֽ�����, endIndex�������ֽ�����)
                                     System.out.println("�ڶ��ַ���"+filelist[i].charAt(0));    //ֱ�ӻ�ȡ�ڼ����ַ�������
                                     
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
         * ɾ��ĳ���ļ����µ������ļ��к��ļ�
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
                                                System.out.println("ɾ���ļ��ɹ�");
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
