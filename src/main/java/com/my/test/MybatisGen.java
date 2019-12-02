package com.my.test;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;

/**
 * @Description:
 * @Author: wangqiang
 * @Date:2019/3/8 14:39
 */
public class MybatisGen {
    public static void generator() throws Exception{
        ArrayList<String> list = new ArrayList<>();
        File file = new File("src/main/resources/mybatis-gen.xml");
        ConfigurationParser cp = new ConfigurationParser(list);
        Configuration config = cp.parseConfiguration(file);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, list);
        myBatisGenerator.generate(null);
    }

    public static void main(String[] args) throws Exception {
        generator();
    }
}
