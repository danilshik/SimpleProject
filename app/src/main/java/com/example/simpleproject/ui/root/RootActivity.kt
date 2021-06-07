package com.example.simpleproject.ui.root

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.circleCropTransform
import com.example.simpleproject.*
import com.example.simpleproject.ui.base.NavigationCommand
import com.example.simpleproject.ui.base.Notify
import com.example.simpleproject.databinding.AcMainBinding
import com.example.simpleproject.extensions.dpToIntPx
import com.example.simpleproject.extensions.selectDestination
import com.google.android.material.snackbar.Snackbar


class RootActivity : AppCompatActivity() {
    private lateinit var bind : AcMainBinding
    lateinit var navController: NavController

    val toolbarBuilder = ToolbarBuilder()


    public val viewModel: RootViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        AppDelegate.appComponent.inject(this)
        bind = AcMainBinding.inflate(layoutInflater)

        setContentView(bind.root)
        setSupportActionBar(bind.toolbar)
        initViews()
        initViewModel()


    }

    private fun initViews(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_location_list, R.id.nav_character_list, R.id.nav_episod_list)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bind.navView.setOnNavigationItemSelectedListener {
            viewModel.navigate(NavigationCommand.To(it.itemId))
            true
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bind.navView.selectDestination(destination)
        }

    }

    private fun initViewModel(){
        viewModel.observeState(this) { subscribeOnState(it) }
        viewModel.observeNotifications(this) { renderNotification(it) }
        viewModel.observeNavigation(this) { subscribeOnNavigation(it) }
    }

    private fun subscribeOnNavigation(navigationCommand: NavigationCommand) {
        when (navigationCommand) {
            is NavigationCommand.To -> {
                navController.navigate(
                    navigationCommand.destination,
                    navigationCommand.args,
                    navigationCommand.options,
                    navigationCommand.extras
                )
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun subscribeOnState(state: IViewModelState){

    }

    fun renderNotification(notify: Notify){
        val snackbar = Snackbar.make(bind.container, notify.message, Snackbar.LENGTH_LONG)
            .apply {
                anchorView = bind.navView
            }

        when (notify) {
            is Notify.TextMessage -> { }

            is Notify.ActionMessage -> {
                snackbar.setAction(notify.actionLabel) { notify.actionHandler.invoke() }
            }

            is Notify.ErrorMessage -> {
                with(snackbar) {
                    setBackgroundTint(ContextCompat.getColor(context, R.color.design_default_color_error))
                    setBackgroundTint(ContextCompat.getColor(context, R.color.design_default_color_error))
                    setTextColor(ContextCompat.getColor(context, android.R.color.white))
                    setActionTextColor(ContextCompat.getColor(context, android.R.color.white))
                    setAction(notify.errLabel) { notify.errHandler?.invoke() }
                }
            }
        }


        snackbar.show()
    }

    inner class ToolbarBuilder {
        var subtitle: String? = null
        var logo: String? = null
        var visibility: Boolean = true
        fun setSubtitle(subtitle: String): ToolbarBuilder {
            this.subtitle = subtitle
            return this
        }

        fun setLogo(logo: String): ToolbarBuilder {
            this.logo = logo
            return this
        }

        fun setVisibility(isVisible: Boolean): ToolbarBuilder {
            this.visibility = isVisible
            return this
        }

        fun invalidate(): ToolbarBuilder {
            this.subtitle = null
            this.logo = null
            this.visibility = true
            return this
        }

        fun prepare(prepareFn: (ToolbarBuilder.() -> Unit)?): ToolbarBuilder {
            prepareFn?.invoke(this)
            return this
        }

        fun build() {

            //show appbar if hidden due to scroll behavior
            bind.appbar.setExpanded(true, true)

            with(bind.toolbar) {
                subtitle = this@ToolbarBuilder.subtitle
                if (this@ToolbarBuilder.logo != null) {
                    val logoSize = context.dpToIntPx(40)
                    val logoMargin = context.dpToIntPx(16)
                    val logoPlaceholder = ContextCompat.getDrawable(context, R.drawable.logo_placeholder)

                    logo = logoPlaceholder

                    val logo = children.last() as? ImageView
                    if (logo != null) {
                        logo.scaleType = ImageView.ScaleType.CENTER_CROP
                        (logo.layoutParams as? Toolbar.LayoutParams)?.let {
                            it.width = logoSize
                            it.height = logoSize
                            it.marginEnd = logoMargin
                            logo.layoutParams = it
                        }

                        Glide.with(context)
                            .load(this@ToolbarBuilder.logo)
                            .apply(circleCropTransform())
                            .override(logoSize)
                            .into(logo)
                    }
                } else {
                    logo = null
                }
            }
        }
    }
}



