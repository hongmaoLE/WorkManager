package com.machine;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;


public class ZipUtil {
    /**
     * 压缩文件
     *
     * @param srcFileName:要压缩的文件或目录
     * @param zipFileName:压缩后的文件名
     */
    public static void compressFile(String srcFileName, String zipFileName) {
        File srcFile = new File(srcFileName);

        if (!srcFile.exists()) {
            return;
        }
        File zipFile = new File(zipFileName);

        if (!zipFile.exists()) {
            zipFile.getParentFile().mkdirs();//如果文件的父目录不存在，创建父目录
        }
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (srcFile.isFile()) {
            //单个文件
            compressFile(srcFile, zos, "");
        } else {
            //压缩目录
            String baseDir = srcFileName + File.separator;
            compressDirectory(srcFile, zos, baseDir);
        }
        try {
            zos.flush();
            zos.close();//zip使用完后，一定要关闭，否则在解压时会报文件损坏的错误
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 压缩文件
     *
     * @param srcFile
     * @param zos
     * @param baseDir
     */
    private static void compressFile(File srcFile, ZipOutputStream zos, String baseDir) {
        //压缩单个文件
        if ("".equals(baseDir)) {
            try {
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //压缩目录下的文件是，写入文件时，要添加在目录下的子目录名
        else {
            try {
                zos.putNextEntry(new ZipEntry(baseDir + srcFile.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            //创建缓冲流读取文件，提高速度
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(srcFile));
            byte[] buffer = new byte[2048];
            int len;
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                //利用zip输出流写入文件
                zos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩目录
     *
     * @param srcFile
     * @param zos
     * @param baseDir
     */
    private static void compressDirectory(File srcFile, ZipOutputStream zos, String baseDir) {
        File[] files = srcFile.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                /**
                 * 压缩文件
                 */
                compressFile(file, zos, baseDir);
            } else {
                /**
                 * 压缩目录
                 */
                compressDirectory(file, zos, baseDir + file.getName() + File.separator);
            }
        }
    }

    public static void main(String[] args) {


        compressFile("D:" + File.separator + "test.txt", "D:" + File.separator + "testFile.zip");
        compressFile("D:" + File.separator + "android-21", "D" + File.separator + "testDirectory.zip");

    }
}