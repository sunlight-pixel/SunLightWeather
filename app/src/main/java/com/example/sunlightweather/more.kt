package com.example.sunlightweather

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_more.*
import kotlinx.android.synthetic.main.activity_weather.*

class more : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    private var bgNum = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more)
        //返回
        more_iv_back.setOnClickListener{
            finish()
        }

        //改变壁纸
        pref = getSharedPreferences("bg_pref", Context.MODE_PRIVATE)
        more_tv_exchangebg.setOnClickListener {
            if (more_rg.getVisibility() == View.VISIBLE) {
                more_rg.setVisibility(View.GONE)
            } else {
                more_rg.setVisibility(View.VISIBLE)
            }
        }
        setRGListener()
        exchangeBg()

        //获取当前版本信息
        val versionName: String? = getVersionName()
        more_tv_version.setText("当前版本:    v$versionName")

        //分享app
        more_tv_share.setOnClickListener {
            shareSoftwareMsg("本天气预报app画面简约，播报天气情况非常精准，快来下载吧！")
        }
    }
    fun getVersionName(): String? {
        /* 获取应用的版本名称*/
        val manager: PackageManager = getPackageManager()
        var versionName: String? = null
        try {
            val info: PackageInfo = manager.getPackageInfo(getPackageName(), 0)
            versionName = info.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return versionName
    }

    //分享app
    //private var pref: SharedPreferences? = null
    //pref = getSharedPreferences("bg_pref", android.content.Context.MODE_PRIVATE)
    private fun shareSoftwareMsg(s: String) {
        /* 分享软件的函数*/
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, s)
        startActivity(Intent.createChooser(intent, "查天气"))
    }

    private fun setRGListener() {
        /* 设置改变背景图片的单选按钮的监听*/
        more_rg.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId -> //                获取目前的默认壁纸
            val bg: Int = pref.getInt("bg", 0)
            val editor: SharedPreferences.Editor = pref.edit()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            when (checkedId) {
                R.id.more_rb_grey -> {
                    if (bg == 0) {
                        Toast.makeText(this, "您选择的为当前背景，无需改变！", Toast.LENGTH_SHORT)
                            .show()
                        return@OnCheckedChangeListener
                    }
                    editor.putInt("bg", 0)
                    editor.commit()
                }
                R.id.more_rb_pink -> {
                    if (bg == 1) {
                        Toast.makeText(this, "您选择的为当前背景，无需改变！", Toast.LENGTH_SHORT)
                            .show()
                        return@OnCheckedChangeListener
                    }
                    editor.putInt("bg", 1)
                    editor.commit()
                }
                R.id.more_rb_blue -> {
                    if (bg == 2) {
                        Toast.makeText(this, "您选择的为当前背景，无需改变！", Toast.LENGTH_SHORT)
                            .show()
                        return@OnCheckedChangeListener
                    }
                    editor.putInt("bg", 2)
                    editor.commit()
                }
            }
            startActivity(intent)
        })
    }
    //        换壁纸的函数
    fun exchangeBg() {
        pref = getSharedPreferences("bg_pref", Context.MODE_PRIVATE)
        bgNum = pref.getInt("bg", 2)
        when (bgNum) {
            0 -> relativeLayout.setBackgroundResource(R.mipmap.bg)
            1 -> relativeLayout.setBackgroundResource(R.mipmap.bg1)
            2 -> relativeLayout.setBackgroundResource(R.mipmap.bg2)
        }
    }
}

