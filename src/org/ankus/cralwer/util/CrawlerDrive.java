/**
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


package org.ankus.cralwer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.ankus.crawler.DEF.ScriptDEF;
import org.ankus.crawler.common.ParseAddr;
import org.ankus.crawler.common.ParseScript;
import org.ankus.crawler.core.Crawler;
import org.ankus.crawler.core.ParseHTML;
import org.ankus.crawler.github.GithubParser;

public class CrawlerDrive {
	
	/**
	 * main function
	 * @param args
	 */
	public static void main(String args[]){
		
		//Script file load
		ParseScript ps = new ParseScript();
		ScriptDEF sd = ps.ParseScript("script.prj");
				
		//http addr load and analysis
		ParseAddr pa = new ParseAddr();
		String addr = sd.sDef.get(0).get("addr");
		
		pa.setAddr(addr);
		
		//crawling list page until 3 page for testing
		for(int p = 1; p < 3; p++){
			
			//target crawler addr
			String url = pa.getMotherAddr()+"?"+pa.getAttrList()[0]+"="+p;
			
			//html br crawler
			Crawler cr = new Crawler();
			BufferedReader br = cr.getGetPage(url, "UTF-8");
			
			//parse html
			ParseHTML pHtml = new ParseHTML();
			ArrayList<String> gitSubListHtml = pHtml.getValue(0, br, sd);
			
			//get link from list to detail page
			GithubParser gp = new GithubParser();		 
			ArrayList<String> gitSubList = gp.getSubLink(gitSubListHtml);
		
			//crawling information in detail link
			for(int i = 0; i < gitSubList.size(); i++){
				br = cr.getGetPage(gitSubList.get(i),"UTF-8");
				ArrayList<String> gitInfoList = pHtml.getValue(1, br, sd);
				
				//parse info
				ArrayList<String> infoList = gp.getInfo(gitInfoList.get(2));
				//parse subInfo
				ArrayList<String> subInfoList = gp.getSubInfo(gitInfoList.get(1));
				
				//print information
				System.out.println(gitSubList.get(i).replace("https://github.com/", ""));
				System.out.println(gitInfoList.get(0));
				for(int j = 0; j < infoList.size(); j++){
					System.out.println(infoList.get(j));
				}
				
				for(int j = 0; j < subInfoList.size(); j++){
					System.out.println(subInfoList.get(j));
				}
			}
		}
	}
}
