package mdl.sinlov.android.demo.multipackage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mdl.sinlov.multipackage.ChannelContent;

public class MainActivity extends MDLTestActivity {

    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.btn_main_check_init)
    Button btnMainCheckInit;
    @BindView(R.id.btn_main_get_channel_name)
    Button btnMainGetChannelName;
    @BindView(R.id.btn_main_get_channel_properties)
    Button btnMainGetChannelProperties;
    @BindView(R.id.activity_main)
    ScrollView activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void bindView(Bundle savedInstanceState) {

    }

    @Override
    protected void bindListener() {

    }

    @OnClick({R.id.tv_result, R.id.btn_main_check_init, R.id.btn_main_get_channel_name,
            R.id.btn_main_get_channel_properties})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_result:
                break;
            case R.id.btn_main_check_init:
                boolean isSuccess = ChannelContent.getInstance().initChannelContent(this.getApplicationContext());
                tvResult.setText("Is Init successful: " + isSuccess);
                break;
            case R.id.btn_main_get_channel_name:
                String channel_name = ChannelContent.getInstance().getChannel_name();
                tvResult.setText("Get Channel Name: " + channel_name);
                break;
            case R.id.btn_main_get_channel_properties:
                Map<String, String> fullInfo = ChannelContent.getInstance().getChanelInfo("channel");
                if (null != fullInfo) {
                    StringBuffer sb = new StringBuffer();
                    sb.append("Channel info\n");
                    for (Map.Entry info :
                            fullInfo.entrySet()) {
                        sb.append("Info key: ");
                        sb.append(info.getKey());
                        sb.append(" value: ");
                        sb.append("\n");
                    }
                    tvResult.setText(sb.toString());
                } else {
                    tvResult.setText("get channel properties empty do you want check?");
                }
                break;
        }
    }
}
