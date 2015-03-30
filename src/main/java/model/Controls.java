package main.java.model;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/*
 @author Barton, Rajcan
 */
public class Controls {
    public static final Map<Integer, Integer[]> controls = new HashMap<Integer, Integer[]>();

    static {
        controls.put(0, new Integer[]{KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT});
        controls.put(1, new Integer[]{KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A});
        controls.put(2, new Integer[]{KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD6, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD4});
        controls.put(3, new Integer[]{KeyEvent.VK_G, KeyEvent.VK_N, KeyEvent.VK_B, KeyEvent.VK_V});
        controls.put(4, new Integer[]{KeyEvent.VK_I, KeyEvent.VK_L, KeyEvent.VK_K, KeyEvent.VK_J});
    }
}
