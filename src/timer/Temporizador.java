package timer;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Temporizador {
        LocalTime momentoInicial;
        LocalTime momentoFinal;

        public void iniciar() {
            this.momentoInicial = LocalTime.now();
        }

        public Duration parar() {
            this.momentoFinal = LocalTime.now();
            Duration tiempoTranscurrido = Duration.between(momentoInicial, momentoFinal);
            return tiempoTranscurrido;
        }

}
