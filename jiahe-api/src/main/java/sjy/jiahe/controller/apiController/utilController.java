package sjy.jiahe.controller.apiController;


import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sjy.jiahe.resoult.Res;

import java.io.File;

@RestController
@RequestMapping("/api/utils")
public class utilController {
    //"C:\\Users\\Administrator\\Desktop\\";
   private String  path= ClassUtils.getDefaultClassLoader().getResource("").getPath();
    //根据用户id,获取收藏图片的列表
    @PostMapping("/getImgs/{userId}")
    public Res takeCode(@PathVariable("userId") Long userId){
        JSONArray ja=new JSONArray();
        for(int i=0;i<4;i++){
            JSONObject jo=new JSONObject();
            jo.put("url","https://img.yzcdn.cn/vant/leaf.jpg");
//            jo.put("status","uploading");
//            jo.put("message","上传中");
            jo.put("name","图片"+i);
            jo.put("isImage",true);
            jo.put("deletable",true);
            ja.add(jo);
        }
        return Res.ok().put("fileList",ja);
    }
    //图片文件上传
    @PostMapping("/fileUpload")
    public Res fileUpload(@RequestParam MultipartFile file){
        System.out.println(path);
        String ImgName= UUID.randomUUID() +".jpg";
        try {
            File f= new File(path+"statics/img/"+ImgName);
            file.transferTo(f);
            String fileName = f.getName();
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
            return Res.ok().put("fileName", "deldeldeldelde");
        }else{
            return Res.error("文件删除失败");
        }
    }
}

