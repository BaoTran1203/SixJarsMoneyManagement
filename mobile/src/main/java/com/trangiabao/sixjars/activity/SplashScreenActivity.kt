package com.trangiabao.sixjars.activity

import android.os.Bundle
import com.trangiabao.sixjars.R
import com.trangiabao.sixjars.system.BaseActivity

class SplashScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        startActivity(HomeActivity::class.java)
    }
}
