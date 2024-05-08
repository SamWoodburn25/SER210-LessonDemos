package edu.quinnipiac.ser210.lesson5

/*
 * Lesson 3- SER210- navigation practice
 * Samantha Woodburn
 * 1/30/24
 * main activity
 */

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.ShareActionProvider
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() , androidx.appcompat.widget.SearchView.OnQueryTextListener{
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment), drawerLayout)
        setupActionBarWithNavController(navController!!, appBarConfiguration)

        findViewById<NavigationView>(R.id.navigationView).setupWithNavController(navController!!)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp(appBarConfiguration)!!
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val retValue = super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        val search = menu?.findItem(R.id.action_search)
        val searchView = search?.actionView as androidx.appcompat.widget.SearchView

        searchView?.apply {
            isSubmitButtonEnabled = true
            setOnQueryTextListener(this@MainActivity)
        }
        return retValue
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when(id){
            R.id.action_setting -> {
                Toast.makeText(this, "here is my settings", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_share -> {
                val shareActionProvider = androidx.appcompat.widget.ShareActionProvider(this)
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "this is a message for you")
                if(shareActionProvider!=null)
                    shareActionProvider.setShareIntent(intent)
                true
            }

            else -> NavigationUI.onNavDestinationSelected(item!!, navController)
                    ||super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Toast.makeText(this, "Searching", Toast.LENGTH_SHORT).show()
        return true
    }
}