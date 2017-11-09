// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WorkList.java

package launcher;

import java.awt.*;
import javax.swing.JList;
import javax.swing.JPanel;

// Referenced classes of package launcher:
//            PropertyReader, Works

class WorkList extends JPanel
{

    WorkList()
    {
        Dimension listSize = new Dimension(prop.intObjectsProperty("LIST_SIZE_X"), prop.intObjectsProperty("LIST_SIZE_Y"));
        Dimension cellSize = new Dimension(prop.intObjectsProperty("CELL_SIZE_X"), prop.intObjectsProperty("CELL_SIZE_Y"));
        setBackground(prop.colorObjectsProperty("LIST_FRAME_COLOR"));
        setPreferredSize(listSize);
        list = new JList(works.getTitle());
        list.setBackground(prop.colorObjectsProperty("CELL_COLOR"));
        list.setForeground(prop.colorObjectsProperty("CELL_LABEL_COLOR"));
        list.setSelectionForeground(prop.colorObjectsProperty("CELL_SELECTION_LABEL_COLOR"));
        list.setSelectionBackground(prop.colorObjectsProperty("CELL_SELECTION_COLOR"));
        list.setFont(new Font(prop.getObjectsProperty("LABEL_FONT"), 1, prop.intObjectsProperty("LIST_LABEL_SIZE")));
        list.setCursor(new Cursor(12));
        list.setFixedCellWidth(cellSize.width);
        list.setFixedCellHeight(cellSize.height);
        add(list);
    }

    void update()
    {
        int index = 0;
        if(list.getSelectedIndex() != -1)
        {
            index = list.getSelectedIndex();
            works.setCurrentNum(index + 1);
        }
    }

    static JList list;
    static Dimension listSize;
    static Dimension cellSize;
    static PropertyReader prop = PropertyReader.getInstance();
    static Works works = Works.getInstance();

}
