package com.example.yeye.metalslug;

import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;


public class MainActivity extends AppCompatActivity {

    public static FrameLayout mainLayout = null;

    public static FrameLayout.LayoutParams mainLP = null;

    public static Resources res = null;

    public static MainActivity mainActivity = null;
    //定义成员变量记录游戏窗口的宽度，高度。
    public static int windowWidth;
    public static int windowHeight;
    //游戏主界面
    public static GameView mainView = null;
    //音乐背景
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity=this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics metric = new DisplayMetrics();

        //获取宽度高度
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        windowHeight = metric.heightPixels;
        windowWidth = metric.widthPixels;
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//软键盘隐藏
        res = getResources();
        //加载main.xml界面设计文件

        //这里有问题
       // setContentView(R.layout.main);
        //获取main.xml文件中ID为mainLayout的组件
       // mainLayout = (FrameLayout) findViewById(R.id.mainLayout);
        //创建GameView组件
       // mainView = new GameView(this.getApplicationContext(),GameView.STAGE_INIT);
       // mainLP = new FrameLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT);


        //mainLayout.addView(mainView,mainLP);
        //播放背景音乐
        //player = MediaPlayer.create(this,R.raw.background);
        player.setLooping(true);
        player.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //当游戏暂停，音乐暂停播放
        if(player !=null && !player.isPlaying()){
            player.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //重新开始时，如果没有播放，重新开始播放
        if(player !=null && player.isPlaying()){
            player.start();
        }
    }
}
