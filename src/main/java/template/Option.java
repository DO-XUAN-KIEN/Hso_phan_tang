package template;

import core.Util;

public class Option {

    private static final int[] parafterupdate
            = new int[]{1, 110, 120, 130, 140, 150, 160, 172, 184, 196, 208, 220, 235, 250, 265, 280};
    public byte id;
    public int param;
    private short idItem;

    public Option(int id, int param, short iditem) {
        this.id = (byte) id;
        this.param = param;
        this.idItem = iditem;
    }
//        public Option(int id, int param) {
//		this.id = (byte) id;
//		this.param = param;
//	}

    public Option(int id, int param) {
        this.id = (byte) id;
        this.param = param;
    }

    public static Option createOpItemStar(int idOption, short idItem) {
        Option newOP;
        switch (idOption) {
            case -115, -103, -88, -87 ->
                newOP = new Option(idOption, 180000, idItem);
            case 129 ->
                newOP = new Option(idOption, 10000, idItem);
            case 130 -> {
                short temp = (short) Util.nextInt(100, 400);
                if (Util.nextInt(20) == 1) {
                    temp = (short) Util.nextInt(100, 900);
                }
                newOP = new Option(idOption, temp, idItem);
            }
            default ->
                newOP = new Option(idOption, Util.random(100, 200), idItem);
        }
        return newOP;
    }

    public int getParam(int tier) {
        if ((id >= 100 && id <= 107) || (id >= 58 && id <= 60)) {
            return param;
        }
        if (Helps.CheckItem.isArmor(idItem)) {
            return param;
        }
        if (Helps.CheckItem.isMeDay(idItem)) {
            return getParamMD(tier);
        }
        //return param;
        long parbuffer = (long) this.param;
        if (tier == 0) {
            return (int) parbuffer;
        }
        if (this.id >= 29 && this.id <= 36 || this.id >= 16 && this.id <= 22 || this.id == 41) {
            parbuffer += 20L * tier;
            return (int) parbuffer;
        }

        if (this.id >= 23 && this.id <= 26) {
            parbuffer += tier;
            return (int) parbuffer;
        }
        if (this.id == 42) {
            parbuffer += tier * 400L;
            return (int) parbuffer;
        }
        if ((this.id >= 7 && this.id <= 13) || this.id == 15 || this.id == 27 || this.id == 28) {
            parbuffer += 100L * tier;
            return (int) parbuffer;
        }
        if ((this.id == 37 || this.id == 38) && tier < 9) {
            return 1;
        }
        if (tier > 15) {
            tier = 15;
        }
        if ((this.id >= 0 && this.id <= 6) || this.id == 14 || this.id == 40) {
            if (parbuffer >= 2_000_000_000 || parbuffer < 0){
                parbuffer = 2_000_000_000;
            }
            return (int) parbuffer;
        }
        return (int) parbuffer;
    }

    public int getParamMD(int tier) {
//            if(tier>15)
//                System.out.println("==================="+tier);
        if (tier == 0) {
            return param;
        }
        if ((this.id == 37 || this.id == 38)) {
            return 1;
        }
        //
        int parbuffer = this.param;
        if (this.id >= 0 && this.id <= 6) {
            return (parbuffer + ((int) (parbuffer * tier * 3)));
        }

        if (this.id == 81 || this.id == 86 || this.id == 88 || this.id == 77 || this.id == 79) // giây dòng vip
        {
            return (int) (parbuffer * tier * 0.3);
        }
        if (this.id == 85 || this.id == 87 || this.id == 80 || this.id == 82) // dòng vip
        {
            return (int) (parbuffer * tier * 0.031);
        }
        if (this.id == 78 || this.id == 76) // dòng vip
        {
            return (int) (parbuffer * tier * 0.1);
        }

        if ((this.id >= 76 && this.id <= 89) || this.id == 97 || this.id == 98 || this.id == 95) // dòng vip
        {
            return (int) (parbuffer * tier * 0.07);
        }
        if (this.id >= 29 && this.id <= 36 || this.id >= 16 && this.id <= 22 || this.id == 41) {
            parbuffer += 50 * tier;
            return parbuffer;
        }

        if (this.id >= 23 && this.id <= 26) {
            return (parbuffer + tier);
        }
        if (this.id == 42) {
            return (parbuffer + tier * 400);
        }
        if ((this.id >= 7 && this.id <= 13)) {
            return (this.param * tier);
        }
        if (this.id == 15 || this.id == 27 || this.id == 28) {
            return (parbuffer + 100 * tier);
        }

        if (tier > 15) {
            tier = 15;
        }
        if ((this.id >= 0 && this.id <= 6) || this.id == 14 || this.id == 40) {
            parbuffer = (parafterupdate[tier] * this.param) / 100;
            return parbuffer;
        }
        return parbuffer;
    }

    public void setParam(int param) {
        this.param = param;
    }
}
