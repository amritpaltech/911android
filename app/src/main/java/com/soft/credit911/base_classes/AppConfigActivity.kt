package com.soft.credit911.base_classes

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

import com.ing.quiz.shared_prefrences.Prefs
import com.ing.quiz.shared_prefrences.SharedPreferencesName

open abstract class AppConfigActivity : AppCompatActivity() {


    override fun onStart() {

        super.onStart()
    }

    fun home() {
        /*val `in` = Intent(this, ActivityDashBoard::class.java)
        `in`.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(`in`)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)*/
    }


    /*************************************** Analytics common function  ***************************************************************************/



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

        }
    }






    fun getUserType(): Int {
        return Prefs.with(this).getInt(SharedPreferencesName.USERID, 0)

    }


    /***************************Common method for handling colors ***********************************************/
    /***
     * Get Color model object
     */
    /***************************Common method for handling colors ***********************************************/


    fun setColorAlpha(colorStr: String, percent: Int): String {
        return if (percent == 60) {

            return colorStr.replace("#", "#99")
        } else if (percent == 50) {
            return colorStr.replace("#", "#80")
        } else if (percent == 40) {
            colorStr.replace("#", "#66")
        } else if (percent == 30) {
            return colorStr.replace("#", "#4D")
        } else if (percent == 20) {
            return colorStr.replace("#", "#33")
        } else if (percent == 10) {
            return colorStr.replace("#", "#1A")
        } else {
            return "#000"
        }
    }


    fun setViewSolidColors(view: View, strokeColor: String, solidColor: String) {
        try {
            val drawable = view?.background as GradientDrawable
            drawable.setStroke(5, Color.parseColor(strokeColor));
            drawable.setColor(Color.parseColor(solidColor))
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

    }


    fun setViewSolidColors(view: View, color: String?) {
        try {
            val drawable = view?.background as GradientDrawable
            drawable.setStroke(3, Color.parseColor(color));
            drawable.setColor(Color.parseColor(color))
        } catch (e: java.lang.Exception) {

        }

    }

    /******************************************Change languages ****************************************************/
    /* @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
     fun changeLanguage() {

         val myLanguage=getLanguageCode()
         var config = Configuration()
         val languageToLoad =myLanguage // your language
         var locale = Locale(languageToLoad)
         locale = Locale(languageToLoad)
         Locale.setDefault(locale)
         config = Configuration()
         config.setLocale(locale)
         baseContext.resources.updateConfiguration(config,
                 baseContext.resources.displayMetrics)
     }*/


    /****************************************Firebase constant ****************************************************************/



    fun getAppVersion(): String {
        var pinfo = getPackageManager().getPackageInfo(getPackageName(), 0)
        val appVersion = pinfo.versionCode;

        return "" + appVersion
    }





    object Notifications {
        val friendRequest = 1
        val postLike = 2

    }

    object TYPE_FLASHCARD {
        val TYPE_RANDOM = 0
        val TYPE_TOPIC = 0
        val BOOKMARKED = 1
    }


}