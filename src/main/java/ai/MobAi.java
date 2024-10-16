/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ai;

import client.Player;
import io.Message;

import java.io.IOException;
import java.util.List;
import map.Map;
import map.Mob_in_map;
import template.MainObject;
import template.Mob;
import template.Part_player;

/**
 *
 * @author chien
 */
public class MobAi extends Mob_in_map {
    public long timeATK;
    public List<Part_player> part_p;//
    private byte head;//
    private byte eye;//
    private byte hair;//
    private int pointpk;//
    private short clan_icon = -1;
    private int clan_id = -1;
    private String clan_name_clan_shorted;
    private byte clan_mem_type;
    private byte[] fashion = new byte[]{-1,-1,-1,-1,-1,-1,-1};//
    private short mat_na=-1;//
    private short phi_phong=-1;//
    private short weapon=-1;//
    private short id_horse=-1;//
    private short id_hair=-1;//
    private short id_wing=-1;//
    private short id_img_mob=-1;//
    private byte type_use_mount=-1;//
    public long act_time;
    public boolean is_move=true;//
    public Player p_target;
    public int p_skill_id;//
    public int crit;
    public long time_hp_buff;
    public int pierce;


    public void send_in4(Player p)throws IOException{
        Message m = new Message(5);
        m.writer().writeShort(this.index);
        m.writer().writeUTF(this.name);
        m.writer().writeShort(this.x);
        m.writer().writeShort(this.y);
        m.writer().writeByte(this.clazz);
        m.writer().writeByte(-1);
        m.writer().writeByte(this.head);
        m.writer().writeByte(this.eye);
        m.writer().writeByte(this.hair);
        m.writer().writeShort(this.level);
        m.writer().writeInt(this.hp);
        m.writer().writeInt(this.hp_max);
        m.writer().writeByte(this.typepk); // type pk
        m.writer().writeShort(this.pointpk);
        m.writer().writeByte(this.part_p.size());
        //
        for (int i = 0; i < this.part_p.size(); i++) {
            m.writer().writeByte(this.part_p.get(i).type);
            m.writer().writeByte(this.part_p.get(i).part);
            m.writer().writeByte(3);
            m.writer().writeShort(-1);
            m.writer().writeShort(-1);
            m.writer().writeShort(-1);
            m.writer().writeShort(-1); // eff
        }
        //
        m.writer().writeShort(this.clan_icon);
        if(clan_icon>-1)
        {
            m.writer().writeInt(this.clan_id);
            m.writer().writeUTF(this.clan_name_clan_shorted);
            m.writer().writeByte(this.clan_mem_type);
        }
        m.writer().writeByte(-1); // pet
        m.writer().writeByte(this.fashion.length);
        for (int i = 0; i < this.fashion.length; i++) {
            if (p.conn.version >= 280) {
                m.writer().writeShort(this.fashion[i]);
            } else {
                m.writer().writeByte(this.fashion[i]);
            }
        }
        //
        m.writer().writeShort(id_img_mob);//id_img_mob
        m.writer().writeByte(this.type_use_mount);
        m.writer().writeBoolean(false);
        m.writer().writeByte(1);
        m.writer().writeByte(0);
        m.writer().writeShort(this.mat_na); // mat na
        m.writer().writeByte(1); // paint mat na trc sau
        m.writer().writeShort(this.phi_phong); // phi phong
        m.writer().writeShort(this.weapon); // weapon
        m.writer().writeShort(this.id_horse);
        m.writer().writeShort(this.id_hair); // hair
        m.writer().writeShort(this.id_wing); // wing
        m.writer().writeShort(-1); // body
        m.writer().writeShort(-1); // leg
        m.writer().writeShort(-1); // bienhinh
        p.conn.addmsg(m);
        m.cleanup();
    }

    @Override
    public int get_TypeObj(){
        return 0;
    }

    @Override
    public void SetDie(Map map, MainObject mainAtk){
        if (isDie) return;
        try{
            if(this.hp >0)return;
            this.hp =0;
            this.isDie = true;
            this.time_back = System.currentTimeMillis() + 120_000;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
