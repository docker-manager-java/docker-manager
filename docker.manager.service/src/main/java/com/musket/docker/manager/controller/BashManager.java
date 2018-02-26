package com.musket.docker.manager.controller;

import com.musket.docker.manager.dockerclientapi.connect.DokerConnect;
import com.musket.docker.manager.dockerclientapi.connect.interfaces.DockerClientApi;
import com.musket.docker.manager.vo.ResultInfo;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Image;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2018/2/26.
 */
@Controller
@RequestMapping("/services")
public class BashManager {
    DockerClientApi dockerClientApi;
    DokerConnect dokerConnect;

    public void setDokerConnect(DokerConnect dokerConnect) {
        this.dokerConnect = dokerConnect;
    }

    public void setDockerClientApi(DockerClientApi dockerClientApi) {
        this.dockerClientApi = dockerClientApi;
    }

    @RequestMapping(value = "/listimages" ,method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo listImages(HttpServletRequest request,
                             HttpServletResponse response){
        ResultInfo result = new ResultInfo();
        DockerClient docker = dokerConnect.connect();
        try {
            List<Image> images =  dockerClientApi.listImanges(docker);
            result.setData(images);
        }catch (Exception e){

        }finally {
            docker.close();
        }
    return  result;
    }
}
