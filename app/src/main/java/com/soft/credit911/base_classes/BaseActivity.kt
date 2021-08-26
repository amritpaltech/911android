package com.ing.quiz.ui.base_classes

import android.Manifest
import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.TargetApi
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import com.android.volley.toolbox.ImageLoader
import com.google.firebase.messaging.FirebaseMessaging
import com.ing.quiz.network.RestClient
import com.ing.quiz.shared_prefrences.Prefs
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.soft.credit911.R
import com.soft.credit911.base_classes.AppConfigActivity
import com.soft.credit911.ui.Splash.SplashActivity
import io.reactivex.schedulers.Schedulers

import java.io.IOException
import java.net.SocketTimeoutException
import java.util.*
import kotlin.collections.ArrayList


abstract class BaseActivity : AppConfigActivity() {

    var isActive: Boolean = false
    var is_image_zoomed: Boolean = false
    var mProgressDialog: Dialog? = null
    var profileClickable: Boolean = true
    var fragmeType = 4
    var animatorSet: AnimatorSet? = null
    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    val permissionsMessage = arrayOf(
        Manifest.permission.READ_SMS,
    )
    var mCurrentFragment: Fragment? = null
    var myView: View? = null
    var count = 0
    var timeCount: Int = 1700

    /********************************************* Show progress and hide progress *******************************************************************************************/
    fun showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = Dialog(this, android.R.style.Theme_Translucent)
            mProgressDialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
            mProgressDialog?.setContentView(R.layout.dialog_progress_overlay)
            mProgressDialog?.setCancelable(true)

        }
        if (!mProgressDialog?.isShowing!!)
            mProgressDialog?.show()
    }

    fun hideProgress() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog?.dismiss()
            mProgressDialog = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animatorSet = AnimatorSet()
        profileClickable = true
        setContentView(getLayoutID())
        onViewCreated()
        val imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        imageLoader?.init(ImageLoaderConfiguration.createDefault(this))
        RestClient?.mBaseActivity = this
    }


    abstract fun getLayoutID(): Int
    abstract fun onViewCreated()
    fun BackBtn(v: View) {
        finish()
    }

    public override fun onResume() {
        super.onResume()
        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        )


    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
        }

        return true
    }


    fun LogOut(v: View) {

        logOut()
    }

    fun logoutUser() {
        Prefs.with(this).removeAll()
        val `in` = Intent(this, SplashActivity::class.java)
        `in`.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(`in`)
        Thread(Runnable {
            try {
                FirebaseMessaging.getInstance().deleteToken()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }).start()
        finish()
    }


    fun logOut() {

        AlertDialog.Builder(this).setTitle(getString(R.string.txt_logout))
            .setMessage(getString(R.string.txt_logout_message))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.txt_yes)) { dialog, id ->
                dialog.cancel()
                logoutUser()
            }.setNegativeButton(getString(R.string.txt_no)) { dialog, id ->
                dialog.cancel()
                // dialogNoInternet.cancel();
            }.show()


    }


    @SuppressWarnings("deprecation")
    public fun getSystemLocaleLegacy(config: Configuration): Locale {
        return config.locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public fun getSystemLocale(config: Configuration): Locale {
        return config.getLocales().get(0);
    }

    @SuppressWarnings("deprecation")
    public fun setSystemLocaleLegacy(config: Configuration, locale: Locale) {

        config.locale = locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public fun setSystemLocale(config: Configuration, locale: Locale) {
        config.setLocale(locale);
    }

    /************************************ Language implementations*******************************************************/
    fun setLafnguage(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            setSystemLocale(config, locale)
        } else {
            setSystemLocaleLegacy(config, locale)
        }
        context.getApplicationContext().getResources().updateConfiguration(
            config,
            context.getResources().getDisplayMetrics()
        );
    }

    fun setSubContentFragment(fragment: Fragment) {
        try {
            supportFragmentManager
                .beginTransaction()
               // .add(R.id.subContainer, fragment)
                .addToBackStack(fragment.javaClass.name)
                .commitAllowingStateLoss()
        } catch (e: java.lang.Exception) {

        }

    }

    fun toProfile() {
        // JavaUtils.startActivity(this, ActivityMyProfile::class.java,Bundle(),false)
        /* val `in` = Intent(this!!, ActivityProfile::class.java)
         startActivity(`in`)
         overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)*/
    }






    override fun onDestroy() {
        super.onDestroy()
    }




}
