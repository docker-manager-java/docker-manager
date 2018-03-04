package com.musket.docker.manager.controller;

import com.musket.docker.manager.util.composeutil.RequestModel;
import com.musket.docker.manager.util.composeutil.YamlUtil;
import com.musket.docker.manager.util.httpclientutil.common.HttpHeader;
import com.musket.docker.manager.vo.ResultInfo;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.Image;
import org.apache.http.Header;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.musket.docker.manager.util.httpclientutil.HttpClientUtil;
import com.musket.docker.manager.util.httpclientutil.builder.HCB;
import com.musket.docker.manager.util.httpclientutil.common.HttpConfig;
import com.musket.docker.manager.util.httpclientutil.common.HttpHeader;
import com.musket.docker.manager.util.httpclientutil.exception.HttpProcessException;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cc-man on 2018/2/27.
 */
@Controller
@RequestMapping("/compose")
public class ComposeManager {
    private String composeUrl;
    public static final String CREATURL = "/api/v1/create-project";


    public void setComposeUrl(String composeUrl) {
        this.composeUrl = composeUrl;
    }

    @RequestMapping(value = "/creatfromyaml", method = RequestMethod.POST)
    public Object listImages(HttpServletRequest request,
                             HttpServletResponse response, String yaml, String name) {
        ResultInfo result = new ResultInfo();
        if("".equals(name) || "".equals(yaml)){
            result.setSuccess(false);
            result.setMessage("Nmae or Yaml is not null！");
        }else {
            String url = composeUrl + CREATURL;
            String yml = "{\"name\":\"" + name + "\",\"yml\":" + YamlUtil.yamlFormart(yaml) + ",\"env\":\"\"}";
            try {
                result.setData(RequestModel.doPost(url, yml));
            } catch (HttpProcessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}