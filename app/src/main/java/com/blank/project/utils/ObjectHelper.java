package com.blank.project.utils;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectHelper {

    public static String convertToString(final Serializable object) {
        final CustomByteArrayOutputStream bo = new CustomByteArrayOutputStream(0, true);
        try {
            final ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(object);
            so.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(Base64.encode(bo.getData(), 0, bo.size(), Base64.URL_SAFE | Base64.NO_WRAP));
    }

    public static Object convertFromString(final String objectAsString, Object defaultValue) {
        if (objectAsString != null) {
            try {
                final ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(objectAsString.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP)));
                final Object object = objectInputStream.readObject();
                objectInputStream.close();
                if (object == null) return defaultValue;
                return object;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    public static byte[] convertToBytes(final Serializable object) {
        final CustomByteArrayOutputStream bo = new CustomByteArrayOutputStream(0, true);
        try {
            final ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(object);
            so.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bo.getData();
    }

    public static Object convertFromBytes(final byte[] objectAsBytes, Object defaultValue) {
        if (objectAsBytes != null) {
            try {
                final ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(objectAsBytes));
                final Object object = objectInputStream.readObject();
                objectInputStream.close();
                if (object == null) return defaultValue;
                return object;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }
}
