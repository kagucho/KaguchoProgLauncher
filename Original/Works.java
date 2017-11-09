// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Works.java

package launcher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package launcher:
//            PropertyReader, Util

class Works
{

    private Works()
    {
        mPropertyReader = PropertyReader.getInstance();
        mSelectedListNum = 1;
        mImageNum = mPropertyReader.intObjectsProperty("SCREENSHOT_NUM");
        loadTitle();
        loadImageFolderPath();
        loadText();
        loadExecFileName();
        loadExecFilePath();
        loadExecFolderPath();
        System.out.println("Works : \u30A4\u30F3\u30B9\u30BF\u30F3\u30B9\u3092\u4F5C\u6210\u3057\u307E\u3057\u305F\u3002");
    }

    static Works getInstance()
    {
        if(mInstance == null)
            mInstance = new Works();
        return mInstance;
    }

    private void loadTitle()
    {
        List list = new ArrayList();
        String key;
        for(int i = 1; mPropertyReader.containsWorksKey(key = (new StringBuilder("WORKS_NAME_")).append(i).toString()); i++)
            list.add(new String(mPropertyReader.getWorksProperty(key)));

        mTitle = (String[])list.toArray(new String[0]);
    }

    private void loadImageFolderPath()
    {
        mImagePath = new String[mTitle.length][mImageNum];
        for(int i = 0; i < mImagePath.length; i++)
        {
            File dir = new File(mPropertyReader.getWorksProperty((new StringBuilder("IMAGE_FOLDER_PATH_")).append(i + 1).toString()));
            File file[] = dir.listFiles();
            for(int j = 0; j < mImagePath[0].length; j++)
                mImagePath[i][j] = !dir.exists() || !file[j].exists() ? (new File(mPropertyReader.getWorksProperty("IMAGE_DUMMY_PATH"))).getPath() : file[j].getPath();

        }

    }

    private void loadText()
    {
        String textPath[] = new String[mTitle.length];
        mText = new String[mTitle.length];
        for(int i = 0; i < textPath.length; i++)
        {
            textPath[i] = mPropertyReader.getWorksProperty((new StringBuilder("TEXT_FILE_PATH_")).append(i + 1).toString());
            if(!(new File(textPath[i])).exists())
                textPath[i] = (new File(mPropertyReader.getWorksProperty("TEXT_DUMMY_PATH"))).getPath();
        }

        mText = loadTextWithPath(textPath);
    }

    private String[] loadTextWithPath(String textPath[])
    {
        try
        {
            String fileText[] = new String[textPath.length];
            for(int i = 0; i < textPath.length; i++)
            {
                char cbuf[] = new char[2];
                InputStreamReader inReader = new InputStreamReader(new FileInputStream(textPath[i]), mPropertyReader.getWorksProperty((new StringBuilder("TEXT_CHARACTERCODE_")).append(i + 1).toString()));
                StringWriter sWriter = new StringWriter();
                int size;
                while((size = inReader.read(cbuf)) != -1) 
                    sWriter.write(cbuf, 0, size);
                fileText[i] = sWriter.toString();
                inReader.close();
            }

            return fileText;
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private void loadExecFilePath()
    {
        execFilePath = new String[mTitle.length];
        for(int i = 0; i < execFilePath.length; i++)
        {
            String key = (new StringBuilder("WORKS_FILE_PATH_")).append(i + 1).toString();
            File dir = new File(mPropertyReader.getWorksProperty(key));
            execFilePath[i] = dir.getAbsolutePath();
        }

    }

    private void loadExecFolderPath()
    {
        execFolderPath = new String[execFilePath.length];
        for(int i = 0; i < execFolderPath.length; i++)
        {
            String str = mPropertyReader.getWorksProperty((new StringBuilder("WORKS_FILE_PATH_")).append(i + 1).toString());
            int length = execFileName[i].length() + 1;
            System.out.println(str.substring(0, str.length() - length));
            execFolderPath[i] = str.substring(0, str.length() - length);
        }

    }

    private void loadExecFileName()
    {
        execFileName = new String[mTitle.length];
        for(int i = 0; i < execFileName.length; i++)
            execFileName[i] = mPropertyReader.getWorksProperty((new StringBuilder("WORKS_FILE_NAME_")).append(i + 1).toString());

    }

    private String getExeFolderAbsolutePath(int i)
    {
        String str_1 = System.getProperty("user.dir");
        String str_2 = execFolderPath[i];
        str_2 = str_2.substring(1, str_2.length());
        System.out.println((new StringBuilder(String.valueOf(str_1))).append(str_2).toString());
        return (new StringBuilder(String.valueOf(str_1))).append(str_2).toString();
    }

    public void runExecFile()
    {
        if(Util.isWindows() && !mPropertyReader.boolWorksProperty((new StringBuilder("ISFOLDEROPEN_")).append(mSelectedListNum).toString()))
            directRunFile();
        else
            folderOpen();
    }

    private void directRunFile()
    {
        System.out.println("directRunFile");
        String path = System.getProperty("user.dir");
        String str[] = {
            (new StringBuilder(String.valueOf(path))).append("/").append(mPropertyReader.getObjectsProperty("AUXILIIARY_NAME")).toString(), getExeFolderAbsolutePath(mSelectedListNum - 1), execFileName[mSelectedListNum - 1]
        };
        runTimeExec(str, true);
    }

    private void folderOpen()
    {
        System.out.println("folderOpen");
        System.out.println(getExeFolderAbsolutePath(mSelectedListNum - 1));
        String str[] = {
            Util.isWindows() ? "explorer" : "open", getExeFolderAbsolutePath(mSelectedListNum - 1)
        };
        runTimeExec(str, false);
    }

    private void runTimeExec(String str[], boolean isDirect)
    {
        try
        {
            Process p = Runtime.getRuntime().exec(str);
            try
            {
                p.waitFor();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            p.destroy();
        }
        catch(IOException e)
        {
            if(isDirect)
                folderOpen();
            e.printStackTrace();
        }
    }

    void setCurrentNum(int i)
    {
        if(mSelectedListNum != i)
            mSelectedListNum = i;
    }

    int getCurrentNum()
    {
        return mSelectedListNum;
    }

    String[] getTitle()
    {
        return mTitle;
    }

    String[] getText()
    {
        return mText;
    }

    String[][] getImagePath()
    {
        return mImagePath;
    }

    String[] getExecFilePath()
    {
        return execFilePath;
    }

    String[] getExecFolderPath()
    {
        return execFolderPath;
    }

    String[] getExecFileName()
    {
        return execFileName;
    }

    private static Works mInstance;
    private static PropertyReader mPropertyReader;
    private static int mSelectedListNum;
    private static int mImageNum;
    private static String mTitle[];
    private static String mText[];
    private static String mImagePath[][];
    private static String execFileName[];
    private static String execFilePath[];
    private static String execFolderPath[];
}
