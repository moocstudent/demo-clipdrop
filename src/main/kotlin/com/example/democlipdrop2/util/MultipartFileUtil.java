package com.example.democlipdrop2.util;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

@NoArgsConstructor
@Slf4j
public class MultipartFileUtil {

    public static MultipartFile toMultipartFile(byte[] bytes,String fileName){
        final FileItem fileItem = createFileItem(new ByteArrayInputStream(bytes),fileName);
        return new CommonsMultipartFile(fileItem);
    }

    public static File toFile(MultipartFile multipartFile){
        //文件上传前的名称
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(fileName);
        OutputStream out = null;
        try{
            //获取文件流，以文件流的方式输出到新文件
            out = new FileOutputStream(file);
            byte[] ss = multipartFile.getBytes();
            for(int i = 0; i < ss.length; i++){
                out.write(ss[i]);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 操作完上的文件 需要删除在根目录下生成的文件
            File f = new File(file.toURI());
//            if (f.delete()){
//                log.info("删除成功");
//            }else {
//                log.error("删除失败");
//            }
            return f;
        }
    }

    private static FileItem createFileItem(InputStream is,String fileName){
        return createFileItem(is,"file",fileName);
    }
    
    private static FileItem createFileItem(InputStream is,String fieldName,String fileName){
        final DiskFileItemFactory fac = new DiskFileItemFactory(10240,null);
        FileItem fileItem = fac.createItem(fieldName,"multipart/form-data",true,fileName);
        final OutputStream fileItemOutStream;
        try {
            fileItemOutStream = fileItem.getOutputStream();
        } catch (IOException e) {
            log.error("获取FileItem输出流异常:{}",e.getMessage(),e);
            throw new RuntimeException("系统异常");
        }

        try {
            IOUtils.copy(is,fileItemOutStream);
        } catch (IOException e) {
            log.error("写入FileItem异常:{}",e.getMessage(),e);
            throw new RuntimeException("系统异常");
        }
        return fileItem;
    }

}
