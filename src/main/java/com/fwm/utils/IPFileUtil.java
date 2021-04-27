package com.fwm.utils;

import java.io.*;

public class IPFileUtil {

    public static String ipRead(){
        String content=null;
        try {
            FileInputStream fis=new FileInputStream("ip.list");
            BufferedInputStream bis=new BufferedInputStream(fis);

            byte[] buffer=new byte[10240];
            int flag=0;
            while((flag=bis.read(buffer))!=-1){
                content += new String(buffer, 0, flag);
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public static void ipWt(String ipStr){
        try {
            FileOutputStream fos = new FileOutputStream("ip.list", true);
            PrintStream ps = new PrintStream(fos);
            ps.println(ipStr);
            ps.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createIPFile(){
        try {
            FileOutputStream out=new FileOutputStream("ip.list");
            PrintStream p=new PrintStream(out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IPFileUtil.ipWt("123.123.45.45");
        IPFileUtil.ipWt("123.123.45.46");
        IPFileUtil.ipWt("123.123.45.47");
        IPFileUtil.ipWt("123.123.45.48");

    }
}
