package com.yunchen.m03homework

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.content, MessageFragment()).commit()

        message.setOnClickListener { switchFragment(TabName.Message) }
        contact.setOnClickListener { switchFragment(TabName.Contact) }
        discover.setOnClickListener { switchFragment(TabName.Discover) }
        me.setOnClickListener { switchFragment(TabName.Me) }
        removeAll.setOnClickListener { removeAllFragment() }
        replace.setOnClickListener { replaceFragment() }
        banner_image.setOnClickListener { openNewActivity() }
    }

    @SuppressLint("ShowToast")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = data?.extras?.getString("text")

        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()

        super.onActivityResult(requestCode, resultCode, data)
    }

    private val messageFragment = MessageFragment()
    private val contactFragment = ContactFragment()
    private val discoverFragment = DiscoverFragment()
    private val meFragment = MeFragment()
    private val replacementFragment = ReplacementFragment()

    private fun switchFragment(tabName: TabName) {

        val fragment: Fragment = when (tabName) {
            TabName.Message -> messageFragment
            TabName.Contact -> contactFragment
            TabName.Discover -> discoverFragment
            TabName.Me -> meFragment
        }

        supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
    }

    enum class TabName {
        Message,
        Contact,
        Discover,
        Me
    }

    private fun removeAllFragment() {
        supportFragmentManager.beginTransaction()
            .remove(messageFragment)
            .remove(contactFragment)
            .remove(discoverFragment)
            .remove(meFragment)
            .remove(replacementFragment)
            .commit()
    }

    private fun replaceFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, replacementFragment)
            .commit()
    }

    private fun openNewActivity() {
        val intent = Intent(this, SecondActivity::class.java)

        startActivityForResult(intent, 1000)
    }

}
