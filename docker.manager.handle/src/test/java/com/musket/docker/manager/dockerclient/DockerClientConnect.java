package com.musket.docker.manager.dockerclient;

import com.musket.docker.manager.dockerclientapi.connect.DokerConnect;
import com.musket.docker.manager.dockerclientapi.connect.interfaces.DockerClientApi;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Image;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Administrator on 2018/2/26.
 */
public class DockerClientConnect extends TestCase {
    private static DokerConnect dockerConnect;
    private static DockerClientApi dockerClientApiImpl;
    public static junit.framework.Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new DockerClientConnect("start"));
        suite.addTest(new DockerClientConnect("testConnectDockerClient"));
        return suite;
    }

    @Before
    public void start() throws Exception{
        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        dockerConnect = (DokerConnect) factory.getBean("dockerConnect");
        dockerClientApiImpl = (DockerClientApi) factory.getBean("dockerClientApiImpl");
    }



    public DockerClientConnect(String name) {
        super(name);
    }

    public void testConnectDockerClient(){
        DockerClient docker =  dockerConnect.connect();
            final List<Image> quxImages = dockerClientApiImpl.listImanges(docker);
            assertNotNull(quxImages);

    }
}
