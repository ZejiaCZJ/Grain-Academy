package com.grainacademy.codegenerator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/grainacademy", "root", "05190602Fc@")
                .globalConfig(builder -> {
                    builder.author("ZejiaCai") // 设置作者
                            .enableSpringdoc() // 开启 springdoc 模式
                            .dateType(DateType.ONLY_DATE)
                            .outputDir("B:\\Side Project\\Grain Academy\\backend\\services\\service_edu\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder ->
                        builder.parent("com.grainacademy.backend") // 设置父包名
                                .moduleName("eduservice") // 设置父包模块名
                )
                .strategyConfig(builder ->
                        builder.addInclude("edu_teacher") // 设置需要生成的表名
                                .addTablePrefix("edu_") // 设置过滤表前缀
                                .entityBuilder()
                                .idType(IdType.ASSIGN_ID)
                                .enableLombok()
                )
                .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
