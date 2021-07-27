package com.soft.credit911.adaptor

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.soft.credit911.R
import com.soft.credit911.adaptor.DocumentDetailsAdapter.DocumentViewHolder
import com.soft.credit911.databinding.DocumentItemListBinding
import com.soft.credit911.datamodel.DocumentModel
import java.util.*

class DocumentDetailsAdapter(var context: Context) : RecyclerView.Adapter<DocumentViewHolder>() {
    var documentModels: List<DocumentModel> = ArrayList()
    var dialog: Dialog? = null
    fun addList(documentModelArrayList: ArrayList<DocumentModel>) {
        documentModels = documentModelArrayList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): DocumentViewHolder {
        val documentItemListBinding: DocumentItemListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.document_item_list, viewGroup, false
        )
        return DocumentViewHolder(documentItemListBinding)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        val documentModelList = documentModels[position]
        when (documentModelList.userStatus) {
            "Completed" -> {
                holder.binding.mainLayoutLl.setBackgroundColor(context.resources.getColor(R.color.light_green))
                holder.binding.tvUserStatus.setTextColor(context.resources.getColor(R.color.green))
                holder.binding.tvUserLicence.setTextColor(context.resources.getColor(R.color.black))
            }
            "Rejected" -> {
                holder.binding.mainLayoutLl.setBackgroundColor(context.resources.getColor(R.color.darkOrange))
                holder.binding.tvUserStatus.setTextColor(context.resources.getColor(R.color.white))
                holder.binding.tvUserLicence.setTextColor(context.resources.getColor(R.color.white))
            }
            "pending" -> holder.binding.mainLayoutLl.setBackgroundColor(context.resources.getColor(R.color.white))
        }
        holder.binding.tvUserLicence.text = documentModelList.userLicence
        holder.binding.tvUserStatus.text = documentModelList.userStatus
        Glide.with(context)
            .load(documentModelList.checkImage)
            .placeholder(R.drawable.ic__check_circle)
            .into(holder.binding.ivCheck)
        holder.binding.mainLayoutLl.setOnClickListener { v: View? ->
            dialog = Dialog(context)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setCancelable(true)
            dialog!!.setContentView(R.layout.document_dailog_box)
            val document = dialog!!.findViewById<TextView>(R.id.tv_Documents)
            val takePhoto = dialog!!.findViewById<TextView>(R.id.tv_Take_photo)
            val gallery = dialog!!.findViewById<TextView>(R.id.tv_Gallery)
            gallery.setOnClickListener { v1: View? ->
                val intent = Intent()
                intent.type = "image/*"
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                intent.action = Intent.ACTION_GET_CONTENT
                (context as Activity).startActivityForResult(
                    Intent.createChooser(
                        intent,
                        "Select Picture"
                    ), 1
                )
            }
            document.setOnClickListener { v1: View? ->
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "*/*"
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                try {
                    (context as Activity).startActivityForResult(
                        Intent.createChooser(intent, "Select a File to Upload"),
                        FILE_SELECT_CODE
                    )
                } catch (ex: ActivityNotFoundException) {
                    Toast.makeText(context, "Please install a File Manager.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            takePhoto.setOnClickListener { v1: View? ->
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                (context as Activity).startActivityForResult(cameraIntent, PICK_FROM_CAMERA)
            }
            dialog!!.show()
        }
    }

    override fun getItemCount(): Int {
        return documentModels.size
    }

    inner class DocumentViewHolder(var binding: DocumentItemListBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    companion object {
        private const val PICK_FROM_CAMERA = 1
        private const val FILE_SELECT_CODE = 0
    }
}