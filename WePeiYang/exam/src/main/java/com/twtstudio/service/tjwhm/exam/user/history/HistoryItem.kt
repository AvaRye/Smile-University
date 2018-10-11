package com.twtstudio.service.tjwhm.exam.user.history

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.twt.wepeiyang.commons.ui.rec.Item
import com.twt.wepeiyang.commons.ui.rec.ItemController
import com.twtstudio.service.tjwhm.exam.R
import com.twtstudio.service.tjwhm.exam.commons.toMode
import com.twtstudio.service.tjwhm.exam.user.OneHistoryBean
import org.jetbrains.anko.layoutInflater
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by tjwhm@TWTStudio at 5:18 PM,2018/8/9.
 * Happy coding!
 */

class HistoryItem(val context: Context, val oneHistoryBean: OneHistoryBean) : Item {
    override val controller: ItemController
        get() = Controller

    companion object Controller : ItemController {
        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
                ItemViewHolder(parent.context.layoutInflater.inflate(R.layout.exam_item_history, parent, false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item) {
            holder as ItemViewHolder
            item as HistoryItem
            holder.apply {
                tvType?.text = item.oneHistoryBean.type.toInt().toMode()
                tvDate?.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(Date(item.oneHistoryBean.timestamp.toLong() * 1000L))
                tvTitle?.text = item.oneHistoryBean.course_name
            }
        }

    }

    private class ItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val tvType: TextView? = itemView?.findViewById(R.id.tv_history_item_type)
        val tvTitle: TextView? = itemView?.findViewById(R.id.tv_history_item_title)
        val tvDate: TextView? = itemView?.findViewById(R.id.tv_history_date)
    }
}

fun MutableList<Item>.historyItem(context: Context, oneHistoryBean: OneHistoryBean) = add(HistoryItem(context, oneHistoryBean))