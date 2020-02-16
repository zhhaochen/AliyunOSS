package com.zhanghc.aliyunoss.controller;

import com.aliyun.oss.model.Bucket;
import com.zhanghc.aliyunoss.utils.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class AliyunOSSController {
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    @GetMapping(value = {"/", "/index"})
    public String test(HttpServletRequest request,
                       Model model){
        HttpSession session = request.getSession();
        List<Bucket> buckets = aliyunOSSUtil.listBuckets();
        model.addAttribute("buckets", buckets);
        model.addAttribute("mode", "upload");
        return "index";
    }

    @GetMapping("/upload")
    public String uploadPic(Model model){
        model.addAttribute("mode", "upload");
        return "index";
    }

    @GetMapping("/management")
    public String management(Model model){
        model.addAttribute("mode", "management");
        return "index";
    }

    @GetMapping("/uploadtest")
    public String uploadtest(){
        return "index";
    }

    @PostMapping("/uploadPic")
    public String uploadPic(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
                            HttpServletRequest  request) throws IOException {
        if (multipartFile != null){
            InputStream inputStream = multipartFile.getInputStream();
            String originalFilename = multipartFile.getOriginalFilename();
            aliyunOSSUtil.putObject(originalFilename, inputStream);
        }else {
            System.out.println("null");
        }
        return "index";
    }

    @ResponseBody
    @PostMapping("/uploadMultiPic")
    public String uploadPic(@RequestParam(value = "uploadFile", required = false)MultipartFile[] multipartFiles) throws IOException {
        String originalFilename;
        InputStream inputStream;
        if (multipartFiles!=null){
            for (MultipartFile multipartFile: multipartFiles){
                originalFilename = multipartFile.getOriginalFilename();
                inputStream = multipartFile.getInputStream();
                aliyunOSSUtil.putObject(originalFilename, inputStream);
            }
        }
        return "上传成功";
    }
}
