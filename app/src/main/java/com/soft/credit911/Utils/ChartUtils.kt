package com.soft.credit911

import android.R
import android.content.Context
import android.graphics.Color
import android.util.Log
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.soft.credit911.datamodel.DashboardResponse


class ChartUtils {
    var knowledgeAreas: ArrayList<DashboardResponse.CreditReportHistoryItem>? = ArrayList()
    var barSet = ArrayList<String>()
    var chart: LineChart? = null
    var context:Context?=null

    fun  setChartData(chart: LineChart?, knowledgeAreas: ArrayList<DashboardResponse.CreditReportHistoryItem>?) {
        if(knowledgeAreas?.size!=0) {
            this.chart = chart
            this.knowledgeAreas?.clear()
            knowledgeAreas?.let { this.knowledgeAreas?.addAll(it) }
            setBarChartInfo()
        }
    }


    fun setBarChartInfo() {

        chart?.setPinchZoom(false)
        chart?.setDrawGridBackground(false)
        val leftAxis = chart?.getAxisLeft()
        val rightAxis = chart?.getAxisRight()
        leftAxis?.mAxisMinimum = 0f
        rightAxis?.setAxisMaxValue(100f)
        leftAxis?.setAxisMaxValue(100f)
        chart?.getAxisRight()?.setEnabled(false)
        chart?.getAxisLeft()?.setEnabled(false)
        val xLabels = chart?.getXAxis()
        val yLabels = chart?.getAxisLeft()
        xLabels?.setPosition(XAxis.XAxisPosition.BOTTOM)
        val l = chart?.getLegend()
        l?.setFormSize(8f)
        l?.setFormToTextSpace(5f)
        l?.setXEntrySpace(10f)
        l?.setEnabled(false)
        chart?.getAxisLeft()?.setLabelCount(0, false)
        chart?.setExtraOffsets(0f, 0f, 0f, 0f);
        chart?.getLegend()?.setWordWrapEnabled(true);
        setData()

    }




    fun setData() {
        val values = ArrayList<Entry>()
        val colors = ArrayList<Int>()
        for (obj in knowledgeAreas!!) {
            barSet.add(obj.scoreDate ?: "")

            Log.e("FF"+knowledgeAreas?.indexOf(obj)?.toFloat(),""+obj.score)
            values.add(Entry(
                knowledgeAreas?.indexOf(obj)?.toFloat() as Float,
                ((obj.score?.toInt()?:0/1.0f).toFloat())
            ))
            colors.add(Color.parseColor("#00655d"))
        }

        val bardataset = LineDataSet(values, "")
        chart!!.animateY(0)
        val data = LineData(bardataset as ILineDataSet)
        bardataset.setColors(colors)
        bardataset.setDrawValues(false)
        chart?.setDescription(null);
        chart?.setPinchZoom(false)
        chart?.setScaleEnabled(false)
        chart?.extraLeftOffset=0.0f
        val rightYAxis: YAxis? = chart?.getAxisRight()

        rightYAxis?.isEnabled = true
        rightYAxis?.setAxisMinValue(0.0f)
        rightYAxis?.setAxisMaxValue(100.0f)

        val leftAxis: YAxis? = chart?.axisLeft
        leftAxis?.setAxisMinValue(0f);
        chart?.data = data
    }
    }