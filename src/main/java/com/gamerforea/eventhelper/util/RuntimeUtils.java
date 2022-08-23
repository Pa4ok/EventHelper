package com.gamerforea.eventhelper.util;

public class RuntimeUtils
{
    private static boolean detect(String cls)
    {
        try {
            Class.forName(cls);
            return true;
        } catch (Throwable ignored) {
            return false;
        }
    }

    public static boolean detectBukkit() {
        return detect("org.bukkit.Bukkit");
    }

    public static boolean detectIdea() {
        return detect("com.intellij.rt.execution.application.AppMainV2");
    }

    public static boolean detectThermos() {
        return detect("thermos.Thermos");
    }
}
