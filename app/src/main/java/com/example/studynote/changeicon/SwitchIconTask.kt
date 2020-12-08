package com.example.studynote.changeicon

data class SwitchIconTask(val launcherComponentClassName: String,  // 启动器组件类名
                          val aliasComponentClassName: String,  // 别名组件类名
                          val presetTime: Long,            // 预设时间
                          val outDateTime: Long)