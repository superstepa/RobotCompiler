package com.superstepa.bot;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SimpleBot extends Robot{
    private static final int EVENT_LMB = InputEvent.BUTTON1_MASK;
    private static final int EVENT_RMB = InputEvent.BUTTON3_MASK;
    private static final int EVENT_ENTER = KeyEvent.VK_ENTER;
    private static final int EVENT_SHIFT = KeyEvent.VK_SHIFT;
    private static final int EVENT_CAPSLOCK = KeyEvent.VK_CAPS_LOCK;
    private static final int KEYSTROKE_DELAY = 20;

    private int min_delay = 200;

    public SimpleBot() throws Exception {
        super();
    }

    public SimpleBot(int delay) throws Exception{
        super();
        min_delay = delay;
    }

    public void leftClick(){
        mousePress(EVENT_LMB);
        delay(min_delay);
        mouseRelease(EVENT_LMB);
        delay(min_delay);
    }

    public void rightClick(){
        mousePress(EVENT_RMB);
        delay(min_delay);
        mouseRelease(EVENT_RMB);
        delay(min_delay);
    }

    public void rightClickPoint(int x, int y, int delay){
        mouseMove(x,y);
        rightClick();
        delay(delay);
    }

    public void rightClickPoint(int x, int y){
        rightClickPoint(x,y,min_delay);
    }


    public void clickPoint(int x, int y, int delay){
        mouseMove(x,y);
        leftClick();
        delay(delay);
    }

    public void clickPoint(int x, int y){
        clickPoint(x,y,min_delay);
    }

    public void enter(){
        keyPress(EVENT_ENTER);
        keyRelease(EVENT_ENTER);
    }

    public void typeKeystroke(int code, boolean capital){
        if (capital)
            keyPress(EVENT_SHIFT);
        keyPress(code);
        delay(KEYSTROKE_DELAY);
        keyRelease(code);
        if (capital)
            keyRelease(EVENT_SHIFT);
    }

    public void typeKeystroke(int code){
        typeKeystroke(code, false);
    }

    //TODO: Alternative non letter character support.
    public void typeChar(char charCode){
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(charCode);
        boolean isUpper = Character.isUpperCase(charCode);
        typeKeystroke(keyCode, isUpper);
    }

    public void typeString(String str){
        for (char ch: str.toCharArray()){
            typeChar(ch);
        }
    }
}
