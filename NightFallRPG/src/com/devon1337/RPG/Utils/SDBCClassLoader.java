package com.devon1337.RPG.Utils;

import java.net.URL;
import java.net.URLClassLoader;

public class SDBCClassLoader extends URLClassLoader {

	public SDBCClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
		// TODO Auto-generated constructor stub
	}
	
	public void addURL(URL url) {
		super.addURL(url);
	}

	
	
}
