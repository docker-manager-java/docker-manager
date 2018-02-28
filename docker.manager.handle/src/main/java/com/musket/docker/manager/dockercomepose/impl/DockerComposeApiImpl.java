package com.musket.docker.manager.dockercomepose.impl;

import com.musket.docker.manager.dockercomepose.interfaces.DockerComposeApi;
import org.apache.http.Header;

/**
 * Created by Administrator on 2018/2/27.
 */
public class DockerComposeApiImpl implements DockerComposeApi {
    String composeUrl;

    public void setComposeUrl(String composeUrl) {
        this.composeUrl = composeUrl;
    }

    @Override
    public String creatCompose(String name, String yml, String env) {

        return null;
    }
}
