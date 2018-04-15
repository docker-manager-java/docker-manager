package com.musket.docker.manager.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.gson.Gson;
import com.musket.docker.manager.vo.*;
import net.sf.json.JSONArray;
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

    public String writeYaml( Map<String,Object>  parms) {
        String result = "";
        JSONObject jsonObj = new JSONObject();
        String[] strings = (String[]) parms.get("ver");
        String ver = strings[0];
        strings =(String[]) parms.get("ip");
        String ip = strings[0];
        strings =(String[]) parms.get("name");
        String name = strings[0];
        strings =(String[]) parms.get("data");
        String data = strings[0];
        yamlTemplate.setVersion(ver);
        //获取模板信息
        jsonObj = JSONObject.fromObject(yamlTemplate);
        Map<String,Object> networks = new HashMap<String, Object>();
        Map<String,Object> networksdriveroopts = new HashMap<String, Object>();
        //设置ip,网络
        networksdriveroopts.put("com.docker.network.bridge.host_binding_ipv4",ip);
        networks.put("driver","bridge");
        networks.put("driver_opts",networksdriveroopts);
        jsonObj.getJSONObject("networks").put(name,networks);
        //获取services数据
        JSONObject jsonObject = JSONObject.fromObject(data);
        JSONArray jsonArray  = (JSONArray) jsonObject.get("data");
        //遍历services数据
        for (int i  = 0;i<jsonArray.size();i++ ){
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
            ServiceMould serviceMould =new ServiceMould();
            List<String> environmentlist = new ArrayList<String>();
            environmentlist.add("SERVICE_27017_NAME=sgs-mongodb-map");
            serviceMould.setEnvironment(environmentlist);
            serviceMould.setImage((String) jsonObject1.get("Image"));
            List<String> pots = new ArrayList<String>();
            pots.add((String) jsonObject1.get("Ports"));
            serviceMould.setPorts(pots);
            List<String> network = new ArrayList<String>();
            network.add(name);
            serviceMould.setNetworks(network);
            jsonObj.getJSONObject("services").put(jsonObject1.get("Name"),serviceMould);
        }
        try {
            result = createYaml(jsonObj.toString());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取yaml文件
     * @param jsonString
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     */
    public static String createYaml(String jsonString) throws JsonProcessingException, IOException {
        String result = "";
        // parse JSON
        JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
        // save it as YAML
        String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);

        Yaml yaml = new Yaml();
        Map<String,Object> map = (Map<String, Object>) yaml.load(jsonAsYaml);
        result  = yaml.dumpAsMap(map);
        if (!"".equals(result)){
            return result;
        }
        return result;
        //createYamlFile("C:\\Users\\Administrator\\Desktop\\s.yaml", map);
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

    /**
     //   * 打印生成yaml格式的string
     //   */
  public static void testDump() {
      Map<String, Object> data = new HashMap<String, Object>();
      data.put("name", "Silenthand Olleander");
      data.put("race", "Human");
      data.put("traits", new String[] { "ONE_HAND", "ONE_EYE" });
      Yaml yaml = new Yaml();
      String output = yaml.dump(data);
      System.out.println(output);
 }
}


