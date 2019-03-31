package com.machine.Test;


import com.machine.ZipUtil;

/**
 * @ProjectName: xzw
 * @Package: com.machine.Test
 * @ClassName: Test1
 * @Description: java类作用描述
 * @Author: simple-love
 * @CreateDate: 2019/3/6 22:15
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/6 22:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Test1 {
    public static void main(String[] args) throws Exception {
        //创建带压缩文件路径和压缩文件路径
        String oriPath = "D:\\即可11602-1604240811-严庭锐-作业1";
        String desPath = "D:\\ss.zip";
        //ZipUtil zipUtil = new ZipUtil();
        ZipUtil.compressFile(oriPath, desPath);

    }
}
