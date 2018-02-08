package com.musket.docker.manager.util.httpclientutil.common.util;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
/**
 * 利用jaxb实现Xml字符串和对象之间的转换关系
 * @author dongwenfeng
 */
public class JaxbUtil {
    /***
     * 将对象转换为XMl字符串
     * @param bean
     * @return
     * @throws JAXBException
     */
    public static <T> String toXMl(T bean) throws JAXBException {
        String result="";
        JAXBContext jaxbContext = JAXBContext.newInstance(bean.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        StringWriter writer=new StringWriter();
        marshaller.marshal(bean, writer);
        result=writer.toString();
        return result;
    }

    /***
     * 将XMl字符串转换为对象
     * @param strXML
     * @param clzz
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromXMl(String strXML,Class<T> clzz) throws JAXBException {
        T obj=null;
        JAXBContext jaxbContext = JAXBContext.newInstance(clzz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader=new StringReader(strXML);
        obj = (T) unmarshaller.unmarshal(reader);
        return obj;
    }
}