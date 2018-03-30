package com.musket.docker.manager.vo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/18.
 */
public class VolumeInfo {

    private List<Map<String,String>> volumemap;

    public List<Map<String, String>> getVolumemap() {
        return volumemap;
    }

    public void setVolumemap(List<Map<String, String>> volumemap) {
        this.volumemap = volumemap;
    }
}
