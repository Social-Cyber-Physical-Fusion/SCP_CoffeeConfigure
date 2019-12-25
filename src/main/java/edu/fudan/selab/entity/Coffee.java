package edu.fudan.selab.entity;



public class Coffee {

    private String level;  //咖啡浓度
    private String mode;   //咖啡模式
    private String num;       //咖啡杯数

    public String getLevel() {
        return level;
    }

    public String getMode() {
        return mode;
    }

    public String getNum() {
        return num;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
