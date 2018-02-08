package com.musket.docker.manager.controller;


import com.musket.docker.manager.context.PullContext;
import com.musket.docker.manager.enums.PullMode;
import com.musket.docker.manager.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by cc-man on 2018/2/7.
 */
@Controller
@RequestMapping("/pull")
public class PullController {

       private PullContext PullFromDockerHubImpl;

       private PullContext PullFromHarborImpl;

       private PullContext PullFromLocalImpl;

       private  PullContext PullFromRegisterImpl;


    @ResponseBody
    @RequestMapping(value = "/log", method = RequestMethod.GET)
  public void yy(){
      System.out.println("kakak");
  }

    public void setPullFromDockerHubImpl(PullContext pullFromDockerHubImpl) {
        PullFromDockerHubImpl = pullFromDockerHubImpl;
    }

    public void setPullFromHarborImpl(PullContext pullFromHarborImpl) {
        PullFromHarborImpl = pullFromHarborImpl;
    }

    public void setPullFromLocalImpl(PullContext pullFromLocalImpl) {
        PullFromLocalImpl = pullFromLocalImpl;
    }

    public void setPullFromRegisterImpl(PullContext pullFromRegisterImpl) {
        PullFromRegisterImpl = pullFromRegisterImpl;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResultInfo doPull(HttpServletRequest request,
                             HttpServletResponse response){
        ResultInfo result = new ResultInfo();

            String name = request.getParameter("name");
            String tag = request.getParameter("tag");
            PullMode pullMode =PullMode.valueOf(request.getParameter("pullmode")) ;
        PullFromLocalImpl.toString();
        /*if (true) {
            try {
                    switch (pullMode.getValue()){
                        case 1: result = PullFromLocalImpl.ContextInterface(name,tag);
                        case 2: result = PullFromDockerHubImpl.ContextInterface(name,tag);
                        case 3: result = PullFromHarborImpl.ContextInterface(name,tag);
                        case 4: result = PullFromRegisterImpl.ContextInterface(name,tag);
                    }

                    result.setSuccess(true);
            } catch (Exception e) {
                result.setMessage("Pull a failure:" + e.getMessage());
            }
        } else {
            result.setMessage("Parameter error" );
        }*/


        return result;
    }

}
