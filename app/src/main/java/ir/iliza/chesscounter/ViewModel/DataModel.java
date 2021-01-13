package ir.iliza.chesscounter.ViewModel;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ir.iliza.chesscounter.Model.TimeModel;
import ir.iliza.chesscounter.R;
import ir.iliza.chesscounter.Util.Utils;


public class DataModel extends ViewModel{


    MutableLiveData<TimeModel> time;

    boolean isInit = false;
    boolean isActivityCreated = false;
    boolean isStarted = false;
    boolean isBlueButtonActive = true;
    boolean isOrangeButtonActive = true;
    int color;
    int BlueColor;
    int OrangeColor;




    public void init(AppCompatActivity activity){
        isInit = true;
        time = new MutableLiveData<>();
        time.setValue(Utils.getTimeModel());
        BlueColor = ContextCompat.getColor(activity, R.color.colorBlue);
        OrangeColor = ContextCompat.getColor(activity,R.color.colorOrange);
    }

    public boolean isInit() {
        return isInit;
    }

    public boolean isOrangeButtonActive() {
        return isOrangeButtonActive;
    }

    public void setOrangeButtonActive(boolean orangeButtonActive) {
        isOrangeButtonActive = orangeButtonActive;
    }

    public boolean isBlueButtonActive() {
        return isBlueButtonActive;
    }


    public void setColor(int color) {
        this.color = color;
    }

    public void setBlueButtonActive(boolean blueButtonActive) {
        isBlueButtonActive = blueButtonActive;
    }

    public int getBlueColor() {
        return BlueColor;
    }

    public int getOrangeColor() {
        return OrangeColor;
    }

    public MutableLiveData<TimeModel> getTime() {
        return time;
    }

    public boolean isActivityCreated() {
        return isActivityCreated;
    }

    public void setActivityCreated(boolean activityCreated) {
        isActivityCreated = activityCreated;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
