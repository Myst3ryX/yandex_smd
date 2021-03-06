package com.sergon146.mobilization18.navigation;

import com.sergon146.mobilization18.ui.main.TabBarScreens;


public enum Screens {
    MAIN_SCREEN,
    FEED_SCREEN(TabBarScreens.FEED),
    REPORT_SCREEN(TabBarScreens.REPORT),
    SETTINGS_SCREEN(TabBarScreens.SETTINGS),
    ADD_TRANSACTION,
    ABOUT_SCREEN;

    public final TabBarScreens tab;

    Screens(TabBarScreens tab) {
        this.tab = tab;
    }

    Screens() {
        this(TabBarScreens.MAIN);
    }

    public static boolean contains(String value) {
        return getEnum(value) != null;
    }

    public static Screens getEnum(String value) {
        for (Screens c : Screens.values()) {
            if (c.name().equals(value)) {
                return c;
            }
        }

        return null;
    }
}
