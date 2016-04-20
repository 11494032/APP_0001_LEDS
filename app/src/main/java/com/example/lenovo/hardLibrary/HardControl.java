package com.example.lenovo.hardLibrary;

public class HardControl
{
    public static native int ledsOpen();
    public static native int ledsCtrl( int which, int status );
    public static native void ledsClose();

    static
    {
        try {
            System.loadLibrary("hardcontrol");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
