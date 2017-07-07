/*
 * Copyright (C) 2011 ankus (http://www.openankus.org).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package org.ankus.crawler.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.ankus.crawler.DEF.ElementDEF;
import org.ankus.crawler.DEF.ScriptDEF;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ParseHTML {

	/**
	 * get value for structure data
	 * @param idx
	 * @param br
	 * @param sDef
	 * @return
	 */
	public ArrayList<String> getValue(int idx, BufferedReader br, ScriptDEF sDef){
		
		ArrayList<ElementDEF> eList = convertSDEFtoEDEF(idx, sDef);
		return convertBrToDoc(br, eList);
		
	}
	
	/**
	 * get structure value with scriptDef
	 * @param br
	 * @param list
	 * @return
	 */
	public ArrayList<String> convertBrToDoc(BufferedReader br, ArrayList<ElementDEF> list){
		String line;
		StringBuffer buf = new StringBuffer();
		ArrayList<String> retList = new ArrayList<String>();
		
		try {
			while((line = br.readLine())!=null){
				buf.append(line);
				buf.append("\r\n");
			}
			
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Document doc = Jsoup.parse(buf.toString());
		Elements eList = doc.getAllElements();
		
		int size = list.size();
		
		for(int i = 0 ; i < size ; i++){
			ElementDEF eDef = list.get(i);
			Elements eleList = null;
			if(eDef.ElementType.equals("CLASS")){
				eleList = doc.getElementsByClass(eDef.ElementValue);
			} else if(eDef.ElementType.equals("TAG")){
				eleList = doc.getElementsByTag(eDef.ElementValue);
			}
			
			if(eleList != null){
				if(eDef.ElementValueType.equals("html")){
					retList.add(eDef.ElementName+"\t"+eleList.toString());
				} else if (eDef.ElementValueType.equals("text")){
					retList.add(eDef.ElementName+"\t"+eleList.text());
				}
				
			}
		}
		return retList;
	}
	
	/**
	 * convert scriptdef for html element
	 * @param idx
	 * @param sDef
	 * @return
	 */
	public ArrayList<ElementDEF> convertSDEFtoEDEF(int idx, ScriptDEF sDef){
		HashMap<String, String> map = sDef.sDef.get(idx);
		Object keyList[] = map.keySet().toArray();
		int len = keyList.length;
		
		ArrayList<ElementDEF> list = new ArrayList<ElementDEF>();
		
		for(int i = 0 ; i < len ; i++){
			String key = (String)keyList[i];
			if(!key.equals("addr")){
				String[] valList = map.get(key).split(",");
				
				ElementDEF eDef = new ElementDEF();
				//info-2:CLASS,conStyle_Bold01,title
				
				eDef.ElementType = valList[0];
				eDef.ElementValue = valList[1];
				eDef.ElementName = valList[2];
				eDef.ElementValueType = valList[3];
				
				list.add(eDef);
			}
		}
		
		return list;
	}
}
