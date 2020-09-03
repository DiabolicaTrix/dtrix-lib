package dev.dtrix.dtrixlib.util;

public class ColorUtils {

    public static int fromHexToIntRGBA(String hex)
    {
        return ColorUtils.fromHexToIntRGBA(hex, 1);
    }

    public static int fromHexToIntRGBA(String hex, float alpha)
    {
        int padding = hex.startsWith("#") ? 1 : 0;
        float red = Integer.parseInt(hex.substring(padding, 2 + padding), 16) / 255.0F;
        float green = Integer.parseInt(hex.substring(2 + padding, 4 + padding), 16) / 255.0F;
        float blue = Integer.parseInt(hex.substring(4 + padding, 6 + padding), 16) / 255.0F;

        int rtn = 0;
        rtn |= (int) (alpha * 255) << 24;
        rtn |= (int) (red * 255) << 16;
        rtn |= (int) (green * 255) << 8;
        rtn |= (int) (blue * 255);
        return rtn;
    }


}
