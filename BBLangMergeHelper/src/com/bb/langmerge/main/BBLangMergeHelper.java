package com.bb.langmerge.main;

import java.io.File;
import java.util.Collections;

import com.bb.langmerge.data.StringList;
import com.bb.langmerge.data.langSet;
import com.bb.langmerge.util.FileUtil;
import com.bb.langmerge.util.ListUtil;

public class BBLangMergeHelper {

	public static void main(String[] args) {
		BBLangMergeHelper langMergeHelper = new BBLangMergeHelper();
		langMergeHelper.doMergeLangFiles();
	}
	
	
	public void doMergeLangFiles() {
		
		try {
			System.out.println("start");
			
			String langPath1 = "C:\\test\\smbc_flow_ko_KR.txt";
			String langPath2 = "C:\\test\\3.7.1_flow_ko_KR.txt";
			
			File langFile1 = new File(langPath1);
			File langFile2 = new File(langPath2);
			
			if (!langFile1.exists()) {
				throw new Exception("langFile1 not exists. [" + langFile1.getAbsolutePath() + "]");
			}
			
			if (!langFile2.exists()) {
				throw new Exception("langFile2 not exists. [" + langFile2.getAbsolutePath() + "]");
			}
			
			StringList fileContent1 = FileUtil.readFile(langFile1);
			StringList fileContent2 = FileUtil.readFile(langFile2);
			
			langSet langSet1 = new langSet(fileContent1);
			langSet langSet2 = new langSet(fileContent2);
			
			StringList totalLangList = ListUtil.clone(langSet1.getLangList());
			System.out.println("initial size : " + totalLangList.size());
			
			ListUtil.addByReference(totalLangList, langSet2.getLangList(), false);
			System.out.println("total size : " + totalLangList.size());
			
			StringList resultList = new StringList();
			
			int langCount1 = 0;
			int langCount2 = 0;
			
			String oneLangCode = "";
			int count = totalLangList.size();
			for (int i=0; i<count; i++) {
				oneLangCode = totalLangList.get(i);
				if (oneLangCode == null || oneLangCode.length() == 0) {
					continue;
				}
				
				String langValue1 = langSet1.getLangMap().get(oneLangCode);
				if (langValue1 != null && langValue1.length() > 0) {
					resultList.add(oneLangCode + "=" + langValue1);
					langCount1++;
				} else {
					String langValue2 = langSet2.getLangMap().get(oneLangCode);
					resultList.add(oneLangCode + "=" + langValue2);
					langCount2++;
				}
			}
			
			// 알파벳순 정렬
			if (resultList != null && resultList.size() > 0) {
				Collections.sort(resultList);
			
				// 파일쓰기
				FileUtil.writeFile("C:\\test\\result.txt", resultList, false);
			}
			
			System.out.println("langCount1 : " + langCount1);
			System.out.println("langCount2 : " + langCount2);
			
			System.out.println("end");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}