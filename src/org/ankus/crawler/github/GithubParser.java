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


package org.ankus.crawler.github;

import java.util.ArrayList;

public class GithubParser {
	
	/**
	 * get github sub hyper link in html
	 * @param list
	 * @return
	 */
	public ArrayList<String> getSubLink(ArrayList<String> list){
		ArrayList<String> retList = new ArrayList<String>();
		for(int i = 0; i < list.size(); i++){
			String[] htmlList = list.get(i).split("\"");
			for(int j = 0; j < htmlList.length; j++ ){
				if(htmlList[j].contains("?")){
					retList.add("https://github.com"+htmlList[j].split("graphs/")[0]);
				}
			}
		}
		
		return retList;
	}
	
	/**
	 * sub info parser
	 */
	public ArrayList<String> getSubInfo(String val){
		ArrayList<String> retList = new ArrayList<String>();
		String valName = val.split("\t")[0];
		String[] valList = val.split("\t")[1].split(" ");
		int len = valList.length;
		
		if(len == 3){
			retList.add("Watch:\t"+valList[0]);
			retList.add("Star:\t"+valList[1]);
			retList.add("Fork:\t"+valList[2]);
		}
		return retList;
	}
	
	/**
	 * info parser
	 */
	public ArrayList<String> getInfo(String val){
		ArrayList<String> retList = new ArrayList<String>();
		String valName = val.split("\t")[0];
		String[] valList = val.split("\t")[1].split(" ");
		int len = valList.length;
		
		if(len == 4){
			retList.add("Commits:\t"+valList[0]);
			retList.add("Branches:\t"+valList[1]);
			retList.add("Releases:\t"+valList[2]);
			retList.add("Contributors:\t"+valList[3]);
		} else if(len == 3){
			retList.add("Commits:\t"+valList[0]);
			retList.add("Branches:\t"+valList[1]);
			retList.add("Releases:\t"+valList[2]);
		}
		
		return retList;
	}
}
