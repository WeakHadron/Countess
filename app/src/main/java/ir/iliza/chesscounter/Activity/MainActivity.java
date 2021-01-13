package ir.iliza.chesscounter.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.iliza.chesscounter.Model.TimeModel;
import ir.iliza.chesscounter.Util.Utils;
import ir.iliza.chesscounter.ViewModel.DataModel;
import ir.iliza.chesscounter.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    View rootview;
    DataModel data;
    int orientation;
    io.reactivex.rxjava3.core.Observer<TimeModel> observer;
    Observable<TimeModel> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        rootview = binding.getRoot();
        setContentView(rootview);
        data = ViewModelProviders.of(this).get(DataModel.class);

        if(!data.isInit()){
            data.init(this);
        }

        if(data.isStarted() && data.isOrangeButtonActive()){
            binding.blueBtn.setVisibility(View.INVISIBLE);
            binding.blueBtn.setEnabled(false);
            binding.timeTxt.setTextColor(data.getBlueColor());
        }
        else if(data.isStarted() && data.isBlueButtonActive()){
            binding.orangeBtn.setVisibility(View.INVISIBLE);
            binding.orangeBtn.setEnabled(false);
            binding.timeTxt.setTextColor(data.getOrangeColor());
        }

        binding.blueBtn.setOnClickListener(v -> {
            Utils.refreshTime();
            data.getTime().setValue(Utils.getTimeModel());
            v.setEnabled(false);
            data.setBlueButtonActive(false);
            v.setVisibility(View.INVISIBLE);
            binding.timeTxt.setTextColor(data.getBlueColor());
            data.setColor(data.getBlueColor());
            binding.orangeBtn.setEnabled(true);
            data.setOrangeButtonActive(true);
            binding.orangeBtn.setVisibility(View.VISIBLE);
            if(!data.isStarted()){
                observeTime();
                data.setStarted(true);
            }
        }
        );

        binding.orangeBtn.setOnClickListener(v -> {
            Utils.refreshTime();
            data.getTime().setValue(Utils.getTimeModel());
            v.setEnabled(false);
            data.setOrangeButtonActive(false);
            v.setVisibility(View.INVISIBLE);
            binding.timeTxt.setTextColor(data.getOrangeColor());
            data.setColor(data.getOrangeColor());
            binding.blueBtn.setEnabled(true);
            data.setBlueButtonActive(true);
            binding.blueBtn.setVisibility(View.VISIBLE);
            if(!data.isStarted()){
                observeTime();
                data.setStarted(true);
            }
        });

        orientation = getResources().getConfiguration().orientation;




        if(!data.isActivityCreated()){
            initActivity();
            data.setActivityCreated(true);

        }

        if(data.isStarted()){
            observeTime();
        }

    }



    void observeTime(){
        data.getTime().observe(this, timeModel -> {
            if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                binding.timeTxt.setText(new StringBuilder()
                        .append(timeModel.getMinutes())
                        .append("\n")
                        .append(timeModel.getSeconds())
                        .append("\n")
                        .append(timeModel.getMilliseconds())
                        .toString()
                );
            }else{
                binding.timeTxt.setText(new StringBuilder()
                        .append(timeModel.getMinutes())
                        .append(" : ")
                        .append(timeModel.getSeconds())
                        .append(" : ")
                        .append(timeModel.getMilliseconds())
                        .toString()
                );

            }
        });
    }




    void initActivity(){


        observer = new io.reactivex.rxjava3.core.Observer<TimeModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }
            @Override
            public void onNext(@NonNull TimeModel integer) {
                data.getTime().postValue(integer);
            }
            @Override
            public void onError(@NonNull Throwable e) {

            }
            @Override
            public void onComplete() {


            }
        };

        observable = Observable.create(emitter -> {

            int mil;
            int sec;
            int min;
            TimeModel model = Utils.getTimeModel();
            while(!emitter.isDisposed()){
                mil = model.getMilliseconds();
                sec = model.getSeconds();
                min = model.getMinutes();
                mil++;
                if(mil == 1000){
                    sec++;
                    mil = 0;
                }
                if(sec == 60){
                    min++;
                    sec = 0;
                }
                model.setMinutes(min)
                        .setSeconds(sec)
                        .setMilliseconds(mil);

                emitter.onNext(model);
                Thread.sleep(1);


            }
        });


        observable.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }


}