package org.fruitsalad.greenplanet.utility;

import com.anychart.chart.common.dataentry.HeatDataEntry;

public class CustomHeatDataEntry extends HeatDataEntry {
    CustomHeatDataEntry(String x, String y, Integer heat, String fill) {
        super(x, y, heat);
        setValue("fill", fill);
    }
}