package com.ing.quiz.ui.base_classes

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.airbnb.lottie.utils.Utils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.ing.quiz.network.RestClient
import com.ing.quiz.shared_prefrences.Prefs
import com.soft.credit911.R
import org.jetbrains.anko.doAsync
import java.net.SocketTimeoutException


abstract class SubBaseActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
            if (Build.VERSION.SDK_INT >= 19) {
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            if (Build.VERSION.SDK_INT >= 21) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = Color.parseColor("#ff6200")
            }

        }

    }


    fun showBottomSheetDialogFragment(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        val bundle = Bundle()
        bottomSheetDialogFragment.arguments = bundle
        bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
    }


    /******************************************** Show msg in snackbar ********************************************************************************************/
    fun showSnackBar(message: String, content: View) {
        this.let {
            Snackbar.make(content, message, Snackbar.LENGTH_LONG).show()
        }
    }

    /******************************************** Show msg in snackbar ********************************************************************************************/


    fun showLoading(show: Boolean?) {
        if (show ?: false) showProgress() else hideProgress()
    }


    /***************************************Call When new update of available on play store***************************************************************************/


    /*fun showUpdateDialog() {
        AlertDialog.Builder(this).setTitle(getString(R.string.labelUpdateTitle)).setMessage(getString(R.string.labelUpdateMessage_re)).setCancelable(false)
                .setPositiveButton(android.R.string.ok) { dialog, id ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.data = Uri.parse(getString(R.string.url_playstore_basic) + getString(R.string.packegeName))
                    startActivity(intent)
                    dialog.dismiss()
                }.setNegativeButton(android.R.string.cancel) { dialog, id ->
                    dialog.dismiss()
                }.show()
    }*/


    /************************************Add  fragment***************************************************************************/
    /*fun openSubContentFragment(fragment: Fragment) {
       supportFragmentManager
               .beginTransaction()
               .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left,
                       R.anim.slide_out_right)
               .addToBackStack("profile")
               .add(R.id.frame_layout, fragment).commit()
   }


  fun openSubContentFragmentNew(fragment: Fragment) {
       supportFragmentManager
               .beginTransaction()
               .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left,
                       R.anim.slide_out_right).addToBackStack("profile")
               .add(R.id.parent, fragment).commit()
   }


   /******************************************** function replace fragment ********************************************************************************//*
   */ fun replaceFragment(fragment: Fragment) {
        val transaction = getSupportFragmentManager().beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.commit()
    }*/






    fun showAlertDialogAndExitApp(message: String) {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle("Alert")
        alertDialog.setCancelable(false)
        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE, "No"
        ) { dialog, which ->
            dialog.dismiss()

        }
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, "Yes"
        ) { dialog, which ->
            dialog.dismiss()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        alertDialog.show()
    }

    fun addSubContentFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left,
                R.anim.slide_out_right
            ).addToBackStack("profile")
            .add(R.id.container, fragment).commit()
    }

    

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.fade_in, R.anim.fade_out, R.anim.fade_in,
                R.anim.fade_out
            )
            .replace(R.id.container, fragment).commit()
    }

    fun addFragment(fragment: Fragment) {


    }




    /**********************************************Handle push notification*******************************************************/


    fun openSubContent(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left,
                R.anim.slide_out_right
            ).addToBackStack("profile")
            .add(R.id.parent, fragment).commit()
    }



}