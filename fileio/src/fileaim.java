public static void fileInput(File file)throws IOException{
		
		//���ļ�������
		FileInputStream fis = new FileInputStream(file);
		DataInputStream dis = new DataInputStream(fis);
		
		StringBuffer str = new StringBuffer();
		
		int length = dis.readInt();
		for(int i=0;i<length;i++){
			char c = dis.readChar();
			str.append(c);
		}
		String name = new String(str);
		int score = dis.readInt();
		System.out.println("name:"+name+"   ������"+score);
		fis.close();
		
		