package com.SmartContactManager.Scm10.scm.services.impl;

import java.io.IOException;
import java.util.UUID;

import com.SmartContactManager.Scm10.scm.helper.AppConstants;
import com.SmartContactManager.Scm10.scm.services.ImageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile contactImage, String filename) {
        //this will return url;
        //String filename=UUID.randomUUID().toString();
        try{
            byte[] data=new byte[contactImage.getInputStream().available()];
            contactImage.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap("public_id",filename));
            return this.getUrlFromPublicId(filename);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }  
    }

    @Override
    public String getUrlFromPublicId(String publicId) {
     return cloudinary.url().transformation(new Transformation<>().width(AppConstants.CONTACT_IMAGE_WIDTH).height(AppConstants.CONTACT_IMAGE_HEIGHT).crop(AppConstants.CONTACT_IMAGE_CROP)).generate(publicId);
    }
 
}
