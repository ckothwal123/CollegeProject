package sandeep.cwc2;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class activity2 extends AppCompatActivity {

  // button to show progress dialog
  Button btnShowProgress;

  // Progress Dialog
  private ProgressDialog pDialog;
  ImageView my_image1;
  ImageView my_image2;
  // Progress dialog type (0 - for Horizontal progress bar)
//    public static final int progress_bar_type = 0;

  // File url to download
  private static String file_url = "http://thinkprosolutions.in/CWC/upload/image.jpg";
  private static String file_url1 = "http://thinkprosolutions.in/CWC/upload/wordresult.php";
  private static String file_url2 = "http://thinkprosolutions.in/CWC/upload/primeresult.php";
  private static String file_url3 = "http://thinkprosolutions.in/CWC/upload/imageblur.php";
  private static String file_url4 = "http://thinkprosolutions.in/CWC/upload/blur.jpg";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_activity2);

    // show progress bar button
    btnShowProgress = (Button) findViewById(R.id.btnProgressBar);
    // Image view to show image after downloading
    my_image1 = (ImageView) findViewById(R.id.or_image);
    my_image2 = (ImageView) findViewById(R.id.bl_image);
    /**
     * Show Progress bar click event
     * */
    btnShowProgress.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        // starting new Async Task
        new DownloadFileURL().execute(file_url);
        new DownloadFileFromURL().execute(file_url1);
        new DownloadFileFromURL().execute(file_url2);
        new DownloadFileFromURL().execute(file_url3);
        new DownloadFileFromURL().execute(file_url4);
      }
    });
  }

  /**
   * Showing Dialog
   */
/*    @Override
    protected Dialog OptionDialog.create(int id) {
        switch (id) {
            case progress_bar_type:
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Processing file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
*/
  class DownloadFileURL extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... f_url) {
      int count;
      try {
        URL url = new URL(f_url[0]);
        URLConnection connection = url.openConnection();
        connection.connect();
        int lenghtOfFile = connection.getContentLength();

        InputStream input = new BufferedInputStream(url.openStream(), 8192);

        OutputStream output = new FileOutputStream("/sdcard/image.jpg");

        byte data[] = new byte[1024];

        long total = 0;
        while ((count = input.read(data)) != -1) {
          total += count;
          output.write(data, 0, count);
        }

        // flushing output
        output.flush();

        // closing streams
        output.close();
        input.close();

      } catch (Exception e) {
        Log.e("Error: ", e.getMessage());
      }

      return null;
    }
  }

  /**
   * Background Async Task to download file
   */
  class DownloadFileFromURL extends AsyncTask<String, String, String> {

    /**
     * Before starting background thread
     * Show Progress Bar Dialog
     */

    @Override
    protected void onPreExecute() {
      super.onPreExecute();


//          OptionDialog.create(progress_bar_type);
    }

    /**
     * Downloading file in background thread
     */
    @Override
    protected String doInBackground(String... f_url) {
      int count;
      try {
        URL url = new URL(f_url[0]);
        URLConnection connection = url.openConnection();
        connection.connect();
        // getting file length
        int lenghtOfFile = connection.getContentLength();

        // input stream to read file - with 8k buffer
        InputStream input = new BufferedInputStream(url.openStream(), 8192);

        // Output stream to write file
        OutputStream output = new FileOutputStream("/sdcard/blur.jpg");

        byte data[] = new byte[1024];

        long total = 0;
        while ((count = input.read(data)) != -1) {
          total += count;
          // publishing the progress....
          // After this onProgressUpdate will be called
//                    publishProgress(""+(int)((total*100)/lenghtOfFile));

          // writing data to file
          output.write(data, 0, count);
        }

        // flushing output
        output.flush();

        // closing streams
        output.close();
        input.close();

      } catch (Exception e) {
        Log.e("Error: ", e.getMessage());
      }

      return null;
    }


    @Override
    protected void onPostExecute(String file_url) {
      // dismiss the dialog after the file was downloaded
//            OptionDialog.destroy(progress_bar_type);
      String imagePath = Environment.getExternalStorageDirectory().toString() + "/image.jpg";
      my_image1.setImageDrawable(Drawable.createFromPath(imagePath));
      // Displaying downloaded image into image view
      // Reading image path from sdcard
      String imagePath1 = Environment.getExternalStorageDirectory().toString() + "/blur.jpg";
      // setting downloaded into image view
      my_image2.setImageDrawable(Drawable.createFromPath(imagePath1));

    }


  }

}