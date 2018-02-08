package com.musket.docker.manager.context;

import com.musket.docker.manager.interfaces.PullManager;
import com.musket.docker.manager.vo.ResultInfo;

/**
 * Created by Administrator on 2018/2/7.
 */
public class PullContext {

    private PullManager pullManager;

    public void setPullManager(PullManager pullManager) {
        this.pullManager = pullManager;
    }

    public PullContext() {

    }
    public PullContext(PullManager pullManager) {
        this.pullManager = pullManager;
    }

    public ResultInfo ContextInterface(String name,String tag){
        return pullManager.doPullimange(name,tag);


    }
}
