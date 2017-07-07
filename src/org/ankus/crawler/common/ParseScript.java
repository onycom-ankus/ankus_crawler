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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.ankus.crawler.DEF.ScriptDEF;

public class ParseScript {

	/**
	 * Script file read and convert ScriptDEF
	 * @param path
	 * @return
	 */
	public ScriptDEF ParseScript(String path){
		FileReader fr;
		BufferedReader br;
		
		String line;
				
		ScriptDEF sd = new ScriptDEF();
		
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			
			String[] lineList;
			
			PageArgument pa = new PageArgument();

			HashMap<String, String> map = new HashMap<String, String>();
			
			while((line = br.readLine())!=null){
				lineList = line.split(":");
				String id = lineList[0];
				String val = line.replace(id+":", "");
				
				if(id.equals("id")){
					if(Integer.parseInt(val) > 0){
						sd.sDef.add(map);
						map = new HashMap<String, String>();
					} 
				} else if(id.equals("addr")){
					map.put("addr", val);
				} else if(id.contains("info")){
					String[] valList = val.split(",");
					map.put(valList[2], val);
				}
				
			}
//			printScriptInfo(map);
			
			sd.sDef.add(map);
			
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sd;
	}
}
