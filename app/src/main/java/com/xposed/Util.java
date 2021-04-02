package com.xposed;

import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

public class Util {
    protected static final Gson gson = new Gson();

    public static boolean isExternalStorageStateMounted(){
        String storageState = Environment.getExternalStorageState();
        return
                Environment.MEDIA_MOUNTED.equals(storageState)||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(storageState)
                ;
    }

    public static File fileAtExternalStorageDirectory(String fileName){
        File file = new File(filePathAtExternalStorageDirectory(fileName));
        return file;
    }

    public static String filePathAtExternalStorageDirectory(String fileName){
        String filePath=Environment.getExternalStorageDirectory()+ File.separator+fileName;
        return filePath;
    }
//    public static class Review{}
//    private static final Type REVIEW_TYPE = new TypeToken<List<Review>>() {}.getType();
    private static final Type CONFIG_ENTITY_TYPE = new TypeToken<ConfigEntity>() {}.getType();

    public static ConfigEntity fetchConfig(String filename) throws FileNotFoundException {
        if (!isExternalStorageStateMounted()){
            return null;
        }

//        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(filePathAtExternalStorageDirectory(filename)));
//        List<Review> data = gson.fromJson(reader, REVIEW_TYPE); // contains the whole reviews list
        ConfigEntity configEntity = gson.fromJson(reader, CONFIG_ENTITY_TYPE); // contains the whole reviews list
        return configEntity;
    }


    public static boolean packageMatch(String packageFromOS, ConfigEntity configEntity){
        if(null==packageFromOS || configEntity==null){
            return false;
        }
        for(String myPackage:configEntity.getPackageList()){
            if( packageFromOS.contains(myPackage) || myPackage.contains(packageFromOS) ){
                return true;
            }
        }
        return false;
    }
}
