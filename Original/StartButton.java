// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StartButton.java

package launcher;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

// Referenced classes of package launcher:
//            PropertyReader, Works

class StartButton extends JPanel
    implements ActionListener
{

    StartButton()
    {
        mButtonSize = new Dimension();
        mButtonSize.width = mProp.intObjectsProperty("BUTTON_SIZE_X");
        mButtonSize.height = mProp.intObjectsProperty("BUTTON_SIZE_Y");
        mButtonFont = new Font(mProp.getObjectsProperty("LABEL_FONT"), 1, mProp.intObjectsProperty("BUTTON_LABEL_SIZE"));
        mButton = new JButton();
        mButton.setText(mProp.getObjectsProperty("BUTTON_TEXT"));
        mButton.setForeground(mProp.colorObjectsProperty("BUTTON_LABEL_COLOR"));
        mButton.setFont(mButtonFont);
        mButton.setPreferredSize(mButtonSize);
        mButton.addActionListener(this);
        add(mButton);
        setBackground(mProp.colorObjectsProperty("BUTTON_PANEL_COLOR"));
        setSize(mButtonSize);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == mButton)
            mWorks.runExecFile();
    }

    static Dimension mButtonSize;
    static Font mButtonFont;
    JButton mButton;
    static PropertyReader mProp = PropertyReader.getInstance();
    static Works mWorks = Works.getInstance();

}
