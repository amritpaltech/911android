package com.soft.credit911.dialog

import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.ing.quiz.ui.base_classes.BaseActivity
import com.soft.credit911.R

abstract class BaseFragmentDialog : DialogFragment() {



    abstract fun onCreated()
    abstract fun getLayoutID(): Int
    var mBaseActivity: BaseActivity?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        val act= activity as BaseActivity
        mBaseActivity=act
        super.onCreate(savedInstanceState)
//        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogJourney)

    }


    override fun onActivityCreated(arg0: Bundle?) {
        super.onActivityCreated(arg0)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogZoomAnim

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        isCancelable = true
        return inflater.inflate(getLayoutID(), container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreated()
    }

    override fun onResume() {
        super.onResume()
//        val window = dialog?.window
//        val size = Point()
//        val display = window!!.windowManager.defaultDisplay
//        display.getSize(size)
//        val width = size.x
//        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
//        window.setGravity(Gravity.CENTER)


    }

}