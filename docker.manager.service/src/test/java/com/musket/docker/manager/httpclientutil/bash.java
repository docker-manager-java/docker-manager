package com.musket.docker.manager.httpclientutil;

import com.musket.docker.manager.util.composeutil.TestYaml;
import com.musket.docker.manager.util.httpclientutil.HttpClientUtil;
import com.musket.docker.manager.util.httpclientutil.builder.HCB;
import com.musket.docker.manager.util.httpclientutil.common.HttpConfig;
import com.musket.docker.manager.util.httpclientutil.common.HttpHeader;
import com.musket.docker.manager.util.httpclientutil.exception.HttpProcessException;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/6.
 */
public class bash {
    public static void main(String[] args) throws HttpProcessException, FileNotFoundException {
        String url = "http://192.168.46.195:5000/api/v1/create-project";

        //最简单的使用：
        //String html = HttpClientUtil.get(HttpConfig.custom().url(url));
        //System.out.println(html);

        //---------------------------------
        //			【详细说明】
        //--------------------------------

        //插件式配置Header（各种header信息、自定义header）
        Header[] headers = HttpHeader.custom()
                /*.userAgent("javacl")
                .other("customer", "自定义")*/
                .contentType("application/json")
                .build();

        //插件式配置生成HttpClient时所需参数（超时、连接池、ssl、重试）
        HCB hcb = HCB.custom()
                .timeout(1000) //超时
                .pool(100, 10) //启用连接池，每个路由最大创建10个链接，总连接数限制为100个
                        //.sslpv(SSLs.SSLProtocolVersion.TLSv1_2) 	//设置ssl版本号，默认SSLv3，也可以调用sslpv("TLSv1.2")
                .ssl()  	  	//https，支持自定义ssl证书路径和密码，ssl(String keyStorePath, String keyStorepass)
                .retry(5)		//重试5次
                ;

        HttpClient client = hcb.build();

        Map<String, Object> map = new HashMap<String, Object>();
       // String ymls ="\"networks:\\n  mysgs:\\n    driver: bridge\\n    driver_opts: {com.docker.network.bridge.host_binding_ipv4: 192.168.46.156}\\nservices:\\n  mongodb-sgs:\\n    environment: [SERVICE_27017_NAME=sgs-mongodb-sgs]\\n    image: supermap/sgs-mongodb:3.0\\n    mem_limit: 256m\\n    networks: [mysgs]\\n    ports: ['27017:27017']\\n    volumes: ['mongo-sgs-data:/data/db']\\n  mongodb-map:\\n    environment: [SERVICE_27017_NAME=sgs-mongodb-map]\\n    image: supermap/sgs-mongodb:3.0\\n    mem_limit: 256m\\n    networks: [mysgs]\\n    ports: ['27018:27017']\\n    volumes: ['mongo-map-data:/data/db']\\n  mongodb-geocoding:\\n    environment: [SERVICE_27017_NAME=sgs-mongodb-geocoding]\\n    image: supermap/sgs-mongodb:3.0\\n    mem_limit: 256m\\n    networks: [mysgs]\\n    ports: ['27019:27017']\\n    volumes: ['mongo-geocoding-data:/data/db']\\n  redis:\\n    environment: [SERVICE_6379_NAME=sgs-redis]\\n    image: supermap/sgs-redis:3.0.3\\n    mem_limit: 50m\\n    networks: [mysgs]\\n    ports: ['6379:6379']\\n  oracle:\\n    environment: [SERVICE_1521_NAME=sgs-oracle]\\n    image: supermap/sgs-oracle:11g\\n    mem_limit: 2048m\\n    networks: [mysgs]\\n    ports: ['1521:1521']\\n    volumes: ['oracle-data:/u01/app/oracle']\\n  activemq:\\n    environment: [SERVICE_8161_NAME=sgs-activemq, SERVICE_61616_NAME=sgs-activemq]\\n    image: supermap/sgs-activemq:5.6.0\\n    mem_limit: 512m\\n    networks: [mysgs]\\n    ports: ['8161:8161', '61616:61616']\\n  analysis:\\n    environment: [SERVICE_8090_NAME=sgs-iserver-service-name-iserver]\\n    hostname: cc\\n    image: supermap/sgs-iserver:8.1.1\\n    links: ['oracle:oracleip']\\n    mem_limit: 1536m\\n    networks: [mysgs]\\n    ports: ['8090']\\n    volumes: ['iserver-log-data:/opt/tomcat/logs', 'idesktop-iserver-data:/opt/Supermap/data']\\n  geoesb:\\n    environment: [SERVICE_8081_NAME=sgs-mule-service-name-mule]\\n    image: supermap/sgs-mule:3.4.0\\n    links: ['redis:redisip', 'activemq:mqip', 'mongodb-sgs:mongodbip', 'oracle:oracleip']\\n    mem_limit: 1024m\\n    networks: [mysgs]\\n    ports: ['8081']\\n  portal:\\n    environment: [PUBLIC_IP=192.168.46.156, SERVICE_8080_NAME=sgs-sgs-service-name-sgs]\\n    hostname: cc\\n    image: supermap/sgs:9.0\\n    links: ['redis:redisip', 'activemq:mqip', 'mongodb-sgs:mongodbip', 'oracle:oracleip',\\n      'geoesb:muleip']\\n    mem_limit: 3062m\\n    networks: [mysgs]\\n    ports: ['8080']\\n    volumes: ['sgs-log-data:/opt/tomcat/logs']\\n  idesktop:\\n    hostname: cc\\n    image: supermap/idesktop:9D\\n    links: ['oracle:oracleip']\\n    networks: [mysgs]\\n    ports: ['5901:5901', '6901:6901']\\n    volumes: ['idesktop-iserver-data:/opt/Supermap/data']\\n  map:\\n    environment: [SERVICE_8080_NAME=sgs-dfc-service-name-dfc-map]\\n    image: supermap/sgs-dfc:9.0\\n    links: ['mongodb-map:mongodbip']\\n    networks: [mysgs]\\n    ports: ['8080']\\n  geocoding:\\n    environment: [SERVICE_8080_NAME=sgs-dfc-service-name-dfc-geocoding]\\n    image: supermap/sgs-dfc:9.0\\n    links: ['mongodb-geocoding:mongodbip']\\n    networks: [mysgs]\\n    ports: ['8080']\\n  consul:\\n    command: [agent, -ui, -dev, -server, -bind=0.0.0.0, -bootstrap, -client=0.0.0.0]\\n    environment: [SERVICE_NAME=sgs-consul, SERVICE_8500_NAME=sgs-consul]\\n    hostname: cc\\n    image: consul:latest\\n    mem_limit: 50m\\n    networks: [mysgs]\\n    ports: ['8500']\\n    restart: unless-stopped\\n  registrator:\\n    command: [-ip=192.168.46.156, -resync, '60', -retry-attempts, '-1', 'consul://consul:8500']\\n    environment: [SERVICE_NAME=sgs-registrator]\\n    hostname: cc\\n    image: gliderlabs/registrator:latest\\n    links: [consul]\\n    mem_limit: 50m\\n    networks: [mysgs]\\n    restart: unless-stopped\\n    volumes: ['/var/run/docker.sock:/tmp/docker.sock']\\n  nginx:\\n    environment: ['CONSUL=consul:8500', PUBLIC_IP=192.168.46.156, SERVICE_NAME=sgs-nginx,\\n      SERVICE_80_NAME=sgs-nginx, SERVICE_81_NAME=sgs-nginx, SERVICE_82_NAME=sgs-nginx,\\n      SERVICE_83_NAME=sgs-nginx, SERVICE_84_NAME=sgs-nginx, SGS_SERVICE=sgs-sgs-service-name-sgs,\\n      MULE_SERVICE=sgs-mule-service-name-mule, ISERVER_SERVICE=sgs-iserver-service-name-iserver,\\n      MAP_SERVICE=sgs-dfc-service-name-dfc-map, GEOCODING_SERVICE=sgs-dfc-service-name-dfc-geocoding]\\n    image: supermap/nginx:1.10.0\\n    links: [consul]\\n    mem_limit: 50m\\n    networks: [mysgs]\\n    ports: ['8080:80', '8081:81', '8090:82', '8180:83', '8280:84']\\n    restart: unless-stopped\\nversion: '2'\\nvolumes: {mongo-sgs-data: null, mongo-map-data: null, mongo-geocoding-data: null,\\n  oracle-data: null, iserver-log-data: null, sgs-log-data: null, idesktop-iserver-data: null}\\n\"\n";
        Map msap = TestYaml.yamlToMap("C:\\Users\\Administrator\\Desktop\\work\\docker-manager\\docker.manager.service\\src\\test\\java\\com\\musket\\docker\\manager\\httpclientutil\\test.yaml");
        // String result = getJsonParam("C:\\Users\\Administrator\\Desktop\\work\\docker-manager\\docker.manager.service\\src\\main\\java\\com\\musket\\docker\\manager\\util\\composeutil\\test.yaml", 111111, 22222222, "333333", "4444444", "1000");
        Yaml yaml = new Yaml();
        StringWriter writer = new StringWriter();
        yaml.dump(msap, writer);
        String yamls=  writer.toString();
        String dddd="\""+yamls.toString()+"\"";
        String  ds = "\\"+"\\"+'n';
       String gg= dddd.replaceAll("\\n",ds);
        //插件式配置请求参数（网址、请求参数、编码、client）
        HttpConfig config = HttpConfig.custom()
                .headers(headers)	//设置headers，不需要时则无需设置
                .url(url)	//设置请求的url
                        //.map(map)	//设置请求参数，没有则无需设置
                .encoding("utf-8")//设置请求和返回编码，默认就是Charset.defaultCharset()
                        //.client(client)	//如果只是简单使用，无需设置，会自动获取默认的一个client对象
                        //.inenc("utf-8") //设置请求编码，如果请求返回一直，不需要再单独设置
                        //.inenc("utf-8")	//设置返回编码，如果请求返回一直，不需要再单独设置
                .json("{\"name\":\"test\",\"yml\":"+gg+",\"env\":\"\"}")     //json方式请求的话，就不用设置map方法，当然二者可以共用。
                //.context(HttpCookies.custom().getContext()) //设置cookie，用于完成携带cookie的操作
                //.out(new FileOutputStream("保存地址"))		//下载的话，设置这个方法,否则不要设置
                //.files(new String[]{"d:/1.txt","d:/2.txt"})	//上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
                ;


        //使用方式：
        // String result1 = HttpClientUtil.get(config);	//get请求
        String result2 = HttpClientUtil.post(config);	//post请求
        //System.out.println(result1);
        System.out.println(result2);

        //HttpClientUtil.down(config);					//下载，需要调用config.out(fileOutputStream对象)
        //HttpClientUtil.upload(config);				//上传，需要调用config.files(文件路径数组)

        //如果指向看是否访问正常
        //String result3 = HttpClientUtil.head(config); // 返回Http协议号+状态码
        //int statusCode = HttpClientUtil.status(config);//返回状态码

    }
}