package net.runelite.client.util;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import java.awt.Frame;
public class WinUtil {

    /**
     * Forcibly set focus to the given component
     *
     */
    public static void requestForeground(Frame frame)
    {
        // SetForegroundWindow can't set iconified windows to foreground, so set the
        // frame state to normal first
        frame.setState(Frame.NORMAL);

        User32 user32 = User32.INSTANCE;

        // Windows does not allow any process to set the foreground window, but it will if
        // the process received the last input event. So we send a F22 key event to the process.
        // https://docs.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-setforegroundwindow
        WinUser.INPUT input = new WinUser.INPUT();
        input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
        input.input.ki.wVk = new WinDef.WORD(0x85); // VK_F22
        user32.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());

        // Now we may set the foreground window
        WinDef.HWND hwnd = new WinDef.HWND(Native.getComponentPointer(frame));
        user32.SetForegroundWindow(hwnd);
    }
}
