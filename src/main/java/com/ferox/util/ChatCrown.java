package com.ferox.util;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public enum ChatCrown {

    SUPPORT("<img=14</img>", 505),
    MOD_CROWN("<img=1</img>", 494),
    ADMIN_CROWN("<img=2</img>", 495),
    OWNER_CROWN("<img=3</img>", 496),
    COMMUNITY_MANAGER("<img=27</img>", 1315),
    DEVELOPER("<img=4</img>", 497),
    HEAD_ADMIN("<img=28</img>",1314),
    HEAD_MODERATOR("<img=29</img>",1313),

    BRONZE_YOUTUBER("<img=8</img>", 501),
    SILVER_YOUTUBER("<img=16</img>", 501),
    GOLD_YOUTUBER("<img=17</img>", 501),

    // Ironman
    IRON_MAN("<img=9</img>", 502),
    ULTIMATE_IRONMAN("<img=10</img>", 503),
    HARDCORE_IRONMAN("<img=11</img>", 504),
    ELITE_IRONMAN("<img=18</img>", 1769),
    GROUP_IRONMAN("<img=19</img>", 1770),
    DARK_LORD("<img=25</img>", 1838),
    SECURITY_MOD("<img=26</img>", 1861),


    //Members
    RUBY("<img=5</img>", 1427),
    SAPPHIRE("<img=6</img>", 500),
    EMERALD("<img=22</img>", 1077),
    DIAMOND("<img=7</img>", 1078),
    DRAGONSTONE("<img=24</img>", 1425),
    ONYX("<img=15</img>", 1426),
    ZENYTE("<img=23</img>", 1048),

    // Extras
    PKER_ICON("<img=21</img>", 506),

    ;

    private final String identifier;
    private final int spriteId;

    ChatCrown(String identifier, int spriteId) {
        this.identifier = identifier;
        this.spriteId = spriteId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getSpriteId() {
        return spriteId;
    }

    private static final Set<ChatCrown> STAFF = EnumSet.of(MOD_CROWN, ADMIN_CROWN, OWNER_CROWN, DEVELOPER, SUPPORT, SECURITY_MOD, COMMUNITY_MANAGER, BRONZE_YOUTUBER, SILVER_YOUTUBER, GOLD_YOUTUBER, IRON_MAN, HARDCORE_IRONMAN, DARK_LORD);

    public boolean isStaff() {
        return STAFF.contains(this);
    }

    public static List<ChatCrown> get(int rights, int donatorRights) {
        List<ChatCrown> crowns = new ArrayList<>();

        PlayerRights playerRights = PlayerRights.get(rights);
        if (playerRights != PlayerRights.PLAYER
                && playerRights.getCrown() != null) {
            crowns.add(playerRights.getCrown());
        }

        MemberRights donorRights = MemberRights.get(donatorRights);
        if (donorRights != MemberRights.NONE
                && donorRights.getCrown() != null) {
            crowns.add(donorRights.getCrown());
        }

        return crowns;
    }
    
}
