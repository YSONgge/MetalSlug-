package com.example.yeye.metalslug.comp;

import android.graphics.Bitmap;

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
    private int drawIndex = 0;
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
        x = ViewManager.SCREEN_WIDTH + Util.rand(ViewManager.SCREEN_WIDTH >> 1) - (ViewManager.SCREEN_WIDTH >> 2);
    }

    //绘制怪物的方法
    public void draw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        //选择图片
        switch (type) {
            case TYPE_BOMB:
                draw(canvas, isDie ? ViewManager.bomb2Image : ViewManager.bombImage);
                break;
            case TYPE_FLY:
                draw(canvas, isDie:ViewManager.flyDieImage:ViewManager.flyImage);
                break;
            case TYPE_MAN:
                draw(canvas, isDie:ViewManager.manDieImage:ViewManager.manImage);
                break;
            default:
                break;
        }
    }

    //根据怪物动画帧图画来绘制怪物动画
    public void drawAni(Canvas canvas, Bitmap[] bitmapArr) {
        if (canvas == null) {
            return;
        }
        if (bitmapArr == null) {
            return;
        }
        if (isDie && dieMaxDrawCount == Integer.MAX_VALUE) {
            dieMaxDrawCount = bitmapArr.length;
        }
        drawIndex = drawIndex % bitmapArr.length;
        //获取当前绘制的动画帧对应的帧图像
        Bitmap bitmap = bitmapArr[drawIndex];
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        int drawX = x;
        //对怪物的位置X坐标微调
        if (isDie) {
            if (type == TYPE_BOMB) {
                drawX = x - (int) (ViewManager.scale * 50);
            } else if (type == TYPE_MAN) {
                drawX = x + (int) (ViewManager.scale * 50);
            }
        }
        //Y坐标微调
        int drawY = y - bitmap.getHeight();
        //绘制怪物的动画帧
        Graphics.drawMatrixImage(canvas, bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), Graphics.TRANS_NONE, drawX, drawY, 0, Graphics.TIMES_SCALE);
        startX = drawX;
        startY = drawY;
        endX = startX + bitmap.getWidth();
        endY = startY + bitmap.getHeight();
        drawCount++;
        //6，4，用于控制人，飞机的子弹速度
        if (drawCount >= (type == TYPE_MAN ? 6 : 4)) {
            //怪物是人，第三帧子弹飞
            if (type == TYPE_MAN && drawIndex == 2) {
                addBullet();
            }
            //飞机
            if (type == TYPE_FLY && drawIndex == bitmapArr.length - 1) {
                addBullet();
            }
            drawIndex++;
            drawCount = 0;
        }
        //死亡动画播一次，dieMaxDrawCount减一
        if (isDie) {
            dieMaxDrawCount--;
        }
        //绘制子弹
        drawBullet(canvas);
    }

    //判断是否被打中
    public boolean isHurt(int x, int y) {
        return x >= startX && x <= = endX && y >= startY && y < endY;
    }

    //实现怪物发射子弹
    public int getBulletType() {
        switch (type) {
            case TYPE_BOMB:
                return 0;
            case TYPE_MAN:
                return Bullet.BULLET_TYPE_2;
            case TYPE_FLY:
                return Bullet.BULLET_TYPE_3;
            default:
                return 0;
        }
    }

    //定义发射子弹
    public void addBullet() {
        int bulletType = getBulletType();
        //如果没有子弹
        if (bulletType <= 0) {
            return;
        }
        //计算子弹坐标
        int drawX = x;
        int drawY = y;
        //飞机则重新计算Y的坐标
        if (type == TYPE_FLY) {
            drawY == y - (int) (ViewManager.scale * 60);
        }
        //创建子弹对象
        Bullet bullet = new Bullet(bulletType, drawX, drawY, Player.DIR_LEFT);
        //添加子弹到怪物发射的子弹集
        bulletList.add(bullet);
    }

    // 更新角色的位置：将角色的X坐标减少shift距离（角色左移）
    // 更新所有子弹的位置：将所有子弹的X坐标减少shift距离（子弹左移）
    public void updateShift(int shift) {
        x -= shift;
        for (Bullet bullet : bulletList) {
            if (bullet == null) {
                continue;
            }
            bullet.setX(bullet.getX() - shift);
        }
    }
    //绘制子弹的方法
    public void drawBullet(Canvas canvas){

    }
}
