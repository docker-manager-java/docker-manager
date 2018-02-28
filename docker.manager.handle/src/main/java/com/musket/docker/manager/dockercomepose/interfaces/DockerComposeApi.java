package com.musket.docker.manager.dockercomepose.interfaces;

/**
 * Created by cc-man on 2018/2/27.
 */
public interface DockerComposeApi {
    /**
     * creat composes
     * @param name
     * @param yml "version: '2'\nservices:\n  hello:\n    image: node:7-alpine\n    command: node -e '${COMMAND}'"
     * @param env "COMMAND=console.log(3*7)"
     * @return
     */
    public String creatCompose(String name ,String yml ,String env);
}
