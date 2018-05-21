package com.twt.service.home

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.twt.service.R
import com.twt.wepeiyang.commons.experimental.extensions.bindNonNull
import com.twt.wepeiyang.commons.experimental.extensions.enableLightStatusBarMode
import com.twt.wepeiyang.commons.ui.rec.withItems
import com.twt.wepeiyang.commons.view.RecyclerViewDivider
import com.twtstudio.retrox.auth.api.authSelfLiveData
import com.twtstudio.retrox.tjulibrary.home.libraryHomeItem
import xyz.rickygao.gpa2.view.gpaHomeItem


class HomeNewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_new)
        enableLightStatusBarMode(true)
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.parseColor("#F5F5F5")
        }
        val imageView = findViewById<ImageView>(R.id.iv_toolbar_avatar)
        authSelfLiveData.bindNonNull(this) {
            Glide.with(this).load(it.avatar).into(imageView)
        }

        val rec = findViewById<RecyclerView>(R.id.rec_main)
        rec.apply {
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(RecyclerViewDivider.Builder(this@HomeNewActivity).setSize(4f).setColor(Color.TRANSPARENT).build())
        }
        rec.withItems {
            gpaHomeItem(this@HomeNewActivity)
            libraryHomeItem(this@HomeNewActivity)
        }
    }
}