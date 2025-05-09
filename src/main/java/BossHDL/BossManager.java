package BossHDL;

import client.Player;
import core.Manager;
import core.Util;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import map.LeaveItemMap;
import map.Mob_in_map;
import template.Mob;
import map.Map;
import template.Mob_Leothap;

/**
 *
 * @author chien
 */
public class BossManager {
    public static final CopyOnWriteArrayList<Mob_in_map> entrys = new CopyOnWriteArrayList<>();
    private static long time_hoi;
    
    private static byte GetIdMap(int idboss){
        switch (idboss) {
            //case = mod - Return = map
//            case 103: return  7;
//            case 104: return 15;
//            case 101: return 25;
//            case 84: return 37;
//            case 105: return 45;
//
            case 83: return  51;
            case 106: return 62;
            case 149: return 76;
            case 155: return 79;
            case 101: return 26;
            case 173: return 113;
            case 195: return 112;
            case 196: return 115;
            case 197: return 114;
            case 186: return 109;
//            case 187: return 110;
//            case 188: return 111;
//            case 193: return 11;
//            case 178: return 36;
            default:
                throw new AssertionError();
        }
    }
    private static short[] GetSite(int idboss){
        switch (idboss) {
            //mod + vị trí
//            case 103: return  new short[]{432,512};
//            case 104: return new short[]{ 530,213};
//            case 101: return new short[]{ 204,284};
//            case 84: return new short[]{ 160,224};
//            case 105: return new short[]{ 816,1064};
//
            case 83: return  new short[]{ 320,1520};
            case 106: return new short[]{ 468,498};
            case 149: return new short[]{ 204,762};
            case 155: return new short[]{ 534,732};
            case 101: return new short[]{ 550,250};
            case 173: return new short[]{ 450,432};
            case 195: return new short[]{ 450,432};
            case 196: return new short[]{ 450,432};
            case 197: return new short[]{ 450,432};
            case 186: return new short[]{ 450,432};
//            case 187: return new short[]{ 450,432};
//            case 188: return new short[]{ 450,432};
//            case 178: return new short[]{ 450,384};
//            case 193: return new short[]{ 280,440};
            default:
                throw new AssertionError();
        }
    }
    public static void init(){
        int idx = 10_000;
        int[] ids = new int[]{83,106,149,155,101,173,195,196,197,186};
        for(int id : ids){
            for(int i=0; i<5;i++){
//                if(id == 174){
//                    if(i == 1 || i == 4 || Manager.gI().event != 2) continue;
//                }
                if(id == 178){
                    if(i == 1 || i == 4) continue;
                }
                if(id == 193){
                    if(Manager.gI().event != 11) continue;
                }
                Mob m = Mob.entrys.get(id);
                Mob_in_map temp = new Mob_in_map();
                temp.template = m;
                temp.x = GetSite(id)[0];
                temp.y = GetSite(id)[1];
                temp.level = id == 178? 150: m.level;
                temp.Set_isBoss(true);
                temp.hp = temp.Set_hpMax(id == 178? (600_000_000 + (i*200_000_000)) : m.hpmax );
//                if(id == 83)
//                    temp.timeBossRecive = 1000 * 60 * 60 * Util.random(20, 25);
//                else if (id == 106)
//                    temp.timeBossRecive = 1000 * 60 * 60 * Util.random(20, 25);
//                else if (id == 149)
//                    temp.timeBossRecive = 1000 * 60 * 60 * Util.random(20, 25);
//                else if (id == 155)
//                    temp.timeBossRecive = 1000 * 60 * 60 * Util.random(20, 25);
//                else if (id == 101)
//                    temp.timeBossRecive = 1000 * 60 * 60 * Util.random(20, 25);
//                else if (id == 173)
//                    temp.timeBossRecive = 1000 * 60 * 60 * Util.random(16,19);
//                else if (id == 195)
//                    temp.timeBossRecive = 1000 * 60 * 60 * Util.random(13,16);
//                else if (id == 196)
//                    temp.timeBossRecive = 1000 * 60 * 60 * Util.random(10,13);
//                else if (id == 197)
//                    temp.timeBossRecive = 1000 * 60 * 60 * Util.random(7,10);
//                else if (id == 186)
//                    temp.timeBossRecive = 1000 * 60 * 60 * Util.random(4,7);
//                else
                    temp.timeBossRecive = 1000 * 60 * 60 * 2;
                temp.map_id = GetIdMap(id);
                temp.zone_id = (byte)i;
                temp.index = idx++;
                entrys.add(temp);
                //map[i].Boss_entrys.add(temp);
            }
        }
    }
    
    public static String GetInfoBoss(int idMob){
        String s = null;
        //long currentTimeMillis = System.currentTimeMillis();
        
        long time = System.currentTimeMillis();
        for(Mob_in_map mob: entrys){
            if(mob.template.mob_id == idMob){
                if(s == null)
                {
                    Map[] m = Map.get_map_by_id(mob.map_id);
                    if(m == null)continue;
                    s = "Map: "+m[0].name;
                }
                s+="\nkhu: "+ (mob.zone_id+1) + (mob.time_back > time ? (" Hồi sinh vào lúc: "+Helps._Time.ConvertTime(mob.time_back) +""):" Còn sống");
            }
        }
        return s;
    }
    
    public static void Update(){
        try{
            long time = System.currentTimeMillis();
            if (time_hoi == 0){
                time_hoi = time;
            }
            for(Mob_in_map mob : entrys){
                if((!mob.is_boss_active || mob.isDie) && time > mob.time_back){
                    Map[] map = Map.get_map_by_id(mob.map_id);
                    if(map == null || map.length <= mob.zone_id)continue;
                    mob.isDie = false;
                    mob.is_boss_active =true;
                    mob.hp = mob.get_HpMax();
                    if(map[mob.zone_id].Boss_entrys.contains(mob))
                        map[mob.zone_id].Boss_entrys.remove(mob);
                    map[mob.zone_id].Boss_entrys.add(mob);
                    //Manager.gI().chatKTGprocess(""+mob.template.name+" đã xuất hiện tại "+map[mob.zone_id].name);
                    //System.out.println("Boss "+mob.template.name+" Acctive "+map[mob.zone_id].name+ " khu "+(mob.zone_id+1));
                }
            }
            if (time - time_hoi >= 10000){
                for (Mob_in_map mob : entrys) {
                    mob.hp += 2_000_000;
                    if (mob.hp >= mob.get_HpMax()) {
                        mob.hp = mob.get_HpMax();
                    }
                }
                time_hoi = time;
            }
        }catch(Exception e){e.printStackTrace();}
    }
    
    
    
    
    public static void DropItemBossEvent(Map map, Mob_in_map mob, Player p)throws IOException{
        if(Manager.gI().event == 2){
            int[] it4 = new int[]{48,49,50,51,52,5,26,131,132,24,10};
            int[] it7 = new int[]{346,349,33,34,12,13};
            
            for(int i=0; i< 15; i++){
                LeaveItemMap.leave_vang(map,mob, -1);
            }
            for(int i=0; i<40; i++){
                int ran = Util.random(100);
                if(ran < 10)
                    LeaveItemMap.leave_item_by_type7(map, (short)Util.random(it7),p, mob.index, p.index);
                if(ran < 40)
                    LeaveItemMap.leave_item_by_type4(map, (short)Util.random(it4),p, mob.index, -1);
                else
                    LeaveItemMap.leave_item_by_type7(map, (short)Util.random(417,464),p, mob.index, -1);
            }
        }
    }
    public static void callBossToMap(int map_id, int boss_id, int x, int y, int hp, int level) {
        Mob m = Mob.entrys.get(boss_id);
        Mob_in_map temp = new Mob_in_map();
        temp.template = m;
        temp.x = (short) x;
        temp.y = (short) y;
        temp.level = (short) level;
        temp.color_name = 5;

        temp.Set_isBoss(true);
        temp.hp = temp.Set_hpMax(hp);
        temp.map_id = (short) map_id;
        temp.zone_id = 0;
        temp.index = 10_000 + entrys.size();
        entrys.add(temp);
    }
    public static void callBossToMapdangcap(int map_id,int zone_id, int boss_id, int x, int y, int hp,int dame, int crit, int phan, int ne, int xg, int level) {
        try {
            Mob m = Mob.entrys.get(boss_id);
            Mob_in_map temp = new Mob_in_map();
            temp.template = m;
            temp.x = (short) x;
            temp.y = (short) y;
            temp.level = (short) level;
            temp.color_name = 5;

            temp.Set_isBoss(true);
            temp.hp = temp.Set_hpMax(hp);
            temp.dameboss = dame;
            temp.ne = ne;
            temp.phan = phan;
            temp.crit = crit;
            temp.xg = xg;
            temp.map_id = (short) map_id;
            temp.zone_id = (byte) zone_id;
            temp.index = 10_000 + entrys.size();
            entrys.add(temp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
}
