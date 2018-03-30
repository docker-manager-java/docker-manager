package com.musket.docker.manager.vo;

import java.util.List;

/**
 * Created by Administrator on 2018/3/18.
 */
public class ServiceMould {

    private List<String> environment;
    private String image;
    private String  mem_limit;
    private  List<String> networks;
    private  List<String> ports;
    private  List<String> volumes;
    private  List<String> links;
    private List<String> command;
    private String hostname;
    private String restart;


    public List<String> getEnvironment() {
        return environment;
    }

    public void setEnvironment(List<String> environment) {
        this.environment = environment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMem_limit() {
        return mem_limit;
    }

    public void setMem_limit(String mem_limit) {
        this.mem_limit = mem_limit;
    }

    public List<String> getNetworks() {
        return networks;
    }

    public void setNetworks(List<String> networks) {
        this.networks = networks;
    }

    public List<String> getPorts() {
        return ports;
    }

    public void setPorts(List<String> ports) {
        this.ports = ports;
    }

    public List<String> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<String> volumes) {
        this.volumes = volumes;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public List<String> getCommand() {
        return command;
    }

    public void setCommand(List<String> command) {
        this.command = command;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getRestart() {
        return restart;
    }

    public void setRestart(String restart) {
        this.restart = restart;
    }
}
