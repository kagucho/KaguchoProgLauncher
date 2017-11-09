// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Image.java

package launcher;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Referenced classes of package launcher:
//            PropertyReader, Works

public class Image extends JPanel
{

    Image()
    {
        mImageSize = new Dimension(mPropertyReader.intObjectsProperty("IMAGE_SIZE_X"), mPropertyReader.intObjectsProperty("IMAGE_SIZE_Y"));
        mImageNum = mPropertyReader.intObjectsProperty("SCREENSHOT_NUM");
        setBackground(mPropertyReader.colorObjectsProperty("IMAGE_FRAME_COLOR"));
        setSize(mImageSize);
        mImageIcons = new ImageIcon[mWorks.getImagePath().length][mImageNum];
        mLabel = new JLabel();
        for(int i = 0; i < mImageIcons.length; i++)
        {
            for(int j = 0; j < mImageIcons[0].length; j++)
            {
                mImageIcons[i][j] = new ImageIcon(mWorks.getImagePath()[i][j]);
                mImageIcons[i][j] = new ImageIcon(mImageIcons[i][j].getImage().getScaledInstance(mImageSize.width, mImageSize.height, 0));
            }

        }

        currentFrame = 0.0D;
        currentIndex_X = 0;
        currentIndex_Y = 0;
        changeFrame = mPropertyReader.doubleObjectsProperty("IMAGE_CHANGE_FRAME");
        mLabel.setIcon(mImageIcons[0][0]);
        mLabel.setSize(mImageSize);
        add(mLabel);
    }

    void update()
    {
        if(isShowing())
        {
            currentFrame = (currentFrame + changeFrame) % (double)mImageNum;
            if(currentIndex_X != mWorks.getCurrentNum() - 1 || currentIndex_Y != (int)currentFrame)
            {
                currentIndex_X = mWorks.getCurrentNum() - 1;
                currentIndex_Y = (int)currentFrame;
                mLabel.setIcon(mImageIcons[currentIndex_X][currentIndex_Y]);
                repaint();
            }
        }
    }

    static PropertyReader mPropertyReader = PropertyReader.getInstance();
    static Works mWorks = Works.getInstance();
    static ImageIcon mImageIcons[][];
    static JLabel mLabel;
    static Dimension mImageSize;
    static int mImageNum;
    static double currentFrame;
    static double changeFrame;
    static int currentIndex_X;
    static int currentIndex_Y;

}
