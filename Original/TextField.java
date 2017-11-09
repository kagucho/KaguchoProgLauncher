// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TextField.java

package launcher;

import java.awt.Dimension;
import javax.swing.*;

// Referenced classes of package launcher:
//            PropertyReader, Works

class TextField extends JPanel
{

    TextField()
    {
        mTextArea = new JTextArea();
        mTextAreaSize = new Dimension();
        mTextAreaSize.width = mProp.intObjectsProperty("EXPLANATION_SIZE_X");
        mTextAreaSize.height = mProp.intObjectsProperty("EXPLANATION_SIZE_Y");
        mTextArea.setWrapStyleWord(true);
        mTextArea.setLineWrap(true);
        mTextArea.setBackground(mProp.colorObjectsProperty("TEXTAREA_COLOR"));
        scrollPane = new JScrollPane(mTextArea);
        scrollPane.setPreferredSize(mTextAreaSize);
        add(scrollPane);
        mText = mWorks.getText();
        mCurrentIndex = 0;
        mTextArea.setText(mText[0]);
        setBackground(mProp.colorObjectsProperty("TEXTAREA_FRAME_COLOR"));
        setSize(mTextAreaSize);
    }

    void update()
    {
        if(mCurrentIndex != mWorks.getCurrentNum() - 1)
        {
            mCurrentIndex = mWorks.getCurrentNum() - 1;
            mTextArea.setText(mText[mCurrentIndex]);
            mTextArea.setCaretPosition(0);
        }
    }

    static JTextArea mTextArea;
    static JScrollPane scrollPane;
    static String mText[];
    static Dimension mTextAreaSize;
    static PropertyReader mProp = PropertyReader.getInstance();
    static Works mWorks = Works.getInstance();
    static int mCurrentIndex;

}
