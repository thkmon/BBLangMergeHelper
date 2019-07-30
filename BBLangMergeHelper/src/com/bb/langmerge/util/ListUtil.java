package com.bb.langmerge.util;

import com.bb.langmerge.data.StringList;

public class ListUtil {

	public static StringList clone(StringList strList) {
		if (strList == null) {
			return null;
		}
		
		StringList resultList = new StringList();
		
		int count = strList.size();
		for (int i=0; i<count; i++) {
			resultList.add(strList.get(i));
		}
		
		return resultList;
	}
	
	
	public static void addByReference(StringList targetList, StringList listToAdd, boolean bAllowToDuplicate) {
		if (targetList == null) {
			targetList = new StringList();
		}
		
		if (listToAdd == null || listToAdd.size() == 0) {
			return;
		}
		
		
		String one = "";
		int count = listToAdd.size();
		for (int i=0; i<count; i++) {
			one = listToAdd.get(i);
			
			if (bAllowToDuplicate) {
				targetList.add(one);
			} else {
				if (targetList.indexOf(one) == -1) {
					targetList.add(one);	
				}
			}
		}
	}
}
