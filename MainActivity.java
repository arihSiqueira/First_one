import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    public static class App{
        public static File _file;
        public static File _dir;
        public static Bitmap bitmap;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void createDirectoryForPicture() {
        App._dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "CameraApp");
        if (!App._dir.exists()) {
            App._dir.mkdir();
        }

    }

        private void takePicture(Object sender, EventArgs eventargs){

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            App._file = new File(App._dir, String.format("Myphoto_{0}.jpg", Guid.newGuid));


    }

    }
