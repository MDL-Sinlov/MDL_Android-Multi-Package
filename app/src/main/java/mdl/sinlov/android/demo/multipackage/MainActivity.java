package mdl.sinlov.android.demo.multipackage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Map;
import java.util.Properties;

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
    @BindView(R.id.btn_main_get_channel_more_info_by_key)
    Button btnMainGetChannelProperties;
    @BindView(R.id.activity_main)
    ScrollView activityMain;
    @BindView(R.id.btn_main_get_channel_more_info_list)
    Button btnMainGetChannelMoreInfoList;
    private boolean intiSuccess;
    private StringBuffer sb = new StringBuffer();

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
            R.id.btn_main_get_channel_more_info_by_key, R.id.btn_main_get_channel_more_info_list})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_result:
                break;
            case R.id.btn_main_check_init:
                intiSuccess = ChannelContent.getInstance().initChannelContent(this.getApplicationContext());
                tvResult.setText(String.format("Is Init successful: %s", intiSuccess));
                break;
            case R.id.btn_main_get_channel_name:
                String channel_name = ChannelContent.getInstance().getChannel_name();
                tvResult.setText(String.format("Get Channel Name: %s", channel_name));
                break;
            case R.id.btn_main_get_channel_more_info_by_key:
                Map<String, String> fullInfo = ChannelContent.getInstance().getChanelInfo("channel");
                if (null != fullInfo) {
                    sb.setLength(0);
                    sb.append("Channel info\n");
                    for (Map.Entry info :
                            fullInfo.entrySet()) {
                        sb.append("Info key: ");
                        sb.append(" value: ");
                        sb.append(info.getValue());
                        sb.append("\n");
                    }
                    tvResult.setText(sb.toString());
                } else {
                    tvResult.setText("get channel properties empty do you want check?");
                }
                break;
            case R.id.btn_main_get_channel_more_info_list:
                if (intiSuccess) {
                    Properties properties = ChannelContent.getInstance().getProperties();
                    if (properties != null) {
                        sb.setLength(0);
                        sb.append("Channel info\n");
                        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                            Object key = entry.getKey();
                            Object value = entry.getValue();
                            sb.append("Info key: ");
                            sb.append(key);
                            sb.append(" value: ");
                            sb.append(value);
                            sb.append("\n");
                        }
                        tvResult.setText(sb.toString());
                    } else {
                        tvResult.setText("get channel properties null do you want check?");
                    }
                } else {
                    tvResult.setText("get channel properties empty do you want check?");
                }
                break;
        }
    }
}
