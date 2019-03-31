package com.machine;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: xzw
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Description: java类作用描述
 * @Author: simple-love
 * @CreateDate: 2019/3/2 13:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/2 13:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@WebServlet("/Servlet")
public class Upload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String savePath = this.getServletContext().getRealPath("/machine");
        //liunx  路径
        //String savePath = File.separator + "root" + File.separator + "doc";
        File parent = new File(savePath);
        if (!parent.mkdir()) {
            parent.mkdir();
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        //取出文件名和文件
        String fileName = null;
        FileItem oriitem = null;
        try {
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                if (item.isFormField()) {
                    continue;
                }
                fileName = item.getName();
                oriitem = item;
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        String ele[] = fileName.split("-");
        String rex = "[\\u4e00-\\u9fa5]{2,3}\\d{5}-\\d{10}-[\\u4e00-\\u9fa5]{2,4}-作业\\d{1}";
        Map<String, Object> map = new HashMap<String, Object>();
//        if (ele.length != 4) {
//            map.put("code", "lock");
//            try {
//                ResponseUtil.write(response, new Gson().toJson(map));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return;
//        }
        //System.out.println(Pattern.compile(rex).matcher(ele[1]).matches());
        if (RexMatcher.getMatcher(rex, fileName) != null) {
            String wordFile = ele[3].substring(0, 3);
            File filePath = new File(savePath + File.separator + wordFile);
            if (!filePath.exists()) {
                filePath.mkdir();
            }
            try {
                oriitem.write(new File(filePath + File.separator + fileName));
                map.put("code", "ok");
                ResponseUtil.write(response, new Gson().toJson(map));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            map.put("code", "error");
            try {
                ResponseUtil.write(response, new Gson().toJson(map));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get");
    }


}
