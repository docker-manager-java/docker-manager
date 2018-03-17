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
@RequestMapping("/compose/model")
public class ComposeManager {
    private String composeUrl;
    public static final String CREATURL = "api/v1/create-project";
    public static final String LISTURL = "api/v1/projects";
    public static final String DELETE = "api/v1/remove-project";
    public static final String START = "api/v1/start";
    public static final String DOWN = "api/v1/down";


    public void setComposeUrl(String composeUrl) {
        this.composeUrl = composeUrl;
    }

    /**
     * create
     *
     * @param request
     * @param response
     * @param yaml     text
     * @param name
     * @return
     */
    @RequestMapping(value = "/creatfromyaml", method = RequestMethod.POST)
    public Object creatFromYaml(HttpServletRequest request,
                                HttpServletResponse response, String yaml, String name) {
        ResultInfo result = new ResultInfo();
        if ("".equals(name) || "".equals(yaml)) {
            result.setSuccess(false);
            result.setMessage("Nmae or Yaml is not null！");
        } else {
            String url = composeUrl + CREATURL;
            String yml = "{\"name\":\"" + name + "\",\"yml\":" + YamlUtil.yamlFormart(yaml) + ",\"env\":\"\"}";
            try {
                result.setData(RequestModel.dosent("POST", url, yml));
            } catch (HttpProcessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * listmodels
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listmodels", method = RequestMethod.GET)
    public Object listModel(HttpServletRequest request,
                            HttpServletResponse response) {
        ResultInfo result = new ResultInfo();
        String url = composeUrl + LISTURL;
        try {
            String r = (String) RequestModel.dosent("GET", url, "");
            if (!"".equals(r)) {
                result.setData(r);
            }
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }

        return result;

    }

    /**
     * showprojectdetails
     *
     * @param request
     * @param response
     * @param name
     * @return
     */
    @RequestMapping(value = "/showprojectdetails", method = RequestMethod.GET)
    public Object inspectModel(HttpServletRequest request,
                               HttpServletResponse response, String name) {
        ResultInfo result = new ResultInfo();
        String url;
        if (!"".equals(name)) {
            url = composeUrl + LISTURL + "/" + name;
        } else {
            result.setSuccess(false);
            return result;
        }

        try {
            String r = (String) RequestModel.dosent("GET", url, "");
            if (!"".equals(r)) {
                if (r.startsWith("error")) {
                    result.setMessage(r);
                } else {
                    result.setSuccess(true);
                    result.setData(r);
                }
            }
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }

        return result;

    }

    @RequestMapping(value = "/deletemodelbyname", method = RequestMethod.GET)
    public Object deleteModelByName(HttpServletRequest request,
                                    HttpServletResponse response, String name) {
        ResultInfo result = new ResultInfo();
        String url;
        if (!"".equals(name)) {
            url = composeUrl + DELETE + "/" + name;
        } else {
            result.setSuccess(false);
            return result;
        }

        try {
            String r = (String) RequestModel.dosent("DELETE", url, "");
            if (!"".equals(r)) {
                if (r.startsWith("error")) {
                    result.setMessage(r);
                } else {
                    result.setSuccess(true);
                }
            }
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }

        return result;

    }


    @RequestMapping(value = "/startproject", method = RequestMethod.GET)
    public Object startProject(HttpServletRequest request,
                               HttpServletResponse response, String name) {
        ResultInfo result = new ResultInfo();
        String url;
        String data;
        String r ;
        if (!"".equals(name)) {
            url = composeUrl + LISTURL;
             data = "{\"id\":\""+name+"\"}";
        } else {
            result.setSuccess(false);
            return result;
        }
        try {
             r = (String) RequestModel.dosent("POST", url,data);
            if (!"".equals(r)) {
                if (r.startsWith("error")) {
                    result.setSuccess(false);
                    result.setMessage(r);
                } else {
                    result.setSuccess(true);
                    result.setData(r);
                }
            }
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }

        return result;

    }



    @RequestMapping(value = "/downproject", method = RequestMethod.GET)
    public Object downProject(HttpServletRequest request,
                               HttpServletResponse response, String name) {
        ResultInfo result = new ResultInfo();
        String url;
        String data;
        String r ;
        if (!"".equals(name)) {
            url = composeUrl + DOWN;
            data = "{\"id\":\""+name+"\"}";
        } else {
            result.setSuccess(false);
            return result;
        }
        try {
            r = (String) RequestModel.dosent("POST", url,data);
            if (!"".equals(r)) {
                if (r.startsWith("error")) {
                    result.setSuccess(false);
                    result.setMessage(r);
                } else {
                    result.setSuccess(true);
                    result.setData(r);
                }
            }
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }

        return result;

    }
}