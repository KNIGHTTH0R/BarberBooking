package com.mif50.barberbooking.ui.home.dialog

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mif50.barberbooking.R
import com.mif50.barberbooking.app.ktx.textValue
import com.mif50.barberbooking.app.ktx.toast
import com.mif50.barberbooking.ui.home.dialog.presenter.AddUserPresenter
import com.mif50.barberbooking.ui.home.dialog.presenter.AddUserPresenterImpl
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.layout_update_information.*

class AddUserBottomSheetDialog : BottomSheetDialogFragment(), AddUserView{


    private val spotDialog: AlertDialog by lazy {
        SpotsDialog.Builder().setContext(this.context).setCancelable(false).build()
    }

    private val presenter: AddUserPresenter<AddUserView> by lazy {
        AddUserPresenterImpl(this)
    }

    private var phoneNumber: String? = null

    companion object{
         val TAG: String = AddUserBottomSheetDialog::class.java.simpleName

        private const val BUNDLE_PHONE_NUMBER: String = "bundle_phone_number"

        fun newInstance(phoneNumber: String): AddUserBottomSheetDialog = AddUserBottomSheetDialog()
            .apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_PHONE_NUMBER,phoneNumber)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBundleData()
    }

    private fun getBundleData(){
        arguments?.let {
            phoneNumber = it.getString(BUNDLE_PHONE_NUMBER)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_update_information,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
    }

    private fun setListener(){
        updateBtn.setOnClickListener {
            if (phoneNumber == null) return@setOnClickListener
            presenter.addUser(phoneNumber!!,nameEt.textValue(),addressEt.textValue())
        }
    }


    override fun onShowLoading() {
        spotDialog.show()
    }

    override fun onHideLoading() {
       spotDialog.dismiss()
    }

    override fun onSuccessAddUser() {
        dismiss()
        context?.toast("Thank you")
        val listener: FinishAddUserListener? = activity as? FinishAddUserListener
        listener?.onUserAddedSuccessful()
    }

    override fun onFailedAddUser(error: String) {
        dismiss()
        context?.toast(error)
    }



}

interface FinishAddUserListener{
    fun onUserAddedSuccessful()
}