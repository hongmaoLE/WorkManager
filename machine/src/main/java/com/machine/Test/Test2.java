package com.machine.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: xzw
 * @Package: com.machine.Test
 * @ClassName: Test2
 * @Description: java类作用描述
 * @Author: simple-love
 * @CreateDate: 2019/3/31 17:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/31 17:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Test2 {

    public static String getMatcher(String regex, String source) {
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(source);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }
}
