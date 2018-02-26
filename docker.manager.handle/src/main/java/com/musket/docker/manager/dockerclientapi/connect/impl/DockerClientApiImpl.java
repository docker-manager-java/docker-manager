package com.musket.docker.manager.dockerclientapi.connect.impl;

import com.musket.docker.manager.dockerclientapi.connect.interfaces.DockerClientApi;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.LogStream;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cc-man on 2018/2/26.
 */
public class DockerClientApiImpl implements DockerClientApi {
    @Override
    public List<ImageSearchResult> searchImages(DockerClient dockerClient, String name) {
        List<ImageSearchResult> list = null;
        try {
            if (!"".equals(name)) {
                list = dockerClient.searchImages(name);
            }
        } catch (DockerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Image> listImanges(DockerClient dockerClient) {
        final List<Image> quxImages;
        try {
            quxImages = dockerClient.listImages();
            return quxImages;
        } catch (DockerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Container> listContainers(DockerClient dockerClient) {
        final List<Container> containers;
        try {
            containers = dockerClient.listContainers();
            return containers;
        } catch (DockerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            dockerClient.close();
        }
        return null;
    }

    @Override
    public ImageInfo inspectImage(DockerClient dockerClient, String imagesID) {
        final ImageInfo info;
        try {
            info = dockerClient.inspectImage(imagesID);
            return info;
        } catch (DockerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ContainerInfo inspectContainer(DockerClient dockerClient, String containerID) {
        final ContainerInfo info;
        try {
            info = dockerClient.inspectContainer(containerID);
            return info;
        } catch (DockerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TopResults topContainer(DockerClient dockerClient, String containerID) {
        final TopResults topResults;
        try {
            topResults = dockerClient.topContainer(containerID);
            return topResults;
        } catch (DockerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ContainerStats stats(DockerClient dockerClient, String containerID) {
        final ContainerStats stats;
        try {
            stats = dockerClient.stats(containerID);
            return stats;
        } catch (DockerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String logs(DockerClient dockerClient, String containerID) {
        final String logs;
        LogStream stream = null;
        try {
            stream = dockerClient.logs(containerID, DockerClient.LogsParam.stdout(), DockerClient.LogsParam.stderr());
            logs = stream.readFully();
            return logs;
        } catch (DockerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ContainerCreation createContainer(DockerClient dockerClient, ContainerConfig containerConfig) {
        final ContainerCreation creation;
        try {
            creation = dockerClient.createContainer(containerConfig);
            return creation;
        } catch (DockerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void startContainer(DockerClient dockerClient, String containerID) {
            final ContainerInfo info = this.inspectContainer(dockerClient,containerID);
            if (info != null){
                try {
                    dockerClient.startContainer(containerID);
                } catch (DockerException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
