package pl.elpassion.eltc.init

import android.content.Intent
import android.os.Bundle
import android.util.Log
import pl.elpassion.eltc.*
import pl.elpassion.eltc.builds.BuildsActivity
import pl.elpassion.eltc.login.LoginActivity

class InitialActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        initModel()
        model.perform(StartApp)
    }

    override fun showState(state: AppState?) {
        Log.w("INIT ACT NEW STATE", state.toString())
        when (state) {
            is LoginState -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            is LoadingState -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            is BuildsState -> {
                startActivity(Intent(this, BuildsActivity::class.java))
                finish()
            }
        }
    }
}