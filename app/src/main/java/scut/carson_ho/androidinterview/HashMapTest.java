package scut.carson_ho.androidinterview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HashMapTest {

    public static final String TAG = "carsonTest：";
    private LruCache<String, Bitmap> mMemoryCache;
    private ImageView mImageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 获得虚拟机能提供的最大内存
        // 注：超过该大小会抛出OutOfMemory的异常
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // 2. 设置LruCache缓存的大小 = 一般为当前进程可用容量的1/8
        // 注：单位 = Kb
        final int cacheSize = maxMemory / 8;

        // 3. 重写sizeOf方法：计算缓存对象的大小（此处的缓存对象 = 图片）
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {

            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
                // 此处返回的是缓存对象的缓存大小（单位 = Kb） ，而不是item的个数
                // 注：缓存的总容量和每个缓存对象的大小所用单位要一致
                // 此处除1024是为了让缓存对象的大小单位 = Kb

            }
        };

        // 4. 点击按钮，则加载图片
        mImageView = (ImageView)findViewById(R.id.image);
        button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 加载图片 ->>分析1
                loadBitmap("test",mImageView);
            }

        });

    }

    /**
     * 分析1：加载图片
     * 加载前，先从内存缓存中读取；若无，则再从数据源中读取
     **/
    public void loadBitmap(String key, ImageView imageView) {

        // 读取图片前，先从内存缓存中读取：即看内存缓存中是否缓存了该图片
        // 1. 若有缓存，则直接从内存中加载
        Bitmap bitmap = mMemoryCache.get(key);

        if (bitmap != null) {
            mImageView.setImageBitmap(bitmap);
            Log.d(TAG, "从缓存中加载图片 ");

            // 2. 若无缓存，则从数据源加载（此处选择在本地加载） & 添加到缓存
        } else {
            Log.d(TAG, "从数据源（本地）中加载: ");

            // 2.1 从数据源加载
            mImageView.setImageResource(R.drawable.test1);

            // 2.1 添加到缓存
            // 注：在添加到缓存前，需先将资源文件构造成1个BitMap对象（含设置大小）
            Resources res = getResources();
            Bitmap bm = BitmapFactory.decodeResource(res, R.drawable.test1);

            // 获得图片的宽高
            int width = bm.getWidth();
            int height = bm.getHeight();

            // 设置想要的大小
            int newWidth = 80;
            int newHeight = 80;

            // 计算缩放比例
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            // 取得想要缩放的matrix参数
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            // 构造成1个新的BitMap对象
            Bitmap bitmap_s = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);


            // 添加到缓存
            if (mMemoryCache.get(key) == null) {
                mMemoryCache.put(key, bitmap_s);
                Log.d(TAG, "添加到缓存: " + (mMemoryCache.get(key)));
            }


        }
    }

}
