package com.soft.credit911

import android.content.Context
import android.graphics.Color
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.soft.credit911.datamodel.DashboardResponse
import com.soft.credit911.model.ScoresItem
import java.text.SimpleDateFormat


class ChartUtilDetailed {
    var knowledgeAreas: ArrayList<ScoresItem>? = ArrayList()
    var barSet = ArrayList<String>()
    var chart: LineChart? = null
    var context:Context?=null

    fun  setChartData(chart: LineChart, knowledgeAreas: ArrayList<ScoresItem>?) {
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
        chart?.setExtraOffsets(0f, 30f, 0f, 0f);
        chart?.getLegend()?.setWordWrapEnabled(true);
        setData()

    }




    fun setData() {
        val entries: MutableList<Entry> = ArrayList()
        val xValsDateLabel = ArrayList<String>()

        for(i in 0 until knowledgeAreas!!.size){
            val entry= knowledgeAreas?.get(i)?.Y?.toInt()?.let { Entry(i.toFloat(), it?.toFloat()) }
            entry?.let { entries.add(it) }
            xValsDateLabel.add(knowledgeAreas?.get(i)?.label.toString())
        }
        val vf: ValueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "" + value.toInt()
            }
        }
        val set1 = LineDataSet(entries, "CK_NAVEEN")
        set1.setValueFormatter(vf)
        var dataSet = java.util.ArrayList<ILineDataSet>()
        dataSet.add(set1)

        val lineData = LineData(dataSet)
        chart?.data = lineData
        chart?.getData()?.setHighlightEnabled(false);
        set1.color = Color.parseColor("#31fce8")
        set1.mode = LineDataSet.Mode.LINEAR
        chart?.description?.text = ""
        chart?.isDoubleTapToZoomEnabled=false
        chart?.fitScreen()
        chart?.setScaleEnabled(false);
        chart?.legend?.isEnabled = false
        val xAxis = chart?.xAxis
        xAxis?.position = XAxis.XAxisPosition.BOTTOM
        xAxis?.setDrawGridLines(false)
        chart?.axisLeft?.isEnabled = true
        chart?.getXAxis()?.setLabelCount(knowledgeAreas?.size?:0, true)
        xAxis?.valueFormatter = (MyValueFormatter(xValsDateLabel))
        chart?.getAxisLeft()?.setAxisMaxValue(900f);
        chart?.getAxisLeft()?.setAxisMinValue(0f);
        chart?.getAxisLeft()?.setLabelCount(9);
//        xAxis?.setAxisMinValue(-.30f)
        xAxis?.granularity = 1f
        xAxis?.isGranularityEnabled = true
        chart?.setPadding(200 , 2 , 2 , 2)
        chart?.setExtraLeftOffset(15f);
        chart?.setExtraRightOffset(15f);
        chart?.setViewPortOffsets(60f, 10f, 50f, 60f)
        chart?.setExtraLeftOffset(36f);
        chart?.getAxisLeft()?.setDrawGridLines(false);
        chart?.getXAxis()?.setDrawGridLines(false);
        chart?.invalidate()
    }



    class MyValueFormatter(private val xValsDateLabel: ArrayList<String>) : ValueFormatter() {

        override fun getFormattedValue(value: Float): String {
            return value.toString()
        }

        override fun getAxisLabel(value: Float, axis: AxisBase): String {
            val parser = SimpleDateFormat("dd/MM/yyyy")
            if (value.toInt() >= 0 && value.toInt() <= xValsDateLabel.size - 1) {
                val date = parser.parse(xValsDateLabel[value.toInt()])
                val outputFormat = SimpleDateFormat("MMM")
                return outputFormat.format(date)
            } else {
                return ("").toString()
            }
        }
    }
    }