package map;

import BossHDL.BossManager;
import Helps.ItemStar;
import core.Service;
import event.EventManager;
import event.Event_1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import client.Player;
import core.Manager;
import core.Util;
import event_daily.Bossnhom;
import io.Message;
import io.Session;
import template.*;

public class LeaveItemMap {

    public static List<Short> item0x = new ArrayList<>();
    public static List<Short> item1x = new ArrayList<>();
    public static List<Short> item2x = new ArrayList<>();
    public static List<Short> item3x = new ArrayList<>();
    public static List<Short> item4x = new ArrayList<>();
    public static List<Short> item5x = new ArrayList<>();
    public static List<Short> item6x = new ArrayList<>();
    public static List<Short> item7x = new ArrayList<>();
    public static List<Short> item8x = new ArrayList<>();
    public static List<Short> item9x = new ArrayList<>();
    public static List<Short> item10x = new ArrayList<>();
    public static List<Short> item11x = new ArrayList<>();
    public static List<Short> item12x = new ArrayList<>();
    public static List<Short> item13x = new ArrayList<>();
    public static List<Short> item14x = new ArrayList<>();
    public static List<Short> item15x = new ArrayList<>();
    public static void leave_vang(Map map, Mob_in_map mob, Player p) throws IOException {
        if (mob != null) {
            int index_item_map = map.get_item_map_index_able();
            if (index_item_map > -1) {
                //
                map.item_map[index_item_map] = new ItemMap();
                map.item_map[index_item_map].id_item = -1;
                map.item_map[index_item_map].color = 0;
                int vang_drop = Util.random(mob.level * 100, mob.level * 200);
                EffTemplate ef = p.get_EffDefault(52);
                if (ef != null) {
                    vang_drop += (vang_drop * (ef.param / 100)) / 100;
                }
                map.item_map[index_item_map].quantity = vang_drop;
                map.item_map[index_item_map].category = 4;
                map.item_map[index_item_map].idmaster = (short) p.index;
                map.item_map[index_item_map].time_exist = System.currentTimeMillis() + 60_000L;
                map.item_map[index_item_map].time_pick = System.currentTimeMillis() + 1_500L;
                String name = "vàng *" + map.item_map[index_item_map].quantity;
                // add in4 game scr
                Message mi = new Message(19);
                mi.writer().writeByte(4);
                mi.writer().writeShort(mob.index); // index mob die
                mi.writer().writeShort(0); // id icon (0 : vang)
                mi.writer().writeShort(index_item_map); //
                mi.writer().writeUTF(name);
                mi.writer().writeByte(0); // color
                mi.writer().writeShort(p.index); // id player
                MapService.send_msg_player_inside(map, p, mi, true);
                mi.cleanup();
            }
        }
    }

    public static void leave_vang(Map map, MainObject mob, Player p, int vang_drop) throws IOException {
        if (mob != null) {
            int index_item_map = map.get_item_map_index_able();
            if (index_item_map > -1) {
                //
                map.item_map[index_item_map] = new ItemMap();
                map.item_map[index_item_map].id_item = -1;
                map.item_map[index_item_map].color = 0;
                EffTemplate ef = p.get_EffDefault(52);
                if (ef != null) {
                    vang_drop += (vang_drop * (ef.param / 100)) / 100;
                }
                map.item_map[index_item_map].quantity = vang_drop;
                map.item_map[index_item_map].category = 4;
                map.item_map[index_item_map].idmaster = (short) p.index;
                map.item_map[index_item_map].time_exist = System.currentTimeMillis() + 60_000L;
                map.item_map[index_item_map].time_pick = System.currentTimeMillis() + 1_500L;
                String name = "vàng *" + map.item_map[index_item_map].quantity;
                // add in4 game scr
                Message mi = new Message(19);
                mi.writer().writeByte(4);
                mi.writer().writeShort(mob.index); // index mob die
                mi.writer().writeShort(0); // id icon (0 : vang)
                mi.writer().writeShort(index_item_map); //
                mi.writer().writeUTF(name);
                mi.writer().writeByte(0); // color
                mi.writer().writeShort(p.index); // id player
                MapService.send_msg_player_inside(map, p, mi, true);
                mi.cleanup();
            }
        }
    }

    public static void leave_item_3(Map map, Mob_in_map mob, Player p) throws IOException {
        if (mob != null) {
            short id_item_can_drop = 0;
            byte color_ = 0;
            if (60 > Util.random(0, 200)) {
                color_ = 1;
            } else if (25 > Util.random(0, 275)) {
                color_ = 2;
            } else if (70 > Util.random(0, 200)) {
                color_ = 3;
            } else if (5 > Util.random(0, 200)) {
                color_ = 4;
            }
            if (mob.color_name != 0) {
                color_ = 3;
            }
            if (mob.level >= 1 && mob.level < 10 && item0x.size() > 0) {
                id_item_can_drop = item0x.get(Util.random(0, item0x.size()));
            } else if (mob.level >= 10 && mob.level < 20 && item1x.size() > 0) {
                id_item_can_drop = item1x.get(Util.random(0, item1x.size()));
            } else if (mob.level >= 20 && mob.level < 30 && item2x.size() > 0) {
                id_item_can_drop = item2x.get(Util.random(0, item2x.size()));
            } else if (mob.level >= 30 && mob.level < 40 && item3x.size() > 0) {
                id_item_can_drop = item3x.get(Util.random(0, item3x.size()));
            } else if (mob.level >= 40 && mob.level < 50 && item4x.size() > 0) {
                id_item_can_drop = item4x.get(Util.random(0, item4x.size()));
            } else if (mob.level >= 50 && mob.level < 60 && item5x.size() > 0) {
                id_item_can_drop = item5x.get(Util.random(0, item5x.size()));
            } else if (mob.level >= 60 && mob.level < 70 && item6x.size() > 0) {
                id_item_can_drop = item6x.get(Util.random(0, item6x.size()));
            } else if (mob.level >= 70 && mob.level < 80 && item7x.size() > 0) {
                id_item_can_drop = item7x.get(Util.random(0, item7x.size()));
            } else if (mob.level >= 80 && mob.level < 90 && item8x.size() > 0) {
                id_item_can_drop = item8x.get(Util.random(0, item8x.size()));
            } else if (mob.level >= 90 && mob.level < 100 && item9x.size() > 0) {
                id_item_can_drop = item9x.get(Util.random(0, item9x.size()));
            } else if (mob.level >= 100 && mob.level < 110 && item10x.size() > 0) {
                id_item_can_drop = item10x.get(Util.random(0, item10x.size()));
            } else if (mob.level >= 110 && mob.level < 120 && item11x.size() > 0) {
                id_item_can_drop = item11x.get(Util.random(0, item11x.size()));
            } else if (mob.level >= 120 && mob.level < 130 && item12x.size() > 0) {
                id_item_can_drop = item12x.get(Util.random(0, item12x.size()));
            } else if (mob.level >= 130 && item13x.size() > 0) {
                id_item_can_drop = item13x.get(Util.random(0, item13x.size()));
            }
            String name = ItemTemplate3.item.get(id_item_can_drop).getName();
            short index_real = 0;
            if (id_item_can_drop < 20) {
                for (int i = 0; i < 20; i++) {
                    if (ItemTemplate3.item.get(i).getName().equals(name) && ItemTemplate3.item.get(i).getColor() == color_) {
                        index_real = (short) i;
                        break;
                    }
                }
            } else {
                for (int i = id_item_can_drop - 5; i < id_item_can_drop + 5; i++) {
                    if (ItemTemplate3.item.get(i).getName().equals(name) && ItemTemplate3.item.get(i).getColor() == color_) {
                        index_real = (short) i;
                        break;
                    }
                }
            }
            //
            leave_item_by_type3(map, index_real, color_, p, name, mob.index);
        }
    }

    public static void leave_item_by_type3(Map map, int index_real, int color_, Player p_master, String name, int index)
            throws IOException {
        int index_item_map = map.get_item_map_index_able();
        if (index_item_map > -1) {
            //
            map.item_map[index_item_map] = new ItemMap();
            map.item_map[index_item_map].id_item = (short) index_real;
            map.item_map[index_item_map].color = (byte) color_;
            map.item_map[index_item_map].quantity = 1;
            map.item_map[index_item_map].category = 3;
            map.item_map[index_item_map].idmaster = (short) p_master.index;
            List<Option> opnew = new ArrayList<Option>();
            for (Option op_old : ItemTemplate3.item.get(index_real).getOp()) {
                Option temp = new Option(1, 1, (short) 0);
                temp.id = op_old.id;
                if (temp.id != 37 && temp.id != 38) {
                    if (op_old.getParam(0) < 10) {
                        temp.setParam(Util.random(0, 10));
                    } else {
                        temp.setParam(Util.random((9 * op_old.getParam(0)) / 10, op_old.getParam(0)));
                    }
                } else {
                    temp.setParam(1);
                }
                opnew.add(temp);
            }
            map.item_map[index_item_map].op = new ArrayList<>();
            map.item_map[index_item_map].op.addAll(opnew);
            map.item_map[index_item_map].time_exist = System.currentTimeMillis() + 60_000L;
            map.item_map[index_item_map].time_pick = System.currentTimeMillis() + 1_500L;
            // add in4 game scr
            Message mi = new Message(19);
            mi.writer().writeByte(3);
            mi.writer().writeShort(index); // index mob die
            mi.writer().writeShort(ItemTemplate3.item.get(map.item_map[index_item_map].id_item).getIcon());
            mi.writer().writeShort(index_item_map); //
            mi.writer().writeUTF(name);
            mi.writer().writeByte(color_); // color
            mi.writer().writeShort(-1); // id player
            MapService.send_msg_player_inside(map, p_master, mi, true);
            mi.cleanup();
        }
    }

    public static void leave_item_4(Map map, Mob_in_map mob, Player p) throws IOException {
        if (mob != null) {
            short index_real = (short) Util.random(0, 6);
            //
            leave_item_by_type4(map, index_real, p, mob.index);
            if(Manager.gI().event == 11) {
                if (Util.random(100) < 2) {
                    leave_item_by_type4(map, (short) 335, p, mob.index);
                }
            }
        }
    }

    public static void leave_item_by_type4(Map map, short id_item, Player p_master, int index_mob) throws IOException {
        int index_item_map = map.get_item_map_index_able();
        if (index_item_map > -1) {
            //
            map.item_map[index_item_map] = new ItemMap();
            map.item_map[index_item_map].id_item = id_item;
            map.item_map[index_item_map].color = 0;
            map.item_map[index_item_map].quantity = 1;
            map.item_map[index_item_map].category = 4;
            map.item_map[index_item_map].idmaster = (short) p_master.index;
            map.item_map[index_item_map].time_exist = System.currentTimeMillis() + 60_000L;
            map.item_map[index_item_map].time_pick = System.currentTimeMillis() + 1_500L;
            // add in4 game scr
            Message mi = new Message(19);
            mi.writer().writeByte(4);
            mi.writer().writeShort(index_mob); // id mob die
            mi.writer().writeShort(ItemTemplate4.item.get(map.item_map[index_item_map].id_item).getIcon());
            mi.writer().writeShort(index_item_map); //
            mi.writer().writeUTF(ItemTemplate4.item.get(map.item_map[index_item_map].id_item).getName());
            mi.writer().writeByte(0); // color
            mi.writer().writeShort(-1); // id player
            MapService.send_msg_player_inside(map, p_master, mi, true);
            mi.cleanup();
        }
    }

    public static void leave_item_7(Map map, Mob_in_map mob, Player p) throws IOException {
        if (mob != null) {
            short index_real = (short) Util.random(0, 4);
            //
            leave_item_by_type7(map, index_real, p, mob.index);
        }
    }

    public static void leave_item_by_type7(Map map, short id_it, Player p_master, int indexmob) throws IOException {
        if (p_master != null && !p_master.isDropMaterialMedal && id_it >= 46 && id_it <= 345) {
            return;
        }
        int index_item_map = map.get_item_map_index_able();
        if (index_item_map > -1) {
            //
            map.item_map[index_item_map] = new ItemMap();
            map.item_map[index_item_map].id_item = id_it;
            if (ItemTemplate7.item.get(map.item_map[index_item_map].id_item).getColor() == 21) {
                map.item_map[index_item_map].color = 1;
            } else {
                map.item_map[index_item_map].color = 0;
            }
            map.item_map[index_item_map].quantity = 3;
            map.item_map[index_item_map].category = 7;
            map.item_map[index_item_map].idmaster = (short) p_master.index;
            map.item_map[index_item_map].time_exist = System.currentTimeMillis() + 10000L;
            map.item_map[index_item_map].time_pick = System.currentTimeMillis() + 1500L;
            // add in4 game scr
            Message mi = new Message(19);
            mi.writer().writeByte(7);
            mi.writer().writeShort(indexmob); // id mob die
            mi.writer().writeShort(ItemTemplate7.item.get(map.item_map[index_item_map].id_item).getIcon());
            mi.writer().writeShort(index_item_map); //
            mi.writer().writeUTF(ItemTemplate7.item.get(map.item_map[index_item_map].id_item).getName());
            mi.writer().writeByte(map.item_map[index_item_map].color); // color
            mi.writer().writeShort(-1); // id player
            MapService.send_msg_player_inside(map, p_master, mi, true);
            mi.cleanup();
        }
    }

    public static void leave_item_boss(Map map, Mob_in_map mob, Player p) throws IOException {
        if (mob != null && mob.isBoss()) {
            // roi do boss co dinh
            short[] id_item_leave3 = new short[]{};
            short[] id_item_leave4 = new short[]{};
            short[] id_item_leave7 = new short[]{};
            short[] id_item_hongio = new short[]{};
          //  short id_medal_material = -19;
            short sizeRandomMedal = 0;
            switch (mob.template.mob_id) {
                case 101, 84, 83, 103, 104, 105, 106, 149,155: { // xa nu
                    id_item_leave4 = new short[]{-1, -1, -1, -1, -1, -1,(short) Util.random(347,357)};
                    id_item_leave7 = new short[]{(short) Util.random(481,493),(short) Util.random(472,480),(short) Util.random(336,346)};
                    if(Manager.gI().event == 11){
                        id_item_hongio = new short[]{336};
                    }
                    p.ngoc_and_coin();
                    //sizeRandomMedal = (short) (30);
                    break;
                }
                case 173, 195, 196, 197, 186, 187, 188: { //tho tuyet
                    id_item_leave4 = new short[]{-1, -1, -1, -1, -1, -1,(short) Util.random(347,357)};
                    id_item_leave7 = new short[]{(short) Util.random(481,493),(short) Util.random(472,480),(short) Util.random(336,346)};
                    if(Manager.gI().event == 11){
                        id_item_hongio = new short[]{336};
                    }
                    p.ngoc_and_coin();
                    break;
                }
                case 191: {//boss phụ của boss nhóm
                    Bossnhom.bossphu += 1;
                    String chat = "Nhóm của bạn đã hoàn thành " +Bossnhom.bossphu+ " Boss / 5 Boss phụ";
                    Service.chat_nhom(p.party,chat);
                    if(Bossnhom.bossphu == 5){
                        Bossnhom.timeAttack = 0;
                        Bossnhom.timeAttack = System.currentTimeMillis() + 1000 * 60* 10;
                        BossManager.callBossToMapdangcap(46,0,194,486,402,1_500_000_000,p.level * 50,p.level * 2,p.level * 2,p.level* 2,p.level * 2,10);
                    }
                    break;
                }
                case 194: {//boss chính của boss nhóm
                    Bossnhom.bosschinh = 1;
                    String chat = "Diệt boss thành công các bạn sẽ thoát khỏi map sau 30s nữa";
                    Service.chat_nhom(p.party,chat);
                    id_item_leave4 = new short[]{-1, -1, -1, -1, -1, -1,(short) Util.random(347,357)};
                    id_item_leave7 = new short[]{(short) Util.random(481,493),(short) Util.random(472,480),(short) Util.random(336,346)};
                    p.ngoc_and_coin();
                    break;
                }
                case 178: { //boss sk
//                    id_item_leave4 = new short[]{-1, -1, -1, -1, -1, -1, 273, 274, 251,(short) Util.random(352,357)};
//                    id_item_leave7 = new short[]{(short) Util.random(481,493),(short) Util.random(472,480),349};
//                    id_sk = new short[]{470};
                        // sizeRandomMedal = (short) (60);
                    break;
                }
                case 193: {// boss event hồn gió
                    id_item_leave4 = new short[]{-1, -1, -1, -1, -1, -1, 273, 274, 251,(short) Util.random(352,357)};
                    id_item_leave7 = new short[]{493};
                    p.ngoc_and_coin();
                    p.boss += 1;
                    break;
                }
            }
            for (short id : id_item_leave3) {
                ItemTemplate3 temp = ItemTemplate3.item.get(id);
                leave_item_by_type3(map, id, temp.getColor(), p, temp.getName(), mob.index);
            }
            for (int i = 0; i < 3; i++) {
                for (short id : id_item_leave4) {
                    if (id == -1) {
                        leave_vang(map, mob, p);
                    } else {
                        leave_item_by_type4(map, id, p, mob.index, p.index);
                    }
                }
            }
            for (int i = 0; i < 2; i++) {
                for (short id : id_item_hongio) {
                    leave_item_by_type4(map, id, p, mob.index, p.index);
                }
            }
            for (int i = 0; i < Util.random(1,6); i++) {
                for (short id : id_item_leave7) {
                    leave_item_by_type7(map, id, p, mob.index, p.index);
                }
            }
            for (int l = 0; l < sizeRandomMedal; l++) {
                leave_item_by_type7(map, (short) Util.random(136, 146), p, mob.index, p.index);
            }
        }
    }

    public static void leave_material(Map map, Mob_in_map mob, Player p) throws IOException {
        if (mob != null) {
            short index_real = -1;
            switch (mob.level) {
                case 1:
                case 2:
                case 3:
                case 4: {
                    index_real = Medal_Material.m_white[0][Util.random(0, 3)];
                    break;
                }
                case 5: {
                    index_real = Medal_Material.m_white[0][Util.random(3, 6)];
                    break;
                }
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17: {
                    index_real = Medal_Material.m_white[0][Util.random(6, 10)];
                    break;
                }
                case 19: {
                    index_real = Medal_Material.m_white[1][Util.random(0, 3)];
                    break;
                }
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26: {
                    index_real = Medal_Material.m_white[1][Util.random(3, 6)];
                    break;
                }
                case 28:
                case 29: {
                    index_real = Medal_Material.m_white[1][Util.random(6, 9)];
                    break;
                }
                case 31:
                case 32:
                case 33:
                case 34:
                case 35: {
                    index_real = Medal_Material.m_white[1][9];
                    break;
                }
                case 37: {
                    index_real = Medal_Material.m_white[2][Util.random(0, 3)];
                    break;
                }
                case 39:
                case 40:
                case 41:
                case 42:
                case 43: {
                    index_real = Medal_Material.m_white[2][Util.random(3, 6)];
                    break;
                }
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54: {
                    index_real = Medal_Material.m_white[2][Util.random(6, 10)];
                    break;
                }
                case 55: {
                    index_real = Medal_Material.m_white[3][Util.random(0, 3)];
                    break;
                }
                case 57:
                case 58:
                case 59: {
                    index_real = Medal_Material.m_white[3][Util.random(3, 6)];
                    break;
                }
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                case 69: {
                    index_real = Medal_Material.m_white[3][Util.random(6, 10)];
                    break;
                }
                case 71:
                case 72:
                case 73:
                case 74: {
                    index_real = Medal_Material.m_white[4][Util.random(0, 3)];
                    break;
                }
                case 76:
                case 77: {
                    index_real = Medal_Material.m_white[4][Util.random(3, 6)];
                    break;
                }
                case 78:
                case 79:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89: {
                    index_real = Medal_Material.m_white[4][Util.random(6, 10)];
                    break;
                }
                case 90:
                case 91:
                case 92: {
                    index_real = Medal_Material.m_white[5][Util.random(0, 3)];
                    break;
                }
                case 93:
                case 94:
                case 95: {
                    index_real = Medal_Material.m_white[5][Util.random(3, 6)];
                    break;
                }
                case 96:
                case 97:
                case 98:
                case 99:
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107: {
                    index_real = Medal_Material.m_white[5][Util.random(6, 10)];
                    break;
                }
                case 109:
                case 110: {
                    index_real = Medal_Material.m_white[6][Util.random(0, 3)];
                    break;
                }
                case 112:
                case 113: {
                    index_real = Medal_Material.m_white[6][Util.random(3, 6)];
                    break;
                }
                case 114:
                case 115:
                case 116:
                case 117:
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                case 123: {
                    index_real = Medal_Material.m_white[6][Util.random(6, 10)];
                    break;
                }
                case 125:
                case 126:
                case 127:
                case 128:
                case 129:
                case 130:
                case 131:
                case 132:
                case 133:
                case 134: {
                    index_real = Medal_Material.m_white[0][Util.random(6, 10)];
                    break;
                }
            }
            //
            if (index_real > -1) {
                //
                leave_item_by_type7(map, index_real, p, mob.index);
            }
//            if (25 > Util.random(0, 100)) {
//                index_real = Medal_Material.m_yellow[Util.random(0, 10)];
//                //
//                leave_item_by_type7(map, index_real, p, mob.index);
//            }
            if (25 > Util.random(0, 100)) {
                index_real = (short) ((15 > Util.random(0, 120)) ? 11
                        : ((35 > Util.random(0, 120)) ? 10 : ((50 > Util.random(0, 120)) ? 9 : 8)));
                //
                leave_item_by_type7(map, index_real, p, mob.index);
            }
        }
    }

    public static void leave_item_event(Map map, Mob_in_map mob, Player p) throws IOException {
        if (Manager.gI().event != 1) {
            return;
        }
        if (mob != null) {
            short index_real = EventManager.item_leave[Manager.gI().event - 1][Util
                    .random(EventManager.item_leave[Manager.gI().event - 1].length)];
            if (!Event_1.check(p.name) && index_real >= 153 && index_real <= 156) {
                return;
            }
            //
            leave_item_by_type4(map, index_real, p, mob.index);
        }
    }

    public static void leave_material_ngockham(Map map, Mob_in_map mob, Player p) throws IOException {
        if (mob != null) {
            short index_real = -1;
            //
            if (25 > Util.random(120)) {
                switch (mob.template.mob_id) {
                    case 167: {
                        index_real = 362;
                        break;
                    }
                    case 168: {
                        index_real = 372;
                        break;
                    }
                    case 169: {
                        index_real = 367;
                        break;
                    }
                    case 170: {
                        index_real = 357;
                        break;
                    }
                    case 171: {
                        index_real = 377;
                        break;
                    }
                    case 172: {
                        index_real = 352;
                        break;
                    }
                }
            }
            if (index_real != -1) {
                leave_item_by_type7(map, index_real, p, mob.index);
            }
        }
    }
     

    public static void leave_vang(Map map, Mob_in_map mob, int id_mater) throws IOException {
        if (mob != null) {
            int index_item_map = map.get_item_map_index_able();
            if (index_item_map > -1) {
                //
                map.item_map[index_item_map] = new ItemMap();
                map.item_map[index_item_map].id_item = -1;
                map.item_map[index_item_map].color = 0;
                int vang_drop = Util.random(mob.level * 25, mob.level * 60);
                map.item_map[index_item_map].quantity = vang_drop;
                map.item_map[index_item_map].category = 4;
                map.item_map[index_item_map].idmaster = (short) id_mater;
                map.item_map[index_item_map].time_exist = System.currentTimeMillis() + 60_000L;
                map.item_map[index_item_map].time_pick = System.currentTimeMillis() + 1_500L;
                String name = "vàng *" + map.item_map[index_item_map].quantity;
                // add in4 game scr
                Message mi = new Message(19);
                mi.writer().writeByte(4);
                mi.writer().writeShort(mob.index); // index mob die
                mi.writer().writeShort(0); // id icon (0 : vang)
                mi.writer().writeShort(index_item_map); //
                mi.writer().writeUTF(name);
                mi.writer().writeByte(0); // color
                mi.writer().writeShort(-1); // id player
                MapService.send_msg_player_inside(map, mob, mi, true);
                mi.cleanup();
            }
        }
    }

    public static void leave_item_by_type3(Map map, int index_real, int color_, Player p_master, String name, int index, int idP)
            throws IOException {
        int index_item_map = map.get_item_map_index_able();
        if (index_item_map > -1) {
            //
            map.item_map[index_item_map] = new ItemMap();
            map.item_map[index_item_map].id_item = (short) index_real;
            map.item_map[index_item_map].color = (byte) color_;
            map.item_map[index_item_map].quantity = 1;
            map.item_map[index_item_map].category = 3;
            map.item_map[index_item_map].idmaster = (short) idP;
            List<Option> opnew = new ArrayList<>();
            for (Option op_old : ItemTemplate3.item.get(index_real).getOp()) {
                Option temp = new Option(1, 1, (short) 0);
                temp.id = op_old.id;
                if (temp.id != 37 && temp.id != 38) {
                    if (op_old.getParam(0) < 10) {
                        temp.setParam(Util.random(0, 10));
                    } else {
                        temp.setParam(Util.random((9 * op_old.getParam(0)) / 10, op_old.getParam(0)));
                    }
                } else {
                    temp.setParam(1);
                }
                opnew.add(temp);
            }
            map.item_map[index_item_map].op = new ArrayList<>();
            map.item_map[index_item_map].op.addAll(opnew);
            map.item_map[index_item_map].time_exist = System.currentTimeMillis() + 60_000L;
            map.item_map[index_item_map].time_pick = System.currentTimeMillis() + 1_500L;
            // add in4 game scr
            Message mi = new Message(19);
            mi.writer().writeByte(3);
            mi.writer().writeShort(index); // index mob die
            mi.writer().writeShort(ItemTemplate3.item.get(map.item_map[index_item_map].id_item).getIcon());
            mi.writer().writeShort(index_item_map); //
            mi.writer().writeUTF(name);
            mi.writer().writeByte(color_); // color
            mi.writer().writeShort(-1); // id player
            MapService.send_msg_player_inside(map, p_master, mi, true);
            mi.cleanup();
        }
    }

    public static void leave_item_by_type4(Map map, short index_real, Player p_master, int index, int idp) throws IOException {
        int index_item_map = map.get_item_map_index_able();
        if (index_item_map > -1) {
            //
            map.item_map[index_item_map] = new ItemMap();
            map.item_map[index_item_map].id_item = index_real;
            map.item_map[index_item_map].color = 0;
            map.item_map[index_item_map].quantity = 1;
            map.item_map[index_item_map].category = 4;
            map.item_map[index_item_map].idmaster = (short) idp;
            map.item_map[index_item_map].time_exist = System.currentTimeMillis() + 60_000L;
            map.item_map[index_item_map].time_pick = System.currentTimeMillis() + 1_500L;
            // add in4 game scr
            Message mi = new Message(19);
            mi.writer().writeByte(4);
            mi.writer().writeShort(index); // id mob die
            mi.writer().writeShort(ItemTemplate4.item.get(map.item_map[index_item_map].id_item).getIcon());
            mi.writer().writeShort(index_item_map); //
            mi.writer().writeUTF(ItemTemplate4.item.get(map.item_map[index_item_map].id_item).getName());
            mi.writer().writeByte(0); // color
            mi.writer().writeShort(-1); // id player
            MapService.send_msg_player_inside(map, p_master, mi, true);
            mi.cleanup();
        }
    }

    public static void leave_item_by_type7(Map map, short id_it, Player p_master, int index, int idp) throws IOException {
        if (p_master != null && !p_master.isDropMaterialMedal && id_it >= 46 && id_it <= 345) {
            return;
        }
        int index_item_map = map.get_item_map_index_able();
        if (index_item_map > -1) {
            //
            map.item_map[index_item_map] = new ItemMap();
            map.item_map[index_item_map].id_item = id_it;
            if (ItemTemplate7.item.get(map.item_map[index_item_map].id_item).getColor() == 21) {
                map.item_map[index_item_map].color = 1;
            } else {
                map.item_map[index_item_map].color = 0;
            }
            map.item_map[index_item_map].quantity = 1;
            map.item_map[index_item_map].category = 7;
            map.item_map[index_item_map].idmaster = (short) idp;
            map.item_map[index_item_map].time_exist = System.currentTimeMillis() + 60_000L;
            map.item_map[index_item_map].time_pick = System.currentTimeMillis() + 1_500L;
            // add in4 game scr
            Message mi = new Message(19);
            mi.writer().writeByte(7);
            mi.writer().writeShort(index); // id mob die
            mi.writer().writeShort(ItemTemplate7.item.get(map.item_map[index_item_map].id_item).getIcon());
            mi.writer().writeShort(index_item_map); //
            mi.writer().writeUTF(ItemTemplate7.item.get(map.item_map[index_item_map].id_item).getName());
            mi.writer().writeByte(map.item_map[index_item_map].color); // color
            mi.writer().writeShort(p_master.index); // id player
            MapService.send_msg_player_inside(map, p_master, mi, true);
            mi.cleanup();
        }
    }
    public static void randomTT(Session conn, byte color, byte type) throws IOException {
        byte clazz = (byte) Util.random(4);
        short type_item = Helps.ItemStar.ConvertType(type, clazz);
        short id_item = Helps.ItemStar.GetIDItem(type, clazz);
        List<Option> ops = ItemStar.GetOpsItemStar(clazz, (byte) type_item, 0);

        Item3 itbag = new Item3();
        itbag.id = id_item;
        itbag.name = ItemTemplate3.item.get(id_item).getName();
        itbag.clazz = ItemTemplate3.item.get(id_item).getClazz();
        itbag.type = ItemTemplate3.item.get(id_item).getType();
        itbag.level = 45;
        itbag.icon = ItemTemplate3.item.get(id_item).getIcon();
        itbag.op = new ArrayList<>();
        for (Option o : ops) {
            int pr = o.getParam(0);
            int pr1 = (int) (pr * color * 0.25);
            if ((o.id >= 58 && o.id <= 60) || (o.id >= 100 && o.id <= 107)) {
                itbag.op.add(new Option(o.id, pr, itbag.id));
            } else if (o.id == 37 || o.id == 38) {
                itbag.op.add(new Option(o.id, 2, itbag.id));
            } else {
                itbag.op.add(new Option(o.id, pr1, itbag.id));
            }
        }
        int[] opAo = {-111, -110, -109, -108, -107};
        int[] opNon = {-102, -113, -105};
        int[] opVK = {-101, -113, -86, -84, -82, -80};
        int[] opNhan = {-89, -87, -104, -86, -84, -82, -80};
        int[] opDayChuyen = {-87, -105, -103, -91};
        int[] opGang = {-89, -103, -91};
        int[] opGiay = {-104, -103, -91};

        if (color == 4) {
            if (itbag.type == 0 || itbag.type == 1) {
                int percent = Util.nextInt(0, 100);
                if (percent > 85) {
                    int opid1 = opAo[Util.nextInt(opAo.length)];
                    int opid2 = opAo[Util.nextInt(opAo.length)];
                    while (opid1 == opid2) {
                        opid1 = opAo[Util.nextInt(opAo.length)];
                    }
                    itbag.op.add(Option.createOpItemStar(opid1, itbag.id));
                    itbag.op.add(Option.createOpItemStar(opid2, itbag.id));
                } else {
                    int opid = opGiay[Util.nextInt(opGiay.length)];
                    itbag.op.add(Option.createOpItemStar(opid, itbag.id));
                }
            } else if (itbag.type == 2) {
                int percent = Util.nextInt(0, 100);
                if (percent > 85) {
                    int opid1 = opNon[Util.nextInt(opNon.length)];
                    int opid2 = opNon[Util.nextInt(opNon.length)];
                    while (opid1 == opid2) {
                        opid1 = opNon[Util.nextInt(opNon.length)];
                    }
                    itbag.op.add(Option.createOpItemStar(opid1, itbag.id));
                    itbag.op.add(Option.createOpItemStar(opid2, itbag.id));
                } else {
                    int opid = opGiay[Util.nextInt(opGiay.length)];
                    itbag.op.add(Option.createOpItemStar(opid, itbag.id));
                }
            } else if (itbag.type == 3) {
                int percent = Util.nextInt(0, 100);
                if (percent > 85) {
                    int opid1 = opGang[Util.nextInt(opGang.length)];
                    int opid2 = opGang[Util.nextInt(opGang.length)];
                    while (opid1 == opid2) {
                        opid1 = opGang[Util.nextInt(opGang.length)];
                    }
                    itbag.op.add(Option.createOpItemStar(opid1, itbag.id));
                    itbag.op.add(Option.createOpItemStar(opid2, itbag.id));
                } else {
                    int opid = opGiay[Util.nextInt(opGiay.length)];
                    itbag.op.add(Option.createOpItemStar(opid, itbag.id));
                }
            } else if (itbag.type == 4) {
                int percent = Util.nextInt(0, 100);
                if (percent > 85) {
                    int opid1 = opNhan[Util.nextInt(opNhan.length)];
                    int opid2 = opNhan[Util.nextInt(opNhan.length)];
                    while (opid1 == opid2) {
                        opid1 = opNhan[Util.nextInt(opNhan.length)];
                    }
                    itbag.op.add(Option.createOpItemStar(opid1, itbag.id));
                    itbag.op.add(Option.createOpItemStar(opid2, itbag.id));
                } else {
                    int opid = opGiay[Util.nextInt(opGiay.length)];
                    itbag.op.add(Option.createOpItemStar(opid, itbag.id));
                }
            } else if (itbag.type == 5) {
                int percent = Util.nextInt(0, 100);
                if (percent > 85) {
                    int opid1 = opDayChuyen[Util.nextInt(opDayChuyen.length)];
                    int opid2 = opDayChuyen[Util.nextInt(opDayChuyen.length)];
                    while (opid1 == opid2) {
                        opid1 = opDayChuyen[Util.nextInt(opDayChuyen.length)];
                    }
                    itbag.op.add(Option.createOpItemStar(opid1, itbag.id));
                    itbag.op.add(Option.createOpItemStar(opid2, itbag.id));
                } else {
                    int opid = opGiay[Util.nextInt(opGiay.length)];
                    itbag.op.add(Option.createOpItemStar(opid, itbag.id));
                }
            } else if (itbag.type == 6) {
                int percent = Util.nextInt(0, 100);
                if (percent > 85) {
                    int opid1 = opGiay[Util.nextInt(opGiay.length)];
                    int opid2 = opGiay[Util.nextInt(opGiay.length)];
                    while (opid1 == opid2) {
                        opid1 = opGiay[Util.nextInt(opGiay.length)];
                    }
                    itbag.op.add(Option.createOpItemStar(opid1, itbag.id));
                    itbag.op.add(Option.createOpItemStar(opid2, itbag.id));
                } else {
                    int opid = opGiay[Util.nextInt(opGiay.length)];
                    itbag.op.add(Option.createOpItemStar(opid, itbag.id));
                }
            } else if (itbag.type > 6) {
                int percent = Util.nextInt(0, 100);
                if (percent > 85) {
                    int opid1 = opVK[Util.nextInt(opVK.length)];
                    int opid2 = opVK[Util.nextInt(opVK.length)];
                    while (opid1 == opid2) {
                        opid1 = opVK[Util.nextInt(opVK.length)];
                    }
                    itbag.op.add(Option.createOpItemStar(opid1, itbag.id));
                    itbag.op.add(Option.createOpItemStar(opid2, itbag.id));
                } else {
                    int opid = opGiay[Util.nextInt(opGiay.length)];
                    itbag.op.add(Option.createOpItemStar(opid, itbag.id));
                }
            }
        } else if (color == 5) {
            if (itbag.type == 0 || itbag.type == 1) {
                int percent = Util.nextInt(0, 100);
                if (percent > 85) {
                    int opid1 = opAo[Util.nextInt(opAo.length)];
                    int opid2 = opAo[Util.nextInt(opAo.length)];
                    int opid3 = opAo[Util.nextInt(opAo.length)];
                    while ((opid1 == opid2) || (opid1 == opid3)) {
                        opid1 = opAo[Util.nextInt(opAo.length)];
                    }
                    while ((opid2 == opid1) || (opid2 == opid3)) {
                        opid2 = opAo[Util.nextInt(opAo.length)];
                    }
                    while ((opid3 == opid2) || (opid1 == opid3)) {
                        opid3 = opAo[Util.nextInt(opAo.length)];
                    }
                    if (percent > 95) {
                        itbag.op.add(Option.createOpItemStar(opid3, itbag.id));
                    }
                    itbag.op.add(Option.createOpItemStar(opid1, itbag.id));
                    itbag.op.add(Option.createOpItemStar(opid2, itbag.id));
                } else {
                    int opid = opGiay[Util.nextInt(opGiay.length)];
                    itbag.op.add(Option.createOpItemStar(opid, itbag.id));
                }
            } else if (itbag.type == 2) {
                int percent = Util.nextInt(0, 100);
                if (percent > 85) {
                    int opid1 = opNon[Util.nextInt(opNon.length)];
                    int opid2 = opNon[Util.nextInt(opNon.length)];
                    int opid3 = opNon[Util.nextInt(opNon.length)];
                    while ((opid1 == opid2) || (opid1 == opid3)) {
                        opid1 = opNon[Util.nextInt(opNon.length)];
                    }
                    while ((opid2 == opid1) || (opid2 == opid3)) {
                        opid2 = opNon[Util.nextInt(opNon.length)];
                    }
                    while ((opid3 == opid2) || (opid1 == opid3)) {
                        opid3 = opNon[Util.nextInt(opNon.length)];
                    }
                    if (percent > 95) {
                        itbag.op.add(Option.createOpItemStar(opid3, itbag.id));
                    }
                    itbag.op.add(Option.createOpItemStar(opid1, itbag.id));
                    itbag.op.add(Option.createOpItemStar(opid2, itbag.id));
                } else {
                    int opid = opGiay[Util.nextInt(opGiay.length)];
                    itbag.op.add(Option.createOpItemStar(opid, itbag.id));
                }
            } else if (itbag.type == 3) {
                int percent = Util.nextInt(0, 100);
                if (percent > 85) {
                    int opid1 = opGang[Util.nextInt(opGang.length)];
                    int opid2 = opGang[Util.nextInt(opGang.length)];
                    int opid3 = opGang[Util.nextInt(opGang.length)];
                    while ((opid1 == opid2) || (opid1 == opid3)) {
                        opid1 = opGang[Util.nextInt(opGang.length)];
                    }
                    while ((opid2 == opid1) || (opid2 == opid3)) {
                        opid2 = opGang[Util.nextInt(opGang.length)];
                    }
                    while ((opid3 == opid2) || (opid1 == opid3)) {
                        opid3 = opGang[Util.nextInt(opGang.length)];
                    }
                    if (percent > 95) {
                        itbag.op.add(Option.createOpItemStar(opid3, itbag.id));
                    }
                    itbag.op.add(Option.createOpItemStar(opid1, itbag.id));
                    itbag.op.add(Option.createOpItemStar(opid2, itbag.id));
                } else {
                    int opid = opGiay[Util.nextInt(opGiay.length)];
                    itbag.op.add(Option.createOpItemStar(opid, itbag.id));
                }
            } else if (itbag.type == 4) {
                int percent = Util.nextInt(0, 100);
                if (percent > 85) {
                    int opid1 = opNhan[Util.nextInt(opNhan.length)];
                    int opid2 = opNhan[Util.nextInt(opNhan.length)];
                    int opid3 = opNhan[Util.nextInt(opNhan.length)];
                    while ((opid1 == opid2) || (opid1 == opid3)) {
                        opid1 = opNhan[Util.nextInt(opNhan.length)];
                    }
                    while ((opid2 == opid1) || (opid2 == opid3)) {
                        opid2 = opNhan[Util.nextInt(opNhan.length)];
                    }
                    while ((opid3 == opid2) || (opid1 == opid3)) {
                        opid3 = opNhan[Util.nextInt(opNhan.length)];
                    }
                    if (percent > 95) {
                        itbag.op.add(Option.createOpItemStar(opid3, itbag.id));
                    }
                    itbag.op.add(Option.createOpItemStar(opid1, itbag.id));
                    itbag.op.add(Option.createOpItemStar(opid2, itbag.id));
                } else {
                    int opid = opGiay[Util.nextInt(opGiay.length)];
                    itbag.op.add(Option.createOpItemStar(opid, itbag.id));
                }
            } else if (itbag.type == 5) {
                int percent = Util.nextInt(0, 100);
                if (percent > 85) {
                    int opid1 = opDayChuyen[Util.nextInt(opDayChuyen.length)];
                    int opid2 = opDayChuyen[Util.nextInt(opDayChuyen.length)];
                    int opid3 = opDayChuyen[Util.nextInt(opDayChuyen.length)];
                    while ((opid1 == opid2) || (opid1 == opid3)) {
                        opid1 = opDayChuyen[Util.nextInt(opDayChuyen.length)];
                    }
                    while ((opid2 == opid1) || (opid2 == opid3)) {
                        opid2 = opDayChuyen[Util.nextInt(opDayChuyen.length)];
                    }
                    while ((opid3 == opid2) || (opid1 == opid3)) {
                        opid3 = opDayChuyen[Util.nextInt(opDayChuyen.length)];
                    }
                    if (percent > 95) {
                        itbag.op.add(Option.createOpItemStar(opid3, itbag.id));
                    }
                    itbag.op.add(Option.createOpItemStar(opid1, itbag.id));
                    itbag.op.add(Option.createOpItemStar(opid2, itbag.id));
                } else {
                    int opid = opGiay[Util.nextInt(opGiay.length)];
                    itbag.op.add(Option.createOpItemStar(opid, itbag.id));
                }
            } else if (itbag.type == 6) {
                int percent = Util.nextInt(0, 100);
                if (percent > 85) {
                    int opid1 = opGiay[Util.nextInt(opGiay.length)];
                    int opid2 = opGiay[Util.nextInt(opGiay.length)];
                    int opid3 = opGiay[Util.nextInt(opGiay.length)];
                    while ((opid1 == opid2) || (opid1 == opid3)) {
                        opid1 = opGiay[Util.nextInt(opGiay.length)];
                    }
                    while ((opid2 == opid1) || (opid2 == opid3)) {
                        opid2 = opGiay[Util.nextInt(opGiay.length)];
                    }
                    while ((opid3 == opid2) || (opid1 == opid3)) {
                        opid3 = opGiay[Util.nextInt(opGiay.length)];
                    }
                    if (percent > 95) {
                        itbag.op.add(Option.createOpItemStar(opid3, itbag.id));
                    }
                    itbag.op.add(Option.createOpItemStar(opid1, itbag.id));
                    itbag.op.add(Option.createOpItemStar(opid2, itbag.id));
                } else {
                    int opid = opGiay[Util.nextInt(opGiay.length)];
                    itbag.op.add(Option.createOpItemStar(opid, itbag.id));
                }
            } else if (itbag.type > 7) {
                int percent = Util.nextInt(0, 100);
                if (percent > 90) {
                    int opid1 = opVK[Util.nextInt(opVK.length)];
                    int opid2 = opVK[Util.nextInt(opVK.length)];
                    int opid3 = opVK[Util.nextInt(opVK.length)];
                    while ((opid1 == opid2) || (opid1 == opid3)) {
                        opid1 = opVK[Util.nextInt(opVK.length)];
                    }
                    while ((opid2 == opid1) || (opid2 == opid3)) {
                        opid2 = opVK[Util.nextInt(opVK.length)];
                    }
                    while ((opid3 == opid2) || (opid1 == opid3)) {
                        opid3 = opVK[Util.nextInt(opVK.length)];
                    }
                    if (percent > 95) {
                        itbag.op.add(Option.createOpItemStar(opid3, itbag.id));
                    }
                    itbag.op.add(Option.createOpItemStar(opid1, itbag.id));
                    itbag.op.add(Option.createOpItemStar(opid2, itbag.id));
                } else {
                    int opid = opGiay[Util.nextInt(opGiay.length)];
                    itbag.op.add(Option.createOpItemStar(opid, itbag.id));
                }
            }
        }
        if (color >= 0) {
            itbag.op.add(Option.createOpItemStar(129, itbag.id));
            itbag.op.add(Option.createOpItemStar(130, itbag.id));
        }
        itbag.color = color;
        itbag.part = ItemTemplate3.item.get(id_item).getPart();
        itbag.tier = 0;
        itbag.time_use = 0;
        itbag.islock = false;
        conn.p.item.add_item_bag3(itbag);
        conn.p.item.char_inventory(3);
    }
}
