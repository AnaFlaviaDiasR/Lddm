package com.example.otto.trabalhopratico2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<String> subjects = new ArrayList<>(Arrays.asList("LDDM", "Grafos", "AED 2"));
    LinkedList<String> anotherSubjects = new LinkedList<>(Arrays.asList("Banco de Dados", "Calculo I", "Cálculo II", "Cálculo III", "Algebra Linear", "Matemática Discreta", "Estatística", "AED 1", "AED 3", "LP", "PAA", "IA", "Compiladores", "PID", "Comp. Paralela", "Comp. Gráfica", "Otimização"));
    Menu menu = null;
    SubMenu pdfSubMenu = null;
    SubMenu linkSubMenu = null;
    SubMenu videoSubMenu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botao();
        iniciarDrawer(toolbar);
        iniciarNavMenu();
    }

    private void botao(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                menu = navigationView.getMenu();
                String subject = anotherSubjects.poll();
                //linkSubMenu.add(subject).setOnMenuItemClickListener(onMenuItemClick("LINK"));
                pdfSubMenu.add(subject).setOnMenuItemClickListener(onMenuItemClick("PDF"));
                //videoSubMenu.add(subject).setOnMenuItemClickListener(onMenuItemClick("VIDEO"));

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void iniciarDrawer(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void iniciarNavMenu() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        menu = navigationView.getMenu();
        pdfSubMenu = menu.addSubMenu("PDF");
        //linkSubMenu = menu.addSubMenu("Link");
        //videoSubMenu = menu.addSubMenu("Vídeo");

        for (String subject : subjects) {
            pdfSubMenu.add(subject).setOnMenuItemClickListener(onMenuItemClick("PDF"));
            //linkSubMenu.add(subject).setOnMenuItemClickListener(onMenuItemClick("LINK"));
            //videoSubMenu.add(subject).setOnMenuItemClickListener(onMenuItemClick("VIDEO"));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public MenuItem.OnMenuItemClickListener onMenuItemClick(final String fileType) {
        return new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Bundle args = new Bundle();

                switch (fileType) {
                    case "PDF":
                        PdfFragment pdfFragment = new PdfFragment();

                        args.putString("subjectName", item.getTitle().toString());
                        args.putString("fileType", fileType);
                        pdfFragment.setArguments(args);
                        ft.replace(R.id.placeholder_fragment, pdfFragment);
                        break;
                    case "LINK":
                        LinkFragment linkFragment = new LinkFragment();

                        args.putString("subjectName", item.getTitle().toString());
                        args.putString("fileType", fileType);
                        linkFragment.setArguments(args);
                        ft.replace(R.id.placeholder_fragment, linkFragment);
                        break;
                    case "VIDEO":
                        VideoFragment videoFragment = new VideoFragment();

                        args.putString("subjectName", item.getTitle().toString());
                        args.putString("fileType", fileType);
                        videoFragment.setArguments(args);
                        ft.replace(R.id.placeholder_fragment, videoFragment);
                        break;
                }

                ft.commit();
                return false;
            }
        };
    }
}
