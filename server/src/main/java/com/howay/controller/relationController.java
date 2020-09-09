package com.howay.controller;

import com.alibaba.fastjson.JSONObject;
import com.howay.service.RelationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 二级评论控制层
 * 
 * @author howay
 * @since 2020/9/8
 */

@RestController
@RequestMapping(value = "/relation")
public class relationController {
    @Autowired
    RelationService relationService;

    @CrossOrigin
	@RequestMapping(value = "/getMessages", method = { RequestMethod.POST })
	public String getMessages(@RequestBody JSONObject req) {
        int u_id = req.getIntValue("u_id");
        return relationService.getMessages(u_id);
    }

    @CrossOrigin
	@RequestMapping(value = "/getReplies", method = { RequestMethod.POST })
	public String getReplies(@RequestBody JSONObject req) {
        int u_id = req.getIntValue("u_id");
        return relationService.getReplies(u_id);
    }

}
