package com.example.myapplication;

import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.actions.interval.IntervalAction;
import org.cocos2d.actions.interval.MoveBy;
import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.actions.interval.Sequence;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCPoint;
import org.cocos2d.types.CCSize;

import java.util.ArrayList;
import java.util.Random;

public class clsJuego {
    CCGLSurfaceView _VistaDelJuego;
    CCSize _Pantalla;
    Sprite _Enemigo;
    Sprite _Lob;
    Sprite _Bala;
    int _Nivel;
    int _CountEnemigo;
    double _Velocidad;
    ArrayList _ListaDeEnemigos;
    ArrayList _ListaDeBalas;
    public clsJuego(CCGLSurfaceView VistaDelJuego) {
        Log.d("ClaseJuego", "Comienza el constructor de la clase");
        this._VistaDelJuego = VistaDelJuego;
    }
        public boolean InterseccionEntreSprites(Sprite Sprite1, Sprite Sprite2) {
            Boolean HayInterseccion = false;
            //Determino los bordes de cada Sprite
            Float Sp1Arriba, Sp1Abajo, Sp1Derecha, Sp1Izquierda, Sp2Arriba,
                    Sp2Abajo, Sp2Derecha, Sp2Izquierda;

            Sp1Arriba = Sprite1.getPositionY() + Sprite1.getHeight() / 2;
            Sp1Abajo = Sprite1.getPositionY() - Sprite1.getHeight() / 2;
            Sp1Derecha = Sprite1.getPositionX() + Sprite1.getWidth() / 2;
            Sp1Izquierda = Sprite1.getPositionX() - Sprite1.getWidth() / 2;
            Sp2Arriba = Sprite2.getPositionY() + Sprite2.getHeight() / 2;
            Sp2Abajo = Sprite2.getPositionY() - Sprite2.getHeight() / 2;
            Sp2Derecha = Sprite2.getPositionX() + Sprite2.getWidth() / 2;
            Sp2Izquierda = Sprite2.getPositionX() - Sprite2.getWidth() / 2;

            Log.d("IntEntSprites", "Sp1 Arr: " + Sp1Arriba + " - Ab: " + Sp1Abajo + " - Der: " + Sp1Derecha + " - Izq: " + Sp1Izquierda);
            Log.d("IntEntSprites", "Sp2 Arr: " + Sp2Arriba + " - Ab: " + Sp2Abajo + " - Der: " + Sp2Derecha + " - Izq: " + Sp2Izquierda);


            //Me fijo si el vértice superior derecho de Sp1 está dentro de Sp2
            if (Sp1Arriba >= Sp2Abajo && Sp1Arriba <= Sp2Arriba &&
                    Sp1Derecha >= Sp2Izquierda && Sp1Derecha <= Sp2Derecha) {
                HayInterseccion = true;
                Log.d("IntEntSprites", "Intersección caso 1");
            }
//Me fijo si el vértice superior izquierdo de Sp1 está dentro de Sp2
            if (Sp1Arriba >= Sp2Abajo && Sp1Arriba <= Sp2Arriba &&
                    Sp1Izquierda >= Sp2Izquierda && Sp1Izquierda <= Sp2Derecha) {
                HayInterseccion = true;
                Log.d("IntEntSprites", "Intersección caso 2");
            }
//Me fijo si el vértice inferior derecho de Sp1 está dentro de Sp2
            if (Sp1Abajo >= Sp2Abajo && Sp1Abajo <= Sp2Arriba &&
                    Sp1Derecha >= Sp2Izquierda && Sp1Derecha <= Sp2Derecha) {
                HayInterseccion = true;
                Log.d("IntEntSprites", "Intersección caso 3");
            }
//Me fijo si el vértice inferior izquierdo de Sp1 está dentro de Sp2
            if (Sp1Abajo >= Sp2Abajo && Sp1Abajo <= Sp2Arriba &&
                    Sp1Izquierda >= Sp2Izquierda && Sp1Izquierda <= Sp2Derecha) {
                HayInterseccion = true;
                Log.d("IntEntSprites", "Intersección caso 4");
            }
//Me fijo si el vértice superior derecho de Sp2 está dentro de Sp1
            if (Sp2Arriba >= Sp1Abajo && Sp2Arriba <= Sp1Arriba &&
                    Sp2Derecha >= Sp1Izquierda && Sp2Derecha <= Sp1Derecha) {
                HayInterseccion = true;
                Log.d("IntEntSprites", "Intersección caso 5");
            }

            //Me fijo si el vértice superior izquierdo de Sp1 está dentro de Sp2
            if (Sp2Arriba >= Sp1Abajo && Sp2Arriba <= Sp1Arriba &&
                    Sp2Izquierda >= Sp1Izquierda && Sp2Izquierda <= Sp1Derecha) {
                HayInterseccion = true;
                Log.d("IntEntSprites", "Intersección caso 6");
            }
//Me fijo si el vértice inferior derecho de Sp1 está dentro de Sp2
            if (Sp2Abajo >= Sp1Abajo && Sp2Abajo <= Sp1Arriba &&
                    Sp2Derecha >= Sp1Izquierda && Sp2Derecha <= Sp1Derecha) {
                HayInterseccion = true;
                Log.d("IntEntSprites", "Intersección caso 7");
            }
//Me fijo si el vértice inferior izquierdo de Sp1 está dentro de Sp2
            if (Sp2Abajo >= Sp1Abajo && Sp2Abajo <= Sp1Arriba &&
                    Sp2Izquierda >= Sp1Izquierda && Sp2Izquierda <= Sp1Derecha) {
                HayInterseccion = true;
                Log.d("IntEntSprites", "Intersección caso 8");
            }
            Log.d("IntEntSprites", "Hay intersección: " + HayInterseccion);
            return HayInterseccion;
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
            _ListaDeEnemigos = new ArrayList();
            _ListaDeBalas = new ArrayList();
            Log.d("CapaJuego", "Comienza el constructor");
            ponerImagenFondo();
            Log.d("CapaJuego", "ubicar posiccion inicial del jugador");
            ponerLob();

            super.schedule("ponerJugador", 3.0f);
            super.schedule("ponerBalas", 3.0f);
            super.schedule("detectarColision" , 0.1f);
            setIsTouchEnabled(true);
        }
        public void detectarColision (float deltaTiempo){
            ArrayList _EnemigosEliminados  = new ArrayList();
            Log.d("DetectarColisiones", "Me fijo si algun enemigo choco al jugador");

            for (int punteroenemigo = 0;punteroenemigo < _ListaDeEnemigos.size(); punteroenemigo++){
                Log.d("DetectarColisiones", "Verifico el enemigon numero " + punteroenemigo);

                Sprite unEnemigoaVerificar;
                unEnemigoaVerificar = (Sprite) _ListaDeEnemigos.get(punteroenemigo);

                Boolean huboColision;

                huboColision = InterseccionEntreSprites(_Bala, unEnemigoaVerificar);
                Log.d("DetectarColisiones", "funciona = " + huboColision);


                if (huboColision == true){
                    Log.d("DetectarColisiones", "BOOOMMMMMMMMMMMMMMMMMMM");
                    _EnemigosEliminados.add(punteroenemigo);


                   // super.removeChild(unEnemigoaVerificar,true);

                    //_ListaDeEnemigos.remove(punteroenemigo);
                  //  Log.d("DetectarColisiones", "");
                }

            }

           for (int punteroEnemigosEliminados = _EnemigosEliminados.size()-1;punteroEnemigosEliminados >= 0; punteroEnemigosEliminados--){
               Log.d("DetectarColisiones", "Voy a eliminar el: " + _EnemigosEliminados.get(punteroEnemigosEliminados));
               Log.d("DetectarColisiones", "Estoy en el for para eliminar");


               Sprite unEnemigoaaEliminar;

               unEnemigoaaEliminar = (Sprite) _ListaDeEnemigos.get((int)_EnemigosEliminados.get(punteroEnemigosEliminados));
                Log.d("DetectarColisiones", "El array tiene: " + _ListaDeEnemigos.size() );
               _ListaDeEnemigos.remove((int)_EnemigosEliminados.get(punteroEnemigosEliminados));
               Log.d("DetectarColisiones", "El array tiene ahora : " + _ListaDeEnemigos.size() );
               super.removeChild(unEnemigoaaEliminar,true);
            }



         /*   for (int punteBala = 0;punteBala < _ListaDeBalas.size(); punteBala++){
                Log.d("DetectarColisiones", "Verifico el enemigon numero " + punteBala);

                Sprite unaBalaVerificar;
                unaBalaVerificar = (Sprite) _ListaDeBalas.get(punteBala);

                if (InterseccionEntreSprites(_Enemigo, unaBalaVerificar)){
                    huboColision = true;
                }

                if (huboColision == true){
                    Log.d("DetectarColisiones", "BOOOMMMMMMMMMMMMMMMMMMM");


                    super.removeChild(unaBalaVerificar,true);

                    _ListaDeBalas.remove(punteBala);
                    Log.d("DetectarColisiones", "");
                }


            }*/







        }
        void ponerImagenFondo(){
            Sprite ImagenFondo;
            ImagenFondo=Sprite.sprite("fondo.jpg");
            ImagenFondo.setPosition(_Pantalla.getWidth()/2 , _Pantalla.getHeight() / 2);
            float factorAncho, factorAlto;
            factorAncho = _Pantalla.getWidth() / ImagenFondo.getWidth();
            factorAlto = _Pantalla.getHeight() / ImagenFondo.getHeight();
            ImagenFondo.runAction(ScaleBy.action(0.01f,factorAncho,factorAlto));

            super.addChild(ImagenFondo);
        }

        @Override
        public boolean ccTouchesMoved(MotionEvent event) {
            float xtocada, ytocada;
            xtocada = event.getX();
            ytocada = _Pantalla.getHeight() - event.getY();
            Log.d("Comienzo Del Toque", "Comienza toque x en: " + xtocada + " - Y: " + ytocada );
            movernaveJugador(xtocada,ytocada);
            return  true;
        }
        void   movernaveJugador( float xtocada, float ytocada){

            Log.d("MoverLog" , "Me pidan que lo mueva a X: " + xtocada + "Y tocada: " + ytocada);
            if (ytocada > _Pantalla.getHeight() / 5) {
            } else {
                _Lob.setPosition(xtocada, ytocada);
            }
        }

        void ponerLob(){
            Log.d("spawnLob", "Asignamos jugador");
            _Lob=Sprite.sprite("leolog.png");

            _Lob.setPosition(_Pantalla.getWidth() / 2, 0 );

            super.addChild(_Lob);
        }

        public  void ponerBalas(float diferencaitiempo){
            Sprite unaBala;
            unaBala = Sprite.sprite("binker.png");

            CCPoint posicionInicialBala;
            posicionInicialBala = new CCPoint();
            posicionInicialBala.x = _Lob.getPositionX();
            posicionInicialBala.y = _Lob.getHeight()/ 2;

            unaBala.setPosition(posicionInicialBala.x, posicionInicialBala.y);

            CCPoint PosicionFinalBala;
            PosicionFinalBala = new CCPoint();
            PosicionFinalBala.x =  _Lob.getPositionX();
            PosicionFinalBala.y = _Pantalla.getHeight() + 100;

            unaBala.runAction(MoveTo.action(3, PosicionFinalBala.x, PosicionFinalBala.y));

            super.addChild(unaBala, 10);
            _Bala = unaBala;
            _ListaDeBalas.add(unaBala);


        }


        void ponerJugador(float diferenciaTiempo){
            Random random;
            Sprite unEnemigo;
            unEnemigo =Sprite.sprite("leolog.png");
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
                    unEnemigo =Sprite.sprite("moni.png");
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
                    unEnemigo.setPosition(posicionInicial.x,posicionInicial.y);

                    Log.d("ponerJugador", "lo agrego a la capa");
                    super.addChild(unEnemigo);


                    azaryFInal = new Random();
                    bfinal= azaryFInal.nextInt((int) (_Pantalla.getHeight() / 3) * 2);
                    posicionFInal.y = bfinal + (_Pantalla.getHeight()  / 3);
                    if (posicionInicial.x == 0) {
                        posicionFInal.x = _Pantalla.getWidth() + unEnemigo.getWidth() / 2;
                    } else {
                        posicionFInal.x = 0 - unEnemigo.getWidth() / 2;
                    }
                    _Velocidad = 3 - 0.1 * _Nivel;
                    unEnemigo.runAction(MoveTo.action((float) _Velocidad, posicionFInal.x, posicionFInal.y));
                    break;
                case 1:
                    unEnemigo =Sprite.sprite("kristal.png");
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
                    unEnemigo.setPosition(posicionInicial.x,posicionInicial.y);

                    Log.d("ponerJugador", "lo agrego a la capa");
                    super.addChild(unEnemigo);

                    _Velocidad = 3 - 0.1 * _Nivel;
                    MoveBy DAr, DArFinal, DAb, DAbFinal, IAr, IArFinal, IAb, IAbFinal;
                    DAr = MoveBy.action((float) _Velocidad, (int) _Pantalla.getWidth() / 3, (int) getHeight() / 3);
                    DArFinal = MoveBy.action((float) _Velocidad, (int) _Pantalla.getWidth() / 3 + unEnemigo.getWidth(), (int) getHeight() / 3);
                    DAb = MoveBy.action((float) _Velocidad, (int) _Pantalla.getWidth() / 3, (int) - (getHeight() / 3));
                    DAbFinal = MoveBy.action((float) _Velocidad, (int) _Pantalla.getWidth() / 3 + unEnemigo.getWidth(), (int) - (getHeight() / 3));
                    IAr = MoveBy.action((float) _Velocidad, (int) - (_Pantalla.getWidth()) / 3, (int) getHeight() / 3);
                    IArFinal = MoveBy.action((float) _Velocidad, (int) - (_Pantalla.getWidth()) / 3 - unEnemigo.getWidth(), (int) getHeight() / 3);
                    IAb = MoveBy.action((float) _Velocidad, (int) - (_Pantalla.getWidth()) / 3, (int) - getHeight() / 3);
                    IAbFinal = MoveBy.action((float) _Velocidad, (int) - (_Pantalla.getWidth()) / 3 - unEnemigo.getWidth(), (int) - getHeight() / 3);

                    IntervalAction zigzag;

                    if (a == 0) {
                        if (_Pantalla.getHeight()<= (_Pantalla.getHeight()/3)*2){
                            Log.d("spawnEnemigo", "estoy en la izquierda abajo");
                            zigzag= Sequence.actions(DAr,DAb,DArFinal);
                            unEnemigo.runAction(zigzag);
                        }else{
                            Log.d("spawnEnemigo", "estoy en la izquierda arriba");
                            zigzag= Sequence.actions(DAb,DAr,DAbFinal);
                            unEnemigo.runAction(zigzag);
                        }

                    } else{
                        if (_Pantalla.getHeight()<= (_Pantalla.getHeight()/3)*2){
                            Log.d("spawnEnemigo", "estoy en la derecha abajo");
                            zigzag= Sequence.actions(IAr,IAb,IArFinal);
                            unEnemigo.runAction(zigzag);
                        }else{
                            Log.d("spawnEnemigo", "estoy en la derecha arriba");
                            zigzag= Sequence.actions(IAb,IAr,IAbFinal);
                            unEnemigo.runAction(zigzag);
                        }
                    }

                    break;
                case 2:
                    unEnemigo =Sprite.sprite("binker.png");
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
                    unEnemigo.setPosition(posicionInicial.x,posicionInicial.y);

                    Log.d("ponerJugador", "lo agrego a la capa");
                    super.addChild(unEnemigo);


                    azaryFInal = new Random();
                    bfinal= azaryFInal.nextInt((int) (_Pantalla.getHeight() / 3) * 2);
                    posicionFInal.y = bfinal + (_Pantalla.getHeight()  / 3);
                    if (posicionInicial.x == 0) {
                        posicionFInal.x = (_Pantalla.getWidth()/3)*2 + unEnemigo.getWidth() / 2;
                    } else {
                        posicionFInal.x = _Pantalla.getWidth()/3  - unEnemigo.getWidth() / 2;
                    }
                    _Velocidad = 3 - 0.1 * _Nivel;
                    unEnemigo.runAction(MoveTo.action((float) _Velocidad/2, posicionFInal.x, posicionFInal.y));
                    unEnemigo.runAction(MoveTo.action((float) _Velocidad/2, posicionInicial.x, posicionInicial.y));
                    break;
                case 3:
                    unEnemigo =Sprite.sprite("freccero.png");
                    break;
                case 4:
                    unEnemigo =Sprite.sprite("dasman.png");
                    break;
            }
            _CountEnemigo++;
            _ListaDeEnemigos.add(unEnemigo);
            _Enemigo = unEnemigo;
            Log.d("PonerEnemigos" , " Ya tengo " + _ListaDeEnemigos.size() + " enemigos");


            Log.d("spawnEnemigo" , "la velocidad es: " + _Velocidad);
            if (_CountEnemigo == 10){
                _Nivel++;
                super.unschedule("ponerJugador");
                super.schedule("ponerJugador",(float)_Velocidad);
                _CountEnemigo = 0;
            }



        }
    }

}