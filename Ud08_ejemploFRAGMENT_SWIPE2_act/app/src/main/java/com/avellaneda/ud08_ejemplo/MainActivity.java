package com.avellaneda.ud08_ejemplo;


import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

import android.os.Bundle;


import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.avellaneda.ud08_ejemplo.clases.ZoomOutPageTransformer;
import com.avellaneda.ud08_ejemplo.fragmentos.BultacoFragment;
import com.avellaneda.ud08_ejemplo.fragmentos.GuzziFragment;
import com.avellaneda.ud08_ejemplo.fragmentos.HarleyFragment;

/**
 * //En este ejercicio, utilizaremos fragments y los mostraremos a través de un sistema de de swipe views,
 * //que  consiste en que cambiaremos de vista deslizado el dedo de  abajo  a arriba para cargar el
 * // fragmento siguiente y de arriba hacia abajo  para cargar el fragmento anterior.
 *
 */
public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(this);
        //Referenciamos el ViewPager que hemos puesto de layout
         viewPager =findViewById(R.id.paginador);
        viewPager.setAdapter(myPagerAdapter);

     /* para que la orientación del desplazamiento sea horizontal*/
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
          /*https://developer.android.com/develop/ui/views/animations/screen-slide-2
        Customize the animation using PageTransformer*/
        viewPager.setPageTransformer(new ZoomOutPageTransformer());


    }
    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
}

/**
 * https://developer.android.com/guide/navigation/navigation-swipe-view-2?hl=es-419
 https://developer.android.com/guide/navigation/navigation-swipe-view?hl=es-419#java */

/*ViewPager2 usa objetos FragmentStateAdapter como aprovisionamiento para que se muestren las páginas nuevas,
de manera que el FragmentStateAdapter usará la clase del fragmento que creaste anteriormente.*/
class MyPagerAdapter extends FragmentStateAdapter
{
    private final int NUM_ITEMS = 3;// NÚMERO DE FRAGMENTOS
    public MyPagerAdapter(Fragment fragment) {
        super(fragment);
    }

    public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    /*
        @Override
        public Fragment getItem(int i) {
        }*/


    @NonNull
    @Override
    public Fragment createFragment(int position) {
    Fragment fragment;
        switch (position) { //Según la posición de paginación, se cargará uno u otro fragment
            case 0:
                fragment = new BultacoFragment(); //Este es el que cargará por defecto (el 0)
                break;
            case 1:
                fragment = new HarleyFragment();
                break;
            case 2:
                fragment = new GuzziFragment();
                break;
            default:
                fragment = new BultacoFragment(); //Este es el que cargará por defecto (el 0)
        }
        return fragment;

    }


    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }


}