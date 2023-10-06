package com.example.warpopenvpnapp;





import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.VpnService;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.warpopenvpnapp.model.Server;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.materialswitch.MaterialSwitch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.blinkt.openvpn.OpenVpnApi;
import de.blinkt.openvpn.core.OpenVPNService;
import de.blinkt.openvpn.core.OpenVPNThread;
import de.blinkt.openvpn.core.VpnStatus;

public class HomeActivity extends AppCompatActivity {

    private ImageView bottomsheetdraghandler;

   private View collaspe;
   private View expand;

   private MaterialSwitch materialSwitch;

   private boolean vpnStart = false;

   private CheckInternetConnection connection;
    private OpenVPNThread vpnThread = new OpenVPNThread();
    private OpenVPNService vpnService = new OpenVPNService();

    private SharedPreference preference;
    private Server server;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomsheetdraghandler = findViewById(R.id.btn_img_slidebottomsheet);

        materialSwitch = findViewById(R.id.materialswitch);


        collaspe = findViewById(R.id.collaspeview);
        expand = findViewById(R.id.expandedview);
        
        


        View bottomsheet =findViewById( R.id.bottom_sheet);

        initializeAll();

        // Checking is vpn already running or not
        isServiceRunning();
        VpnStatus.initLogCache(getCacheDir());

        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(bottomsheet);
        bottomSheetBehavior.setPeekHeight(300,true);

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

               switch (newState){
                   case BottomSheetBehavior.STATE_EXPANDED:
//                       bottomsheetdraghandler.setImageResource(R.drawable.ic_baseline_remove);
                       Toast.makeText(HomeActivity.this, "Sheet is expanded", Toast.LENGTH_SHORT).show();
                       break;



                   case BottomSheetBehavior.STATE_COLLAPSED:
//                       bottomsheetdraghandler.setImageResource(R.drawable.ic_uparrow);
                       Toast.makeText(HomeActivity.this, "State is collapsed", Toast.LENGTH_SHORT).show();
                       break;
               }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.d("slide", "onSlide: "+slideOffset);
                if (slideOffset >= 0.33) {
                    bottomsheetdraghandler.setImageResource(R.drawable.ic_baseline_remove);
//                    bottomsheetContent.removeView(findViewById(R.id.collapsestate));
//                    bottomsheetContent.addView(findViewById(R.id.expandedstate));
//                    expand.setVisibility(View.VISIBLE);
                    expand.animate().alpha(1.0f)
//                                    .scaleX(1.0f)
//                                            .scaleY(1.0f)
                                                    .setDuration(300)
                                                            .setListener(new Animator.AnimatorListener() {
                                                                @Override
                                                                public void onAnimationStart(@NonNull Animator animator) {

                                                                }

                                                                @Override
                                                                public void onAnimationEnd(@NonNull Animator animator) {

                                                                    expand.setVisibility(View.VISIBLE);

                                                                }

                                                                @Override
                                                                public void onAnimationCancel(@NonNull Animator animator) {

                                                                }

                                                                @Override
                                                                public void onAnimationRepeat(@NonNull Animator animator) {

                                                                }
                                                            });

//                    collaspe.setVisibility(View.GONE);
                    collaspe.animate().alpha(0.0f)
//                            .scaleX(0.0f).scaleY(0.0f).setDuration(300)
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(@NonNull Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(@NonNull Animator animator) {
                                    collaspe.setVisibility(View.GONE);

                                }

                                @Override
                                public void onAnimationCancel(@NonNull Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(@NonNull Animator animator) {

                                }
                            });
                }
                else {

                    bottomsheetdraghandler.setImageResource(R.drawable.ic_uparrow);
//                    bottomsheetContent.removeView(findViewById(R.id.expandedstate));
//                    bottomsheetContent.addView(findViewById(R.id.collapsestate));

//                    collaspe.setVisibility(View.VISIBLE);
                    collaspe.animate().alpha(1f)
//                            .scaleX(1).scaleY(1)
                                    .setDuration(300)
                                            .setListener(new Animator.AnimatorListener() {
                                                @Override
                                                public void onAnimationStart(@NonNull Animator animator) {

                                                }

                                                @Override
                                                public void onAnimationEnd(@NonNull Animator animator) {
                                                    collaspe.setVisibility(View.VISIBLE);

                                                }

                                                @Override
                                                public void onAnimationCancel(@NonNull Animator animator) {

                                                }

                                                @Override
                                                public void onAnimationRepeat(@NonNull Animator animator) {

                                                }
                                            });

//                    expand.setVisibility(View.GONE);
                    expand.animate().alpha(0f)
//                            .scaleX(0f).scaleY(0f)
                            .setDuration(300)
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(@NonNull Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(@NonNull Animator animator) {
                                    expand.setVisibility(View.GONE);

                                }

                                @Override
                                public void onAnimationCancel(@NonNull Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(@NonNull Animator animator) {

                                }
                            });




                }

            }
        });


        materialSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
//                    Toast.makeText(HomeActivity.this, "switch is on state", Toast.LENGTH_SHORT).show();
                    prepareVpn();
                } else {
                    Toast.makeText(HomeActivity.this, "switch is off-state", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }




    private void initializeAll() {

        preference = new SharedPreference(this);
        server = preference.getServer();



        connection = new CheckInternetConnection();
    }

    private void prepareVpn() {


        if (!vpnStart) {
            if (getInternetStatus()) {

                // Checking permission for network monitor
                Intent intent = VpnService.prepare(this);

                if (intent != null) {
                    startActivityForResult(intent, 1);
                } else startVpn();//have already permission

                // Update confection status
                status("connecting");

            } else {

                // No internet connection available
                showToast("you have no internet connection !!");
            }

        } else if (stopVpn()) {

            // VPN is stopped, show a Toast message.
            showToast("Disconnect Successfully");
        }


    }



    private boolean stopVpn() {
        try {
            vpnThread.stop();

            status("connect");
            vpnStart = false;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            //Permission granted, start the VPN
            startVpn();
        } else {
            showToast("Permission Deny !! ");
        }
    }



    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    public boolean getInternetStatus() {
        return connection.netCheck(this);
    }

    public void isServiceRunning() {
        setStatus(vpnService.getStatus());
    }



    private void startVpn() {
        try {
            // .ovpn file
            InputStream conf = getAssets().open(server.getOvpn());
            InputStreamReader isr = new InputStreamReader(conf);
            BufferedReader br = new BufferedReader(isr);
            String config = "";
            String line;

            while (true) {
                line = br.readLine();
                if (line == null) break;
                config += line + "\n";
            }

            br.readLine();
            OpenVpnApi.startVpn(this, config, "USA", server.getOvpnUserName(), server.getOvpnUserPassword());

            // Update log
//            binding.logTv.setText("Connecting...");
            vpnStart = true;

        } catch (IOException | RemoteException e) {
            e.printStackTrace();
        }
    }



    private void setStatus(String connectionState) {

        if (connectionState!= null)
            switch (connectionState) {
                case "DISCONNECTED":
                    status("connect");
                    vpnStart = false;
                    vpnService.setDefaultStatus();
//                    binding.logTv.setText("");
                    break;
                case "CONNECTED":
                    vpnStart = true;// it will use after restart this activity
                    status("connected");
//                    binding.logTv.setText("");
                    break;
                case "WAIT":
//                    binding.logTv.setText("waiting for server connection!!");
                    break;
                case "AUTH":
//                    binding.logTv.setText("server authenticating!!");
                    break;
                case "RECONNECTING":
                    status("connecting");
//                    binding.logTv.setText("Reconnecting...");
                    break;
                case "NONETWORK":
//                    binding.logTv.setText("No network connection");
                    break;
            }



    }

    private void status(String status) {


        if (status.equals("connect")) {
//            binding.vpnBtn.setText(getContext().getString(R.string.connect));
        } else if (status.equals("connecting")) {
//            binding.vpnBtn.setText(getContext().getString(R.string.connecting));
        } else if (status.equals("connected")) {

//            binding.vpnBtn.setText(getContext().getString(R.string.disconnect));

        } else if (status.equals("tryDifferentServer")) {

//            binding.vpnBtn.setBackgroundResource(R.drawable.button_connected);
//            binding.vpnBtn.setText("Try Different\nServer");
        } else if (status.equals("loading")) {
//            binding.vpnBtn.setBackgroundResource(R.drawable.button);
//            binding.vpnBtn.setText("Loading Server..");
        } else if (status.equals("invalidDevice")) {
//            binding.vpnBtn.setBackgroundResource(R.drawable.button_connected);
//            binding.vpnBtn.setText("Invalid Device");
        } else if (status.equals("authenticationCheck")) {
//            binding.vpnBtn.setBackgroundResource(R.drawable.button_connecting);
//            binding.vpnBtn.setText("Authentication \n Checking...");
        }


    }



    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                setStatus(intent.getStringExtra("state"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {

                String duration = intent.getStringExtra("duration");
                String lastPacketReceive = intent.getStringExtra("lastPacketReceive");
                String byteIn = intent.getStringExtra("byteIn");
                String byteOut = intent.getStringExtra("byteOut");

                if (duration == null) duration = "00:00:00";
                if (lastPacketReceive == null) lastPacketReceive = "0";
                if (byteIn == null) byteIn = " ";
                if (byteOut == null) byteOut = " ";
                updateConnectionStatus(duration, lastPacketReceive, byteIn, byteOut);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    public void updateConnectionStatus(String duration, String lastPacketReceive, String byteIn, String byteOut) {
//        binding.durationTv.setText("Duration: " + duration);
//        binding.lastPacketReceiveTv.setText("Packet Received: " + lastPacketReceive + " second ago");
//        binding.byteInTv.setText("Bytes In: " + byteIn);
//        binding.byteOutTv.setText("Bytes Out: " + byteOut);
    }


    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("connectionState"));

        if (server == null) {
            server = preference.getServer();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        super.onPause();
    }


    @Override
    public void onStop() {
        if (server != null) {
            preference.saveServer(server);
        }

        super.onStop();
    }


}