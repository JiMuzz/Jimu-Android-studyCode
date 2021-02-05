package com.panda.buildsrc

import org.gradle.api.Plugin
import org.gradle.api.Project

class Jimu implements Plugin<Project> {
    @Override
    void apply(Project target) {
        def test = target.extensions.create("jimu",TestExtension)
        target.afterEvaluate {
            println "nihao ${test.age}"
        }
    }
}