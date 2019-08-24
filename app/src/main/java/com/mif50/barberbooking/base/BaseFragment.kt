package com.mif50.barberbooking.base

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.mif50.barberbooking.app.ktx.getLayoutRes
import com.mif50.barberbooking.base.mvp.BaseView

abstract class BaseFragment : Fragment(), BaseView {

    lateinit var baseActivity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as BaseActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(getLayoutRes().menu != 0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(getLayoutRes().layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(savedInstanceState)
    }

    abstract fun bindView(savedInstanceState: Bundle?)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (getLayoutRes().menu != 0) {
            inflater.inflate(getLayoutRes().menu, menu)
            onMenuCreated(menu)
        }
        super.onCreateOptionsMenu(menu, inflater)

    }

    open fun onMenuCreated(menu: Menu?) {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onMenuItemClickListener(item, item.itemId)
        return super.onOptionsItemSelected(item)

    }

    open fun onMenuItemClickListener(item: MenuItem?, id: Int) {}

    override fun onShowLoading() {}

    override fun onHideLoading() {}
}