package com.answer.base.api.v1;

import com.answer.base.core.interceptors.ScopeLevel;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${upload.path}")
    private String base_path;
    public final static String UPLOAD_PATH_PREFIX = "/uploadFile/";
    @RequestMapping("/file")
    @ScopeLevel(0)
    public Msg uploadFile(@RequestParam MultipartFile file){
        if(file.isEmpty()){
            throw new ParameterException(60001);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String format = sdf.format(new Date());
        String realPath = base_path + UPLOAD_PATH_PREFIX;
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
//            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/" + format + newName;
            String filePath ="/uploadFile/" + format + newName;
            return ResultUtil.success(filePath,"上传成功");
        }catch (Exception e){
            e.printStackTrace();
            throw new ParameterException(60002);
        }

    }
}
