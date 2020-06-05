package com.iluwatar.decorator;

/**
 * @Author: dream
 * @Date: 2020/6/4 22:27
 * @Description: 新增一层装饰器，便于扩展，可以选择自主选择不同的装饰器，这时候应该把它定义为借口还是抽象类呢 抽象类就实现 troll 接口则继承 troll
 * 目前我的理解是如果需要维护一个对象，则使用抽象类
 * 抽象类可以不实现接口的方法
 *
 */
public abstract class Decorator implements Troll {

    protected Troll decorated;

    public Decorator(Troll troll) {
        this.decorated = troll;
    }

    // 这个方法在抽象类就进行了实现，不增强
    @Override
    public int getAttackPower() {
        return 0;
    }
}
