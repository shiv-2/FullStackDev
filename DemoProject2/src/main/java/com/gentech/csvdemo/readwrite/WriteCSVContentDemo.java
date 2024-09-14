package com.gentech.csvdemo.readwrite;
import java.io.*;
public class WriteCSVContentDemo {
    public static void main(String[] args) {
        writeContent();
    }

    private static void writeContent()
    {
        FileWriter fw=null;
        try
        {
            fw=new FileWriter("E:\\Vijay Kumar A\\Employees.csv");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {

            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
