package com.mif50.barberbooking.ui.authorized

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import com.facebook.accountkit.AccountKit
import com.facebook.accountkit.AccountKitLoginResult
import com.facebook.accountkit.ui.AccountKitActivity
import com.facebook.accountkit.ui.AccountKitConfiguration
import com.facebook.accountkit.ui.LoginType
import com.mif50.barberbooking.R
import com.mif50.barberbooking.app.helper.LayoutRes
import com.mif50.barberbooking.app.ktx.startActivity
import com.mif50.barberbooking.app.ktx.toast
import com.mif50.barberbooking.base.BaseActivity
import com.mif50.barberbooking.ui.authorized.presenter.LoginPresenter
import com.mif50.barberbooking.ui.authorized.presenter.LoginPresenterImpl
import com.mif50.barberbooking.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@LayoutRes(layout = R.layout.activity_main)
class MainActivity : BaseActivity(), View.OnClickListener, LoginView {


    private val presenter: LoginPresenter<LoginView> by lazy {
        LoginPresenterImpl(this)
    }


    companion object{
        val TAG: String by lazy { MainActivity::class.java.simpleName }
        private const val RQ_FACE_KIT = 1992
    }

    override fun bindView(savedInstanceState: Bundle?) {
        presenter.checkIfUserHasAccessToken()
        setListener()
    }

    private fun setListener(){
        loginBtn.setOnClickListener(this)
        skipTv.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            loginBtn.id -> loginUserWithFacebookAccountKit()
            skipTv.id -> goHome(false)
        }
    }

    private fun loginUserWithFacebookAccountKit(){
        val intent = Intent(this, AccountKitActivity::class.java)
        val configurationBuilder = AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE,
            AccountKitActivity.ResponseType.TOKEN)
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,configurationBuilder.build())
        startActivityForResult(intent, RQ_FACE_KIT)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RQ_FACE_KIT && data != null){
            val loginResult = data.getParcelableExtra<AccountKitLoginResult>(AccountKitLoginResult.RESULT_KEY)
            when {
                loginResult.error != null -> toast(loginResult.error!!.errorType.message)
                loginResult.wasCancelled() -> toast("login cancelled. ")
                else -> showHome()
            }
        }

    }


    override fun showHome() {
        Log.d(TAG,"User login with access token ")
        goHome(true)
    }

    private fun goHome(isLogin: Boolean){
        startActivity<HomeActivity>(HomeActivity.getStartIntent(isLogin))
        finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    @SuppressLint("PackageManagerGetSignatures")
    fun printHashKey() {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey = String(Base64.encode(md.digest(), 0))
                Log.d(TAG, "printHashKey() Hash Key: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.e(TAG, "printHashKey()", e)
        } catch (e: Exception) {
            Log.e(TAG, "printHashKey()", e)
        }

    }
}
