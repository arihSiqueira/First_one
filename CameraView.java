import android.content.Context;


import android.hardware.camera2.*
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Aluno on 29/10/2015.
 */
public class CameraView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder mholder;
    private CameraDevice mCamera;

    public CameraView(Context context, CameraDevice camera){
        super(context);
        mCamera = camera;

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
