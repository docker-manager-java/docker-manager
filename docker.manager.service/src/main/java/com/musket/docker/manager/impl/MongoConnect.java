package com.musket.docker.manager.impl;

import com.musket.docker.manager.impl.DbConnect;
import com.musket.docker.manager.vo.Url;

public class MongoConnect extends DbConnect {

    @Override
    public Object creat(Url url) {
        return null;
    }

    @Override
    public boolean delete(Url url) {
        return false;
    }
}
