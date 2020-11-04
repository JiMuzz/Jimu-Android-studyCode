package com.example.studynote.Hilt

import javax.inject.Inject

data class UserData(var name: String) {

    @Inject
    constructor() : this("bob")



}