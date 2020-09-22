package com.howay.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping(value = "/sunway/image")
public class ImgController {

	// 生产
	public static final String BASE_DIR = "";
	// public static final String BASE_DIR = "C:\\saveZipdirectory\\sunweiImg\\";
	public static final String BASE_UPLOAD_DIR = "";
	private final String IMG_URL_PATH = "";

	@CrossOrigin
	@RequestMapping(value = "/upload", method = { RequestMethod.POST, RequestMethod.GET })
	public String upload(HttpSession session, MultipartFile file) throws IOException {

		InputStream is = file.getInputStream();
		UUID uuid = UUID.randomUUID();
		File of = new File(BASE_DIR + uuid);
		of.createNewFile();
		FileOutputStream fos = new FileOutputStream(of);
		fos.write(file.getBytes());
		fos.flush();
		fos.close();
		is.close();

		String originalFileName = file.getOriginalFilename();
		String type = originalFileName.substring(originalFileName.lastIndexOf("."));
		String newFileName = uuid + type;
		File temp = new File(BASE_UPLOAD_DIR + "temp" + type);
		temp.createNewFile();
		fos = new FileOutputStream(temp);
		fos.write(file.getBytes());
		fos.flush();
		fos.close();
		is.close();
		//ImgCompressUtil.compress(temp, 1f, 0.5f, BASE_UPLOAD_DIR + newFileName);
		temp.delete();
		JSONObject jo = new JSONObject();
		jo.put("imgId", uuid.toString());
		jo.put("img_url", IMG_URL_PATH + newFileName.toString());
		jo.put("code", 0);
		jo.put("msg", "SUCCESS");
		JSONObject jo1 = new JSONObject();
		jo1.put("src", "/sunway/image/get/" + uuid.toString());
		jo.put("data", jo1);
		return jo.toJSONString();
	}

	
}
