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


package org.ankus.crawler.common;

import java.util.HashMap;

public class ParseAddr {
	private String m_addr;
	private HashMap<String, String> m_map;
	
	/**
	 * Test Main
	 * @param args
	 */
	public static void main(String[] args){
		ParseAddr pa = new ParseAddr();
		String addr = "http://prod.danawa.com/info/?pcode=4667830&cate=11310561";
		
		pa.setAddr(addr);
		System.out.println(pa.getLength());
		
		for(int i = 0; i < pa.getLength(); i++){
			System.out.println(pa.getMotherAddr()+"\t"+pa.getAttrList()[i]+"\t"+pa.getValue(pa.getAttrList()[i]));
		}
	}
	
	/**
	 * length of addr param
	 * @return length of addr param
	 */
	public int getLength(){
		return m_map.size();
	}
	
	/**
	 * convert addr String to map
	 * @param addr
	 */
	
	public void setAddr(String addr){
		
		if(canGetAttr(addr)){
			String subAddr = addr.replace("http://", "").replace("https://", "").split("[?]")[1];
			String[] attrList = new String[subAddr.split("&").length];
			
			m_map = new HashMap<String, String>();
			for(int i = 0; i < attrList.length; i++){
				m_map.put(subAddr.split("&")[i].split("=")[0], subAddr.split("&")[i].split("=")[1]);
			}
		}
		
		this.m_addr = addr;
	}
	
	/**
	 * output addr
	 * @return
	 */
	public String getAddr(){
		return m_addr;
	}
	
	/**
	 * is convert to map
	 * @param addr
	 * @return
	 */
	public boolean canGetAttr(String addr){
		if(addr.replace("http://", "").replace("https://", "").split("[?]").length > 1){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * is convert to map
	 * @return
	 */
	public boolean canGetAttr(){
		if(m_addr.replace("http://", "").replace("https://", "").split("[?]").length > 1){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * get value of attibute
	 * @param attr
	 * @return
	 */
	public String getValue(String attr){
		return m_map.get(attr);
	}
	
	/**
	 * get attribute name list
	 * @return
	 */
	public String[] getAttrList(){
		String attrList[] = new String[m_map.size()];
		Object keyList[] = m_map.keySet().toArray();
		
		for(int i = 0; i < keyList.length; i++){
			attrList[i] = (String)keyList[i];
		}
		
		return attrList;
	}
	
	/**
	 * get original addr
	 * @return
	 */
	public String getMotherAddr(){
		return "http://"+m_addr.replace("http://", "").replace("https://", "").split("[?]")[0];
	}
}

