package com.example.myapplication;

import android.util.Log;

import org.cocos2d.actions.interval.IntervalAction;
import org.cocos2d.actions.interval.MoveBy;
import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.Sequence;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCPoint;
import org.cocos2d.types.CCSize;

import java.util.Random;

public class clsJuego {
    CCGLSurfaceView _VistaDelJuego;
    CCSize _Pantalla;
    Sprite _Enemigo;
    int _Nivel;
    int _CountEnemigo;
    double _Velocidad;

    public clsJuego(CCGLSurfaceView VistaDelJuego) {
        Log.d("ClaseJuego", "Comienza el constructor de la clase");
        this._VistaDelJuego = VistaDelJuego;
    }

    public void comenzarJuego()
    {
        Log.d("ComenzarJuego", "Comienza el juego");
        Director.sharedDirector().attachInView(_VistaDelJuego);

        _Nivel = 1;

        _Pantalla = Director.sharedDirector().displaySize();
        Log.d("ComenzarJuego", "pantalla - ancho: " + _Pantalla.getWidth() + " - alto: " + _Pantalla.getHeight());

        Scene EscenaaUsar;
        EscenaaUsar = EscenaComienzo();

        Log.d("ComenzarJuego", "Le digo al director que inicie la escena");
        Director.sharedDirector().runWithScene(EscenaaUsar);

    }

    private Scene EscenaComienzo(){
        Log.d("EscenaComienzo", "Comienza");
        Scene escenaDevolver;
        escenaDevolver = Scene.node();

        Log.d("EscenaComienzo", "Voy a agregar ls capa");
        capaJuego unaCapa;
        unaCapa = new capaJuego();
        escenaDevolver.addChild(unaCapa);
        return escenaDevolver;
    }

    class capaJuego extends Layer {
        public capaJuego(){
            Log.d("CapaJuego", "Comienza el constructor");

            Log.d("CapaJuego", "ubicar posiccion inicial del jugador");

            super.schedule("ponerJugador", 3.0f);
        }
        void ponerJugador(float diferenciaTiempo){
            Random random;
            int enemigo = 0;
            random = new Random();
            Log.d("spawnEnemigo" , "Estoy en el nivel: " + _Nivel );
            if (_Nivel == 1){//moni
                enemigo = random.nextInt((int) 1);

            }
            if (_Nivel == 2){
                enemigo = random.nextInt((int) 2);

            }
            if (_Nivel == 3){
                enemigo = random.nextInt((int) 3);

            }
            if (_Nivel == 4){
                enemigo = random.nextInt((int) 4);

            }
            if (_Nivel >= 5){
                enemigo = random.nextInt((int) 5);
            }
            Log.d("spawnEnemigo" , "El random para el nivel: " + _Nivel + " es: " + enemigo);
            Log.d("spawnEnemigo", "Comienzo a ubicar al jugador");
            CCPoint posicionInicial;
            posicionInicial = new CCPoint();
            Random azarX;
            int a;
            CCPoint posicionFInal;
            posicionFInal = new CCPoint();
            Random azary;
            int b;
            Random azaryFInal;
            int bfinal;

            switch (enemigo){
                case 0:
                    _Enemigo =Sprite.sprite("moni.png");
                    Log.d("ponerJugador", "Cant enemigos es: " + _CountEnemigo);
                    Log.d("ponerJugador", "x al azar");
                    azarX = new Random();
                    a = azarX.nextInt((int) 2);
                    Log.d("a","sf" + a);
                    if (a == 0) {
                        posicionInicial.x = 0;
                    } else {
                        posicionInicial.x = _Pantalla.getWidth();
                    }
                    Log.d("ponerJugador", "y al azar");

                    azary = new Random();
                    b= azary.nextInt((int) (_Pantalla.getHeight() / 3) * 2);
                    posicionInicial.y = b + (_Pantalla.getHeight()  / 3);

                    Log.d("ponerJugador", "le pongo su ubicacion inicial");
                    _Enemigo.setPosition(posicionInicial.x,posicionInicial.y);

                    Log.d("ponerJugador", "lo agrego a la capa");
                    super.addChild(_Enemigo);


                    azaryFInal = new Random();
                    bfinal= azaryFInal.nextInt((int) (_Pantalla.getHeight() / 3) * 2);
                    posicionFInal.y = bfinal + (_Pantalla.getHeight()  / 3);
                    if (posicionInicial.x == 0) {
                        posicionFInal.x = _Pantalla.getWidth() + _Enemigo.getWidth() / 2;
                    } else {
                        posicionFInal.x = 0 - _Enemigo.getWidth() / 2;
                    }
                    _Velocidad = 3 - 0.1 * _Nivel;
                    _Enemigo.runAction(MoveTo.action((float) _Velocidad, posicionFInal.x, posicionFInal.y));
                    break;
                case 1:
                    _Enemigo =Sprite.sprite("kristal.png");
                    Log.d("ponerJugador", "Cant enemigos es: " + _CountEnemigo);
                    Log.d("ponerJugador", "x al azar");
                    azarX = new Random();
                    a = azarX.nextInt((int) 2);
                    Log.d("a","sf" + a);
                    if (a == 0) {
                        posicionInicial.x = 0;
                    } else {
                        posicionInicial.x = _Pantalla.getWidth();
                    }
                    Log.d("ponerJugador", "y al azar");
                    azary = new Random();
                    b= azary.nextInt((int) (_Pantalla.getHeight() / 3) * 2);
                    posicionInicial.y = b + (_Pantalla.getHeight()  / 3);

                    Log.d("ponerJugador", "le pongo su ubicacion inicial");
                    _Enemigo.setPosition(posicionInicial.x,posicionInicial.y);

                    Log.d("ponerJugador", "lo agrego a la capa");
                    super.addChild(_Enemigo);

                    _Velocidad = 3 - 0.1 * _Nivel;
                    MoveBy DAr, DArFinal, DAb, DAbFinal, IAr, IArFinal, IAb, IAbFinal;
                    DAr = MoveBy.action((float) _Velocidad, (int) _Pantalla.getWidth() / 3, (int) getHeight() / 3);
                    DArFinal = MoveBy.action((float) _Velocidad, (int) _Pantalla.getWidth() / 3 + _Enemigo.getWidth(), (int) getHeight() / 3);
                    DAb = MoveBy.action((float) _Velocidad, (int) _Pantalla.getWidth() / 3, (int) - (getHeight() / 3));
                    DAbFinal = MoveBy.action((float) _Velocidad, (int) _Pantalla.getWidth() / 3 + _Enemigo.getWidth(), (int) - (getHeight() / 3));
                    IAr = MoveBy.action((float) _Velocidad, (int) - (_Pantalla.getWidth()) / 3, (int) getHeight() / 3);
                    IArFinal = MoveBy.action((float) _Velocidad, (int) - (_Pantalla.getWidth()) / 3 - _Enemigo.getWidth(), (int) getHeight() / 3);
                    IAb = MoveBy.action((float) _Velocidad, (int) - (_Pantalla.getWidth()) / 3, (int) - getHeight() / 3);
                    IAbFinal = MoveBy.action((float) _Velocidad, (int) - (_Pantalla.getWidth()) / 3 - _Enemigo.getWidth(), (int) - getHeight() / 3);

                    IntervalAction zigzag;

                    if (a == 0) {
                        if (_Pantalla.getHeight()<= (_Pantalla.getHeight()/3)*2){
                            Log.d("spawnEnemigo", "estoy en la izquierda abajo");
                            zigzag= Sequence.actions(DAr,DAb,DArFinal);
                            _Enemigo.runAction(zigzag);
                        }else{
                            Log.d("spawnEnemigo", "estoy en la izquierda arriba");
                            zigzag= Sequence.actions(DAb,DAr,DAbFinal);
                            _Enemigo.runAction(zigzag);
                        }

                    } else{
                        if (_Pantalla.getHeight()<= (_Pantalla.getHeight()/3)*2){
                            Log.d("spawnEnemigo", "estoy en la derecha abajo");
                            zigzag= Sequence.actions(IAr,IAb,IArFinal);
                            _Enemigo.runAction(zigzag);
                        }else{
                            Log.d("spawnEnemigo", "estoy en la derecha arriba");
                            zigzag= Sequence.actions(IAb,IAr,IAbFinal);
                            _Enemigo.runAction(zigzag);
                        }
                    }

                    break;
                case 2:
                    _Enemigo =Sprite.sprite("binker.png");
                    Log.d("ponerJugador", "Cant enemigos es: " + _CountEnemigo);
                    Log.d("ponerJugador", "x al azar");
                    azarX = new Random();
                    a = azarX.nextInt((int) 2);
                    Log.d("a","sf" + a);
                    if (a == 0) {
                        posicionInicial.x = 0;
                    } else {
                        posicionInicial.x = _Pantalla.getWidth();
                    }
                    Log.d("ponerJugador", "y al azar");

                    azary = new Random();
                    b= azary.nextInt((int) (_Pantalla.getHeight() / 3) * 2);
                    posicionInicial.y = b + (_Pantalla.getHeight()  / 3);

                    Log.d("ponerJugador", "le pongo su ubicacion inicial");
                    _Enemigo.setPosition(posicionInicial.x,posicionInicial.y);

                    Log.d("ponerJugador", "lo agrego a la capa");
                    super.addChild(_Enemigo);


                    azaryFInal = new Random();
                    bfinal= azaryFInal.nextInt((int) (_Pantalla.getHeight() / 3) * 2);
                    posicionFInal.y = bfinal + (_Pantalla.getHeight()  / 3);
                    if (posicionInicial.x == 0) {
                        posicionFInal.x = (_Pantalla.getWidth()/3)*2 + _Enemigo.getWidth() / 2;
                    } else {
                        posicionFInal.x = _Pantalla.getWidth()/3  - _Enemigo.getWidth() / 2;
                    }
                    _Velocidad = 3 - 0.1 * _Nivel;
                    _Enemigo.runAction(MoveTo.action((float) _Velocidad/2, posicionFInal.x, posicionFInal.y));
                    _Enemigo.runAction(MoveTo.action((float) _Velocidad/2, posicionInicial.x, posicionInicial.y));
                    break;
                case 3:
                    _Enemigo =Sprite.sprite("freccero.png");
                    break;
                case 4:
                    _Enemigo =Sprite.sprite("dasman.png");
                    break;
            }
            _CountEnemigo++;



            Log.d("spawnEnemigo" , "la velocidad es: " + _Velocidad);
            if (_CountEnemigo == 1){
                _Nivel++;
                super.unschedule("ponerJugador");
                super.schedule("ponerJugador",(float)_Velocidad);
                _CountEnemigo = 0;
            }



        }
    }

}