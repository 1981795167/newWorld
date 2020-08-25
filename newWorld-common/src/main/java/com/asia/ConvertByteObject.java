package com.asia;

import java.io.*;

/**
 * @author Xavier.liu
 * @date 2020/5/14 16:41
 */
public class ConvertByteObject {

    public static byte[] getByteFromObject(Object object){
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(object);
            oo.close();
            bo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bo.toByteArray();
    }

    public static Object getObjectFromByte(byte[] bytes){
        ByteArrayInputStream bo = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream oo = new ObjectInputStream(bo);
            oo.close();
            bo.close();
            return oo.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Object();
    }
}
