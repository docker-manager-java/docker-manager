package com.musket.docker.manager.enums;

/**
 * Created by cc-man on 2018/2/7.
 */
public enum PullMode {


    //本地
    LOCAL {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String toString() {
            return "LOCAL";
        }

        @Override
        public String getChineseVaule() {
            return "本地";
        }

    },
    DOCKERHUB {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String toString() {
            return "DOCKERHUB";
        }

        @Override
        public String getChineseVaule() {
            return "DOCKERHUB";
        }

    },
    HARBOR {
        @Override
        public int getValue() {
            return 1;
        }

        @Override
        public String toString() {
            return "HARBOR";
        }

        @Override
        public String getChineseVaule() {
            return "HARBOR";
        }

    };

    public abstract int getValue();

    public abstract String toString();

    public abstract String getChineseVaule();

    /**
     * 根据枚举值获取枚举对象
     * */
    public static PullMode getUserLevel(int typeValue) {
        PullMode pullMode = null;

        for (int i = 0; i < PullMode.values().length; i++) {
            if (PullMode.values()[i].getValue() == typeValue) {
                pullMode = PullMode.values()[i];
                break;
            }
        }

        return pullMode;
    }

    /**
     * 根据枚举字符串表达式获取枚举对象
     * */
    public static PullMode getUserLevel(String typeString) {
        PullMode pullMode = null;

        for (int i = 0; i < PullMode.values().length; i++) {
            if (PullMode.values()[i].toString().equalsIgnoreCase(
                    typeString.trim())) {
                pullMode = PullMode.values()[i];
                break;
            }
        }

        return pullMode;
    }
}
