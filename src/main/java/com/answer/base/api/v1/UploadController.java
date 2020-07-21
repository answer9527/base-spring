package com.answer.base.api.v1;

import com.answer.base.exception.http.ParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/v1/upload")
@CrossOrigin(origins ="*")
public class UploadController {
    public final static String UPLOAD_PATH_PREFIX = "static/uploadFile/";
    @RequestMapping("/file")
    public String uploadFile(@RequestParam MultipartFile file, HttpServletRequest request){
        if(file.isEmpty()){
            throw new ParameterException(60001);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String format = sdf.format(new Date());
        String realPath = "src/main/resources/" + UPLOAD_PATH_PREFIX;
        String fileName = file.getOriginalFilename();
//        创建上传文件的文件夹
        File myFile = new File(realPath+format);
        if(!myFile.exists()){
            myFile.mkdirs();
        }
//        生成新的文件名
        String newName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."),fileName.length());
        try {
            //构建真实的文件路径
            File newFile = new File(myFile.getAbsolutePath() + File.separator + newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            file.transferTo(newFile);
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/" + format + newName;
            return filePath;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "上传失败";
    }
}
