package app.vut.secnote.ui.splash

import android.os.Bundle
import android.os.PersistableBundle
import app.vut.secnote.R
import app.vut.secnote.databinding.ActivitySplashBinding
import app.vut.secnote.ui.base.BaseBindingActivity
import app.vut.secnote.ui.navigation.NavigationActivity
import javax.inject.Inject

class SplashActivity : BaseBindingActivity<SplashViewModel, SplashViewState, ActivitySplashBinding>(), SplashView {

    @Inject
    override lateinit var viewModelFactory: SplashViewModelFactory

    override val layoutResId = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        setTheme(R.style.AppTheme_Launcher)
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onResume() {
        super.onResume()
        startActivity(NavigationActivity.getStartIntent(this))
    }

}
