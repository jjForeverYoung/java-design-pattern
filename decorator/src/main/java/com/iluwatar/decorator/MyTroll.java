package com.iluwatar.decorator;

/**
 * @Author: dream
 * @Date: 2020/6/4 22:35
 * @Description:
 */
public class MyTroll extends Decorator {

    // 思考这里super的使用
    public MyTroll(Troll troll) {
        super(troll);
    }

    @Override
    public void attack() {
        decorated.attack();
        System.out.println("装饰了攻击");
    }

//    @Override
//    public int getAttackPower() {
//        return 10;
//    }

    @Override
    public void fleeBattle() {
        decorated.fleeBattle();
        System.out.println("装饰了反击");
    }
}
