package com.ing.quiz.ui.base_classes


import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.utils.Utils
import com.soft.credit911.R
import com.soft.credit911.base_classes.BaseViewModel
import com.soft.credit911.fcm.notificationObject


abstract class BaseFragment : androidx.fragment.app.Fragment() {
    var navController: NavController? = null
    abstract fun getLayoutID(): Int
    abstract fun onViewCreated()
    var myView: View? = null
    var baseActivity: BaseActivity? = null
    var suBaseActivity: SubBaseActivity? = null

    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val permissionsStorage = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    val permissionCamera = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onAttach(context: Context) {
        context?.let { super.onAttach(it) }
        if (context is BaseActivity) {
            baseActivity = context
        }
        if (context is SubBaseActivity) {
            suBaseActivity = context
        }
    }

    override fun onResume() {
        super.onResume()


    }

    fun onBackPress(){
        (activity as BaseActivity).onBackPressed()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutID(), container, false)
        this.myView = view
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseActivity?.mCurrentFragment = this
        try {
            navController = Navigation.findNavController(view)
        } catch (e: Exception) {

        }

        onViewCreated()
    }


    /****************************** Navigation fragment handler *****************************************/
    fun navigateFragment(id: Int, currentFragment: Int, args: Bundle?, removeFromBack: Boolean) {


        navController?.navigate(
            id, args,
            NavOptions.Builder().setPopUpTo(
                currentFragment,
                removeFromBack
            ).setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left).build()
        )
    }

    /****************************** Navigation fragment handler *****************************************/
    fun navigateFragmentAnim(
        id: Int,
        currentFragment: Int,
        args: Bundle?,
        removeFromBack: Boolean
    ) {

        navController?.navigate(
            id, args,
            NavOptions.Builder().setPopUpTo(
                currentFragment,
                removeFromBack
            ).setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.fade_out).build()
        )
    }

    fun navigateSubContentFragment(id: Int, args: Bundle?) {

        navController?.navigate(
            id, args,
            NavOptions.Builder().setEnterAnim(R.anim.fade_in)
                .setExitAnim(R.anim.fade_out).build()
        )
    }


    fun showErrorMessage(msg: String) {
      //  Utils.showSnackbarError(myView, msg)
    }

    fun showAlertMessage(msg: String) {

       // baseActivity?.showAlertMessage(msg)
    }
    fun showMessageMessage(msg: String) {
       // Utils.showSnackbar(myView, msg)
    }

    /**************************Save Wedding code***************************************/

    /**************************Get Wedding code***************************************/


    fun setLazyLoaderForRecyclerView(view: RecyclerView, onLoad: (Boolean) -> Unit) {
        val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = view.layoutManager?.getChildCount()
                val totalItemCount = view.layoutManager?.getItemCount()
                val firstVisibleItemPosition =
                    (view.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (visibleItemCount != null) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount!!
                        && firstVisibleItemPosition >= 0
                    ) {

                        onLoad(true)
                    }

                }
            }
        }

        view.addOnScrollListener(recyclerViewOnScrollListener)
    }

    fun showHide(boolean: Boolean) {
        //  val act=activity as ActivityHome
        // act.hideShowIcon(boolean)
    }


    fun replaceFragment(fragment: Fragment) {
        suBaseActivity?.replaceFragment(fragment)
    }


    /****************************** Register view models *****************************************/
    fun setViewModels(viewModel: BaseViewModel?) {
        viewModel?.apiError?.observe(this, androidx.lifecycle.Observer {

            showErrorMessage(it)

        })

        viewModel?.isLoading?.observe(this, androidx.lifecycle.Observer {

            if (it) {
                baseActivity?.showProgress()
            } else {
                baseActivity?.hideProgress()
            }

        })

    }


    /****************************Save Login response in DB*******************************************************/


    protected open fun onBecameVisibleToUser() {
        //
    }


    protected open fun onRecycle() {
        //
    }

    protected open fun onBecameInvisibleToUser() {
        //
    }


    fun  showProgress()
    {
        suBaseActivity?.showProgress()
    }

    fun hideProgress()
    {
        suBaseActivity?.hideProgress()
    }


    fun showHTMLTEXT(textView:TextView,text: String?)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView.setText(Html.fromHtml(text));
        }
    }

    fun addSubContentFragment(fragment: Fragment) {
        suBaseActivity?.addSubContentFragment(fragment)
    }

}
