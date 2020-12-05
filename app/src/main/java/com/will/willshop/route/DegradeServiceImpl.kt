package com.will.willshop.route

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.DegradeService
import com.alibaba.android.arouter.launcher.ARouter

/**
 * 全局降级服务，当路由时，页面不存在，则重定向到一个统一的错误页
 */
@Route(path = "/degrade/global/service")
class DegradeServiceImpl : DegradeService {
    override fun init(context: Context?) {
        TODO("Not yet implemented")
    }

    override fun onLost(context: Context?, postcard: Postcard?) {
        ARouter.getInstance().build("/degrade/global/activity").greenChannel().navigation();
    }
}