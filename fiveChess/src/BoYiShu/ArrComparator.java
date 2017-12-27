package BoYiShu;

import java.util.Comparator;

public class ArrComparator implements Comparator<Object> {
	int colum=2;
	int sort=-1;
	public ArrComparator(){}
	public int compare(Object a,Object b){
		if(a instanceof int[]){
			return sort*(((int [])a)[colum]-((int [])b)[colum]);
		}throw new IllegalArgumentException("a,b不是Int []类型的");
		
	}
}
