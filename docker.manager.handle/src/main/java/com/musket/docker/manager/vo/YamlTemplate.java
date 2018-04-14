package com.musket.docker.manager.vo;

import java.util.List;

/**
 * Created by Administrator on 2018/3/18.
 */
public class YamlTemplate {

    private NetWorksiInfo networks;
    private ServicesInfo services;
    private String version;
    private VolumeInfo volumes;

    public NetWorksiInfo getNetworks() {
        return networks;
    }

    public void setNetworks(NetWorksiInfo networks) {
        this.networks = networks;
    }

    public ServicesInfo getServices() {
        return services;
    }

    public void setServices(ServicesInfo services) {
        this.services = services;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public VolumeInfo getVolumes() {
        return volumes;
    }

    public void setVolumes(VolumeInfo volumes) {
        this.volumes = volumes;
    }
}