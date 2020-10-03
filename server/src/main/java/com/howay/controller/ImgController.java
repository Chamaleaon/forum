package com.howay.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.howay.util.PathsUtil;

@RestController
@RequestMapping(value = "/img")
public class ImgController {
	// 生产
	public static final String BASE_DIR = PathsUtil.IMG_DIR;
	public static final String IMG_URL = PathsUtil.IMG_URL;

	@CrossOrigin
	@RequestMapping(value = "/upload", method = { RequestMethod.POST, RequestMethod.GET })
	public String upload(HttpSession session, MultipartFile file) throws IOException {
		//System.out.println("getName:" + file.getOriginalFilename());
		//System.out.println("getContentType:" + file.getContentType());
		//System.out.println("getSize:" + file.getSize());
		InputStream is = file.getInputStream();
		UUID uuid = UUID.randomUUID();//生成唯一标识
		File dir = new File(BASE_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String originalFileName[] = file.getOriginalFilename().split("\\.");
		String fileName = uuid + "." + originalFileName[1];
		File temp = new File(BASE_DIR + fileName);
		FileOutputStream fos = new FileOutputStream(temp);
		temp.createNewFile();
		fos.write(file.getBytes());
		fos.flush();
		fos.close();
		is.close();
		JSONObject jo = new JSONObject();
		jo.put("url", IMG_URL + fileName);
		return jo.toJSONString();
	}

}
