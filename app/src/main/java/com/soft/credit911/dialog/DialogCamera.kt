package com.soft.credit911.dialog

import com.soft.credit911.R
import kotlinx.android.synthetic.main.document_dailog_box.*


class DialogCamera(val  onClick: (op:Int) -> Unit) : BaseFragmentDialog() {


    override fun onCreated() {
        onClickEvents()
        setData()
    }

    override fun getLayoutID(): Int {
        return R.layout.document_dailog_box
    }

    fun onClickEvents()
    {
        tv_Take_photo.setOnClickListener {


            dismiss()
            onClick(1)
        }

        tv_Gallery.setOnClickListener {

            dismiss()
            onClick(2)
        }
        tv_Documents.setOnClickListener {

            dismiss()
            onClick(3)
        }
        closeBtn.setOnClickListener {
            dismiss()
        }
    }

    fun setData()
    {

    }
}