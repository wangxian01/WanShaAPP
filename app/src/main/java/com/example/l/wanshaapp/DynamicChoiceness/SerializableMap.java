package com.example.l.wanshaapp.DynamicChoiceness;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by cap on 2018/5/29.
 */
public class SerializableMap implements Serializable {

    private Map<String,Object> map;

    public Map<String, Object> getMap(String s) {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }


}
