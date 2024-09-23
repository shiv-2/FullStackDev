package com.gentech.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedInputStreamDemo {
    public static void main(String[] args) {
        readContent();
    }
    public static void readContent(){
        FileInputStream fin=null;
        BufferedInputStream br=null;
        int data;
        try{
           fin=new FileInputStream("E:\\Vijay Kumar A\\Demo\\Test3.txt");
           br=new BufferedInputStream(fin);
           while((data=br.read())!=-1){
               System.out.print((char)data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           try{
               fin.close();
               br.close();
           }catch (IOException e){
               e.printStackTrace();
           }
        }
    }
}