package com.musket.docker.manager.dockerclientapi.comtext;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.*;
import java.util.List;


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