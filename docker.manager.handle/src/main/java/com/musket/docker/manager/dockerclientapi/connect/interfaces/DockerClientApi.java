package com.musket.docker.manager.dockerclientapi.connect.interfaces;

import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.LogStream;
import com.spotify.docker.client.messages.*;

import java.util.List;

/**
 * Created by cc-man on 2018/2/26.
 */
public interface DockerClientApi {
    /**
     * searchImages 搜索镜像
     * @param dockerClient
     * @param name
     * @return
     */
    public List<ImageSearchResult> searchImages(DockerClient dockerClient,String name);

    /**
     * listImanges 查看镜像列表
     * @param dockerClient
     * @return
     */
    public List<Image> listImanges(DockerClient dockerClient);

    /**
     * listContainers 获取容器列表
     * @param dockerClient
     * @return
     */
    public List<Container> listContainers(DockerClient dockerClient);

    /**
     * inspectImage 检查镜像健康状态
     * @param dockerClient
     * @param imagesID
     * @return
     */
    public ImageInfo inspectImage(DockerClient dockerClient,String imagesID);

    /**
     * inspectContainer 检查容器健康状态
     * @param dockerClient
     * @param containerID
     * @return
     */
    public ContainerInfo inspectContainer(DockerClient dockerClient,String containerID);


    /**
     * topContainer 获取容器进程状态
     * @param dockerClient
     * @param containerID
     * @return
     */
    public TopResults topContainer(DockerClient dockerClient,String containerID);

    /**\
     * stats 获取容器监控
     * @param dockerClient
     * @return
     */
    public ContainerStats stats(DockerClient dockerClient,String containerID);

    /**
     * logs 获取容器日志
     * @param dockerClient
     * @param containerID
     * @return
     */
    public String logs(DockerClient dockerClient, String containerID);

    /**
     * createContainer 创建容器
     * @param dockerClient
     * @param containerConfig
     * @return
     */
    public ContainerCreation createContainer(DockerClient dockerClient ,ContainerConfig containerConfig);

    /**
     * startContainer 启动容器
     * @param dockerClient
     * @param containerID
     */
    public void startContainer(DockerClient dockerClient,String containerID);



}
