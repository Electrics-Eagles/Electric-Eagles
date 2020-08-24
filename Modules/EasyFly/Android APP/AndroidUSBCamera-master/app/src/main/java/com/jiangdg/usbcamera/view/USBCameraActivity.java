package com.jiangdg.usbcamera.view;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.jiangdg.usbcamera.R;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jiangdg.usbcamera.UVCCameraHelper;
import com.jiangdg.usbcamera.application.MyApplication;
import com.jiangdg.usbcamera.utils.FileUtils;
import com.serenegiant.usb.CameraDialog;
import com.serenegiant.usb.Size;
import com.serenegiant.usb.USBMonitor;
import com.serenegiant.usb.common.AbstractUVCCameraHandler;
import com.serenegiant.usb.encoder.RecordParams;
import com.serenegiant.usb.widget.CameraViewInterface;


import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * UVCCamera use demo
 * <p>
 * Created by jiangdongguo on 2017/9/30.
 */

public class USBCameraActivity extends AppCompatActivity implements CameraDialog.CameraDialogParent, CameraViewInterface.Callback {
    private static final String TAG = "Debug";
    @BindView(R.id.camera_view)
    public View mTextureView;
    @BindView(R.id.toolbar2)
    public Toolbar mToolbar;


    private UVCCameraHelper mCameraHelper;
    private CameraViewInterface mUVCCameraView;
    private AlertDialog mDialog;

    private boolean isRequest;
    private boolean isPreview;
    public static double start_point_longitute;
    public static double start_point_lagitude;
    LocationManager locationManager = null;
    private FusedLocationProviderClient fusedLocationClient;
    private static String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    public static UsbSerialPort port;
    public  static boolean recording= false;

    private UVCCameraHelper.OnMyDevConnectListener listener = new UVCCameraHelper.OnMyDevConnectListener() {

        @Override
        public void onAttachDev(UsbDevice device) {
            // request open permission
            if (!isRequest) {
                isRequest = true;
                if (mCameraHelper != null) {
                    mCameraHelper.requestPermission(1);
                }
            }
        }

        @Override
        public void onDettachDev(UsbDevice device) {
            // close camera
            if (isRequest) {
                isRequest = false;
                mCameraHelper.closeCamera();
                showShortMsg(device.getDeviceName() + " is out");
            }
        }

        @Override
        public void onConnectDev(UsbDevice device, boolean isConnected) {
            if (!isConnected) {
                showShortMsg("fail to connect,please check resolution params");
                isPreview = false;
            } else {
                isPreview = true;
                showShortMsg("connecting");
                // initialize seekbar
                // need to wait UVCCamera initialize over
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Looper.prepare();
                        if(mCameraHelper != null && mCameraHelper.isCameraOpened()) {

                        }
                        Looper.loop();
                    }
                }).start();
            }
        }

        @Override
        public void onDisConnectDev(UsbDevice device) {
            showShortMsg("disconnecting");
        }
    };

    private void setText(final TextView text, final String value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
                text.setTextColor(Color.WHITE);
            }
        });
    }



    public void read_serial_port() {
        final MapView mapview = (MapView) findViewById(R.id.mapview);
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);


        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            return;
        }
        UsbSerialDriver driver = availableDrivers.get(0);
        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
        PendingIntent mPermissionIntent = PendingIntent.getBroadcast(this, 0, new
                Intent(ACTION_USB_PERMISSION), 0);

        UsbManager mUsbManager = (UsbManager)
                getSystemService(Context.USB_SERVICE);
        mUsbManager.requestPermission(driver.getDevice(), mPermissionIntent);
        System.out.println(driver.getPorts());






        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        mapview.setBuiltInZoomControls(true);
        mapview.setMultiTouchControls(true);
        IMapController mapController = mapview.getController();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        final TextView status = (TextView) findViewById(R.id.textView);
        status.setTextColor(Color.WHITE);
        final TextView height = (TextView) findViewById(R.id.textView7);
        final TextView distance = findViewById(R.id.textView6);
        final TextView reiversignal = (TextView) findViewById(R.id.textView3);
        final TextView battary_value = (TextView) findViewById(R.id.textView5);
        final TextView sats = (TextView) findViewById(R.id.textView10);
        final Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);

        mapview.getMapCenter();
        height.setTextColor(Color.WHITE);
        battary_value.setTextColor(Color.WHITE);
        distance.setTextColor(Color.WHITE);
        reiversignal.setTextColor(Color.WHITE);
        height.setText("H:");
        battary_value.setText("Bat:67%");
        distance.setText("D:");
        reiversignal.setText("Sig:");
        if (connection == null) {

            return;
        }

        UsbSerialPort port = driver.getPorts().get(0); // Most devices have just one port (port 0)
        try {
            port.open(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            port.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread t = new Thread(
                new Runnable() {

                    @Override
                    public void run() {
                        while (true) {

                            byte[] request = new byte[256];
                            try {
                                port.read(request, 1000);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            String value = (new String(request).split("\n")[0]);


                            // System.out.println(value);

                            String[] data = value.split(";");
                            if (data.length > 5) {


                                ArrayList<String> codes = new ArrayList<String>(); // Create an ArrayList object
                                codes.add("GPS-U");
                                codes.add("GPS-B");
                                codes.add("BARO-U");
                                codes.add("BARO-O");
                                codes.add("GYRO-O");

                                String[] values = {"GPS & Ultrasoncis", "GPS & Barometer", "Barometer & Utrasonics", "Barometer Only", "Gyroscope Only. (ATTI)"};
                                int[] colors = {Color.parseColor("#4B51B9"), Color.parseColor("#4F9612"), Color.parseColor("#B9A919"), Color.parseColor("#B9850C"), Color.parseColor("#AD1716")};
                                try {
                                    int fly_mode = codes.indexOf(data[1]);
                                    toolbar2.setBackgroundColor(colors[fly_mode]);
                                    Location loc1 = new Location("");
                                    loc1.setLatitude(start_point_longitute);
                                    loc1.setLongitude(start_point_lagitude);

                                    Location loc2 = new Location("");
                                    loc2.setLatitude(Double.parseDouble(data[4]));
                                    loc2.setLongitude(Double.parseDouble(data[3]));
                                    Marker drone_maker = new Marker(mapview);
                                    GeoPoint startPoint= new GeoPoint(loc2.getLongitude(),loc2.getLatitude());
                                    GeoPoint p = new GeoPoint(start_point_lagitude, start_point_longitute);
                                    Polyline line = new Polyline(mapview);
                                    line .addPoint(p);
                                    line .addPoint(startPoint);
                                    mapview.getOverlays().add(line);
                                    drone_maker.setPosition(startPoint);
                                    drone_maker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                                   // drone_maker.setIcon(getResources().getDrawable(R.drawable.drone_foreground));
                                    drone_maker.setTitle("Drone");
                                    mapview.getOverlays().add(drone_maker);
                                    System.out.println("Phone are:" + loc1.getLongitude() + "and" + loc1.getLatitude());
                                    System.out.println("Drone are:" + loc2.getLongitude() + "and" + loc2.getLatitude());
                                    int distanceInMeters = (int)loc2.distanceTo(loc1);
                                    System.out.println("Distance between them: " + loc1.distanceTo(loc2));
                                    String alt = "H: " + data[2] + "M";
                                    String dist = "D:" + distanceInMeters + "M";
                                    String resv = "Sig: " + data[5] + "%";
                                    String batt = "Bat: " + data[6];
                                    String satel = "Drone sats:" + data[7];
                                    setText(status, values[fly_mode]);
                                    setText(height, alt);
                                    setText(reiversignal, resv);
                                    setText(battary_value, batt);
                                    setText(distance, dist);
                                    setText(sats, satel);
                                }
                                catch(Exception e) {
                                    System.out.println(e.getMessage());
                                }


                            }
                        }

                    }


                });
        t.start();


    }
    public void mapworker() {
        final MapView mapview = (MapView) findViewById(R.id.mapview);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object

                            start_point_longitute=location.getLongitude();
                            start_point_lagitude=location.getLatitude();
                            GeoPoint startPoint = new GeoPoint(start_point_lagitude, start_point_longitute);
                            Marker startMarker = new Marker(mapview);
                            startMarker.setPosition(startPoint);
                            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                            //startMarker.setIcon(getResources().getDrawable(R.drawable.home_foreground));
                            startMarker.setTitle("Home");
                            mapview.getOverlays().add(startMarker);

                            IMapController value = mapview.getController();
                            GeoPoint p = new GeoPoint(start_point_longitute, start_point_lagitude);
                            value.setCenter(p);
                            IMapController mapController = mapview.getController();
                            mapController.setZoom(18);
                            GeoPoint home_point = new GeoPoint(start_point_lagitude, start_point_longitute);
                            mapController.setCenter(home_point);



                        }
                    }
                });
    }

    public void showDialog() throws Exception {
        AlertDialog.Builder builder = new AlertDialog.Builder(USBCameraActivity.this);

        builder.setMessage("Are you connected arduino");

        builder.setPositiveButton("Ring", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){


                read_serial_port();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Abort", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        builder.show();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usbcamera);
        ButterKnife.bind(this);
        mUVCCameraView = (CameraViewInterface) mTextureView;
        mUVCCameraView.setCallback(this);
        mCameraHelper = UVCCameraHelper.getInstance();
        mCameraHelper.setDefaultFrameFormat(UVCCameraHelper.FRAME_FORMAT_MJPEG);
        mCameraHelper.initUSBMonitor(this, mUVCCameraView, listener);
        mCameraHelper.setOnPreviewFrameListener(new AbstractUVCCameraHandler.OnPreViewResultListener() {
            @Override
            public void onPreviewResult(byte[] nv21Yuv) {
                Log.d(TAG, "onPreviewResult: " + nv21Yuv.length);
            }
        });
       // read_serial_port();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mapworker();
            }
        });


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // step.1 initialize UVCCameraHelper



        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);

        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            return;
        }
        try {
            showDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        setSupportActionBar(mToolbar);


    }

    @Override
    protected void onStart() {
        super.onStart();
        // step.2 register USB event broadcast
        if (mCameraHelper != null) {
            mCameraHelper.registerUSB();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // step.3 unregister USB event broadcast
        if (mCameraHelper != null) {
            mCameraHelper.unregisterUSB();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toobar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_takepic:
                if (mCameraHelper == null || !mCameraHelper.isCameraOpened()) {
                    showShortMsg("sorry,camera open failed");
                    return super.onOptionsItemSelected(item);
                }
                String picPath = UVCCameraHelper.ROOT_PATH + MyApplication.DIRECTORY_NAME +"/images/"
                        + System.currentTimeMillis() + UVCCameraHelper.SUFFIX_JPEG;

                mCameraHelper.capturePicture(picPath, new AbstractUVCCameraHandler.OnCaptureListener() {
                    @Override
                    public void onCaptureResult(String path) {
                        if(TextUtils.isEmpty(path)) {
                            return;
                        }
                        new Handler(getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(USBCameraActivity.this, "save path:"+path, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                break;
            case R.id.menu_recording:
                if (mCameraHelper == null || !mCameraHelper.isCameraOpened()) {
                    showShortMsg("sorry,camera open failed");
                    return super.onOptionsItemSelected(item);
                }
                if (!mCameraHelper.isPushing()) {
                    String videoPath = UVCCameraHelper.ROOT_PATH + MyApplication.DIRECTORY_NAME +"/videos/" + System.currentTimeMillis()
                            + UVCCameraHelper.SUFFIX_MP4;

//                    FileUtils.createfile(FileUtils.ROOT_PATH + "test666.h264");
                    // if you want to record,please create RecordParams like this
                    RecordParams params = new RecordParams();
                    params.setRecordPath(videoPath);
                    params.setRecordDuration(0);                        // auto divide saved,default 0 means not divided


                    params.setSupportOverlay(true); // overlay only support armeabi-v7a & arm64-v8a
                    mCameraHelper.startPusher(params, new AbstractUVCCameraHandler.OnEncodeResultListener() {
                        @Override
                        public void onEncodeResult(byte[] data, int offset, int length, long timestamp, int type) {
                            // type = 1,h264 video stream
                            if (type == 1) {
                                FileUtils.putFileStream(data, offset, length);
                            }
                            // type = 0,aac audio stream
                            if(type == 0) {

                            }
                        }

                        @Override
                        public void onRecordResult(String videoPath) {
                            if(TextUtils.isEmpty(videoPath)) {
                                return;
                            }
                            new Handler(getMainLooper()).post(() -> Toast.makeText(USBCameraActivity.this, "save videoPath:"+videoPath, Toast.LENGTH_SHORT).show());
                        }
                    });
                    // if you only want to push stream,please call like this
                    // mCameraHelper.startPusher(listener);
                    showShortMsg("start record...");

                } else {
                    FileUtils.releaseFile();
                    mCameraHelper.stopPusher();
                    showShortMsg("stop record...");

                }
                break;
            case R.id.menu_resolution:
                if (mCameraHelper == null || !mCameraHelper.isCameraOpened()) {
                    showShortMsg("sorry,camera open failed");
                    return super.onOptionsItemSelected(item);
                }
                showResolutionListDialog();
                break;
            case R.id.menu_focus:
                if (mCameraHelper == null || !mCameraHelper.isCameraOpened()) {
                    showShortMsg("sorry,camera open failed");
                    return super.onOptionsItemSelected(item);
                }
                mCameraHelper.startCameraFoucs();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showResolutionListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(USBCameraActivity.this);
        View rootView = LayoutInflater.from(USBCameraActivity.this).inflate(R.layout.layout_dialog_list, null);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_dialog);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(USBCameraActivity.this, android.R.layout.simple_list_item_1, getResolutionList());
        if (adapter != null) {
            listView.setAdapter(adapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (mCameraHelper == null || !mCameraHelper.isCameraOpened())
                    return;
                final String resolution = (String) adapterView.getItemAtPosition(position);
                String[] tmp = resolution.split("x");
                if (tmp != null && tmp.length >= 2) {
                    int widht = Integer.valueOf(tmp[0]);
                    int height = Integer.valueOf(tmp[1]);
                    mCameraHelper.updateResolution(widht, height);
                }
                mDialog.dismiss();
            }
        });

        builder.setView(rootView);
        mDialog = builder.create();
        mDialog.show();
    }

    // example: {640x480,320x240,etc}
    private List<String> getResolutionList() {
        List<Size> list = mCameraHelper.getSupportedPreviewSizes();
        List<String> resolutions = null;
        if (list != null && list.size() != 0) {
            resolutions = new ArrayList<>();
            for (Size size : list) {
                if (size != null) {
                    resolutions.add(size.width + "x" + size.height);
                }
            }
        }
        return resolutions;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FileUtils.releaseFile();
        // step.4 release uvc camera resources
        if (mCameraHelper != null) {
            mCameraHelper.release();
        }
    }

    private void showShortMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public USBMonitor getUSBMonitor() {
        return mCameraHelper.getUSBMonitor();
    }

    @Override
    public void onDialogResult(boolean canceled) {
        if (canceled) {
            showShortMsg("取消操作");
        }
    }

    public boolean isCameraOpened() {
        return mCameraHelper.isCameraOpened();
    }

    @Override
    public void onSurfaceCreated(CameraViewInterface view, Surface surface) {
        if (!isPreview && mCameraHelper.isCameraOpened()) {
            mCameraHelper.startPreview(mUVCCameraView);
            isPreview = true;
        }
    }

    @Override
    public void onSurfaceChanged(CameraViewInterface view, Surface surface, int width, int height) {

    }

    @Override
    public void onSurfaceDestroy(CameraViewInterface view, Surface surface) {
        if (isPreview && mCameraHelper.isCameraOpened()) {
            mCameraHelper.stopPreview();
            isPreview = false;
        }
    }
}
