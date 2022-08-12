package com.gamerforea.eventhelper.util;

public class RuntimeUtils
{
    public static boolean detectBukkit()
    {
        try {
            Class.forName("org.bukkit.Bukkit");
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean detectIdea()
    {
        try {
            Class.forName("com.intellij.rt.execution.application.AppMainV2");
            return true;
        } catch (Throwable t) {
            return false;
        }
    }
}
