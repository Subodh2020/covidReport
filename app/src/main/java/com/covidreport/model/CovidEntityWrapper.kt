package com.covidreport.model

import com.google.gson.annotations.SerializedName

data class CovidEntityWrapper(

    @SerializedName("statewise")
    var stateWise : List<CovidEntityModel>
)