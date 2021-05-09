package UDP;

public class Velocidad {
    /* Classe que porporciona una velocitat (int) aleatòria, per implementar l'exemple de Multicast:
     * ClientVelocimetre1.java
     * ClientVelocimetre2.java
     * SrvVelocitats.java
     */

    int vel,max;
    public Velocidad(int max) {
        this.max = max;
    }

    public int getVel() {
        return vel;
    }

    public void setVel(int vel) {
        this.vel = vel;
    }

    public int agafaVelocitat() {
        setVel((int)(Math.random()*max)+1);
        return getVel();
    }
}
