// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Frame.java

package launcher;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Referenced classes of package launcher:
//            PropertyReader, WorkList, Image, TextField, 
//            StartButton

class Frame extends JFrame
    implements ActionListener
{

    Frame()
    {
        mPanelLeft = new JPanel();
        mPanelLeft.setPreferredSize(new Dimension(mPropertyReader.intObjectsProperty("LIST_SIZE_X"), size().height));
        mPanelLeft.setBackground(mPropertyReader.colorObjectsProperty("WINDOW_LEFTPANEL_COLOR"));
        add(mPanelLeft, "West");
        initWorkList();
        initStartButton();
        mPanelRight = new JPanel();
        mPanelRight.setBackground(mPropertyReader.colorObjectsProperty("WINDOW_RIGHTPANEL_COLOR"));
        add(mPanelRight, "Center");
        initImage();
        initTextField();
        setSize(mPropertyReader.intObjectsProperty("FRAME_SIZE_X"), mPropertyReader.intObjectsProperty("FRAME_SIZE_Y"));
        setTitle(mPropertyReader.getObjectsProperty("TITLE"));
        setBackground(mPropertyReader.colorObjectsProperty("WINDOW_COLOR"));
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        mTimer = new Timer(100, this);
        mTimer.start();
    }

    private void initWorkList()
    {
        mWorkList = new WorkList();
        mPanelLeft.add(mWorkList, "North");
        mWorkList.setVisible(true);
    }

    private void initImage()
    {
        mImage = new Image();
        mPanelRight.add(mImage, "North");
        mImage.setVisible(true);
    }

    private void initTextField()
    {
        mTextField = new TextField();
        mPanelRight.add(mTextField, "South");
        mTextField.setVisible(true);
    }

    private void initStartButton()
    {
        mStartButton = new StartButton();
        mPanelLeft.add(mStartButton, "South");
        mStartButton.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == mTimer && isShowing())
        {
            mWorkList.update();
            mImage.update();
            mTextField.update();
        }
    }

    static Timer mTimer;
    private static PropertyReader mPropertyReader = PropertyReader.getInstance();
    JPanel mPanelLeft;
    JPanel mPanelRight;
    WorkList mWorkList;
    Image mImage;
    TextField mTextField;
    StartButton mStartButton;

}
