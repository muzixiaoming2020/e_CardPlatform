package jsu.bean;

import java.util.*;

public class Power {
    //属性  权限
    //属性：性别，姓名
    //权限：1-可对外分享
    private Map<String,Integer> map;
    public Power(){
        map=new HashMap<String,Integer>();
    }

    public Power(Map<String, Integer> map) {
        this.map = map;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }
}
