package com.twt.service.ecard.window

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.twt.service.ecard.R
import com.twt.service.ecard.model.TransactionInfo
import kotlinx.android.synthetic.main.ecard_transaction_top_pop.view.*
import org.jetbrains.anko.*

class ECardTransactionPop(context: Context, val transactionInfo: TransactionInfo) : TopPopupWindow(context) {
    override fun createContentView(parent: ViewGroup?): View {
        val view = context.layoutInflater.inflate(R.layout.ecard_transaction_top_pop, parent, false).apply {
            layoutParams = FrameLayout.LayoutParams(matchParent, wrapContent).apply {
                gravity = Gravity.TOP
            }
        }
        view.apply {
            tv_transaction_amount.text = "-￥${transactionInfo.amount}"
            tv_trans_location.text = transactionInfo.location
            val transactionTime = "${transactionInfo.time.substring(0,2)}:${transactionInfo.time.substring(2,4)}"
            tv_trans_time.text = "${transactionInfo.date} - $transactionTime"
            tv_trans_balance.text = "交易后余额：${transactionInfo.balance}"
            tv_trans_type.text = "POS消费"
        }

        return view
    }


    init {
        isDismissOnClickBack = true
        isDismissOnTouchBackground = true
    }
}