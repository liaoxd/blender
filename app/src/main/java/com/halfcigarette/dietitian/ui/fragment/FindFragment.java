package com.halfcigarette.dietitian.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;

import com.halfcigarette.dietitian.R;
import com.halfcigarette.dietitian.base.BaseFragment;
import com.halfcigarette.dietitian.base.CustomBaseAdapter;
import com.halfcigarette.dietitian.beans.AdDomain;
import com.halfcigarette.dietitian.beans.FindExample;
import com.halfcigarette.dietitian.data.FindExampleAdapter;
import com.halfcigarette.dietitian.ui.custom.TitleBar;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindFragment extends BaseFragment {
    @Bind(R.id.find_title_bar)
    TitleBar find_title_bar;

    //关联主视图。
    private View mLayoutView;
    private ArrayList<FindExample> findExamples;
    //findexample
    private ListView findexampleListView;
    private CustomBaseAdapter findExampleAdapter;

    public static String IMAGE_CACHE_PATH = "imageloader/Cache"; // 图片缓存路径

    private ViewPager adViewPager;
    private List<ImageView> imageViews;// 滑动的图片集合

    private List<View> dots; // 图片标题正文的那些点
    private List<View> dotList;

    private int currentItem = 0; // 当前图片的索引号
    // 定义的五个指示点
    private View dot0;
    private View dot1;
    private View dot2;
    private View dot3;
    private View dot4;

    // 定时任务
    private ScheduledExecutorService scheduledExecutorService;

    // 异步加载图片
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;

    // 轮播banner的数据
    private List<AdDomain> adList;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            adViewPager.setCurrentItem(currentItem);
        }

        ;
    };

    public FindFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView() {
        find_title_bar.setTitle("膳食专家");

        initImageLoader();

        // 获取图片加载实例
        mImageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.banner0)
                .showImageForEmptyUri(R.drawable.banner0)
                .showImageOnFail(R.drawable.banner0)
                .cacheInMemory(true).cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY).build();
        //初始化banner数据
        initAdData();
        //开始播放banner
        startAd();

        findexampleListView = (ListView) mLayoutView.findViewById(R.id.findexampleListView);
        findExamples = new ArrayList<>();
        findExamples.clear();
        findExamples.add(new FindExample(BitmapFactory.decodeResource(getResources(), R.drawable.find_exampleimage1),
                "和极致浪漫的法餐sayhellow！",
                "正式的法国大餐，有一定的上菜顺序，也正是因为每一道菜都是现点现做，所以法餐的上菜时细水长流的时间让急性子的小伙伴有点像“热锅上的蚂蚁”。"));
        findExamples.add(new FindExample(BitmapFactory.decodeResource(getResources(), R.drawable.find_exampleimage2),
                "排骨花样吃法",
                "最喜欢吃红烧排骨了。外酥里嫩，色泽金红。香气被锁在肉里，吮吸一下满满的都是肉骨头的味道。吃货们是不是已经开始流口水了呢？"));
        findExamples.add(new FindExample(BitmapFactory.decodeResource(getResources(), R.drawable.find_exampleimage5),
                "我从林中来_银耳",
                "银耳是一种极珍贵的食用菌和药用菌，由此做成的银耳汤具有极高的营养价值和保健作用，同时还具有美容功效。"));
        findExamples.add(new FindExample(BitmapFactory.decodeResource(getResources(), R.drawable.find_exampleimage6),
                "异域微醺_泰越小菜",
                "来自安藤家铁板烧秦兴存的大厨食谱：虎虾的体形足足有手掌般大，虾膏特别多，肉大而入口爽甜。虎虾之中的“黑虎虾”，是亚洲海域常见的一种虾类。"));
        findExampleAdapter = new FindExampleAdapter(mLayoutView.getContext().getApplicationContext(), findExamples);
        findexampleListView.setAdapter(findExampleAdapter);
    }

    @Override
    public void initTitleBar() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mLayoutView = getCreateView(inflater, container);
        ButterKnife.bind(this, mLayoutView);
        initView();
        return mLayoutView;
    }


    @Override
    public void onResume() {
        super.onResume();
        findExampleAdapter.notifyDataSetChanged();
        find_title_bar.setFocusable(true);
        find_title_bar.setFocusableInTouchMode(true);
        find_title_bar.requestFocus();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.gc();
        scheduledExecutorService.shutdown();
    }

    /**
     * 初始化ImageLoader
     */
    private void initImageLoader() {
        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils
                .getOwnCacheDirectory(mLayoutView.getContext().getApplicationContext(),
                        IMAGE_CACHE_PATH);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mLayoutView.getContext()).defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new LruMemoryCache(12 * 1024 * 1024))
                .memoryCacheSize(12 * 1024 * 1024)
                .discCacheSize(32 * 1024 * 1024).discCacheFileCount(100)
                .discCache(new UnlimitedDiscCache(cacheDir))
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();

        ImageLoader.getInstance().init(config);
    }

    /**
     * 初始化广告数据
     */
    private void initAdData() {
        // 广告数据
        adList = getBannerAd();

        imageViews = new ArrayList<ImageView>();

        // 点
        dots = new ArrayList<View>();
        dotList = new ArrayList<View>();
        dot0 = mLayoutView.findViewById(R.id.v_dot0);
        dot1 = mLayoutView.findViewById(R.id.v_dot1);
        dot2 = mLayoutView.findViewById(R.id.v_dot2);
        dot3 = mLayoutView.findViewById(R.id.v_dot3);
        dot4 = mLayoutView.findViewById(R.id.v_dot4);
        dots.add(dot0);
        dots.add(dot1);
        dots.add(dot2);
        dots.add(dot3);
        dots.add(dot4);

        adViewPager = (ViewPager) mLayoutView.findViewById(R.id.vp);
        adViewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        adViewPager.setOnPageChangeListener(new MyPageChangeListener());
        addDynamicView();
    }

    private void addDynamicView() {
        // 动态添加图片和下面指示的圆点
        // 初始化图片资源
        int[] resources = new int[6];
        resources[0] = R.drawable.banner0;
        resources[1] = R.drawable.banner1;
        resources[2] = R.drawable.banner2;
        resources[3] = R.drawable.banner3;
        resources[4] = R.drawable.banner2;
        resources[5] = R.drawable.banner1;
        for (int i = 0; i < adList.size(); i++) {
            ImageView imageView = new ImageView(mLayoutView.getContext());
            // 异步加载图片
            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), resources[i]));
            imageView.setScaleType(ScaleType.CENTER_CROP);
            imageViews.add(imageView);
            dots.get(i).setVisibility(View.VISIBLE);
            dotList.add(dots.get(i));
        }
    }

    private void startAd() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
                TimeUnit.SECONDS);
    }

    private class ScrollTask implements Runnable {

        @Override
        public void run() {
            synchronized (adViewPager) {
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }

    private class MyPageChangeListener implements OnPageChangeListener {

        private int oldPosition = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            AdDomain adDomain = adList.get(position);
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }
    }

    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return adList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = imageViews.get(position);
            ((ViewPager) container).addView(iv);
            final AdDomain adDomain = adList.get(position);
            // 在这个方法里面设置图片的点击事件
            iv.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 处理跳转逻辑
                }
            });
            return iv;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }

    }

    /**
     * 轮播广播模拟数据
     *
     * @return
     */
    public static List<AdDomain> getBannerAd() {
        List<AdDomain> adList = new ArrayList<AdDomain>();

        AdDomain adDomain = new AdDomain();
        adDomain.setAd(false);
        adList.add(adDomain);

        AdDomain adDomain2 = new AdDomain();
        adDomain2.setAd(false);
        adList.add(adDomain2);

        AdDomain adDomain3 = new AdDomain();
        adDomain3.setAd(false);
        adList.add(adDomain3);

        AdDomain adDomain4 = new AdDomain();
        adDomain4.setAd(false);
        adList.add(adDomain4);

        AdDomain adDomain5 = new AdDomain();
        adDomain5.setAd(true); // 代表是广告
        adList.add(adDomain5);

        return adList;
    }
}
