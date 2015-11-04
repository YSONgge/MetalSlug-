package com.example.yeye.metalslug.comp;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeye on 2015/11/3.
 */
public class Player {
    // 定义角色的最高生命值
    public static final int MAX_HP = 500;
    // 定义控制角色动作的常量
    // 此处只控制该角色包含站立、跑、跳等动作
    public static final int ACTION_STAND_RIGHT = 1;
    public static final int ACTION_STAND_LEFT = 2;
    public static final int ACTION_RUN_RIGHT = 3;
    public static final int ACTION_RUN_LEFT = 4;
    public static final int ACTION_JUMP_RIGHT = 5;
    public static final int ACTION_JUMP_LEFT = 6;
    // 定义角色向右移动的常量
    public static final int DIR_RIGHT = 1;
    // 定义角色向左移动的常量
    public static final int DIR_LEFT = 2;
    // 控制角色的默认坐标
    public static int X_DEFAULT = 0;
    public static int Y_DEFALUT = 0;
    public static int Y_JUMP_MAX = 0;
    // 保存角色名字的成员变量
    private String name;
    // 保存角色生命值的成员变量
    private int hp;
    // 保存角色所使用枪的类型（以后可考虑让角色能更换不同的枪）
    private int gun;
    // 保存角色当前动作的成员变量（默认向右站立）
    private int action = ACTION_STAND_RIGHT;
    // 代表角色X坐标的成员变量
    private int x = -1;
    // 代表角色Y坐标的成员变量
    private int y = -1;
    // 保存角色射出的所有子弹
    private final List<Bullet> bulletList = new ArrayList<>();
    // 定义控制角色移动的常量
    // 此处控制该角色只包含站立、向右移动、向左移动三种移动方式
    public static final int MOVE_STAND = 0;
    public static final int MOVE_RIGHT = 1;
    public static final int MOVE_LEFT = 2;
    // 保存角色移动方式的成员变量
    public int move = MOVE_STAND;
    public static final int MAX_LEFT_SHOOT_TIME = 6;
    // 控制射击状态的保留计数器
    // 每当用户发射一枪时，leftShootTime会被设为MAX_LEFT_SHOOT_TIME。
    // 只有当leftShootTime变为0时，用户才能发射下一枪
    private int leftShootTime = 0;
    // 保存角色是否跳动的成员变量
    public boolean isJump = false;
    // 保存角色是否跳到最高处的成员变量
    public boolean isJumpMax = false;
    // 控制跳到最高处的停留时间
    public int jumpStopCount = 0;
    // 当前正在绘制角色脚部动画的第几帧
    private int indexLeg = 0;
    // 当前正在绘制角色头部动画的第几帧
    private int indexHead = 0;
    // 当前绘制头部图片的X坐标
    private int currentHeadDrawX = 0;
    // 当前绘制头部图片的Y坐标
    private int currentHeadDrawY = 0;
    // 当前正在画的脚部动画帧的图片
    private Bitmap currentLegBitmap = null;
    // 当前正在画的头部动画帧的图片
    private Bitmap currentHeadBitmap = null;
    // 该变量控制用于控制动画刷新的速度
    private int drawCount = 0;
    // 定义Player的构造器
    public Player(String name, int hp)
    {
        this.name = name;
        this.hp = hp;
    }
}
