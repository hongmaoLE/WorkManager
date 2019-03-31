package com.machine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @ProjectName: xzw
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Description: java类作用描述
 * @Author: simple-love
 * @CreateDate: 2019/3/3 21:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/3 21:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@WebServlet("/download")
public class download extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setHeader("content-type", "application/octet-stream");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        String task = request.getParameter("task");
        System.out.println(task);

        //String basePath = this.getServletContext().getRealPath("/machine");
        //liunx 路径
        String basePath = File.separator + "root" + File.separator + "doc";
        //基础文件路径 且是源文件路径
        String filePath = basePath + File.separator + task;
        //判断是否存在不存在返回
        File sourceFile = new File(filePath);
        String script = "<script>" +
                "        alert(\"你输入参数有误\");" +
                "    </script>";
        if (!sourceFile.exists()) {
            response.getWriter().write(script);
            return;
        }
        //定义zip文件名
        String zipFileName = basePath + File.separator + task + ".zip";
        //先判断Zip文件是否存在  存在删除
        File zipFile = new File(zipFileName);
        if (!zipFile.exists()) {
            zipFile.delete();
        }
        //压缩文件
        ZipUtil.compressFile(filePath, zipFileName);
        //得到下载文件
        File file = new File(zipFileName);
        String downFileName = task + ".zip";
        String userAgent = (String) request.getHeader("USER-AGENT");
        //火狐浏览器还是乱码
        if (userAgent.toLowerCase().contains("firefix")) {
            response.setHeader("Content-disposition", "attachment;filename*=utf-8'zh_cn'" + URLEncoder.encode(downFileName, "UTF-8"));
        } else {
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(downFileName, "UTF-8"));
        }

        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
        // 循环将输入流中的内容读取到缓冲区中
        while ((len = in.read(buffer)) > 0) {
            // 输出缓冲区内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        // 关闭文件流
        in.close();
        // 关闭输出流
        out.close();

    }

    /**
     * 根据浏览器的不同进行编码设置，返回编码后的文件名
     */
    public String getFilename(HttpServletRequest request,
                              String filename) throws Exception {
        // IE不同版本User-Agent中出现的关键词
        String[] IEBrowserKeyWords = {"MSIE", "Trident", "Edge"};
        // 获取请求头代理信息
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : IEBrowserKeyWords) {
            if (userAgent.contains(keyWord)) {
                //IE内核浏览器，统一为UTF-8编码显示
                return URLEncoder.encode(filename, "UTF-8");
            }
        }
        //火狐等其它浏览器统一为ISO-8859-1编码显示
        return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
    }
}
