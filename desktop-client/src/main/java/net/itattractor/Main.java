package net.itattractor;

public class Main {
    public static void main(String args[])
    {
        AppLauncher appLauncher = new AppLauncher();
        appLauncher.init();

        Thread.setDefaultUncaughtExceptionHandler(new Handler());
    }
}