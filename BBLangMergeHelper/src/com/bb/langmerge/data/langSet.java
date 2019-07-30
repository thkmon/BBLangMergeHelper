package com.bb.langmerge.data;

import java.util.HashMap;

public class langSet {
	private HashMap<String, String> langMap = new HashMap<String, String>();
	private StringList langList = new StringList();
	
	
	public HashMap<String, String> getLangMap() {
		return langMap;
	}
	
	
	public void setLangMap(HashMap<String, String> langMap) {
		this.langMap = langMap;
	}
	
	
	public StringList getLangList() {
		return langList;
	}
	
	
	public void setLangList(StringList langList) {
		this.langList = langList;
	}
	
	
	public langSet(StringList strList) {
		if (strList == null || strList.size() == 0) {
			return;
		}
		
		String oneLine = "";
		int lineCount = strList.size();
		for (int i=0; i<lineCount; i++) {
			oneLine = strList.get(i);
			if (oneLine == null || oneLine.length() == 0) {
				continue;
			}
			
			if (oneLine.startsWith("#")) {
				continue;
			}
			
			int idxEqual = oneLine.indexOf("=");
			if (idxEqual < 0) {
				continue;
			}
			
			String leftStr = oneLine.substring(0, idxEqual);
			if (leftStr == null || leftStr.length() == 0) {
				continue;
			}
			
			String rightStr = oneLine.substring(idxEqual + 1);
			if (rightStr == null || rightStr.length() == 0) {
				continue;
			}
			
			if (!leftStr.matches("[A-Z][A-Z][0-9][0-9][0-9][0-9]")) {
				continue;
			}
			
			if (langMap.get(leftStr) == null) {
				langMap.put(leftStr, rightStr);
				langList.add(leftStr);
			} else {
				System.err.println("중복 검출됨 : " + leftStr);
			}
		}
	}
}