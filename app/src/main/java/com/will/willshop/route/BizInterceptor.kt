package com.will.willshop.route

import android.content.Context
import android.widget.Toast
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import java.lang.RuntimeException

@Route(path = "biz_interceptor")
class BizInterceptor : IInterceptor {
    private var context: Context? = null

    override fun init(context: Context?) {
        this.context = context
    }

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        val flag = postcard!!.extra

        if (flag and RouteFlag.FLAG_LOGIN != 0) {
            callback!!.onInterrupt(RuntimeException("need login"))
            showToast("请先登录")
        } else if (flag and RouteFlag.FLAG_AUTH != 0) {
            callback!!.onInterrupt(RuntimeException("need authentication"))
            showToast("请先进行实名认证")
        } else if (flag and RouteFlag.FLAG_VIP != 0) {

        } else {
            callback!!.onContinue(postcard)
        }
    }

    private fun showToast(str: String) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }
}