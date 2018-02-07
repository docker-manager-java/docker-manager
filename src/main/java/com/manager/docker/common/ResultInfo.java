package com.manager.docker.common;

/**
 * Created by Administrator on 2018/2/7.
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 返回操作结果的对象模型
 * @author dongwenfeng
 *
 */
@XmlRootElement(name="ResultInfo")
@JsonPropertyOrder({ "success", "message","data" })
public class ResultInfo {

    private boolean success;
    private String message;
    private Object data;

    public ResultInfo(){

    }

    @JsonProperty
    @XmlAttribute
    public boolean getSuccess() {
        return success;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }
    @XmlAttribute
    @JsonProperty
    public String getMessage() {
        return message;
    }

    public void setMessage(String errorMsg) {
        this.success =false;
        this.message = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.success =true;
        this.data = data;
    }
}