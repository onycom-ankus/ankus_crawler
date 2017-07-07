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
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;

public class Crawler {
	/**
	 * get html page(GET, POST)
	 * @param type
	 * @param addr
	 * @return
	 */
	public BufferedReader getPage(String type, String addr){		
		if(type.equals("get")){
			return getGetPage(addr);
		} else if(type.equals("post")){
			return getPostPage(addr);
		} else {
			return null;
		}
	}
	
	/**
	 * get post type html page with charSet
	 * @param addr
	 * @param charSet
	 * @return
	 */
	public BufferedReader getPostPage(String addr, String charSet){
		HttpPost http = new HttpPost(addr);

	    HttpClient httpClient = HttpClientBuilder.create().build();
	  
	    HttpResponse response = null;
	    
	    BufferedReader retBr = null;
		try {
			response = httpClient.execute(http);
		    HttpEntity entity = response.getEntity();
		    ContentType contentType = ContentType.getOrDefault(entity);
		    retBr = new BufferedReader(new InputStreamReader(entity.getContent(),charSet));
		   
		} catch (IOException e) {
			System.out.println("getPostPage 오류가 발생했습니다."); 
		}
	    
	    return retBr;
	}

	/**
	 * get Get type html page with charSet
	 * @param addr
	 * @param charSet
	 * @return
	 */
	public BufferedReader getGetPage(String addr, String charSet){
		HttpGet http = new HttpGet(addr);

	    HttpClient httpClient = HttpClientBuilder.create().build();
	  
	    HttpResponse response = null;
	    
	    BufferedReader retBr = null;
		try {
			response = httpClient.execute(http);
		    HttpEntity entity = response.getEntity();
		    ContentType contentType = ContentType.getOrDefault(entity);
	        retBr = new BufferedReader(new InputStreamReader(entity.getContent(),charSet));
		   
		} catch (IOException e) {
			System.out.println("getGetPage 오류가 발생했습니다."); 
		}
	    
	    return retBr;
	}
	
	/**
	 * get post type page
	 * @param addr
	 * @return
	 */
	public BufferedReader getPostPage(String addr){
		HttpPost http = new HttpPost(addr);

	    HttpClient httpClient = HttpClientBuilder.create().build();
	  
	    HttpResponse response = null;
	    
	    BufferedReader retBr = null;
		try {
			response = httpClient.execute(http);
		    HttpEntity entity = response.getEntity();
		    ContentType contentType = ContentType.getOrDefault(entity);
	        Charset charset = contentType.getCharset();
		    retBr = new BufferedReader(new InputStreamReader(entity.getContent(),charset));
		   
		} catch (IOException e) {
			System.out.println("getPostPage 오류가 발생했습니다."); 
		}
	    
	    return retBr;
	}

	/**
	 * get Get type html page
	 * @param addr
	 * @return
	 */
	public BufferedReader getGetPage(String addr){
		HttpGet http = new HttpGet(addr);

	    HttpClient httpClient = HttpClientBuilder.create().build();
	  
	    HttpResponse response = null;
	    
	    BufferedReader retBr = null;
		try {
			response = httpClient.execute(http);
		    HttpEntity entity = response.getEntity();
		    ContentType contentType = ContentType.getOrDefault(entity);
	        Charset charset = contentType.getCharset();
	        retBr = new BufferedReader(new InputStreamReader(entity.getContent(),charset));
		   
		} catch (IOException e) {
			System.out.println("getGetPage 오류가 발생했습니다."); 
		}
	    
	    return retBr;
	}
}
