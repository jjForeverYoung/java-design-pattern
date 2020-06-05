package com.iluwatar.builder;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @Author: dream
 * @Date: 2020/6/5 21:07
 * @Description: 使用@Builder注解就可以实现建造者设计模式，还要加上@Getter外部才能访问,@Builder的文档写得十分详细
 */

public class LombokDemo {
    public static void main(String[] args) {
        // 生成的builder()是一个静态方法
        BuilderDemo dream = BuilderDemo.builder().name("dream").age("18").build();
        System.out.println(dream.getAge());
    }

}

@ToString
@Getter
@Builder
class BuilderDemo{
    private String name;
    private String age;
}
