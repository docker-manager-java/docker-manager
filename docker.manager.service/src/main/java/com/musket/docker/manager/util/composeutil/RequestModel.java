package com.musket.docker.manager.util.composeutil;

import com.musket.docker.manager.util.httpclientutil.HttpClientUtil;
import com.musket.docker.manager.util.httpclientutil.builder.HCB;
import com.musket.docker.manager.util.httpclientutil.common.HttpConfig;
import com.musket.docker.manager.util.httpclientutil.common.HttpHeader;
import com.musket.docker.manager.util.httpclientutil.exception.HttpProcessException;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/27.
 */
public class RequestModel {

    public static Object doPost(String url,String jsondata) throws HttpProcessException {
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
                .ssl()        //https，支持自定义ssl证书路径和密码，ssl(String keyStorePath, String keyStorepass)
                .retry(5)        //重试5次
                ;

        HttpClient client = hcb.build();

        //插件式配置请求参数（网址、请求参数、编码、client）
        HttpConfig config = HttpConfig.custom()
                .headers(headers)    //设置headers，不需要时则无需设置
                .url(url)    //设置请求的url
                        //.map(map)	//设置请求参数，没有则无需设置
                .encoding("utf-8")//设置请求和返回编码，默认就是Charset.defaultCharset()
                        //.client(client)	//如果只是简单使用，无需设置，会自动获取默认的一个client对象
                        //.inenc("utf-8") //设置请求编码，如果请求返回一直，不需要再单独设置
                        //.inenc("utf-8")	//设置返回编码，如果请求返回一直，不需要再单独设置
                .json(jsondata)     //json方式请求的话，就不用设置map方法，当然二者可以共用。
                //.context(HttpCookies.custom().getContext()) //设置cookie，用于完成携带cookie的操作
                //.out(new FileOutputStream("保存地址"))		//下载的话，设置这个方法,否则不要设置
                //.files(new String[]{"d:/1.txt","d:/2.txt"})	//上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
                ;


        //使用方式：
        // String result1 = HttpClientUtil.get(config);	//get请求
        String result2 = HttpClientUtil.post(config);    //post请求
        return result2;
        //System.out.println(result1);

        //HttpClientUtil.down(config);					//下载，需要调用config.out(fileOutputStream对象)
        //HttpClientUtil.upload(config);				//上传，需要调用config.files(文件路径数组)

        //如果指向看是否访问正常
        //String result3 = HttpClientUtil.head(config); // 返回Http协议号+状态码
        //int statusCode = HttpClientUtil.status(config);//返回状态码
    }
}
