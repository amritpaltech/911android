package com.soft.credit911.datamodel
import java.io.Serializable

class data_docs {
    val documents: Documents? = null

    val message: String? = null

    val status: String? = null

    class Documents:Serializable{
        val other: ArrayList<DocData>?=null

        val required: ArrayList<DocData>?=null
    }
    class DocData:Serializable{
        val Label: String? = null

        val case_id: String? = null
        val fk_documentrequest_id:Int?=0

        val description: String? = null
        val notes: String? = null
        val action: String? = null
        val message: String? = null
        val doc_id: String? = null

        val document_type: String? = null

        val status: String? = null
    }
}