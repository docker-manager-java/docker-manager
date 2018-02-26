package com.musket.docker.manager.dockerclientapi.connect;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;


/**
 * Created by Administrator on 2018/2/26.
 */
public class DokerConnect {

    public static DockerClient connect(String url) {
        final DockerClient docker;
        docker = new DefaultDockerClient(url);
        return docker;
    }
}