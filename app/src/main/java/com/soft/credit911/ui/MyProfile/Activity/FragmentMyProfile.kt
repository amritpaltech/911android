package com.soft.credit911.ui.MyProfile.Activity

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.gson.JsonParser
import com.ing.quiz.ui.base_classes.BaseFragment
import com.ing.quiz.ui.base_classes.SubBaseActivity
import com.soft.credit911.R
import com.soft.credit911.Utils.AppPreference
import com.soft.credit911.Utils.CommonUtils
import com.soft.credit911.datamodel.LoginResponse
import com.soft.credit911.datamodel.dataCountries
import com.soft.credit911.dialog.DialogCaptureSignature
import com.soft.credit911.dialog.DialogCredInfo
import com.soft.credit911.fcm.notificationObject
import com.soft.credit911.model.Countries
import com.soft.credit911.ui.dashboard.LandingActivity
import com.soft.credit911.ui.dashboard.UserProfile.Fragment.ProfileViewModel
import com.soft.credit911.ui.signature.SignatureProfileActivity
import com.soft.credit911.ui.signature.signatureObject
import com.soundcloud.android.crop.Crop
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FragmentMyProfile : BaseFragment() , AdapterView.OnItemSelectedListener {

    var mViewModel: ProfileViewModel? = null
    var countryData: ArrayList<Countries>? =null
    var isDoneOnce=false
    var slectedCountryCode=""
    var dob=""
    override fun getLayoutID(): Int {
        return R.layout.activity_my_profile
    }

    override fun onViewCreated() {
        toolbarTitle.text = "My Profile"
        countryData=AppPreference(activity).getCountries().country
        navigationIcon.setOnClickListener { v: View? -> super.onBackPress() }
        initView()
        setProfileData()
        mViewModel= ProfileViewModel()
        attachObserver()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    fun setCountryInfo( cId:String): String? {
        slectedCountryCode=cId
        if (countryData != null) {
            for(i in countryData!!){
            if(i.code?.equals(cId)?:false){
                return i.name
                break
            }
            }
        }
        return ""
    }

    fun setProfileData(){
        et_first_name.setText(AppPreference(activity).getUserObject().data?.firstName)
        etLastName.setText(AppPreference(activity).getUserObject().data?.lastName)
        etPhone.setText(AppPreference(activity).getUserObject().data?.phoneNumber)
        et_email.setText(AppPreference(activity).getUserObject().data?.email)
        et_city.setText(AppPreference(activity).getUserObject().data?.city)
        et_state.setText(AppPreference(activity).getUserObject().data?.state)
        et_Zip.setText(AppPreference(activity).getUserObject().data?.postal_code)
        et_address1.setText(AppPreference(activity).getUserObject().data?.address_line_1)
        et_address2.setText(AppPreference(activity).getUserObject().data?.address_line_2)
        et_Suffix.setText(AppPreference(activity).getUserObject().data?.suffix)
        et_middle_name.setText(AppPreference(activity).getUserObject().data?.middle_name)
        txtCountry.text= AppPreference(activity).getUserObject().data?.country?.let { setCountryInfo(it) }
        etScanCreditName.setText(AppPreference(activity).getUserObject().data?.scancredit_username)
        etConfirmPassword.setText(AppPreference(activity).getUserObject().data?.scancredit_password)
        txtLast4SSn.setText(AppPreference(activity).getUserObject().data?.last_4_ssn)
        AppPreference(activity).getUserObject().data?.birthday?.let { setDobText(it) }
        if(AppPreference(activity).getUserObject().data?.signature!=null &&
            AppPreference(activity).getUserObject().data?.signature?.length?:0>0){
            tvAddSignature.visibility=View.GONE
            tvViewSignature.visibility=View.VISIBLE
        }else{
            tvAddSignature.visibility=View.VISIBLE
            tvViewSignature.visibility=View.GONE
        }

        var list_of_items = ArrayList<String>()
        for(i in countryData!!){
            i.name?.let { list_of_items.add(it) };
        }
        val aa =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, list_of_items) }
        // Set layout to use when the list of choices appear
        aa?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinCountry.setAdapter(aa)

        if(arguments?.containsKey("pushData")==true){
            (activity as LandingActivity).pushDataMain = arguments?.getSerializable("pushData") as notificationObject
            (activity as LandingActivity).showPushDialog()
        }
    }

    fun setDobText(input: String){
        var format = SimpleDateFormat("yyyy-MM-dd")
        val newDate: Date = format.parse(input)
        dob=SimpleDateFormat("dd/MM/yyyy").format(newDate)
        format = SimpleDateFormat("dd, MMM yyyy")
        val date: String = format.format(newDate)
        txtBirthDate.setText(date)
    }

    private fun initView() {
        txtCountry.setOnClickListener {
            spinCountry.performClick()
        }

        tvAddSignature.setOnClickListener {
            startActivity( Intent(activity,SignatureProfileActivity::class.java))
        }
        tvViewSignature.setOnClickListener {
           val frg=FragmentSignatureView()
            val bundle=Bundle()
            bundle.putString("image",AppPreference(activity).getUserObject().data?.signature)
            frg.arguments=bundle
            addSubContentFragment(frg)
        }
        steps.setOnClickListener {
            val dilog= DialogCredInfo()
            dilog.show(childFragmentManager,"")
        }
        spinCountry!!.setOnItemSelectedListener(this)

        tvSave.setOnClickListener { v: View? ->
            if (isValid) {
                val mJsObjParam = JSONObject()
                mJsObjParam.put("middle_name", et_middle_name.text.toString().trim ())
                mJsObjParam.put("suffix", et_Suffix.text.toString().trim ())
                mJsObjParam.put("first_name", et_first_name.text.toString().trim ())
                mJsObjParam.put("first_name", et_first_name.text.toString().trim ())
                mJsObjParam.put("last_name",  etLastName.text.toString().trim ())
                mJsObjParam.put("phone_number",  etPhone.text.toString().trim ())
                mJsObjParam.put("address_line_1",  et_address1.text.toString().trim ())
                mJsObjParam.put("address_line_2",  et_address2.text.toString().trim ())
                mJsObjParam.put("city",  et_city.text.toString().trim ())
                mJsObjParam.put("state",  et_state.text.toString().trim ())
                mJsObjParam.put("postal_code",  et_Zip.text.toString().trim ())
                mJsObjParam.put("scancredit_username",  etScanCreditName.text.toString().trim ())
                mJsObjParam.put("scancredit_password",  etConfirmPassword.text.toString().trim ())
                mJsObjParam.put("country",  slectedCountryCode)
                mJsObjParam.put("birthday", dob)
                mJsObjParam.put("last_4_ssn",   txtLast4SSn.text.toString().trim ())
                val myOb = JsonParser().parse(mJsObjParam.toString()).asJsonObject
                mViewModel?.updateUserInfo(myOb)
            }
        }

//        tvAddSignature.setOnClickListener {
//         var dialog= DialogCaptureSignature{
//             val encodedImage: String = encodeImage(it).toString()
//             mViewModel?.uploadProfileSignature(encodedImage);
//         }
//            dialog.show(supportFragmentManager,"");
//        }


        txtBirthDate.setOnClickListener {
            hideKeyBoard()
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd =
                activity?.let { it1 ->
                    DatePickerDialog(it1, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                        // Display Selected date in textbox

                        setDobText(""+year+"-"+(monthOfYear+1)+"-"+dayOfMonth)
                    }, year, month, day)
                }

            dpd?.show()
        }
    }
    fun hideKeyBoard(){
        val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow( txtBirthDate.windowToken, 0)
    }
    private val isValid: Boolean
        private get() {
            if (et_first_name.text.toString().trim { it <= ' ' } == "") {
                et_first_name.error = resources.getString(R.string.first_name_error)
                et_first_name.requestFocus()
                return false
            }
            if (et_Suffix.text.toString().trim { it <= ' ' } == "") {
                et_Suffix.error = "Enter your suffix"
                et_Suffix.requestFocus()
                return false
            }
            if (etLastName.text.toString().trim { it <= ' ' } == "") {
                etLastName.error = resources.getString(R.string.last_name_error)
                etLastName.requestFocus()
                return false
            }
            if (etPhone.text.toString().trim { it <= ' ' } == "") {
                etPhone.error = resources.getString(R.string.phone_number_error)
                etPhone.requestFocus()
                return false
            }
            return true
        }

    fun attachObserver(){
        mViewModel?.updateProfileResponse?.observe(viewLifecycleOwner, Observer {myProfileResponse->
            activity?.let { CommonUtils.showdialog(myProfileResponse.message, it, false) }
            var dataObj:LoginResponse=AppPreference(activity).getUserObject()
            dataObj.data?.firstName=et_first_name.text.toString()
            dataObj.data?.lastName=etLastName.text.toString()
            dataObj.data?.phoneNumber=etPhone.text.toString()
            dataObj.data?.country=slectedCountryCode
            dataObj.data?.middle_name=et_middle_name.text.toString()
            dataObj.data?.suffix=et_Suffix.text.toString()
            dataObj.data?.address_line_1=et_address1.text.toString()
            dataObj.data?.address_line_2=et_address2.text.toString()
            dataObj.data?.city=et_city.text.toString()
            dataObj.data?.state=et_state.text.toString()
            dataObj.data?.postal_code=et_Zip.text.toString()
            dataObj.data?.postal_code=et_Zip.text.toString()
            dataObj.data?.scancredit_username=etScanCreditName.text.toString()
            dataObj.data?.scancredit_password=etConfirmPassword.text.toString()
            var format = SimpleDateFormat("dd/MM/yyyy")
            val newDate: Date = format.parse(dob)
            dob=SimpleDateFormat("yyyy-MM-dd").format(newDate)

            dataObj.data?.birthday=dob
            dataObj.data?.last_4_ssn=txtLast4SSn.text.toString()
            AppPreference(activity).setUserObject(dataObj)
            EventBus.getDefault().post(dataObj)
        })

        mViewModel?.updateResponse2?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it.message,Toast.LENGTH_LONG).show()
            var dataObj:LoginResponse=AppPreference(activity).getUserObject()
            dataObj.data?.signature=it.data?.signature
            AppPreference(activity).setUserObject(dataObj)
            EventBus.getDefault().post(dataObj)
        })
        mViewModel?.isLoading?.observe(viewLifecycleOwner, Observer {
            if(it){
               showProgress()
            }else{
               hideProgress()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, result: Intent?) {
        super.onActivityResult(requestCode, resultCode, result)
        if (requestCode == Crop.REQUEST_CROP && resultCode == RESULT_OK) {
            val imageUri: Uri? = Crop.getOutput(result)
//            if (photo2.isFile) {
            val imageStream = imageUri?.let { activity?.contentResolver?.openInputStream(it) }
            val selectedImage: Bitmap = BitmapFactory.decodeStream(imageStream)
            val encodedImage: String = encodeImage(selectedImage).toString()

             mViewModel?.uploadProfileSignature(encodedImage);
//            }
        }
    }

    private fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        if(isDoneOnce) {
            slectedCountryCode = countryData?.get(position)?.code.toString()
            txtCountry.text = countryData?.get(position)?.name.toString()
        }
        else{
            isDoneOnce=true
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: signatureObject?) {
        event?.signatureStr?.let { mViewModel?.uploadProfileSignature(it) }
    }
    override fun onNothingSelected(arg0: AdapterView<*>) {

    }
}