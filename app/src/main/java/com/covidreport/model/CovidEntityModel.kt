package com.covidreport.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "covidReport")
data class CovidEntityModel(

    @PrimaryKey
    @SerializedName("statecode")
    var stateCode: String,

    @SerializedName("active")
    var active: Int,

    @SerializedName("confirmed")
    var confirmed: Int,

    @SerializedName("deaths")
    var deaths: Int,

    @SerializedName("deltaconfirmed")
    var deltaConfirmed: Int,

    @SerializedName("deltadeaths")
    var deltaDeaths : Int,

    @SerializedName("deltarecovered")
    var deltaRecovered : Int,

    @SerializedName("lastupdatedtime")
    var lastUpdatedTime : String,

    @SerializedName("migratedother")
    var migratedOther : Int,

    @SerializedName("recovered")
    var recovered: Int,

    @SerializedName("state")
    var state: String,

    @SerializedName("statenotes")
    var stateNotes: String? = null


)