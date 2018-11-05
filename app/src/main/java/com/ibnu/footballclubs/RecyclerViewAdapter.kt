package com.ibnu.footballclubs

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.*

class RecyclerViewAdapter(
    private val context: Context,
    private val items: List<Item>,
    private val listener: (Item) -> Unit
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(RecyclerViewAdapterUI().createView(AnkoContext.create(parent.context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        /*
            private val name = view.findViewById<TextView>(R.id.name)
            private val image = view.findViewById<ImageView>(R.id.image)
         */

        private val clubName = containerView.find<TextView>(R.id.club_name)
        private val clubLogo = containerView.find<ImageView>(R.id.club_logo)

        fun bindItem(items: Item, listener: (Item) -> Unit) {

            // Android Extensions implementation
            clubName.text = items.name
            items.image?.let { Picasso.get().load(it).into(clubLogo) }

            // Set OnClick Listener for Higher-Order Function to execute
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }

    class RecyclerViewAdapterUI : AnkoComponent<ViewGroup> {

        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {

            // Vertical Linear Layout
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)

                // ImageView for the Club Logo
                imageView{
                    id = R.id.club_logo
                    layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
                }.lparams(width = dip(40), height = dip(40))

                // TextView for the Club Name
                textView {
                    id = R.id.club_name
                    layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
                    textSize = 16f
                }.lparams{
                    margin = dip(16)
                }
            }
        }
    }
}