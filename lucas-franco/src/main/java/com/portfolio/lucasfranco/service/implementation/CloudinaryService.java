package com.portfolio.lucasfranco.service.implementation;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.*;

@Service
public class CloudinaryService {
    Cloudinary cloudinary;
    private Map<String, String> valuesMap= new HashMap<>();

    public CloudinaryService() {
        valuesMap.put(CLOUD_NAME, NAME_CLOUD);
        valuesMap.put(API_KEY, KEY_API);
        valuesMap.put(API_SECRET, SECRET_API);
        cloudinary=new Cloudinary(valuesMap);
    }

    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result= cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;

    }
    public Map delete(String id) throws IOException {
        Map result= cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }
    public File convert(MultipartFile multipartFile) throws IOException {
        File file= new File(multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return file;
    }
}
