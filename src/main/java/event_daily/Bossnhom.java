package event_daily;

import BossHDL.BossManager;
import client.Player;
import core.Manager;
import core.Service;
import io.Message;
import io.Session;
import map.Map;
import map.Vgo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Bossnhom {
    public static final ConcurrentHashMap<String, List<String>> Party_entrys = new ConcurrentHashMap<>();
    private static final List<String>[] subMaps = new ArrayList[4];
    public Session conn;
    public static long timeAttack;
    public static long timeend;
    public static int bosschinh;
    public static int bossphu;
    public static int vaomap;
    public static int outmap;
    public static int thuacuoc;
    public static long lastTime ;
    public static void PartyRegister(Player p){
        try {
            if (p.party == null || p.party.get_mems().size() < 5) {
                Service.send_notice_box(p.conn, "Cần tạo nhóm 5 thành viên trong nhóm có level không quá 100 level so với chủ nhóm.");
            }else if (!p.party.get_mems().get(0).name.equals(p.name)) {
                Service.send_notice_box(p.conn, "Bạn không phải trưởng nhóm.");
            } else if (Party_entrys.containsKey(p.party)) {
                Service.send_notice_box(p.conn, "Nhóm của bạn đã có tên trong danh sách.");
            } else {
                List<String> nameP = new ArrayList<>();
                for (int i = 0; i < p.party.get_mems().size(); i++) {
                    Player p2 = p.party.get_mems().get(i);
                    if (p2 == null || p2.conn == null || !p2.conn.connected) {
                        Service.send_notice_box(p.conn, "Có lỗi xảy ra hãy tạo lại nhóm và thử lại.");
                        return;
                    }
                    if(p2.party == null || !(Math.abs(p.party.get_mems().get(0).level - p2.level) <= 100)){
                        Service.send_notice_box(p.conn, "Cần tạo nhóm 5 thành viên trong nhóm có level không quá 100 level so với chủ nhóm.");
                        return;
                    }
                    nameP.add(p2.name);
                }
                Party_entrys.put(p.party.toString(), nameP);
                String chat = "Trưởng nhóm đã gọi boss thành công cùng các người chơi sau đây: ";
                String text = "Sẽ vào map sau 10s nữa";
                for (int i = 0; i < nameP.size(); i++) {
                    chat += "\n" + nameP.get(i);
                }
                Service.chat_nhom(p.party, chat);
                Service.send_notice_box(p.conn, chat);
                vaomap = 1;
                resetmap();
                timeAttack = System.currentTimeMillis() + 1000 * 60* 10;
                BossManager.callBossToMapdangcap(46,0,191,714,456,1_000_000_000,p.level *10,p.level * 2,p.level * 2,p.level* 2,p.level * 2,10);
                BossManager.callBossToMapdangcap(46,0,191,684,270,1_000_000_000,p.level *10,p.level * 2,p.level * 2,p.level* 2,p.level * 2,10);
                BossManager.callBossToMapdangcap(46,0,191,348,282,1_000_000_000,p.level *10,p.level * 2,p.level * 2,p.level* 2,p.level * 2,10);
                BossManager.callBossToMapdangcap(46,0,191,318,444,1_000_000_000,p.level *10,p.level * 2,p.level * 2,p.level* 2,p.level * 2,10);
                BossManager.callBossToMapdangcap(46,0,191,486,402,1_000_000_000,p.level *10,p.level * 2,p.level * 2,p.level* 2,p.level * 2,10);
                Service.chat_nhom(p.party,text);
                Service.send_notice_nobox_white(p.conn,text);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void update(){
//        if (timeAttack == 0)
//            return;
        long time = System.currentTimeMillis();
        if (lastTime == 0) {
            lastTime = time;
        }
        // vào map  sau 30s
        if (vaomap >= 1 && vaomap <= 10) {
            if (vaomap == 10) {
                vaomap = -2;
            } else {
                vaomap++;
                //System.out.println("time vào"+vaomap);
            }
        } else if (vaomap == -2) {
            Vaomap();
            vaomap = 11;
        }
        //
        // out map sau 30s
        if (bosschinh == 1){
            outmap = 1;
            bosschinh = 0;
        }
        if (outmap >= 1 && outmap <= 10) {
            if (outmap == 10) {
                outmap = -2;
            } else {
                outmap++;
                //System.out.println("time out"+outmap);
            }
        } else if (outmap == -2) {
            endboss();
        }
        if (timeAttack < time && vaomap == 11){
            thuacuoc = 1;
            timeend = System.currentTimeMillis() + 1000 * 60* 10;
            if (time - lastTime >= 60_000){
                BossManager.callBossToMapdangcap(46,0,194,450,384,2_000_000_000,10_000_000,7000,7000,7000,7000,10);
                lastTime = time;
            }
        }
        if (timeend < time && thuacuoc == 1){
            endboss();
        }

    }
    private static void resetmap(){
        timeAttack = 0;
        thuacuoc = 0;
        bossphu = 0;
        timeend = 0;
        outmap = 0;
    }
    public static void endboss(){
        for (Map[] map : Map.entrys) {
            if (map == null || map.length ==0 || !map[0].ismapbossnhom()) continue;
            for(Map m : map){
                for(int i= 0;i < m.players.size(); i++){
                    Player p = m.players.get(i);
                    if(p == null)continue;
                    try{
                        Vgo v = new Vgo();
                        v.id_map_go = 1;
                        v.x_new = 350;
                        v.y_new = 350;
                        p.change_map(p, v);
                    }catch(Exception e){
                        try{
                            p.conn.close();
                        }catch(Exception ee){}
                    }
                }
            }
        }
    }
    public static void Vaomap() {
        try {
            SetupMap();
        } catch (Exception e) {
        }
    }
    public static void SetupMap() {
        try {
            synchronized (subMaps) {
                int k = 0;
                for (java.util.Map.Entry<String, List<String>> entry : Party_entrys.entrySet()) {
                    if (subMaps[k] == null) {
                        subMaps[k] = new ArrayList<>();
                    }
                    String key = entry.getKey();
                    subMaps[k].add(key);
                    k += (k + 1 ) % 4;
                }
                for (int i = Session.client_entrys.size() - 1; i >= 0; i--) {
                    Session s = Session.client_entrys.get(i);
                    if (s.connected && s.get_in4 && s.p != null && s.p.map != null) {
                        joinMap(s.p);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
    public static boolean joinMap(Player p){
        try {
            Vgo v = new Vgo();
            v.id_map_go = 46;
            v.x_new = 300;
            v.y_new = 300;
            p.change_map(p, v);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    public static void SenDataTime(Session conn) throws IOException {
        long _time = System.currentTimeMillis();
        Message m = new Message(-104);
        m.writer().writeByte(0);
        m.writer().writeInt(-1);
        m.writer().writeUTF("");
        conn.addmsg(m);
        m.cleanup();
        m = new Message(-104);
        m.writer().writeByte(1);
        m.writer().writeByte(1);
        if (timeAttack > 0) {
            m.writer().writeShort((int) ((timeAttack - _time) / 1000));
        }else {
            m.writer().writeShort((int) ((timeend - _time) / 1000));
        }
        m.writer().writeUTF("Time còn lại");
        conn.addmsg(m);
        m.cleanup();
    }
}
