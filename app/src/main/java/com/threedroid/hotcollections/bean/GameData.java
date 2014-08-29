package com.threedroid.hotcollections.bean;

/**
 * Created by Administrator on 2014-8-28.
 */

/**
 * "game_id":"55222","game_name":"全民快跑",
 * "game_logo":"http://img.pipaw.net/Pipaw/Logo/2014/08/19/484c500843374d6db094cd022ee1efda.png",
 * "game_type":"","game_score":"3,1,1","show_type":"3","version_id":"","game_size":"0","game_downclick":"125569"
 */
public class GameData {
    public String game_id;
    public String game_name;
    public String game_logo;
    public String game_type;
    public String game_score;
    public String show_type;
    public String version_id;
    public String game_size;
    public String game_downclick;

    /**
     * showtype=online_ph（排行）online_zx（最新）
     * 首页最新和排行
     */
    public static final String SHOW_TYPE_PH_HOME = "online_ph";
    public static final String SHOW_TYPE_ZX_HOME = "online_zx";
    /**
     * showtype=list_ph_3（排行）list_zx_3（最新）
     * 分类内页最新和排行
     */
    public static final String SHOW_TYPE_PH_INNER = "list_ph_3";
    public static final String SHOW_TYPE_ZX_INNER = "list_zx_3";

    public static final String GAME_TYPE_JUESE = "29";
    public static final String GAME_TYPE_DONGZUO = "31";
    public static final String GAME_TYPE_FEIXING = "32";
    public static final String GAME_TYPE_XIUXIAN = "33";
    public static final String GAME_TYPE_TIYU = "34";
    public static final String GAME_TYPE_SAICHE = "36";
    public static final String GAME_TYPE_QIPAI = "37";
    public static final String GAME_TYPE_MAOXIAN = "39";

}
