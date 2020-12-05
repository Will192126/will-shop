package com.will.willshop.route;

public interface RouteFlag {
    int FLAG_LOGIN = 0x01;
    int FLAG_AUTH = FLAG_LOGIN << 1;
    int FLAG_VIP = FLAG_AUTH << 1;
}
