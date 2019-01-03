package com.jnn.mylibrary.util;

import android.util.Log;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    /**
     * 创建文件
     *
     * @param fileName
     */
    public void creatFile(String fileName) {
        //新建一个File类型的成员变量，传入文件名路径。
        File mFile = new File(fileName);
        //判断文件是否存在，存在就删除
        if (mFile.exists()) {
            mFile.delete();
        }
        try {
            //创建文件
            mFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("创建文件异常....", e.getMessage());
        }
    }
}
