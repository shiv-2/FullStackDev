package com.gentech.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamDemo {
    public static void main(String[] args) {
        writeContent();
    }
    public static void writeContent(){
        FileOutputStream fout=null;
        BufferedOutputStream bw=null;
        try{
            fout=new FileOutputStream("E:\\Vijay Kumar A\\Demo\\Test.txt");
            bw=new BufferedOutputStream(fout);
            String str="My name is Vijay Kumar A";
            bw.write(str.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                bw.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
