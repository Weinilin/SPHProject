package com.example.sphproject

import com.example.sphproject.Models.DisplayDataModel
import com.example.sphproject.Models.RecordsResponse
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainActivityLogicTest {
    val mainActivity = MainActivity()
    val arr: ArrayList<RecordsResponse> = arrayListOf()

    @Before
    fun setup() {
        arr.add(RecordsResponse("1.1", "2009-1",1))
        arr.add(RecordsResponse("3.1", "2009-2",2))
        arr.add(RecordsResponse("2.1", "2009-3",3))
        arr.add(RecordsResponse("2.1", "2010-1",4))
    }
    @Test
    fun testCalculations() {
        val expectedSum = (1.1+3.1+2.1).toString()
        val displayDataModel : ArrayList<DisplayDataModel> = mainActivity.addQuarterData(arr)
        Assert.assertEquals(displayDataModel[0].totalVol, expectedSum)
        Assert.assertEquals(displayDataModel[0].year, "2009")
        Assert.assertEquals(displayDataModel[0].hasDecreaseVol, true)

        Assert.assertEquals(displayDataModel[1].year, "2010")
        Assert.assertEquals(displayDataModel[1].totalVol, "2.1")
        Assert.assertEquals(displayDataModel[1].hasDecreaseVol, false)
    }

    @Test
    fun testGetYears(){
        val mainActivity = MainActivity()

        val year = mainActivity.getYear("2009-8")
        Assert.assertEquals(year, "2009")
    }

    @Test
    fun testGetBlankYears(){
        val mainActivity = MainActivity()

        val year = mainActivity.getYear("20098")
        Assert.assertEquals(year, "")
    }

    @Test
    fun testHasDecreasedVol(){
        val mainActivity = MainActivity()

        val hasDecreasedVol = mainActivity.hasDecreasedVol(8888.255, 4444.4)
        Assert.assertEquals(hasDecreasedVol, true)
    }

}
