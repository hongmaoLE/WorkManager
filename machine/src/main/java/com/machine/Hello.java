package com.machine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: xzw
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Description: java类作用描述
 * @Author: simple-love
 * @CreateDate: 2019/3/2 14:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/2 14:40
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get hello");
    }
}
