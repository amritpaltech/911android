package com.soft.credit911.ui.documnet

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.soft.credit911.R
import com.soft.credit911.adaptor.DocumentDetailsAdapter
import com.soft.credit911.adaptor.OtherDocumentDetailsAdapter
import com.soft.credit911.databinding.ActivityDocumentBinding
import com.soft.credit911.databinding.ToolbarBinding
import com.soft.credit911.datamodel.DocumentModel
import com.soft.credit911.datamodel.OtherDocumentModel
import java.util.*

class DocumentActivity : AppCompatActivity() {
    private var layoutBinding: ActivityDocumentBinding? = null
    private var toolbarBinding: ToolbarBinding? = null
    private var documentDetailsAdapter: DocumentDetailsAdapter? = null
    private var otherDocumentDetailsAdapter: OtherDocumentDetailsAdapter? = null
    private val otherDocumentModels = ArrayList<OtherDocumentModel>()
    private val documentModels = ArrayList<DocumentModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivityDocumentBinding.inflate(
            layoutInflater
        )
        val view = layoutBinding!!.root
        setContentView(view)
        toolbarBinding = layoutBinding!!.toolbarLayout
        toolbarBinding!!.toolbarTitle.text = "Document"
        toolbarBinding!!.navigationIcon.setOnClickListener { v: View? -> onBackPressed() }
        documentDetailsAdapter = DocumentDetailsAdapter(this)
        layoutBinding!!.rvDocument.adapter = documentDetailsAdapter
        val document = DocumentModel()
        document.userLicence = "Driving Licence (Font)"
        document.userStatus = "pending"
        document.checkImage = R.drawable.ic__check_circle
        documentModels.add(document)
        val document1 = DocumentModel()
        document1.userLicence = "Driving Licence (Back)"
        document1.userStatus = "pending"
        document1.checkImage = R.drawable.ic__check_circle
        documentModels.add(document1)
        val document2 = DocumentModel()
        document2.userLicence = "Social Security Number"
        document2.userStatus = "Completed"
        document2.checkImage = R.drawable.ic_check_circle_green
        documentModels.add(document2)
        val document3 = DocumentModel()
        document3.userLicence = "Residence Proof"
        document3.userStatus = "Rejected"
        document3.checkImage = R.drawable.alert
        documentModels.add(document3)
        documentDetailsAdapter!!.addList(documentModels)
        otherDocumentDetailsAdapter = OtherDocumentDetailsAdapter(this)
        layoutBinding!!.rvOtherDocument.adapter = otherDocumentDetailsAdapter
        val otherDocument = OtherDocumentModel()
        otherDocument.userLicence = "Other Document 1"
        otherDocument.userStatus = "...."
        otherDocument.checkImage = R.drawable.ic__check_circle
        otherDocumentModels.add(otherDocument)
        val otherDocument2 = OtherDocumentModel()
        otherDocument2.userLicence = "Other Document 2"
        otherDocument2.userStatus = "...."
        otherDocument2.checkImage = R.drawable.ic__check_circle
        otherDocumentModels.add(otherDocument2)
        otherDocumentDetailsAdapter!!.addList(otherDocumentModels)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SELECT_PICTURES -> if (resultCode == RESULT_OK) {
                if (data!!.clipData != null) {
                    val count = data.clipData!!.itemCount
                    var i = 0
                    while (i < count) {
                        val imageUri = data.clipData!!.getItemAt(i).uri
                        i++
                    }
                }
            } else if (data!!.data != null) {
                val imagePath = data.data!!.path
            }
            PICK_FROM_CAMERA -> if (resultCode == RESULT_OK) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, PICK_FROM_CAMERA)
            }
        }
    }

    companion object {
        private const val SELECT_PICTURES = 0
        private const val PICK_FROM_CAMERA = 1
    }
}