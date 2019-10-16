package com.example.sphproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.sphproject.Adapter.MobileDataAdapter
import com.example.sphproject.Models.BaseResponse
import com.example.sphproject.ViewModel.MobileDataViewModel
import com.example.sphproject.Models.DisplayDataModel
import com.example.sphproject.Models.RecordsResponse
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    private var total: String = "59"
    private var isRecall = false
    lateinit var mobileDataViewModel: MobileDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mobileDataViewModel = ViewModelProviders.of(this).get(MobileDataViewModel::class.java)
        callAPI()
    }

    private fun callAPI() {
        if (isRecall)
            mobileDataViewModel.getMobileVolData()?.removeObserver(observer)
        mobileDataViewModel.init(total)
        mobileDataViewModel.getMobileVolData()?.observe(this, observer)
    }

    val observer = Observer<BaseResponse> { response ->
        if (response.isSuccess) {
            val data = response.serviceResponse.result.records

            if (response.serviceResponse.result.total > total.toInt()) {
                total = response.serviceResponse.result.total.toString()
                isRecall = true
                callAPI()
            } else {
                setupRecyclerView(data)
            }
        } else {
            Toast.makeText(applicationContext, response.errorMsg, Toast.LENGTH_LONG).show()
        }
    }

    fun setupRecyclerView(data: ArrayList<RecordsResponse>) {
        val recyclerView: RecyclerView = findViewById(R.id.list)

        recyclerView.setHasFixedSize(true)
        // use a linear layout manager
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setLayoutManager(layoutManager)
        val mAdapter = MobileDataAdapter(addQuarterData(data), applicationContext)
        recyclerView.setAdapter(mAdapter)
    }

    // calculation of the total vol per year
    fun addQuarterData(response: ArrayList<RecordsResponse>): ArrayList<DisplayDataModel> {
        val displayDataArray: ArrayList<DisplayDataModel> = arrayListOf()
        var tempYear = ""
        var tempVol = 0.0
        var volumes = 0.0
        var hasDecresedVol = false
        for (i in 0..response.size - 1) {
            val year = getYear(response.get(i).quarter)
            if (i == 0)
                tempYear = year
            if (year.equals(tempYear)) {
                val vol = response.get(i).volume_of_mobile_data.toDouble()
                hasDecresedVol = hasDecreasedVol(tempVol, vol)
                volumes += vol
                tempVol = vol
            } else {
                displayDataArray.add(DisplayDataModel(tempYear, volumes.toString(), hasDecresedVol))
                tempYear = year
                volumes = response.get(i).volume_of_mobile_data.toDouble()
                tempVol = volumes
                hasDecresedVol = false

            }
            if (i == response.size - 1)
                displayDataArray.add(
                    DisplayDataModel(
                        tempYear,
                        volumes.toString(),
                        hasDecresedVol
                    )
                )
        }
        return displayDataArray
    }

    fun getYear(quarter: String): String {
        val split = quarter.split("-")
        if (split.size > 1) {
            return split.get(0)
        }
        return ""
    }

    fun hasDecreasedVol(prevVol: Double, vol: Double): Boolean {
        return prevVol > vol
    }
}

