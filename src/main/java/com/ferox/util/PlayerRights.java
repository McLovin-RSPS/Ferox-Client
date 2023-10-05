package com.ferox.util;

import java.util.HashMap;
import java.util.Map;

public enum PlayerRights {
    //Warning: the rights in here must match the server-side Rights enum.
    PLAYER(null),
    BRONZE_YOUTUBER(ChatCrown.BRONZE_YOUTUBER),
    SILVER_YOUTUBER(ChatCrown.SILVER_YOUTUBER),
    GOLD_YOUTUBER(ChatCrown.GOLD_YOUTUBER),
    SUPPORT(ChatCrown.SUPPORT),
    MODERATOR(ChatCrown.MOD_CROWN),
    HEAD_MODERATOR(ChatCrown.HEAD_MODERATOR),
    ADMINISTRATOR(ChatCrown.ADMIN_CROWN),
    HEAD_ADMIN(ChatCrown.HEAD_ADMIN),
    COMMUNITY_MANAGER(ChatCrown.COMMUNITY_MANAGER),
    DEVELOPER(ChatCrown.DEVELOPER),
    OWNER(ChatCrown.OWNER_CROWN),
    IRON_MAN(ChatCrown.IRON_MAN),
    ULTIMATE_IRONMAN(ChatCrown.ULTIMATE_IRONMAN),
    HARDCORE_IRONMAN(ChatCrown.HARDCORE_IRONMAN),
    ELITE_IRONMAN(ChatCrown.ELITE_IRONMAN),
    GROUP_IRONMAN(ChatCrown.GROUP_IRONMAN),
    DARK_LORD(ChatCrown.DARK_LORD),
    SECURITY_MODERATOR(ChatCrown.SECURITY_MOD),
    EVENT_MANAGER(ChatCrown.COMMUNITY_MANAGER),
    ;

    private final ChatCrown crown;

    PlayerRights(ChatCrown crown) {
        this.crown = crown;
    }

    public ChatCrown getCrown() {
        return crown;
    }

    private static final Map<Integer, PlayerRights> rights = new HashMap<>();
    static {
        for (PlayerRights r : PlayerRights.values()) {
            rights.put(r.ordinal(), r);
        }
    }

    public static PlayerRights get(int ordinal) {
        return rights.getOrDefault(ordinal, PlayerRights.PLAYER);
    }
}
