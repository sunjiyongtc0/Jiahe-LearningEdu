package sjy.jiahe.controller.apiController;


import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sjy.jiahe.entity.User.UserCollectionEntity;
import sjy.jiahe.resoult.Res;
import sjy.jiahe.service.User.UserCollectionService;

import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/utils")
public class utilController {

    @Autowired
    private UserCollectionService userCollectionService;


   private String  path= ClassUtils.getDefaultClassLoader().getResource("").getPath();
    //根据用户id,获取收藏图片的列表
    @PostMapping("/getImgs/{userId}")
    public Res takeCode(@PathVariable("userId") Long userId){
        List<UserCollectionEntity> luc= userCollectionService.findImgByUserId(userId);
        JSONArray ja=new JSONArray();
        for(UserCollectionEntity uc:luc){
            JSONObject jo=new JSONObject();
//            jo.put("url","https://img.yzcdn.cn/vant/leaf.jpg");
//            jo.put("status","uploading");
//            jo.put("message","上传中");
            jo.put("name",uc.getRemarks());
            jo.put("isImage",true);
            jo.put("deletable",true);
            ja.add(jo);
        }
        return Res.ok().put("fileList",ja);
    }
    //图片文件上传
    @PostMapping("/fileUpload")
    public Res fileUpload(@RequestParam MultipartFile file,@RequestHeader("userId") Long userId){
        System.out.println("RequestHeader");
        System.out.println(userId);
        String ImgName= UUID.randomUUID() +".jpg";
        try {
            File f= new File(path+"statics/img/"+ImgName);
            file.transferTo(f);
            String fileName = f.getName();
            UserCollectionEntity uc=new UserCollectionEntity();
            uc.setCreatTime(new Date());
            uc.setRemarks(ImgName);
            uc.setFlagId(0);
            uc.setTypeId(1);
            uc.setUserId(userId);
            userCollectionService.save(uc);
            return Res.ok().put("fileName",ImgName);
        }catch (Exception e){
            return Res.error("图片上传失败"+e.getMessage());
        }

    }

    //图片文件删除
    @PostMapping("/fileDel/{fileName}")
    public Res fileDel(@PathVariable("fileName") String  fileName){
        File f= new File(path+"statics/img/"+fileName);
        if(f.delete()) {
            userCollectionService.delByImgName(fileName);
            return Res.ok().put("fileName", "deldeldeldelde");
        }else{
            return Res.error("文件删除失败");
        }
    }
}

