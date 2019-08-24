package com.mif50.barberbooking.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mif50.barberbooking.R
import com.mif50.barberbooking.app.helper.LayoutRes
import com.mif50.barberbooking.app.ktx.toast
import com.mif50.barberbooking.base.BaseActivity
import com.mif50.barberbooking.ui.home.dialog.AddUserBottomSheetDialog
import com.mif50.barberbooking.ui.home.dialog.FinishAddUserListener
import com.mif50.barberbooking.ui.home.fragment.dashboard.DashboardFragment
import com.mif50.barberbooking.ui.home.presenter.HomePresenter
import com.mif50.barberbooking.ui.home.presenter.HomePresenterImpl
import com.mif50.barberbooking.ui.home.fragment.shopping.ShoppingFragment
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_home.*

@LayoutRes(layout = R.layout.activity_home)
class HomeActivity : BaseActivity(), HomeView ,FinishAddUserListener{


    private val presenter: HomePresenter<HomeView> by lazy {
        HomePresenterImpl(this)
    }

    private val spotDialog: AlertDialog by lazy {
        SpotsDialog.Builder().setContext(this).setCancelable(false).build()
    }

    companion object{
        private const val EXTRA_LOGON ="is_user_login"

        fun getStartIntent(isLogin: Boolean): Intent = Intent()
            .putExtra(EXTRA_LOGON,isLogin)
    }

    override fun bindView(savedInstanceState: Bundle?) {
        checkIfUserLogin()




        setListener()
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null){
            supportFragmentManager.beginTransaction().replace(frameContainer.id,fragment).commit()
            return true
        }
        return false
    }

    private fun checkIfUserLogin(){
        // if user login = true enable full access
        // if user login = false just let user around shopping to view
        val isUserLogin = intent.getBooleanExtra(EXTRA_LOGON,false)
        if (isUserLogin){
            presenter.checkUserLoginWithAccountKit()
        }
    }

    private fun setListener(){
        bottomNavigation.setOnNavigationItemSelectedListener {
            var fragment: Fragment? = null
            if (it.itemId == R.id.actionHome){
                fragment = DashboardFragment()

            }else if(it.itemId == R.id.actionShopping){
                fragment = ShoppingFragment()
            }
            return@setOnNavigationItemSelectedListener loadFragment(fragment)
        }
    }

    override fun onShowLoading() {
        super.onShowLoading()
        spotDialog.show()
    }

    override fun onHideLoading() {
        super.onHideLoading()
        spotDialog.dismiss()
    }


    override fun onErrorAccountKit(message: String?) {
        toast(message ?: "Error account kit ")
    }

    override fun showDashboard() {
        bottomNavigation.selectedItemId = R.id.actionHome
    }

    override fun showDialogToAddUser(phoneNumber: String) {
        AddUserBottomSheetDialog.newInstance(phoneNumber).also {
            it.isCancelable = false
            it.show(supportFragmentManager, AddUserBottomSheetDialog.TAG)
        }
    }

    override fun onUserAddedSuccessful() {
        showDashboard()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
