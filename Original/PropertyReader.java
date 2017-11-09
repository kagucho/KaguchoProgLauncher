// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PropertyReader.java

package launcher;

import java.awt.Color;
import java.io.*;
import java.util.Properties;

// Referenced classes of package launcher:
//            Util

class PropertyReader
{

    private PropertyReader()
    {
        InputStream inStream;
        worksProperty = new Properties();
        objectsProperty = new Properties();
        inStream = null;
        try
        {
            inStream = new BufferedInputStream(new FileInputStream("works.properties"));
            worksProperty.load(inStream);
            inStream = new BufferedInputStream(new FileInputStream("objects.properties"));
            objectsProperty.load(inStream);
            System.out.println("PropertyReader : \u30A4\u30F3\u30B9\u30BF\u30F3\u30B9\u3092\u4F5C\u6210\u3057\u307E\u3057\u305F\u3002");
            break MISSING_BLOCK_LABEL_132;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            if(inStream != null)
                inStream.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        break MISSING_BLOCK_LABEL_150;
        Exception exception;
        exception;
        try
        {
            if(inStream != null)
                inStream.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        throw exception;
        try
        {
            if(inStream != null)
                inStream.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    static PropertyReader getInstance()
    {
        if(propertyReader == null)
            propertyReader = new PropertyReader();
        return propertyReader;
    }

    boolean containsWorksKey(String key)
    {
        return worksProperty.containsKey(key);
    }

    String getWorksProperty(String key)
    {
        if(containsWorksKey(key))
        {
            return worksProperty.getProperty(key);
        } else
        {
            System.out.println((new StringBuilder("\u30B3\u30F3\u30D5\u30A3\u30B0\u30D5\u30A1\u30A4\u30EB\u306B")).append(key).append("\u306B\u5BFE\u5FDC\u3059\u308B\u5024\u304C\u5B58\u5728\u3057\u307E\u305B\u3093").toString());
            return null;
        }
    }

    boolean boolWorksProperty(String key)
    {
        return getWorksProperty(key).equals("YES");
    }

    boolean containsObjectsPropertyKey(String key)
    {
        return objectsProperty.containsKey(key);
    }

    String getObjectsProperty(String key)
    {
        if(containsObjectsPropertyKey(key))
        {
            return objectsProperty.getProperty(key);
        } else
        {
            System.out.println((new StringBuilder("\u5B9A\u6570\u30D5\u30A1\u30A4\u30EB\u306B")).append(key).append("\u306B\u5BFE\u5FDC\u3059\u308B\u5024\u304C\u5B58\u5728\u3057\u307E\u305B\u3093").toString());
            return null;
        }
    }

    int intObjectsProperty(String key)
    {
        return Integer.parseInt(getObjectsProperty(key));
    }

    double doubleObjectsProperty(String key)
    {
        return Double.parseDouble(getObjectsProperty(key));
    }

    Color colorObjectsProperty(String key)
    {
        return Util.toColor(getObjectsProperty(key));
    }

    private static PropertyReader propertyReader;
    private static Properties worksProperty;
    private static Properties objectsProperty;
    private static final String worksPropertyName = "works.properties";
    private static final String objectsPropertyName = "objects.properties";
}
