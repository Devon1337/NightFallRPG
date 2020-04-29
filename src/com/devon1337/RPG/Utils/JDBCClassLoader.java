package com.devon1337.RPG.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class JDBCClassLoader extends ClassLoader {

	public void test() {
		/*
		try {
			System.out.println("test.getClass().getClassLoader() : " + JDBCClassLoader.class.getClassLoader());
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver", true, JDBCClassLoader.class.getClassLoader().getParent());
			System.out.println("test.getClass().getClassLoader() : " + JDBCClassLoader.class.getClassLoader());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		*/
		
		try {
			findClass("mssql-jdbc-8.2.2.jre11.jar");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Class findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassFromFile(name);
        return defineClass(name, b, 0, b.length);
    }
 
    private byte[] loadClassFromFile(String fileName)  {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                fileName.replace('.', File.separatorChar) + ".class");
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ( (nextValue = inputStream.read()) != -1 ) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }
	
}
