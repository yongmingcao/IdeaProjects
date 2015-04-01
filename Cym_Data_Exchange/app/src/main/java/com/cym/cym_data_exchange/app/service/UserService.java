package com.cym.cym_data_exchange.app.service;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public interface UserService {
    public void login(String LoginName, String LoginPsw)throws Exception;
    public void register(String LoginName,List<String> interests) throws Exception;
}
