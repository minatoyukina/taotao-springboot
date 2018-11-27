package com.taotao.manager.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface PictureSerice {
    Map uploadPicture(MultipartFile uploadFile);
}
