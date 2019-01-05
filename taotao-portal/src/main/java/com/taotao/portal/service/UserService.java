package com.taotao.portal.service;

import com.taotao.common.pojo.TbUser;

public interface UserService {
    TbUser getUserByToken(String token);
}
