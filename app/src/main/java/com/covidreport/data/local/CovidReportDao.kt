package com.covidreport.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.covidreport.model.CovidEntityModel

@Dao
interface CovidReportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCovidReports(covidEntityModel: List<CovidEntityModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCovidReport(covidEntityModel: CovidEntityModel)

    @Query("SELECT * FROM covidReport order by state ASC")
    fun getCovidReportList(): List<CovidEntityModel>
}