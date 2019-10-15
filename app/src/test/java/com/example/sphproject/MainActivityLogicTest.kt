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
    val data1: ArrayList<RecordsResponse> = arrayListOf()
    val data2: ArrayList<RecordsResponse> = arrayListOf()
    val data3: ArrayList<RecordsResponse> = arrayListOf()

    @Before
    fun setup() {
        data1.add(RecordsResponse("1.1", "2009-1",1))
        data1.add(RecordsResponse("3.1", "2009-2",2))
        data1.add(RecordsResponse("2.1", "2009-3",3))
        data1.add(RecordsResponse("2.1", "2010-1",4))

        data2.add(RecordsResponse("1.1", "2009-1",1))
        data2.add(RecordsResponse("3.1", "2010-2",2))
        data2.add(RecordsResponse("2.1", "2010-3",3))
        data2.add(RecordsResponse("2.1", "2010-1",4))

        data3.add(RecordsResponse("1.1", "2009-1",1))
        data3.add(RecordsResponse("3.1", "2010-2",2))
        data3.add(RecordsResponse("2.1", "2010-3",3))
        data3.add(RecordsResponse("2.1", "2010-1",4))
        data3.add(RecordsResponse("1.1", "2011-1",1))
        data3.add(RecordsResponse("3.1", "2012-2",2))
        data3.add(RecordsResponse("2.1", "2013-3",3))
        data3.add(RecordsResponse("2.1", "2013-1",4))
    }
    @Test
    fun testCalculationsForData1() {
        val expectedSum = (1.1+3.1+2.1).toString()
        val displayDataModel : ArrayList<DisplayDataModel> = mainActivity.addQuarterData(data1)
        Assert.assertEquals(displayDataModel[0].totalVol, expectedSum)
        Assert.assertEquals(displayDataModel[0].year, "2009")
        Assert.assertEquals(displayDataModel[0].hasDecreaseVol, true)

        Assert.assertEquals(displayDataModel[1].year, "2010")
        Assert.assertEquals(displayDataModel[1].totalVol, "2.1")
        Assert.assertEquals(displayDataModel[1].hasDecreaseVol, false)
    }

    @Test
    fun testCalculationsForData2() {
        val expectedSum = (1.1).toString()
        val expectedSum1 = (3.1 + 2.1 + 2.1).toString()

        val displayDataModel : ArrayList<DisplayDataModel> = mainActivity.addQuarterData(data2)
        Assert.assertEquals(displayDataModel[0].totalVol, expectedSum)
        Assert.assertEquals(displayDataModel[0].year, "2009")
        Assert.assertEquals(displayDataModel[0].hasDecreaseVol, false)

        Assert.assertEquals(displayDataModel[1].year, "2010")
        Assert.assertEquals(displayDataModel[1].totalVol, expectedSum1)
        Assert.assertEquals(displayDataModel[1].hasDecreaseVol, false)
    }

    @Test
    fun testCalculationsForData3() {
        val expectedSum = (3.1+2.1+2.1).toString()
        val expectedSum1 = (2.1+2.1).toString()

        val displayDataModel : ArrayList<DisplayDataModel> = mainActivity.addQuarterData(data3)
        Assert.assertEquals(displayDataModel[0].totalVol, "1.1")
        Assert.assertEquals(displayDataModel[0].year, "2009")
        Assert.assertEquals(displayDataModel[0].hasDecreaseVol, false)

        Assert.assertEquals(displayDataModel[1].year, "2010")
        Assert.assertEquals(displayDataModel[1].totalVol, expectedSum)
        Assert.assertEquals(displayDataModel[1].hasDecreaseVol, false)

        Assert.assertEquals(displayDataModel[2].year, "2011")
        Assert.assertEquals(displayDataModel[2].totalVol, "1.1")
        Assert.assertEquals(displayDataModel[2].hasDecreaseVol, false)

        Assert.assertEquals(displayDataModel[3].year, "2012")
        Assert.assertEquals(displayDataModel[3].totalVol, "3.1")
        Assert.assertEquals(displayDataModel[3].hasDecreaseVol, false)

        Assert.assertEquals(displayDataModel[4].year, "2013")
        Assert.assertEquals(displayDataModel[4].totalVol, expectedSum1)
        Assert.assertEquals(displayDataModel[4].hasDecreaseVol, false)
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
