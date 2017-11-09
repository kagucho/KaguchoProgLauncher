// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Util.java

package launcher;

import java.awt.Color;
import java.io.PrintStream;

class Util
{

    private Util()
    {
    }

    private static boolean isOS(String osName)
    {
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println((new StringBuilder(String.valueOf(osName))).append(":").append(os.startsWith(osName)).toString());
        return os.startsWith(osName);
    }

    static boolean isWindows()
    {
        return isOS("win");
    }

    static boolean isMac()
    {
        return isOS("mac");
    }

    static Color toColor(String id)
    {
        String s;
        switch((s = id).hashCode())
        {
        default:
            break;

        case -1955522002: 
            if(s.equals("ORANGE"))
                return Color.orange;
            break;

        case -1770733863: 
            if(s.equals("DARKGRAY"))
                return Color.darkGray;
            break;

        case -1680910220: 
            if(s.equals("YELLOW"))
                return Color.yellow;
            break;

        case -822127527: 
            if(s.equals("LIGHTGRAY"))
                return Color.lightGray;
            break;

        case 81009: 
            if(s.equals("RED"))
                return Color.red;
            break;

        case 2041946: 
            if(s.equals("BLUE"))
                return Color.blue;
            break;

        case 2083619: 
            if(s.equals("CYAN"))
                return Color.cyan;
            break;

        case 2196067: 
            if(s.equals("GRAY"))
                return Color.gray;
            break;

        case 2455926: 
            if(s.equals("PINK"))
                return Color.pink;
            break;

        case 63281119: 
            if(s.equals("BLACK"))
                return Color.black;
            break;

        case 68081379: 
            if(s.equals("GREEN"))
                return Color.green;
            break;

        case 82564105: 
            if(s.equals("WHITE"))
                return Color.white;
            break;

        case 1546904713: 
            if(s.equals("MAGENTA"))
                return Color.magenta;
            break;
        }
        return Color.white;
    }
}
