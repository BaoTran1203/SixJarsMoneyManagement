package com.trangiabao.sixjars.base.activity

import android.os.Bundle
import com.trangiabao.sixjars.R

class SplashScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        startActivity(HomeActivity::class.java)
    }
}
