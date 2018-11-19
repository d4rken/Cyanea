package com.jaredrummler.cyanea.app

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.FragmentActivity
import com.jaredrummler.cyanea.Cyanea
import com.jaredrummler.cyanea.CyaneaResources
import com.jaredrummler.cyanea.delegate.CyaneaDelegate

/**
 * Base class for a [FragmentActivity] that use [Cyanea] for dynamic themes.
 *
 * You must implement [BaseCyaneaActivity.getThemeResId] and return a valid cyanea theme.
 */
abstract class CyaneaFragmentActivity : FragmentActivity(), BaseCyaneaActivity {

  private val delegate: CyaneaDelegate by lazy {
    CyaneaDelegate.create(this, cyanea, getThemeResId())
  }

  private val resources: CyaneaResources by lazy {
    CyaneaResources(super.getResources(), cyanea)
  }

  override fun attachBaseContext(newBase: Context) {
    super.attachBaseContext(delegate.wrap(newBase))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    delegate.onCreate(savedInstanceState)
    super.onCreate(savedInstanceState)
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    delegate.onPostCreate(savedInstanceState)
  }

  override fun onStart() {
    super.onStart()
    delegate.onStart()
  }

  override fun onResume() {
    super.onResume()
    delegate.onResume()
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    delegate.onCreateOptionsMenu(menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun getResources(): Resources = resources

}