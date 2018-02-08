package com.musket.docker.manager.interfaces;

import com.musket.docker.manager.enums.PullMode;
import com.musket.docker.manager.vo.ResultInfo;

/**
 * Created by cc-man on 2018/2/7.
 */
public interface PullManager {
    /**
     *
     * @param name
     * @param tag
     * @return
     */
    public ResultInfo doPullimange(String name,String tag);
}
