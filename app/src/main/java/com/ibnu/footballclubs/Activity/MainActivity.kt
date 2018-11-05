package com.ibnu.footballclubs.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.ibnu.footballclubs.Data.Item
import com.ibnu.footballclubs.R
import com.ibnu.footballclubs.RecyclerViewAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userInterface = MainActivityUI().apply {
            setContentView(this@MainActivity)
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        // val list = findViewById<RecyclerView>(R.id.club_list)

        initData()

        /*
        // Android Extensions implementation
        club_list.layoutManager = LinearLayoutManager(this)
        club_list.adapter = RecyclerViewAdapter(this, items) {
            // Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT).show()
            startActivity<DetailsActivity>("name" to it.name, "image" to it.image, "details" to it.details)
        }
        */

        userInterface.recyclerView.adapter = RecyclerViewAdapter(this@MainActivity, items) {
            startActivity<DetailsActivity>(
                "name" to it.name,
                "image" to it.image,
                "details" to it.details
            )
        }
    }

    class MainActivityUI: AnkoComponent<MainActivity>{
        lateinit var recyclerView: RecyclerView

        override fun createView(ui: AnkoContext<MainActivity>) = with(ui){
            verticalLayout{
                lparams(width = matchParent, height = matchParent)

                recyclerView = recyclerView().lparams(width = matchParent, height = matchParent)
            }
        }
    }

    // Function to get the initial Data
    private fun initData() {

        val name = resources.getStringArray(R.array.club_names)
        val image = resources.obtainTypedArray(R.array.club_images)
        val details = resources.getStringArray(R.array.club_details)
        items.clear()

        for (i in name.indices) {
            items.add(
                Item(
                    name[i],
                    image.getResourceId(i, 0),
                    details[i]
                )
            )
        }

        //Recycle the typed array
        image.recycle()
    }
}
