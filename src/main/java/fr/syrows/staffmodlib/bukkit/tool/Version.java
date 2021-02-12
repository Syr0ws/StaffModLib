package fr.syrows.staffmodlib.bukkit.tool;

import org.bukkit.Bukkit;

import java.util.Arrays;

public class Version {

    public static final int[] LEGACY_VERSION = {1, 12, 0};

    private final int[] version;
    private final boolean legacy;

    public Version() {

        String version = Bukkit.getServer().getVersion();

        version = version.substring(version.indexOf("(") + 5, version.indexOf(")"));

        String[] format = version.split("\\.");

        int a = Integer.parseInt(format[0]);
        int b = Integer.parseInt(format[1]);
        int c = format.length > 2 ? Integer.parseInt(format[2]) : 0;

        this.version = new int[]{a, b, c};
        this.legacy = this.isLegacyInternal();
    }

    private boolean isLegacyInternal() {

        for(int i = 0; i < this.version.length; i++) {

            if(this.version[i] < LEGACY_VERSION[i]) return true;
        }
        return false;
    }

    public boolean isLegacy() {
        return this.legacy;
    }

    public int[] getVersion() {
        return Arrays.copyOf(this.version, this.version.length);
    }
}
