package com.talleresco.taller1_cliente;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Observer {

    private Button up, down, left, rigt, help, reload;
    private String comando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        up= (Button) findViewById(R.id.up);
        down= (Button) findViewById(R.id.down);
        left= (Button) findViewById(R.id.left);
        rigt= (Button) findViewById(R.id.rigth);
        help= (Button) findViewById(R.id.ayuda);
        reload= (Button) findViewById(R.id.reload);

        comando="";


        up.setOnClickListener(this);
        down.setOnClickListener(this);
        left.setOnClickListener(this);
        rigt.setOnClickListener(this);
        reload.setOnClickListener(this);
        help.setOnClickListener(this);

        ComManager.getInstance().addObserver(this);

    }

    @Override
    public void onClick(View view) {
        Comunicacion com= new Comunicacion();
        switch (view.getId()){

            case R.id.up:

                comando="up";

                //com.execute(comando);
                ComManager.getInstance().sendMessage(comando, ComManager.MULTI_GROUP_ADDRESS, ComManager.DEFAULT_PORT);

                Toast.makeText(getBaseContext(), "arriba", Toast.LENGTH_SHORT).show();

                break;
            case R.id.down:


                comando="down";

                com.execute(comando);
                ComManager.getInstance().sendMessage(comando, ComManager.MULTI_GROUP_ADDRESS, ComManager.DEFAULT_PORT);
                Toast.makeText(getBaseContext(), "abajo", Toast.LENGTH_SHORT).show();
                break;

            case R.id.left:

                comando="left";

               // com.execute(comando);
                ComManager.getInstance().sendMessage(comando, ComManager.MULTI_GROUP_ADDRESS, ComManager.DEFAULT_PORT);
                Toast.makeText(getBaseContext(), "inzquierda", Toast.LENGTH_SHORT).show();

                break;

            case R.id.rigth:


                comando="rigth";

               // com.execute(comando);
                ComManager.getInstance().sendMessage(comando, ComManager.MULTI_GROUP_ADDRESS, ComManager.DEFAULT_PORT);
                Toast.makeText(getBaseContext(), "derecha", Toast.LENGTH_SHORT).show();

                break;

            case R.id.ayuda:


                comando="aiuda";
               // com.execute(comando);
                ComManager.getInstance().sendMessage(comando, ComManager.MULTI_GROUP_ADDRESS, ComManager.DEFAULT_PORT);
                Toast.makeText(getBaseContext(), "aiuda", Toast.LENGTH_SHORT).show();

                break;

            case R.id.reload:

                comando="reload";

                //com.execute(comando);
                ComManager.getInstance().sendMessage(comando, ComManager.MULTI_GROUP_ADDRESS, ComManager.DEFAULT_PORT);
                Toast.makeText(getBaseContext(), "reload", Toast.LENGTH_SHORT).show();

                break;
        }


    }

    @Override
    public void update(Observable observable, Object o) {

    }


    public class Comunicacion extends AsyncTask<String,Integer, String>{

        private int port;
        private InetAddress ip;
        private DatagramPacket pack;
        private MulticastSocket sock;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        try {
            port = 5000;
            ip= InetAddress.getByName("224.2.33.33");
            sock = new MulticastSocket();
            sock.joinGroup(ip);


        }catch (Exception e){
        e.printStackTrace();
        }

    }

    @Override
    protected String doInBackground(String... strings) {

        String s= strings[0];
        byte[] conversion= s.getBytes();
        pack= new DatagramPacket(conversion, conversion.length,ip, port);

        try {
                sock.send(pack);

        }catch(Exception e){
            e.printStackTrace();

        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }


}

}
