package jsu.bean;

import java.util.*;

public class Power {
    //����  Ȩ��
    //���ԣ��Ա�����
    //Ȩ�ޣ�1-�ɶ������
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
