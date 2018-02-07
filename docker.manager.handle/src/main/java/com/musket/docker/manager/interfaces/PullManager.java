package com.musket.docker.manager.interfaces;

import com.manager.docker.common.ResultInfo;
import com.musket.docker.manager.enums.PullMode;

/**
 * Created by cc-man on 2018/2/7.
 */
public interface PullManager {
    /**
     *
     * @param name
     * @param tag
     * @param pullMode  pull type : loacl dockerhub harbor
     * @return
     */
    public ResultInfo doPullimange(String name,String tag,PullMode pullMode);
}
