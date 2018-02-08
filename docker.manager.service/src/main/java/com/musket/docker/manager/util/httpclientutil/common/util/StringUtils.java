package com.musket.docker.manager.util.httpclientutil.common.util;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理的工具类
 * @author jiaoyk 2012-04-20
 * @version 6.2.0
 * */
public class StringUtils {
    public static final Pattern FLOAT_PATTERN = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$");
    public static final Pattern INTEGER_PATTERN = Pattern.compile("^-?[1-9]\\d*$");
    public static final Pattern UINTEGER_INZERO_PATTERN = Pattern.compile("^\\+?[0-9][0-9]*$");
    public static final Pattern UINTEGER_OUTZERO_PATTERN = Pattern.compile("^\\+?[1-9][0-9]*$");
    /**
     * 将UTF-8编码的字符串进行解码, 该方法通常用与对Get请求参数进行解码
     *
     * @param str 被解码的字符串
     *
     * @return 解码后的字符串
     * @throws UnsupportedEncodingException
     * */
    public static String decodeString(String str) throws UnsupportedEncodingException
    {
        String strReturn = "";

        //weblogic下需要注释掉下面的行
        str=new String(str.getBytes("ISO_8859_1"), "UTF-8");
        strReturn=java.net.URLDecoder.decode(str,"UTF-8");

        return strReturn;
    }

    /**
     * 将字符串以UTF-8方式进行编码, 该方法通常用与服务端发送Get请求时,对参数进行 编码
     *
     * @param str 被编码的字符串
     *
     * @return 编码后的字符串
     * @throws UnsupportedEncodingException
     * */
    public static String encodeString(String str) throws UnsupportedEncodingException
    {
        String strReturn = "";

        strReturn = java.net.URLEncoder.encode(str, "UTF-8");

        return strReturn;
    }
    /**
     * 判断字符串内容是否是非零的正整数,该工具类常用来判断Servlet发送参数是否合法
     * @param strNumber 字符串类型的数值
     * @param isContainZero 是否包含零
     * @return true/false
     * */
    public static boolean isUInteger(String strNumber, boolean isContainZero)
    {
        boolean blnUInteger = false;

        Matcher matcher = null;
        if(isContainZero){
            matcher = UINTEGER_INZERO_PATTERN.matcher(strNumber);
        }else{
            matcher = UINTEGER_OUTZERO_PATTERN.matcher(strNumber);
        }

        blnUInteger = matcher.matches();

        return blnUInteger;
    }

    /**
     * 判断字符串内容是否是浮点型数字
     * @param strNumber 字符串类型的数字
     * @return boolean true表示是浮点型,false表示不是浮点型数据
     * */
    public static boolean isFloat(String strNumber){
        boolean blnFloat = false;

        Matcher matcher = FLOAT_PATTERN.matcher(strNumber);
        blnFloat = matcher.matches();

        return blnFloat;
    }

    /**
     * 判断字符串内容是否是非零的双精度浮点数
     *
     * @param strNumber 字符串型非零双精度浮点数
     *
     * @return boolean true表示是非零双精度浮点数
     * */
    public static boolean isUDouble(String strNumber)
    {
        boolean blnUDouble = false;

        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]*)$");
        Matcher matcher = pattern.matcher(strNumber);

        blnUDouble = matcher.matches();

        return blnUDouble;
    }



    /**
     * 判断字符串内容是否是整数
     * @param strNumber 字符串类型的数字
     * @return boolean true表示是整数,false表示不是整数
     * */
    public static boolean isInteger(String strNumber){
        boolean blnInt = false;

        Matcher matcher = INTEGER_PATTERN.matcher(strNumber);
        blnInt = matcher.matches();

        return blnInt;
    }


    /**
     * 生成UUID唯一标识字段
     * @return
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

