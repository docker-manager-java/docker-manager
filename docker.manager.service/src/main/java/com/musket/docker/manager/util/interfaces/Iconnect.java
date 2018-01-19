package com.musket.docker.manager.util.interfaces;

import com.musket.docker.manager.util.vo.Url;

public interface Iconnect {
    /**
     *
     * @param url
     * @return  对应返回连接对象
     */
	public abstract Object creat(Url url);

    /**
     *
     * @param url
     * @return
     */
	public abstract boolean delete(Url url);

}
