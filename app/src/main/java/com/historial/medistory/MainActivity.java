package com.historial.medistory;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        DocPerfilFragment.OnFragmentInteractionListener, DocPacientesFragment.OnFragmentInteractionListener, DocQrFragment.OnFragmentInteractionListener,
        PerfilFragment.OnFragmentInteractionListener,HistorialFragment.OnFragmentInteractionListener,QrFragment.OnFragmentInteractionListener {
    public Boolean type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        type = intent.getBooleanExtra("type",true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (type){
            navigationView.getMenu().findItem(R.id.menu_doctor_perfil).setVisible(false);
            navigationView.getMenu().findItem(R.id.menu_doctor_pacientes).setVisible(false);
            navigationView.getMenu().findItem(R.id.menu_doctor_qr).setVisible(false);
            Toast.makeText(this, "Bienvenido Paciente", Toast.LENGTH_SHORT).show();
        }else {
            navigationView.getMenu().findItem(R.id.menu_paciente_perfil).setVisible(false);
            navigationView.getMenu().findItem(R.id.menu_paciente_historial).setVisible(false);
            navigationView.getMenu().findItem(R.id.menu_paciente_qr).setVisible(false);
            navigationView.getMenu().findItem(R.id.menu_paciente_config).setVisible(false);
            Toast.makeText(this, "Bienvenido Doctor", Toast.LENGTH_SHORT).show();
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.Fragment fragment = null;
        FragmentManager fm = getSupportFragmentManager();
        //Menu Paciente
        if (id == R.id.menu_paciente_perfil) {
            fm.beginTransaction().replace(R.id.Contenedor, new PerfilFragment()).commit();
            //fragment=new PerfilFragment();
        } else if (id == R.id.menu_paciente_historial) {
            fm.beginTransaction().replace(R.id.Contenedor, new HistorialFragment()).commit();

        } else if (id == R.id.menu_paciente_qr) {
            fm.beginTransaction().replace(R.id.Contenedor, new QrFragment()).commit();

        } else if (id == R.id.menu_paciente_config) {
            Intent intent = new Intent(this, DetalleActivity.class);
            startActivity(intent);
        }
        //Menu Doctor
        else if (id == R.id.menu_doctor_perfil) {
            fm.beginTransaction().replace(R.id.Contenedor, new DocPerfilFragment()).commit();

        } else if (id == R.id.menu_doctor_pacientes) {
            fm.beginTransaction().replace(R.id.Contenedor, new DocPacientesFragment()).commit();

        } else if (id == R.id.menu_doctor_qr) {
            fm.beginTransaction().replace(R.id.Contenedor, new DocQrFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
