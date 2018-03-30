package com.musket.docker.manager.util;

import com.musket.docker.manager.vo.*;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created by Administrator on 2018/3/18.
 */
public class YamlHelper {

    private YamlTemplate yamlTemplate;
    private NetWorksiInfo netWorksiInfo;
    private ServicesInfo servicesInfo;
    private VersionInfo versionInfo;
    private VolumeInfo volumeInfo;

    public void setYamlTemplate(YamlTemplate yamlTemplate) {
        this.yamlTemplate = yamlTemplate;
    }

    public void setNetWorksiInfo(NetWorksiInfo netWorksiInfo) {
        this.netWorksiInfo = netWorksiInfo;
    }

    public void setServicesInfo(ServicesInfo servicesInfo) {
        this.servicesInfo = servicesInfo;
    }

    public YamlTemplate getYamlTemplate() {
        return yamlTemplate;
    }

    public NetWorksiInfo getNetWorksiInfo() {
        return netWorksiInfo;
    }

    public ServicesInfo getServicesInfo() {
        return servicesInfo;
    }

    public VersionInfo getVersionInfo() {
        return versionInfo;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVersionInfo(VersionInfo versionInfo) {
        this.versionInfo = versionInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public  void write() {


        JSONObject jsonObj = new JSONObject();
        jsonObj = JSONObject.fromObject(yamlTemplate);
        Map<String,String> map_networks =new HashMap<String, String>();
        map_networks.put("driver","bridge");
        map_networks.put("driver_opts","com.docker.network.bridge.host_binding_ipv4: 192.168.46.195");
        jsonObj.getJSONObject("networks").put("sgs", map_networks);
        jsonObj.toString();

        ServiceMould serviceMould =new ServiceMould();
        List<String> environmentlist = new ArrayList<String>();
        environmentlist.add("SERVICE_27017_NAME=sgs-mongodb-map");
        serviceMould.setEnvironment(environmentlist);
        serviceMould.setImage("supermap/sgs-mongodb:3.0");
        List<String> pots = new ArrayList<String>();
        pots.add("27019:27017");
        serviceMould.setPorts(pots);
        List<String> network = new ArrayList<String>();
        network.add("sgs");
        serviceMould.setNetworks(network);

        jsonObj.getJSONObject("services").put("mongodb-map", serviceMould);

        ServiceMould serviceMould1 =new ServiceMould();
        List<String> environmentlist1 = new ArrayList<String>();
        environmentlist1.add("SERVICE_8080_NAME=sgs-dfc-service-name-dfc-map");
        serviceMould1.setEnvironment(environmentlist1);
        serviceMould1.setImage("supermap/sgs-dfc:9.0");
        List<String> pots1 = new ArrayList<String>();
        pots.add("8080:8080");
        serviceMould1.setPorts(pots);
        serviceMould1.setNetworks(network);
        List<String> link = new ArrayList<String>();
        link.add(" mongodb-map:mongodbip");
        serviceMould1.setLinks(link);
        jsonObj.getJSONObject("services").put("map", serviceMould1);
    }

}


