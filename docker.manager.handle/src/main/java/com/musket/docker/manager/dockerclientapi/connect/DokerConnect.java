package com.musket.docker.manager.dockerclientapi.connect;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;


/**
 * Created by Administrator on 2018/2/26.
 */
public class DokerConnect {
    private String url;


    public void setUrl(String url) {
        this.url = url;
    }

    public  DockerClient connect() {
        return new DefaultDockerClient(url);
    }

}