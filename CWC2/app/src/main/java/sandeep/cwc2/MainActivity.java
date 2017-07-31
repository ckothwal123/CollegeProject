package sandeep.cwc2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

  private TextView txtName;
  private TextView txtEmail;
  private Button btnLogout;
  TextView textBatteryLevel = null;
  String batteryLevelInfo = "Battery Level";

  private sandeep.cwc2.SQLiteHandler db;
  private sandeep.cwc2.SessionManager session;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    txtName = (TextView) findViewById(R.id.name);
    txtEmail = (TextView) findViewById(R.id.email);
    btnLogout = (Button) findViewById(R.id.btnLogout);
    Button bu = (Button) findViewById(R.id.bu);
    bu.setVisibility(View.INVISIBLE);

    // SqLite database handler
    db = new sandeep.cwc2.SQLiteHandler(getApplicationContext());
    // session manager
    session = new sandeep.cwc2.SessionManager(getApplicationContext());
    if (!session.isLoggedIn()) {
      logoutUser();
    }

    // Fetching user details from SQLite
    HashMap<String, String> user = db.getUserDetails();

    String name = user.get("name");
    String email = user.get("email");

    // Displaying the user details on the screen
    txtName.setText(name);
    txtEmail.setText(email);

    bu.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, activity2.class);
        startActivity(intent);
      }
    });
    textBatteryLevel = (TextView) findViewById(R.id.txtBatteryInfo);

    registerBatteryLevelReceiver();

    // Logout button click event
    btnLogout.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        logoutUser();
      }
    });
  }

  /**
   * Logging out the user. Will set isLoggedIn flag to false in shared
   * preferences Clears the user data from sqlite users table
   */
  private void logoutUser() {
    session.setLogin(false);

    db.deleteUsers();

    // Launching the login activity
    Intent intent = new Intent(MainActivity.this, Login.class);
    startActivity(intent);
    finish();
  }

  @Override
  protected void onDestroy() {
    unregisterReceiver(battery_receiver);

    super.onDestroy();
  }

  private BroadcastReceiver battery_receiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      boolean isPresent = intent.getBooleanExtra("present", false);
      String technology = intent.getStringExtra("technology");
      int plugged = intent.getIntExtra("plugged", -1);
      int scale = intent.getIntExtra("scale", -1);
      int health = intent.getIntExtra("health", 0);
      int status = intent.getIntExtra("status", 0);
      int rawlevel = intent.getIntExtra("level", -1);
      int voltage = intent.getIntExtra("voltage", 0);
      int temperature = intent.getIntExtra("temperature", 0);
      int level = 0;

      Bundle bundle = intent.getExtras();

      Log.i("BatteryLevel", bundle.toString());

      if (isPresent) {
        if (rawlevel >= 0 && scale > 0) {
          level = (rawlevel * 100) / scale;
        }

        String info = "Battery Level: " + level + "%\n";
        info += ("Technology: " + technology + "\n");
        info += ("Plugged: " + getPlugTypeString(plugged) + "\n");
        info += ("Health: " + getHealthString(health) + "\n");
        info += ("Status: " + getStatusString(status) + "\n");
        info += ("Voltage: " + voltage + "\n");
        info += ("Temperature: " + temperature + "\n");

        setBatteryLevelText(info + "\n\n" + bundle.toString());
      } else {
        setBatteryLevelText("No Battery!!!");
      }
    }
  };


  private String getPlugTypeString(int plugged) {
    String plugType = "Unknown";

    switch (plugged) {
      case BatteryManager.BATTERY_PLUGGED_AC:
        plugType = "AC";
        break;
      case BatteryManager.BATTERY_PLUGGED_USB:
        plugType = "USB";
        break;
    }

    return plugType;
  }

  private String getHealthString(int health) {
    String healthString = "Unknown";

    switch (health) {
      case BatteryManager.BATTERY_HEALTH_DEAD:
        healthString = "Dead";
        break;
      case BatteryManager.BATTERY_HEALTH_GOOD:
        healthString = "Good";
        break;
      case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
        healthString = "Over Voltage";
        break;
      case BatteryManager.BATTERY_HEALTH_OVERHEAT:
        healthString = "Over Heat";
        break;
      case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
        healthString = "Failure";
        break;
    }

    return healthString;
  }

  public String getStatusString(int status) {
    String statusString = "Unknown";


    switch (status) {
      case BatteryManager.BATTERY_STATUS_CHARGING:
        statusString = "Charging";
        Button button = (Button) findViewById(R.id.bu);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, activity2.class);
            startActivity(intent);
          }
        });
        break;
      case BatteryManager.BATTERY_STATUS_DISCHARGING:
        statusString = "Discharging";

        break;
      case BatteryManager.BATTERY_STATUS_FULL:
        statusString = "Full";

        break;
      case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
        statusString = "Not Charging";

        break;
    }

    return statusString;
  }

  private void setBatteryLevelText(String text) {
    textBatteryLevel.setText(text);
  }

  private void registerBatteryLevelReceiver() {
    IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    registerReceiver(battery_receiver, filter);

  }

}
