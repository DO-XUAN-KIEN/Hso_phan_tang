package core;

import client.Player;
import io.Message;
import template.ItemTemplate3;
import template.Part_fashion;

import java.io.IOException;

//import static core.GameSrc.check_item_can_rebuild;

public class hopdovip {
    public static void hop_do(Player p, Message m2) throws IOException { // Hợp đồ
        byte type = m2.reader().readByte();
        short id = m2.reader().readShort();
        if (type == 0) {
            if (p.item.bag3[id] != null && Part_fashion.fashions.contains(p.item.bag3[id].id)) {
                Service.send_notice_box(p.conn, "Trang bị không phù hợp!");
                return;
            }
//            if (p.item.bag3[id].color != 4){
//                Service.send_notice_box(p.conn, "Chỉ có thể hợp trang bị cam");
//                return;
//            }
            if (p.item.bag3[id].type == 7) {
                Service.send_notice_box(p.conn, "Không hợp được cánh");
                return;
            }
            if (p.item.bag3[id].type == 16) {
                Service.send_notice_box(p.conn, "Không hợp mề đay");
                return;
            }
            if (!(p.item.bag3[id].type >= 0 && p.item.bag3[id].type <= 11)){
                Service.send_notice_box(p.conn,"Chỉ có thể hợp trang bị 1");
                return;
            }
            if (p.item_replace == -1) {
                if (p.item.bag3[id] != null && Helps.CheckItem.isMeDay(p.item.bag3[id].id)) {
                    Service.send_notice_box(p.conn, "Trang bị không phù hợp!");
                    return;
                }
                if (!(p.item.bag3[id].type >= 0 && p.item.bag3[id].type <= 11)){
                    Service.send_notice_box(p.conn,"Chỉ có thể hợp trang bị 1");
                    return;
                }
                if (p.item.bag3[id].color < 4){
                    Service.send_notice_box(p.conn, "Chỉ có thể hợp trang bị cam trở lên");
                    return;
                }
//                if (p.item.bag3[id].type == 7) {
//                    Service.send_notice_box(p.conn, "Không hợp được cánh");
//                    return;
//                }
//                if (p.item.bag3[id].type == 16) {
//                    Service.send_notice_box(p.conn, "Không hợp mề đay");
//                    return;
//                }
                int cap = p.item.bag3[id].tierhop * 10;
                if (cap > p.item.bag3[id].tier){
                    Service.send_notice_box(p.conn,"Bạn chưa đủ cấp cường hóa để hợp, cấp yêu cầu cấp: "+cap);
                    return;
                }
                p.item_replace = id;
            } else if (p.item_replace2 == -1) {
                if (p.item.bag3[p.item_replace].id != p.item.bag3[id].id){
                    Service.send_notice_box(p.conn,"Chỉ có thể bỏ vào 2 vật phẩm giống nhau");
                    return;
                }
                if (p.item.bag3[p.item_replace].tier != p.item.bag3[id].tier){
                    Service.send_notice_box(p.conn,"Chỉ có thể bỏ vào 2 vật phẩm cường hóa giống nhau");
                    return;
                }
                if (p.item.bag3[p.item_replace].tierhop != p.item.bag3[id].tierhop){
                    Service.send_notice_box(p.conn,"Chỉ có thể bỏ vào 2 vật phẩm đẳng cấp giống nhau");
                    return;
                }
                if (p.item.bag3[p.item_replace].color != p.item.bag3[id].color){
                    Service.send_notice_box(p.conn, "Chỉ có thể hợp trang bị cam trở lên");
                    return;
                }
                p.item_replace2 = id;
            } else {
                Service.send_notice_box(p.conn, "Lỗi, hãy thử lại");
                return;
            }
            Message m = new Message(73);
            m.writer().writeByte(0);
            m.writer().writeShort(id);
            if (p.item_replace2 == -1) {
                m.writer().writeByte(1);
            } else {
                m.writer().writeByte(0);
            }
            p.conn.addmsg(m);
            m.cleanup();
        } else if (type == 1) {
            if (p.item.bag3[p.item_replace].tier < p.item.bag3[p.item_replace2].tier) {
                Service.send_notice_box(p.conn, "Đã hợp đồ xong rồi!!");
                return;
            }
            if (!p.item.bag3[p.item_replace2].islock) {
                p.item.bag3[p.item_replace2].islock = true;
                p.item.bag3[p.item_replace2].name = ItemTemplate3.item.get(p.item.bag3[p.item_replace2].id).getName() + " [Khóa]";
                p.item.bag3[p.item_replace2].UpdateName();
                p.item.char_inventory(4);
                p.item.char_inventory(7);
                p.item.char_inventory(3);
            }
            Service.send_box_input_yesno(p.conn, -100, "Bạn có chắc chắn muốn hợp đồ với 1 đá hợp đồ trang bị 1");
        }
    }
    public static void hop_do_tb2(Player p, Message m2) throws IOException { // Hợp đồ tb2
        byte type = m2.reader().readByte();
        short id = m2.reader().readShort();
        if (type == 0) {
            if (p.item.bag3[id] != null && Part_fashion.fashions.contains(p.item.bag3[id].id)) {
                Service.send_notice_box(p.conn, "Trang bị không phù hợp!");
                return;
            }
//            if (p.item.bag3[id].color != 4){
//                Service.send_notice_box(p.conn, "Chỉ có thể hợp trang bị cam");
//                return;
//            }
            if (p.item.bag3[id].type == 7) {
                Service.send_notice_box(p.conn, "Không hợp được cánh");
                return;
            }
            if (p.item.bag3[id].type == 16) {
                Service.send_notice_box(p.conn, "Không hợp mề đay");
                return;
            }
            if (!((p.item.bag3[id].type >= 21 && p.item.bag3[id].type <= 28) || p.item.bag3[id].type == 55 || p.item.bag3[id].type == 102)){
                Service.send_notice_box(p.conn,"Chức năng này chỉ dành cho hợp trang bị 2");
                return;
            }
            if (p.item_replace == -1) {
                if (p.item.bag3[id] != null && Helps.CheckItem.isMeDay(p.item.bag3[id].id)) {
                    Service.send_notice_box(p.conn, "Trang bị không phù hợp!");
                    return;
                }
                if (!((p.item.bag3[id].type >= 21 && p.item.bag3[id].type <= 28) || p.item.bag3[id].type == 55 || p.item.bag3[id].type == 102)){
                    Service.send_notice_box(p.conn,"Chức năng này chỉ dành cho hợp trang bị 2");
                    return;
                }
                if (p.item.bag3[id].color < 4){
                    Service.send_notice_box(p.conn, "Chỉ có thể hợp trang bị cam trở lên");
                    return;
                }
//                if (p.item.bag3[id].type == 7) {
//                    Service.send_notice_box(p.conn, "Không hợp được cánh");
//                    return;
//                }
//                if (p.item.bag3[id].type == 16) {
//                    Service.send_notice_box(p.conn, "Không hợp mề đay");
//                    return;
//                }
                int cap = p.item.bag3[id].tierhop * 10;
                if (cap > p.item.bag3[id].tier){
                    Service.send_notice_box(p.conn,"Bạn chưa đủ cấp cường hóa để hợp, cấp yêu cầu cấp: "+cap);
                    return;
                }
                p.item_replace = id;
            } else if (p.item_replace2 == -1) {
                if (p.item.bag3[p.item_replace].id != p.item.bag3[id].id){
                    Service.send_notice_box(p.conn,"Chỉ có thể bỏ vào 2 vật phẩm giống nhau");
                    return;
                }
                if (p.item.bag3[p.item_replace].tier != p.item.bag3[id].tier){
                    Service.send_notice_box(p.conn,"Chỉ có thể bỏ vào 2 vật phẩm cường hóa giống nhau");
                    return;
                }
                if (p.item.bag3[p.item_replace].tierhop != p.item.bag3[id].tierhop){
                    Service.send_notice_box(p.conn,"Chỉ có thể bỏ vào 2 vật phẩm đẳng cấp giống nhau");
                    return;
                }
                if (p.item.bag3[p.item_replace].color != p.item.bag3[id].color){
                    Service.send_notice_box(p.conn, "Chỉ có thể hợp trang bị cam trở lên");
                    return;
                }
                p.item_replace2 = id;
            } else {
                Service.send_notice_box(p.conn, "Lỗi, hãy thử lại");
                return;
            }
            Message m = new Message(73);
            m.writer().writeByte(0);
            m.writer().writeShort(id);
            if (p.item_replace2 == -1) {
                m.writer().writeByte(1);
            } else {
                m.writer().writeByte(0);
            }
            p.conn.addmsg(m);
            m.cleanup();
        } else if (type == 1) {
            if (p.item.bag3[p.item_replace].tier < p.item.bag3[p.item_replace2].tier) {
                Service.send_notice_box(p.conn, "Đã hợp đồ xong rồi!!");
                return;
            }
            if (!p.item.bag3[p.item_replace2].islock) {
                p.item.bag3[p.item_replace2].islock = true;
                p.item.bag3[p.item_replace2].name = ItemTemplate3.item.get(p.item.bag3[p.item_replace2].id).getName() + " [Khóa]";
                p.item.bag3[p.item_replace2].UpdateName();
                p.item.char_inventory(4);
                p.item.char_inventory(7);
                p.item.char_inventory(3);
            }
            Service.send_box_input_yesno(p.conn, -101, "Bạn có chắc chắn muốn hợp đồ với 1 đá hợp đồ trang bị 2");
        }
    }
}
