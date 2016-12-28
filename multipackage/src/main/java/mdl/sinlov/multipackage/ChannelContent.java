package mdl.sinlov.multipackage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * <pre>
 *     sinlov
 *
 *     /\__/\
 *    /`    '\
 *  ≈≈≈ 0  0 ≈≈≈ Hello world!
 *    \  --  /
 *   /        \
 *  /          \
 * |            |
 *  \  ||  ||  /
 *   \_oo__oo_/≡≡≡≡≡≡≡≡o
 *
 * </pre>
 * Created by sinlov on 16/12/28.
 */
public class ChannelContent {

    private static final String META_INF_FILE_START = "META-INF/pl_channel_";
    private static final String CHANNEL_FILE_MARK = "pl_channel_";
    private String channel_name;
    private static ChannelContent instance;
    private Properties properties;
    private boolean hasChannelFile = false;

    public static ChannelContent getInstance() {
        if (null == instance) {
            instance = new ChannelContent();
        }
        return instance;
    }

    private ChannelContent() {
    }

    public boolean initChannelContent(Context context) {
        hasChannelFile = false;
        ApplicationInfo appInfo = context.getApplicationInfo();
        String sourceDir = appInfo.sourceDir;
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.contains("../")) {
                    break;
                }
                if (entryName.startsWith(META_INF_FILE_START)) {
                    if (entryName.contains(".")) {
                        break;
                    }
                    String[] split = entryName.split(CHANNEL_FILE_MARK);
                    channel_name = split[split.length - 1];
                    hasChannelFile = true;
                    long size = entry.getSize();
                    if (size > 0) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(zipfile.getInputStream(entry)));
                        properties = new Properties();
                        properties.load(br);

//                        String line;
//                        int lines = 0;
//                        while ((line = br.readLine()) != null) {
//                            lines++;
//                            sb.append(line.trim());
//                        }

                        br.close();
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return hasChannelFile;
    }

    public String getChannel_name() {
        if (TextUtils.isEmpty(channel_name)) {
            new ExceptionInInitializerError("has your initChannelContent() ?").printStackTrace();
        }
        return channel_name;
    }

    public Map<String, String> getChanelInfo(String... key) {
        if (properties == null || properties.isEmpty()) {
            new ExceptionInInitializerError("has your initChannelContent() ?").printStackTrace();
        }
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (String s : key) {
            hashMap.put(s, properties.getProperty(s, ""));
        }
        return hashMap;
    }
}
