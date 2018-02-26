package com.musket.docker.manager.dockerclient;

import com.musket.docker.manager.dockerclientapi.connect.DokerConnect;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Image;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Created by Administrator on 2018/2/26.
 */
public class DockerClientConnect extends TestCase {

    public static junit.framework.Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new DockerClientConnect("testConnectDockerClient"));
        return suite;
    }

    public DockerClientConnect(String name) {
        super(name);
    }

    public void testConnectDockerClient(){
        DockerClient docker =  DokerConnect.connect("http://192.168.46.132:2375/");
        try {
            final List<Image> quxImages = docker.listImages();
            assertNotNull(quxImages);
        } catch (DockerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
