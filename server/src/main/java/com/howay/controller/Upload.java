package com.howay.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping(value = "/upload")
public class Upload {
	String path = "F:/temp/";
	
	@CrossOrigin
	@RequestMapping(value = "/article", method = { RequestMethod.POST })
	public String uploadArticle(@RequestBody String reqjoStr) {
		JSONObject reqjo = JSONObject.parseObject(reqjoStr);
		JSONObject rejo = new JSONObject();
		rejo.put("1", "ok");
		String html = reqjo.getString("html");
		String name = "first"+".html";
		System.out.println(name);
		System.out.println(html);
		File file = new File(path+name);
		if(!file.exists()) {
			try {
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(String.valueOf(html).getBytes());
		        fos.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			rejo.clear();
			rejo.put("9999", "file is already exist");
		}
		return rejo.toJSONString();
	}

}
