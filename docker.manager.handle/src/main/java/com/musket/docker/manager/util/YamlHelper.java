package com.musket.docker.manager.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.gson.Gson;
import com.musket.docker.manager.vo.*;
import net.sf.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2018/3/18.
 */
public class YamlHelper {

    private YamlTemplate yamlTemplate;
    private NetWorksiInfo netWorksiInfo;
    private ServicesInfo servicesInfo;
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



    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }



    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public  void write() {


        JSONObject jsonObj = new JSONObject();
        yamlTemplate.setVersion("2");
        jsonObj = JSONObject.fromObject(yamlTemplate);
        Map<String,Object> networks = new HashMap<String, Object>();
        Map<String,Object> networksdriveroopts = new HashMap<String, Object>();
        networksdriveroopts.put("com.docker.network.bridge.host_binding_ipv4","192.168.46.195");

        networks.put("driver","bridge");
        networks.put("driver_opts",networksdriveroopts);
        jsonObj.getJSONObject("networks").put("sgs",networks);

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
        link.add("mongodb-map:mongodbip");
        serviceMould1.setLinks(link);
        jsonObj.getJSONObject("services").put("map", serviceMould1);
       // map_networks.put("driver_opts","com.docker.network.bridge.host_binding_ipv4: 192.168.46.195");




        jsonObj.toString();
        try {
            createYaml(jsonObj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }



       /* ServiceMould serviceMould =new ServiceMould();
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
        jsonObj.getJSONObject("services").put("map", serviceMould1);*/
    }



    public static void createYaml(String jsonString) throws JsonProcessingException, IOException {
        // parse JSON
        JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
        // save it as YAML
        String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);

        Yaml yaml = new Yaml();
        Map<String,Object> map = (Map<String, Object>) yaml.load(jsonAsYaml);

        createYamlFile("C:\\Users\\Administrator\\Desktop\\s.yaml", map);
    }

    /**
     * 将数据写入yaml文件
     * @param url yaml文件路径
     * @param data 需要写入的数据
     */
    public static void createYamlFile(String url,Map<String, Object> data){
        Yaml yaml = new Yaml();
        FileWriter writer;
        try {
            writer = new FileWriter(url);
            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 讀取yaml生成json并返回
     *
     * @param file
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String yamlToJson(String file) {
        Gson gs = new Gson();
        Map<String, Object> loaded = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            Yaml yaml = new Yaml();
            loaded = (Map<String, Object>) yaml.load(fis);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return gs.toJson(loaded);
    }

}


