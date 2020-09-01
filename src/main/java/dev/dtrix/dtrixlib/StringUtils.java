package dev.dtrix.dtrixlib;

import net.minecraft.client.gui.FontRenderer;

import java.util.List;

public class StringUtils {

    public static String abbreviateSplitString(FontRenderer fontRenderer, String str, int wrapWidth, int maxLines) {
        List<String> stringList = fontRenderer.listFormattedStringToWidth(str, wrapWidth);
        if(stringList.size() >= maxLines) {
            stringList = stringList.subList(0, maxLines);
            String last = stringList.get(maxLines-1);
            if(fontRenderer.getStringWidth(last) >= wrapWidth) {
                last = last.substring(0, last.length() - 2);
                stringList.set(maxLines-1, last + "...");
            }
        }
        return String.join(" ", stringList);
    }

}
