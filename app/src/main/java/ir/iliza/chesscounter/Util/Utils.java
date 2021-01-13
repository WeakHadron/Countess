package ir.iliza.chesscounter.Util;




import ir.iliza.chesscounter.Model.TimeModel;

public class Utils {


    private static TimeModel model;

    public static TimeModel getTimeModel(){
        if(model == null){
            model = new TimeModel(0,0,0);
        }
        return model;
    }

    public static void refreshTime(){

        model.setMinutes(0).setSeconds(0).setMilliseconds(0);
    }




}
