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
    val data4: ArrayList<RecordsResponse> = arrayListOf()

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

        data4.add(RecordsResponse("1.1", "2009-1",1))
        data4.add(RecordsResponse("13.555", "2010-2",2))
        data4.add(RecordsResponse("55211.44", "2013-3",3))
        data4.add(RecordsResponse("8810", "2019-1",4))
    }

    @Test
    fun testCalculationsForData4() {
        val displayDataModel : ArrayList<DisplayDataModel> = mainActivity.addQuarterData(data4)
        assertEquals(displayDataModel[0].totalVol, "1.1")
        assertEquals(displayDataModel[0].year, "2009")
        assertEquals(displayDataModel[0].hasDecreaseVol, false)

        assertEquals(displayDataModel[1].year, "2010")
        assertEquals(displayDataModel[1].totalVol, "13.555")
        assertEquals(displayDataModel[1].hasDecreaseVol, false)


        assertEquals(displayDataModel[2].year, "2013")
        assertEquals(displayDataModel[2].totalVol, "55211.44")
        assertEquals(displayDataModel[2].hasDecreaseVol, false)


        assertEquals(displayDataModel[3].year, "2019")
        assertEquals(displayDataModel[3].totalVol, "8810.0")
        assertEquals(displayDataModel[3].hasDecreaseVol, false)
    }
    @Test
    fun testCalculationsForData1() {
        val expectedSum = (1.1+3.1+2.1).toString()
        val displayDataModel : ArrayList<DisplayDataModel> = mainActivity.addQuarterData(data1)
        assertEquals(displayDataModel[0].totalVol, expectedSum)
        assertEquals(displayDataModel[0].year, "2009")
        assertEquals(displayDataModel[0].hasDecreaseVol, true)

        assertEquals(displayDataModel[1].year, "2010")
        assertEquals(displayDataModel[1].totalVol, "2.1")
        assertEquals(displayDataModel[1].hasDecreaseVol, false)
    }

    @Test
    fun testCalculationsForData2() {
        val expectedSum = (1.1).toString()
        val expectedSum1 = (3.1 + 2.1 + 2.1).toString()

        val displayDataModel : ArrayList<DisplayDataModel> = mainActivity.addQuarterData(data2)
        assertEquals(displayDataModel[0].totalVol, expectedSum)
        assertEquals(displayDataModel[0].year, "2009")
        assertEquals(displayDataModel[0].hasDecreaseVol, false)

        assertEquals(displayDataModel[1].year, "2010")
        assertEquals(displayDataModel[1].totalVol, expectedSum1)
        assertEquals(displayDataModel[1].hasDecreaseVol, false)
    }

    @Test
    fun testCalculationsForData3() {
        val expectedSum = (3.1+2.1+2.1).toString()
        val expectedSum1 = (2.1+2.1).toString()

        val displayDataModel : ArrayList<DisplayDataModel> = mainActivity.addQuarterData(data3)
        assertEquals(displayDataModel[0].totalVol, "1.1")
        assertEquals(displayDataModel[0].year, "2009")
        assertEquals(displayDataModel[0].hasDecreaseVol, false)

        assertEquals(displayDataModel[1].year, "2010")
        assertEquals(displayDataModel[1].totalVol, expectedSum)
        assertEquals(displayDataModel[1].hasDecreaseVol, false)

        assertEquals(displayDataModel[2].year, "2011")
        assertEquals(displayDataModel[2].totalVol, "1.1")
        assertEquals(displayDataModel[2].hasDecreaseVol, false)

        assertEquals(displayDataModel[3].year, "2012")
        assertEquals(displayDataModel[3].totalVol, "3.1")
        assertEquals(displayDataModel[3].hasDecreaseVol, false)

        assertEquals(displayDataModel[4].year, "2013")
        assertEquals(displayDataModel[4].totalVol, expectedSum1)
        assertEquals(displayDataModel[4].hasDecreaseVol, false)
    }

    @Test
    fun testGetYears(){
        val mainActivity = MainActivity()

        val year = mainActivity.getYear("2009-8")
        assertEquals(year, "2009")
    }

    @Test
    fun testGetBlankYears(){
        val mainActivity = MainActivity()

        val year = mainActivity.getYear("20098")
        assertEquals(year, "")
    }

    @Test
    fun testHasDecreasedVol(){
        val mainActivity = MainActivity()

        val hasDecreasedVol = mainActivity.hasDecreasedVol(8888.255, 4444.4)
        assertEquals(hasDecreasedVol, true)
    }

}
