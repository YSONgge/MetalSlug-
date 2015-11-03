package com.example.yeye.metalslug.comp;

import android.view.ViewManager;
import android.graphics.Canvas;

import java.util.ArrayList;


/**
 * Created by yeye on 2015/11/2.
 */

public class Monster {

    public static final int TYPE_BOMB = 1;
    public static final int TYPE_FLY = 2;
    public static final int TYPE_MAN = 3;

    //定义怪物类型的成员变量
    private int type = TYPE_BOMB;
    //定义怪物的X，Y的做标的成员变量
    private int x = 0;
    private int y = 0;
    //是否死亡
    private boolean isDie = false;
    //绘制怪物图片左上方的X，Y坐标
    private int startX = 0;
    private int startY = 0;
    //绘制怪物图片右下方的X，Y坐标
    private int endX = 0;
    private int endY = 0;
    //控制动画刷新的速度；
    int drawCount = 0;
    //定义当前正在绘制怪物动画的第几帧的变量
    private int drawindex = 0;
    //死亡后动作为0
    private int dieMaxDrawCount = Integer.MAX_VALUE;
    //定义怪物射出的子弹
    private List<Bullet> bulletList = new ArrayList<>();

    //怪物的构造器
    public Monster(int type) {
        this.type = type;

        //根据怪物大小初始化X，Y的位置
        //如果怪物时炸弹（BOMB）和敌人（MAN）
        //怪物的Y与玩家一样
        if (type == TYPE_BOMB || type == TYPE_MAN) {
            y = Player.Y_DEFALUT;
        } else if (type == TYPE_FLY) {
            y = ViewManager.SCREEN_HEIGHT * 50 / 100 - Util.rand((int) (ViewManager.scale * 100));
        }
        //x的坐标
        x = ViewManager.SCREEN_WIDTH + Util.rand(ViewManager.SCREEN_WIDTH >> 1) - (ViewManager.SCREEN_WIDTH >> 2 );
    }

    //绘制怪物的方法
    public void draw(Canvas canvas){
        if (canvas == null){
            return;
        }
        //选择图片
        switch (type){
            case TYPE_BOMB:
                draw(canvas,isDie ? ViewManager.bomb2Image:ViewManager.bombImage);
                break;
            case TYPE_FLY:
                draw(canvas,isDie : ViewManager.flyDieImage : ViewManager.flyImage);
                break;
            case TYPE_MAN:
                draw(canvas,isDie : ViewManager.manDieImage : ViewManager.manImage);
                break;
            default:
                break;
        }
    }
    //根据怪物动画帧图画来绘制怪物动画
}
