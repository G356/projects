package com.sample.web.admin.geb


import com.sample.web.admin.geb.page.LoginPage
import com.sample.web.admin.geb.page.TopPage
import geb.spock.GebSpec

class ShowTopSpec extends GebSpec {
    def "トップページを表示するシナリオ"() {
        when: "ログインする"
        to LoginPage
        ログインする()
        then:
        at TopPage
    }
}
