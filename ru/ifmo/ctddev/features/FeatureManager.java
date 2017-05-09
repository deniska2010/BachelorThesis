package ru.ifmo.ctddev.features;

import java.io.*;
import java.util.*;

import ru.ifmo.ctddev.datasets.CSVWriter;

import org.json.simple.parser.*;

import ru.ifmo.ctddev.utils.FileRead;

public class FeatureManager {

    public ArrayList<Feature> JsonParse(String json) throws FileNotFoundException {
        /*
        String json = "";
        try {
            json = FileRead.read(jsonFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        ArrayList<Feature> featureList = new ArrayList<>();
        try {
            FeatureCountOrders featureCountOrders = new FeatureCountOrders();
            Feature f1 = featureCountOrders.createFeature(json);
            featureList.add(f1);
            FeatureCountPerformers featureCountPerformers = new FeatureCountPerformers();
            Feature f2 = featureCountPerformers.createFeature(json);
            featureList.add(f2);
            FeatureCountTransports featureCountTrasports = new FeatureCountTransports();
            Feature f3 = featureCountTrasports.createFeature(json);
            featureList.add(f3);
            GeolocationFeature geolocationFeature = new GeolocationFeature();
            Feature f4 = geolocationFeature.createFeature(json);
            featureList.add(f4);
            Feature f5 = RangMaxStorageTimeMatrixFeature.createFeature(json);
            featureList.add(f5);
            Feature f6 = RangOnBoardFeature.createFeature(json);
            featureList.add(f6);
            Feature f7 = OrderCompatibilityMatrixFeature.createFeature(json);
            featureList.add(f7);
            Feature f8 = PerformerCompatibilityMatrixFeature.createFeature(json);
            featureList.add(f8);
            Feature f9 = PerformerTransportCompatibilityMatrixFeature.createFeature(json);
            featureList.add(f9);
            Feature f10 = TransportLocationCompatibilityMatrixFeature.createFeature(json);
            featureList.add(f10);
            Feature f11 = TransportTypeFeature.createFeature(json);
            featureList.add(f11);
            Feature f12 = RangLengthMatrixFeature.createFeature(json);
            featureList.add(f12);
            Feature f13 = RangTimeMatrixFeature.createFeature(json);
            featureList.add(f13);
            Feature f14 = AverageTimeMatrixFeature.createFeature(json);
            featureList.add(f14);
            Feature f15 = AverageLengthMatrixFeature.createFeature(json);
            featureList.add(f15);
            Feature f16 = CostPerUnitFeature.createFeature(json);
            featureList.add(f16);
            Feature f17 = StageLengthFeature.createFeature(json);
            featureList.add(f17);
            Feature f18 = CostPerShiftFeature.createFeature(json);
            featureList.add(f18);
            Feature f19 = ArrivalDurationFeature.createFeature(json);
            featureList.add(f19);
            Feature f20 = DepartureDurationFeature.createFeature(json);
            featureList.add(f20);
            CountLocationsFeature countLocationsFeature = new CountLocationsFeature();
            Feature f21 = countLocationsFeature.createFeature(json);
            featureList.add(f21);
            Feature f22 = CapacityTimeWindowDurationFeature.createFeature(json);
            featureList.add(f22);
            Feature f23 = CapacityWindowMaxTransportsFeature.createFeature(json);
            featureList.add(f23);
            Feature f24 = CountCapacityWindowTypePICKUPFeature.createFeature(json);
            featureList.add(f24);
            Feature f25 = CountCapacityWindowTypeDROPFeature.createFeature(json);
            featureList.add(f25);
            Feature f26 = CountCapacityWindowTypeWORKFeature.createFeature(json);
            featureList.add(f26);
            Feature f27 = CargosCapacityFeature.createFeature(json);
            featureList.add(f27);
            Feature f28 = AverageCargosPerDemandFeature.createFeature(json);
            featureList.add(f28);
            Feature f29 = AverageEventDurationFeature.createFeature(json);
            featureList.add(f29);
            Feature f30 = AverageEventHardTimeWindowDurationFeature.createFeature(json);
            featureList.add(f30);
            Feature f31 = AverageEventSoftTimeWindowDurationFeature.createFeature(json);
            featureList.add(f31);
            Feature f32 = AverageNumberOfPossibleEventsFeature.createFeature(json);
            featureList.add(f32);
            Feature f33 = CapacityTimeWindowFromFeature.createFeature(json);
            featureList.add(f33);
            Feature f34 = CapacityTimeWindowToFeature.createFeature(json);
            featureList.add(f34);
            Feature f35 = AverageEventHardTimeWindowFromFeature.createFeature(json);
            featureList.add(f35);
            Feature f36 = AverageEventHardTimeWindowToFeature.createFeature(json);
            featureList.add(f36);
            Feature f37 = AverageEventSoftTimeWindowFromFeature.createFeature(json);
            featureList.add(f37);
            Feature f38 = AverageEventSoftTimeWindowToFeature.createFeature(json);
            featureList.add(f38);
            Feature f39 = BreakTimeStartFeature.createFeature(json);
            featureList.add(f39);
            Feature f40 = CountBreaksFeature.createFeature(json);
            featureList.add(f40);
            Feature f41 = BreakTimeDurationFeature.createFeature(json);
            featureList.add(f41);
            Feature f42 = CountBreakTypeBT_FixedFeature.createFeature(json);
            featureList.add(f42);
            Feature f43 = CountBreakTypeBT_IterativeFeature.createFeature(json);
            featureList.add(f43);
            Feature f44 = AverageCountWorkShifts.createFeature(json);
            featureList.add(f44);
            Feature f45 = PerformerShiftTimeWindowDuration.createFeature(json);
            featureList.add(f45);
            Feature f46 = PerformerShiftTimeWindowFrom.createFeature(json);
            featureList.add(f46);
            Feature f47 = PerformerShiftTimeWindowTo.createFeature(json);
            featureList.add(f47);
            Feature f48 = AverageNumberOfBoxes.createFeature(json);
            featureList.add(f48);
            Feature f49 = AverageNumberOfCapacityInBoxes.createFeature(json);
            featureList.add(f49);
            Feature f50 = BoxesCapacityFeature.createFeature(json);
            featureList.add(f50);
            Feature f51 = TransportShiftTimeWindowDuration.createFeature(json);
            featureList.add(f51);
            Feature f52 = TransportShiftTimeWindowFrom.createFeature(json);
            featureList.add(f52);
            Feature f53 = TransportShiftTimeWindowTo.createFeature(json);
            featureList.add(f53);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return featureList;
    }
}
