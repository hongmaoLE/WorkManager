package com.machine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: xzw
 * @Package: com.machine
 * @ClassName: RexMatcher
 * @Description: java类作用描述
 * @Author: simple-love
 * @CreateDate: 2019/3/31 17:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/31 17:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RexMatcher {
    public static String getMatcher(String regex, String source) {
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(source);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }
}
